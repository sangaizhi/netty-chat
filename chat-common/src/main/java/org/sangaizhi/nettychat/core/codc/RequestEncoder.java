package org.sangaizhi.nettychat.core.codc;

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

    }
}
