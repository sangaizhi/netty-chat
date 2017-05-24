package org.sangaizhi.nettychat.core.codc;

import java.util.List;

import org.sangaizhi.nettychat.core.model.Response;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 请求响应解码器器
 * <pre>
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——----——+——----——+
 * |  包头	|  模块号      |  命令号    |  结果码    |  长度       |   数据     |
 * +——----——+——-----——+——----——+——----——+——----——+——----——+
 * </pre>
 * @author sangaizhi
 * @date 2017/5/22
 */
public class ResponseDecoder extends ByteToMessageDecoder {

    public static int BASE_NUM = 4 + 2 + 2 + 4 + 4;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        while(true){
            if(byteBuf.readableBytes() >= BASE_NUM){
                //第一个可读数据包的起始位置
                int beginIndex;

                while(true) {
                    //包头开始游标点
                    beginIndex = byteBuf.readerIndex();
                    //标记初始读游标位置
                    byteBuf.markReaderIndex();
                    if (byteBuf.readInt() == Constants.HEADER_FLAG) {
                        break;
                    }
                    //未读到包头标识略过一个字节
                    byteBuf.resetReaderIndex();
                    byteBuf.readByte();

                    //不满足
                    if(byteBuf.readableBytes() < BASE_NUM){
                        return ;
                    }
                }
                //读取模块号命令号
                short module = byteBuf.readShort();
                short cmd = byteBuf.readShort();

                int stateCode = byteBuf.readInt();

                //读取数据长度
                int length = byteBuf.readInt();
                if(length < 0 ){
                    channelHandlerContext.channel().close();
                }

                //数据包还没到齐
                if(byteBuf.readableBytes() < length){
                    byteBuf.readerIndex(beginIndex);
                    return ;
                }

                //读数据部分
                byte[] data = new byte[length];
                byteBuf.readBytes(data);

                Response response = new Response();
                response.setModule(module);
                response.setCommand(cmd);
                response.setResultCode(stateCode);
                response.setData(data);
                //解析出消息对象，继续往下面的handler传递
                list.add(response);
            }else{
                break;
            }
        }
        //数据不完整，等待完整的数据包
        return ;
    }
}
