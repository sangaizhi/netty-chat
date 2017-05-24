package org.sangaizhi.nettychat.module.chat;

/**
 * 聊天模块
 * @author sangaizhi
 * @date 2017/5/24
 */
public interface ChatCommand {

    /**
     * 广播消息
     */
    short PUBLIC_CHAT = 1;

    /**
     * 私人消息
     */
    short PRIVATE_CHAT = 2;

    /**
     * 消息推送
     */
    short PUSHCHAT = 101;
}
