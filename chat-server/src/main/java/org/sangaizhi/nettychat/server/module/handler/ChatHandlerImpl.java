package org.sangaizhi.nettychat.server.module.handler;

import org.sangaizhi.nettychat.core.exception.ErrorCodeException;
import org.sangaizhi.nettychat.core.model.Result;
import org.sangaizhi.nettychat.core.model.ResultCode;
import org.sangaizhi.nettychat.module.chat.request.PrivateChatRequest;
import org.sangaizhi.nettychat.module.chat.request.PublicChatRequest;
import org.sangaizhi.nettychat.server.module.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author sangaizhi
 * @date 2017/5/24
 */
@Component
public class ChatHandlerImpl implements ChatHander {

	@Autowired
	private ChatService chatService;

	@Override
	public Result<?> publicChat(Long userId, byte[] data) {
		try {
            // 反序列化
            PublicChatRequest publicChatRequest = new PublicChatRequest();
            publicChatRequest.readFromBytes(data);
            //参数校验
            if (StringUtils.isEmpty(publicChatRequest.getContext())) {
                return Result.ERROR(ResultCode.AGRUMENT_ERROR);
            }
            chatService.publicChat(userId, publicChatRequest.getContext());

        } catch (ErrorCodeException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    e.printStackTrace();
		}

		return Result.SUCCESS();
	}

	@Override
	public Result<?> privateChat(Long userId, byte[] data) {
	    try {
            PrivateChatRequest request = new PrivateChatRequest();
            request.readFromBytes(data);
            //参数校验
            if(StringUtils.isEmpty(request.getContext()) || request.getTargetUserId() <= 0){
                return Result.ERROR(ResultCode.AGRUMENT_ERROR);
            }
            //执行业务
            chatService.privateChat(userId, request.getTargetUserId(), request.getContext());
        } catch (ErrorCodeException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
	}
}
