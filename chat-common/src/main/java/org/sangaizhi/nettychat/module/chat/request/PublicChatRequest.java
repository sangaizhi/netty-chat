package org.sangaizhi.nettychat.module.chat.request;

import org.sangaizhi.nettychat.core.serialize.Serializer;

/**
 * 广播消息请求
 * @author sangaizhi
 * @date 2017/5/24
 */
public class PublicChatRequest extends Serializer {

    /**
     * 群聊消息内容
     */
    private String context;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    protected void read() {
    this.context = context;
    }

    @Override
    protected void write() {
        writeString(context);
    }
}
