package org.sangaizhi.nettychat.server.module.handler;

import org.sangaizhi.nettychat.core.annotation.SocketCommand;
import org.sangaizhi.nettychat.core.annotation.SocketModule;
import org.sangaizhi.nettychat.core.model.Result;
import org.sangaizhi.nettychat.core.session.Session;
import org.sangaizhi.nettychat.module.ModuleId;
import org.sangaizhi.nettychat.module.user.UserCommand;
import org.sangaizhi.nettychat.module.user.response.UserResponse;

/**
 * created by sangaizhi on 2017/5/24.
 */
@SocketModule(module = ModuleId.USER)
public interface UserHandler {
    /**
     * 创建并登陆账号
     * @param session
     * @param data
     * @return
     */
    @SocketCommand(command = UserCommand.REGISTER_AND_LOGIN)
    Result<UserResponse> registerAndLogin(Session session, byte[] data);

    @SocketCommand(command = UserCommand.LOGIN)
    Result<UserResponse> login(Session session,byte[] data);

}
