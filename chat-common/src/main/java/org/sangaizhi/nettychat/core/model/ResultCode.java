package org.sangaizhi.nettychat.core.model;

/**
 * @author sangaizhi
 * @date 2017/5/22
 */
public interface ResultCode {

    /**
     * 成功
     */
    int SUCCESS = 0;

    /**
     * 找不到命令
     */
    int NO_INVOKER = 1;

    /**
     * 参数异常
     */
    int AGRUMENT_ERROR = 2;

    /**
     * 未知异常
     */
    int UNKOWN_EXCEPTION = 3;

    /**
     * 玩家名或密码不能为空
     */
    int USER_NAME_NULL = 4;

    /**
     * 玩家名已使用
     */
    int USER_EXIST = 5;

    /**
     * 玩家不存在
     */
    int USER_NO_EXIST = 6;

    /**
     * 密码错误
     */
    int PASSWARD_ERROR = 7;

    /**
     * 您已登录
     */
    int HAS_LOGIN = 8;

    /**
     * 登录失败
     */
    int LOGIN_FAIL = 9;

    /**
     * 玩家不在线
     */
    int USER_NO_ONLINE = 10;

    /**
     * 请先登录
     */
    int LOGIN_PLEASE = 11;

    /**
     * 不能私聊自己
     */
    int CAN_CHAT_YOUSELF = 12;
}
