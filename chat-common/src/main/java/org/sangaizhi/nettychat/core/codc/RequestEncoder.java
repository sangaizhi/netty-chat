package org.sangaizhi.nettychat.core.codc;

import com.sun.org.apache.bcel.internal.classfile.ConstantValue;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.sangaizhi.nettychat.core.model.Request;

/**
 * 请求编码器:将响应消息编码返回客户端
 * <pre>
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——----——+——----——+
 * |  包头	|  模块号      |  命令号    |  结果码    |  长度       |   数据     |
 * +——----——+——-----——+——----——+——----——+——----——+——----——+
 * </pre>
 * @author sangaizhi
 * @date 2017/5/22
 */
public class RequestEncoder extends MessageToByteEncoder<Request> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Request request, ByteBuf byteBuf) throws Exception {

        //包头
        byteBuf.writeInt(Constants.HEADER_FLAG);
        //module
        byteBuf.writeShort(request.getModule());
        //cmd
        byteBuf.writeShort(request.getCommand());
        //长度
        int lenth = request.getData()==null? 0 : request.getData().length;
        if(lenth <= 0){
            byteBuf.writeInt(lenth);
        }else{
            byteBuf.writeInt(lenth);
            byteBuf.writeBytes(request.getData());
        }
    }
}
