package org.sangaizhi.nettychat.client.module.player.handler;

import org.sangaizhi.nettychat.core.annotation.SocketCommand;
import org.sangaizhi.nettychat.core.annotation.SocketModule;
import org.sangaizhi.nettychat.module.ModuleId;
import org.sangaizhi.nettychat.module.user.UserCommand;

/**
 * 玩家模块
 * @author -琴兽-
 *
 */
@SocketModule(module = ModuleId.USER)
public interface PlayerHandler {
	
	
	/**
	 * 创建并登录帐号
	 * @param resultCode
	 * @param data {@link null}
	 */
	@SocketCommand(command = UserCommand.REGISTER_AND_LOGIN)
	public void registerAndLogin(int resultCode, byte[] data);
	

	/**
	 * 创建并登录帐号
	 * @param resultCode
	 * @param data {@link null}
	 */
	@SocketCommand(command = UserCommand.LOGIN)
	public void login(int resultCode, byte[] data);

}
