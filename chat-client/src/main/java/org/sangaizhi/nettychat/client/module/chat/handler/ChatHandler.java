package org.sangaizhi.nettychat.client.module.chat.handler;

import org.sangaizhi.nettychat.core.annotation.SocketCommand;
import org.sangaizhi.nettychat.core.annotation.SocketModule;
import org.sangaizhi.nettychat.module.ModuleId;
import org.sangaizhi.nettychat.module.chat.ChatCommand;

/**
 * 聊天
 * @author -琴兽-
 *
 */
@SocketModule(module = ModuleId.CHAT)
public interface ChatHandler {
	
	/**
	 * 发送广播消息回包
	 * @param resultCode
	 * @param data {@link null}
	 * @return
	 */
	@SocketCommand(command = ChatCommand.PUBLIC_CHAT)
	public void publicChat(int resultCode, byte[] data);
	
	/**
	 * 发送私人消息回包
	 * @param resultCode
	 * @param data {@link null}
	 * @return
	 */
	@SocketCommand(command = ChatCommand.PRIVATE_CHAT)
	public void privateChat(int resultCode, byte[] data);
	
	/**
	 * 收到推送聊天信息
	 * @param resultCode
	 * @param data {@link org.sangaizhi.nettychat.module.chat.response.ChatResponse}
	 * @return
	 */
	@SocketCommand(command = ChatCommand.PUSHCHAT)
	public void receieveMessage(int resultCode, byte[] data);
}
