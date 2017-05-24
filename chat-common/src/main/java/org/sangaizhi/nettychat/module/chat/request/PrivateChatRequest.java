package org.sangaizhi.nettychat.module.chat.request;

import org.sangaizhi.nettychat.core.serialize.Serializer;

/**
 * 私聊请求
 * 
 * @author sangaizhi
 * @date 2017/5/24
 */
public class PrivateChatRequest extends Serializer {

	/**
	 * 私聊的对象
	 */
	private long targetUserId;

	/**
	 * 私聊的内容
	 */
	private String context;

	public long getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(long targetUserId) {
		this.targetUserId = targetUserId;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	protected void read() {
		this.targetUserId = readLong();
		this.context = readString();
	}

	@Override
	protected void write() {
        writeLong(targetUserId);
        writeString(context);
	}
}
