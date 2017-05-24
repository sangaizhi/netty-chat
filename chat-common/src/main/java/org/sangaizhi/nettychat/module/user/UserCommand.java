package org.sangaizhi.nettychat.module.user;

/**
 * 用户模块
 * @author sangaizhi
 * @date 2017/5/24
 */
public interface UserCommand {

    /**
     * 创建账号并登陆
     */
    short REGISTER_AND_LOGIN = 1;

    /**
     * 登陆
     */
    short LOGIN = 2;

}
