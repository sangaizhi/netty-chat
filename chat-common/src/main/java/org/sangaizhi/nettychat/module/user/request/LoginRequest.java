package org.sangaizhi.nettychat.module.user.request;

import org.sangaizhi.nettychat.core.serialize.Serializer;

/**
 * 登录请求
 * @author sangaizhi
 * @date 2017/5/24
 */
public class LoginRequest extends Serializer {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected void read() {
        this.userName = readString();
        this.password = readString();
    }

    @Override
    protected void write() {
        writeString(userName);
        writeString(password);
    }
}
