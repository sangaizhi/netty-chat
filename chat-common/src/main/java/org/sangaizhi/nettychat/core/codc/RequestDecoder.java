package org.sangaizhi.nettychat.core.codc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.sangaizhi.nettychat.core.model.Request;

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
	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list)
		throws Exception {
		while (true) {
			if (byteBuf.readableBytes() >= BASE_NUM) {
				int beginIndex;
				while (true) {
					beginIndex = byteBuf.readerIndex();
					byteBuf.markReaderIndex();
					if (byteBuf.readInt() == Constants.HEADER_FLAG) {
						break;
					}
					byteBuf.resetReaderIndex();
					byteBuf.readByte();

					if (byteBuf.readableBytes() < BASE_NUM) {
						return;
					}
				}
				//读取命令号
				short module = byteBuf.readShort();
				short command = byteBuf.readShort();
				//读取数据长度
				int length = byteBuf.readInt();
				if (length < 0) {
					channelHandlerContext.channel().close();
				}

				//数据包还没到齐
				if (byteBuf.readableBytes() < length) {
					byteBuf.readerIndex(beginIndex);
					return;
				}

				//读数据部分
				byte[] data = new byte[length];
				byteBuf.readBytes(data);

				Request message = new Request();
				message.setModule(module);
				message.setCommand(command);
				message.setData(data);
				//解析出消息对象，继续往下面的handler传递
				list.add(message);
			}
		}
	}
}
