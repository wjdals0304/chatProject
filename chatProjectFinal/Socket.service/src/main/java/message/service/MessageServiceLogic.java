package message.service;

import java.util.List;

import chat.domain.Message;
import chat.message.MessageDao;
import chat.message.MessageDaoLogic;


public class MessageServiceLogic implements MessageService {
	

	private MessageDao messageDao = new MessageDaoLogic();
	
	@Override
	public void registerMessage(Message message) {
		
		
		int number = messageDao.retrieveCount(message.getRoomNumber());
		message.setMessageNumber(number);
		messageDao.registerMessage(message);
	
	}
	
	@Override
	public List<Message> retrieveMessage(String roomNumber, String page) {
		return messageDao.retrieveMessage(roomNumber, page);
	}

}
