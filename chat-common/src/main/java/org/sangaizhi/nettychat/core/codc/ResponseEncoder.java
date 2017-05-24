package org.sangaizhi.nettychat.core.codc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.sangaizhi.nettychat.core.model.Response;

/**
 * 请求响应编码器
 * <pre>
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——----——+——----——+
 * |  包头	|  模块号      |  命令号    |  结果码    |  长度       |   数据     |
 * +——----——+——-----——+——----——+——----——+——----——+——----——+
 * </pre>
 * @author sangaizhi
 * @date 2017/5/22
 */
public class ResponseEncoder extends MessageToByteEncoder<Response> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Response response, ByteBuf byteBuf) throws Exception {
        System.out.println("返回请求:" + "module:" +response.getModule() +" cmd:" + response.getCommand() + " resultCode:" + response.getResultCode());

        //包头
        byteBuf.writeInt(Constants.HEADER_FLAG);
        //module和cmd
        byteBuf.writeShort(response.getModule());
        byteBuf.writeShort(response.getCommand());
        //结果码
        byteBuf.writeInt(response.getResultCode());
        //长度
        int lenth = response.getData()==null? 0 : response.getData().length;
        if(lenth <= 0){
            byteBuf.writeInt(lenth);
        }else{
            byteBuf.writeInt(lenth);
            byteBuf.writeBytes(response.getData());
        }
    }
}
