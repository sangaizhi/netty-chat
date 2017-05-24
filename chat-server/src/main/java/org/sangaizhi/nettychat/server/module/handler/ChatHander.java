package org.sangaizhi.nettychat.server.module.handler;

import org.sangaizhi.nettychat.core.annotation.SocketCommand;
import org.sangaizhi.nettychat.core.annotation.SocketModule;
import org.sangaizhi.nettychat.core.model.Result;
import org.sangaizhi.nettychat.module.ModuleId;
import org.sangaizhi.nettychat.module.chat.ChatCommand;

/**
 * 聊天模块的处理器接口，可处理广播消息和私聊消息
 * created by sangaizhi on 2017/5/24.
 */
@SocketModule(module = ModuleId.CHAT)
public interface ChatHander {

    /**
     * 广播消息
     * @param userId
     * @param data
     * @return
     */
    @SocketCommand(command = ChatCommand.PUBLIC_CHAT)
    Result<?> publicChat(Long userId, byte[] data);

    /**
     * 私聊消息
     * @param userId
     * @param data
     * @return
     */
    @SocketCommand(command = ChatCommand.PRIVATE_CHAT)
    Result<?> privateChat(Long userId, byte[] data);
}
