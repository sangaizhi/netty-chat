package org.sangaizhi.nettychat.server.module.service.impl;

import org.sangaizhi.nettychat.core.exception.ErrorCodeException;
import org.sangaizhi.nettychat.core.model.ResultCode;
import org.sangaizhi.nettychat.core.session.SessionManager;
import org.sangaizhi.nettychat.module.ModuleId;
import org.sangaizhi.nettychat.module.chat.ChatCommand;
import org.sangaizhi.nettychat.module.chat.response.ChatResponse;
import org.sangaizhi.nettychat.module.chat.response.ChatType;
import org.sangaizhi.nettychat.server.module.entity.User;
import org.sangaizhi.nettychat.server.module.service.ChatService;
import org.sangaizhi.nettychat.server.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author sangaizhi
 * @date 2017/5/24
 */
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private UserService userService;


    @Override
    public void publicChat(Long userId, String context) {
        User user = userService.getById(userId);
        // 获取所有在线的用户
        Set<Long> onlineUsers = SessionManager.getOnLineUser();

        // 创建消息对象
        ChatResponse chatResponse = new ChatResponse();
        chatResponse.setChatType(ChatType.PUBLIC_CHAT);
        chatResponse.setSendUserId(userId);
        chatResponse.setSendUeerName(user.getUserName());
        chatResponse.setMessage(context);

        // 发送消息
        for(Long targetUserId : onlineUsers){
            SessionManager.sendMessage(targetUserId, ModuleId.CHAT, ChatCommand.PUSHCHAT, chatResponse);
        }
    }

    @Override
    public void privateChat(Long userId,Long targetUserId, String context) {
        if(userId == targetUserId){
            throw new ErrorCodeException(ResultCode.CAN_CHAT_YOUSELF);
        }
        User user = userService.getById(userId);
        User targetUser = userService.getById(targetUserId);
        if(null == targetUser){
            throw new ErrorCodeException(ResultCode.USER_NO_EXIST);
        }
        if(!SessionManager.isOnLine(targetUserId)){
            throw new ErrorCodeException(ResultCode.USER_NO_ONLINE);
        }

        //创建消息对象
        ChatResponse chatResponse = new ChatResponse();
        chatResponse.setChatType(ChatType.PRIVATE_CHAT);
        chatResponse.setSendUserId(userId);
        chatResponse.setSendUeerName(user.getUserName());
        chatResponse.setTargetUserName(targetUser.getUserName());
        chatResponse.setMessage(context);

        //给目标用户发送消息
        SessionManager.sendMessage(targetUserId, ModuleId.CHAT, ChatCommand.PRIVATE_CHAT, chatResponse);
        //给自己也回一个(偷懒做法)
        SessionManager.sendMessage(userId, ModuleId.CHAT, ChatCommand.PUSHCHAT, chatResponse);
    }
}
