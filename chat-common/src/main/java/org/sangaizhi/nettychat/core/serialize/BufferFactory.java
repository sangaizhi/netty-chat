package org.sangaizhi.nettychat.core.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PoolArenaMetric;
import io.netty.buffer.PooledByteBufAllocator;

import java.nio.ByteOrder;

/**
 * buffer 工厂
 * 
 * @author sangaizhi
 * @date 2017/5/22
 */
public class BufferFactory {

	public static ByteOrder BYTE_ORDER = ByteOrder.BIG_ENDIAN;

	public static ByteBufAllocator byteBufAllocator = PooledByteBufAllocator.DEFAULT;

    /**
     * 获取一个Buffer
     * @return
     */
	public static ByteBuf getBuffer() {
		ByteBuf byteBuf = byteBufAllocator.buffer();
		byteBuf = byteBuf.order(BYTE_ORDER);
		return byteBuf;
	}

    /**
     * 获取一个buffer,并将数据写入buffer
     * @param data
     * @return
     */
	public static ByteBuf getBuffer(byte[] data){
        ByteBuf byteBuf = byteBufAllocator.buffer();
        byteBuf = byteBuf.order(BYTE_ORDER);
        byteBuf.writeBytes(data);
        return byteBuf;
    }

}
