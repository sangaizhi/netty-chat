package org.sangaizhi.nettychat.server.module.handler;

import org.sangaizhi.nettychat.core.exception.ErrorCodeException;
import org.sangaizhi.nettychat.core.model.Result;
import org.sangaizhi.nettychat.core.model.ResultCode;
import org.sangaizhi.nettychat.core.session.Session;
import org.sangaizhi.nettychat.module.user.request.LoginRequest;
import org.sangaizhi.nettychat.module.user.request.RegisterRequest;
import org.sangaizhi.nettychat.module.user.response.UserResponse;
import org.sangaizhi.nettychat.server.module.entity.User;
import org.sangaizhi.nettychat.server.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author sangaizhi
 * @date 2017/5/24
 */
@Component
public class UserHandlerImpl implements UserHandler {

    @Autowired
    private UserService userService;

	@Override
	public Result<UserResponse> registerAndLogin(Session session, byte[] data) {
		UserResponse response = null;
		try {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.readFromBytes(data);
            if(StringUtils.isEmpty(registerRequest.getUserName())|| StringUtils.isEmpty(registerRequest.getPassword())){
                return Result.ERROR(ResultCode.USER_NAME_NULL);
            }
            response = userService.registerAndLogin(session, registerRequest.getUserName(), registerRequest.getPassword());
		} catch (ErrorCodeException e) {
		    return Result.ERROR(e.getErrorCode());
		} catch (Exception e) {
		    e.printStackTrace();
		    return Result.ERROR(ResultCode.UNKOWN_EXCEPTION);
		}

		return Result.SUCCESS();
	}

	@Override
	public Result<UserResponse> login(Session session, byte[] data) {
	    UserResponse response = null;
	    try{
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.readFromBytes(data);
            if(StringUtils.isEmpty(loginRequest.getUserName()) || StringUtils.isEmpty(loginRequest.getPassword())){
                return Result.ERROR(ResultCode.USER_NAME_NULL);
            }
            response = userService.login(session, loginRequest.getUserName(), loginRequest.getPassword());
        }catch (ErrorCodeException e){
            return Result.ERROR(e.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.UNKOWN_EXCEPTION);
        }
        return Result.SUCCESS(response);
	}
}
