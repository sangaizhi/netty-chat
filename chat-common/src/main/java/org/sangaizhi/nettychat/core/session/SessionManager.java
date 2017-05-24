package org.sangaizhi.nettychat.core.session;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.sangaizhi.nettychat.core.model.Response;
import org.sangaizhi.nettychat.core.serialize.Serializer;

import com.google.protobuf.GeneratedMessage;

/**
 * 会话管理器
 * @author sangaizhi
 * @date 2017/5/24
 */
public class SessionManager {

    /**
     * 存储所有在线的用户
     */
    private static final ConcurrentHashMap<Long, Session> onLineSessions = new ConcurrentHashMap<>();


    /**
     * 加入
     * @param userId
     * @param session
     * @return
     */
    public static boolean putSession(long userId, Session session){
        if(!onLineSessions.containsKey(userId)){
            return onLineSessions.putIfAbsent(userId, session) == null ;
        }
        return false;
    }

    /**
     * 移除
     * @param userId
     * @return
     */
    public static Session removeSession(long userId){
        return onLineSessions.remove(userId);
    }

    /**
     * 发送消息：自定义协议
     * @param userId
     * @param module
     * @param command
     * @param message
     * @param <T>
     */
    public static <T extends Serializer> void sendMessage(Long userId, Short module, Short command, T message){
        Session session = onLineSessions.get(userId);
        if(null != session && session.isConnected()){
            Response response = new Response(module, command, message.getBytes());
            session.write(response);
        }
    }

    /**
     * 发送消息（protobuf 协议额）
     * @param userId
     * @param module
     * @param command
     * @param message
     * @param <T>
     */
    public static <T extends GeneratedMessage> void sendMessage(Long userId, Short module, Short command, T message){
        Session session = onLineSessions.get(userId);
        if(null != session && session.isConnected()){
            Response response = new Response(module, command, message.toByteArray());
            session.write(response);
        }
    }

    /**
     * 是否在线
     * @param userId
     * @return
     */
    public static boolean isOnLine(Long userId){
        return onLineSessions.containsKey(userId);
    }

    /**
     * 获取所有在线的用户
     * @return
     */
    public static Set<Long> getOnLineUser(){
        return Collections.unmodifiableSet(onLineSessions.keySet());
    }

}
