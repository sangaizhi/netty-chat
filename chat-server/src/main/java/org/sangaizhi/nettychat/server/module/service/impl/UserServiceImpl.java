package org.sangaizhi.nettychat.server.module.service.impl;

import org.sangaizhi.nettychat.core.exception.ErrorCodeException;
import org.sangaizhi.nettychat.core.model.ResultCode;
import org.sangaizhi.nettychat.core.session.Session;
import org.sangaizhi.nettychat.core.session.SessionManager;
import org.sangaizhi.nettychat.module.user.response.UserResponse;
import org.sangaizhi.nettychat.server.module.dao.UserDao;
import org.sangaizhi.nettychat.server.module.entity.User;
import org.sangaizhi.nettychat.server.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sangaizhi
 * @date 2017/5/24
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User getById(Long userId) {
		return userDao.getById(userId);
	}

	@Override
	public User getByName(String name) {
		return userDao.getByName(name);
	}

	@Override
	public Long saveUser(User user) {
		return userDao.saveUser(user);
	}

	@Override
	public UserResponse registerAndLogin(Session session, String userName, String password) {
		User existUser = this.getByName(userName);
		if (null != existUser) {
			throw new ErrorCodeException(ResultCode.USER_EXIST);
		}
		//创建新账号
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		userDao.saveUser(user);

		return login(session, userName, password);
	}

	@Override
	public UserResponse login(Session session, String userName, String password) {
		if (session.getAttachment() != null) {
			throw new ErrorCodeException(ResultCode.HAS_LOGIN);
		}
		User user = userDao.getByName(userName);
		if(null == user){
		    throw new ErrorCodeException(ResultCode.USER_NO_EXIST);
        }
        if(!user.getPassword().equals(password)){
		    throw new ErrorCodeException(ResultCode.PASSWARD_ERROR);
        }
        boolean onlineUser = SessionManager.isOnLine(user.getUserId());
        if(onlineUser){
            Session oldSession = SessionManager.removeSession(user.getUserId());
            oldSession.removeAttachment();
            oldSession.close();
        }
        if(SessionManager.putSession(user.getUserId(), session)){
            session.setAttachment(user);
        }else{
            throw new ErrorCodeException(ResultCode.LOGIN_FAIL);
        }

        UserResponse response = new UserResponse();
        response.setUserId(user.getUserId());
        response.setUserName(user.getUserName());
		return response;
	}
}
