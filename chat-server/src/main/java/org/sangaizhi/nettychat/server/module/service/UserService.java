package org.sangaizhi.nettychat.server.module.service;

import org.sangaizhi.nettychat.core.session.Session;
import org.sangaizhi.nettychat.module.user.response.UserResponse;
import org.sangaizhi.nettychat.server.module.entity.User;

/**
 * created by sangaizhi on 2017/5/24.
 */
public interface UserService {

    User getById(Long userId);

    User getByName(String name);
    Long saveUser(User user);
    UserResponse registerAndLogin(Session session, String userName, String password);

    UserResponse login(Session session, String userName,String password);
}
