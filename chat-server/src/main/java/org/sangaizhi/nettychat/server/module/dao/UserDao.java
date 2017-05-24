package org.sangaizhi.nettychat.server.module.dao;

import org.sangaizhi.nettychat.server.module.entity.User;
import org.springframework.stereotype.Repository;

/**
 * created by sangaizhi on 2017/5/24.
 */
@Repository
public interface UserDao {

    User getById(Long userId);

    User getByName(String name);

    Long saveUser(User user);
}
