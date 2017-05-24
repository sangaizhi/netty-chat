package org.sangaizhi.nettychat.core.session;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * @author sangaizhi
 * @date 2017/5/24
 */
public class SessionImpl implements Session {

    /**
     * 绑定对象key
     */
    public static AttributeKey<Object>  ATTACHMENT_KEY = AttributeKey.valueOf("ATTACHMENT_KEY");

    /**
     * 实际会话对象
     */
    private Channel channel;

    public SessionImpl(Channel channel){
        this.channel = channel;
    }

    @Override
    public Object getAttachment() {
        return channel.attr(ATTACHMENT_KEY).get();
    }

    @Override
    public void setAttachment(Object attachment) {
        channel.attr(ATTACHMENT_KEY).set(attachment);
    }

    @Override
    public void removeAttachment() {
        channel.attr(ATTACHMENT_KEY).remove();
    }

    @Override
    public void write(Object message) {
        channel.writeAndFlush(message);
    }

    @Override
    public boolean isConnected() {
        return channel.isActive();
    }

    @Override
    public void close() {
        channel.close();
    }
}
