package org.sangaizhi.nettychat.client.module.chat.handler;

import org.sangaizhi.nettychat.client.swing.ResultCodeTip;
import org.sangaizhi.nettychat.client.swing.Swingclient;
import org.sangaizhi.nettychat.core.model.ResultCode;
import org.sangaizhi.nettychat.module.chat.response.ChatResponse;
import org.sangaizhi.nettychat.module.chat.response.ChatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatHandlerImpl implements ChatHandler{
	
	@Autowired
	private Swingclient swingclient;
	@Autowired
	private ResultCodeTip resultCodeTip;

	@Override
	public void publicChat(int resultCode, byte[] data) {
		if(resultCode == ResultCode.SUCCESS){
			swingclient.getTips().setText("发送成功");
		}else{
			swingclient.getTips().setText(resultCodeTip.getTipContent(resultCode));
		}
	}

	@Override
	public void privateChat(int resultCode, byte[] data) {
		if(resultCode == ResultCode.SUCCESS){
			swingclient.getTips().setText("发送成功");
		}else{
			swingclient.getTips().setText(resultCodeTip.getTipContent(resultCode));
		}
	}

	@Override
	public void receieveMessage(int resultCode, byte[] data) {
		
		ChatResponse chatResponse = new ChatResponse();
		chatResponse.readFromBytes(data);
		
		if(chatResponse.getChatType()== ChatType.PUBLIC_CHAT){
			StringBuilder builder = new StringBuilder();
			builder.append(chatResponse.getSendUeerName());
			builder.append("[");
			builder.append(chatResponse.getSendUserId());
			builder.append("]");
			builder.append(" 说:\n\t");
			builder.append(chatResponse.getMessage());
			builder.append("\n\n");
			
			swingclient.getChatContext().append(builder.toString());
		}else if(chatResponse.getChatType()==ChatType.PRIVATE_CHAT){
			StringBuilder builder = new StringBuilder();
			
			if(swingclient.getPlayerResponse().getUserId() == chatResponse.getSendUserId()){
				builder.append("您悄悄对 ");
				builder.append("[");
				builder.append(chatResponse.getTargetUserName());
				builder.append("]");
				builder.append(" 说:\n\t");
				builder.append(chatResponse.getMessage());
				builder.append("\n\n");
			}else{
				builder.append(chatResponse.getSendUeerName());
				builder.append("[");
				builder.append(chatResponse.getSendUserId());
				builder.append("]");
				builder.append(" 悄悄对你说:\n\t");
				builder.append(chatResponse.getMessage());
				builder.append("\n\n");
			}
			
			swingclient.getChatContext().append(builder.toString());
		}
	}
}
