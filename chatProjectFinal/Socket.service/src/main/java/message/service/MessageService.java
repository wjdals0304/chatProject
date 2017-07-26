package message.service;

import java.util.List;

import chat.domain.Message;

public interface MessageService {
	
		void registerMessage(Message message);
		
		List<Message> retrieveMessage(String roomNumber,String page);	
}
