package org.sangaizhi.nettychat.server;

import org.sangaizhi.nettychat.core.model.Request;
import org.sangaizhi.nettychat.core.model.Response;
import org.sangaizhi.nettychat.core.model.Result;
import org.sangaizhi.nettychat.core.model.ResultCode;
import org.sangaizhi.nettychat.core.serialize.Serializer;
import org.sangaizhi.nettychat.core.session.Session;
import org.sangaizhi.nettychat.core.session.SessionImpl;
import org.sangaizhi.nettychat.core.session.SessionManager;
import org.sangaizhi.nettychat.module.ModuleId;
import org.sangaizhi.nettychat.server.module.entity.User;
import org.sangaizhi.nettychat.server.scanner.Invoker;
import org.sangaizhi.nettychat.server.scanner.InvokerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.GeneratedMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *
 * 消息接收梳理
 * @author sangaizhi
 * @date 2017/5/24
 */
public class ServerHandler extends SimpleChannelInboundHandler<Request> {

    private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Request request) throws Exception {
        handMessage(new SessionImpl(channelHandlerContext.channel()), request);
    }

    private void handMessage(Session session, Request request){
        Response response = new Response(request);
        logger.info("module:{}, command:{}", request.getModule(),request.getCommand());
        //获取命令执行器
        Invoker invoker = InvokerManager.getInvoker(request.getModule(), request.getCommand());
        if(null != invoker){
            try{
                Result<?> result = null;
                if(request.getModule() == ModuleId.USER){
                    result = (Result<?>) invoker.invoke(session, request.getData());
                }else {
                    Object attachment = session.getAttachment();
                    if(null != attachment){
                        User user = (User) attachment;
                        result = (Result<?>) invoker.invoke(user.getUserId(), request.getData());
                    }else{
                        // 会话未登录，拒绝请求
                        response.setResultCode(ResultCode.LOGIN_PLEASE);
                        session.write(request);
                        return;
                    }
                }
                if(result.getResultCode() == ResultCode.SUCCESS){
                    // 回写数据
                    Object object = result.getContent();
                    if(null != object){
                        if(object instanceof Serializer){
                            Serializer content = (Serializer) object;
                            response.setData(content.getBytes());
                        }else if(object instanceof GeneratedMessage){
                            GeneratedMessage content = (GeneratedMessage) object;
                            response.setData(content.toByteArray());
                        }else{
                            logger.error("不可识别的执行结果：{}", object);
                        }
                    }
                    session.write(response);
                }else{
                    response.setResultCode(result.getResultCode());
                    session.write(response);
                    return;
                }
            }catch (Exception e){
                e.printStackTrace();
                //系统未知异常
                response.setResultCode(ResultCode.UNKOWN_EXCEPTION);
                session.write(response);
            }
        }else{
            logger.info("请求地址不合法");
            response.setResultCode(ResultCode.NO_INVOKER);
            session.write(response);
            return;
        }

    }

    /**
     * 下线移除会话
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception{
        Session session = new SessionImpl(ctx.channel());
        Object object = session.getAttachment();
        if(null != object){
            User user = (User)object;
            SessionManager.removeSession(user.getUserId());
        }
    }

}
