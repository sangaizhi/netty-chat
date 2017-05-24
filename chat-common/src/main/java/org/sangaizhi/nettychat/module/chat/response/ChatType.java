package org.sangaizhi.nettychat.module.chat.response;

/**
 * 聊天消息定义
 * @author sangaizhi
 * @date 2017/5/24
 */
public interface ChatType {

    /**
     * 广播消息
     */
    byte PUBLIC_CHAT = 0;

    /**
     * 私聊消息
     */
    byte PRIVATE_CHAT = 1;

}
