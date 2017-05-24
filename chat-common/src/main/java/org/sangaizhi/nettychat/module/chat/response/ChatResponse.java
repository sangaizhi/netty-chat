package org.sangaizhi.nettychat.module.chat.response;

import org.sangaizhi.nettychat.core.serialize.Serializer;

/**
 * @author sangaizhi
 * @date 2017/5/24
 */
public class ChatResponse extends Serializer{

    /**
     * 发送者Id
     */
    private Long sendUserId;

    /**
     * 发送者用户名
     */
    private String sendUeerName;

    /**
     * 目标用户用户名
     */
    private String targetUserName;

    /**
     * 聊天消息类型
     * 0 广播消息
     * 1 私聊消息
     * {@link ChatType}
     */
    private byte chatType;

    /**
     * 消息
     */
    private String message;

    @Override
    protected void read() {
        this.sendUserId = readLong();
        this.sendUeerName = readString();
        this.targetUserName = readString();
        this.chatType = readByte();
        this.message = readString();
    }

    @Override
    protected void write() {
        writeLong(this.sendUserId);
        writeString(this.sendUeerName);
        writeString(this.targetUserName);
        writeByte(this.chatType);
        writeString(this.message);
    }

    public Long getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Long sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getSendUeerName() {
        return sendUeerName;
    }

    public void setSendUeerName(String sendUeerName) {
        this.sendUeerName = sendUeerName;
    }

    public String getTargetUserName() {
        return targetUserName;
    }

    public void setTargetUserName(String targetUserName) {
        this.targetUserName = targetUserName;
    }

    public byte getChatType() {
        return chatType;
    }

    public void setChatType(byte chatType) {
        this.chatType = chatType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
