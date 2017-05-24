package org.sangaizhi.nettychat.server.module.service;

/**
 * created by sangaizhi on 2017/5/24.
 */
public interface ChatService {

    /**
     * 群发消息
     * @param userId
     * @param context
     */
    void publicChat(Long userId, String context);

    /**
     * 私聊消息
     * @param userId
     * @param targetUserId
     * @param context
     */
    void privateChat(Long userId, Long targetUserId, String context);
}
