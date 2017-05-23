package org.sangaizhi.nettychat.core.codc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 请求解码器
 * 
 * <pre>
 *     数据格式
 * +——----——+——-----——+——----——+——----——+——-----——+
 * |  包头	|  模块号      |  命令号    |   长度     |   数据       |
 * +——----——+——-----——+——----——+——----——+——-----——+
 * </pre>
 * 
 * 包头4字节,模块号2字节,命令号2字节,长度4字节（数据部分占有字节数量）
 * 
 * @author sangaizhi
 * @date 2017/5/22
 */
public class RequestDecoder extends ByteToMessageDecoder {

	public static int BASE_NUM = 4 + 2 + 2 + 4;

	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

	}
}
