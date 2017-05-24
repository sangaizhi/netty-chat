package org.sangaizhi.nettychat.module.user.response;

import org.sangaizhi.nettychat.core.serialize.Serializer;

/**
 * 用户响应
 *
 * @author sangaizhi
 * @date 2017/5/24
 */
public class UserResponse extends Serializer {

    private Long userId;

    private String userName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    protected void read() {
        this.userId = readLong();
        this.userName = readString();
    }

    @Override
    protected void write() {
        writeLong(this.userId);
        writeString(this.userName);
    }
}
