package org.sangaizhi.nettychat.core.session;

/**
 * @author sangaizhi
 * @date 2017/5/23
 */
public interface Session {

    /**
     * 会话绑定的对象
     * @return
     */
    Object getAttachment();

    /**
     * 绑定会话对象
     */
    void setAttachment(Object attachment);

    /**
     * 移除会话绑定对象
     */
    void removeAttachment();

    /**
     * 向会话中写入消息
     * @param message
     */
    void write(Object message);

    /**
     * 判断会话是否链接
     * @return
     */
    boolean isConnected();

    /**
     * 关闭会话
     */
    void close();
}
