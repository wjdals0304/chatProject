package chat.message;

import java.util.List;

import chat.domain.Message;

public interface MessageDao {

	void registerMessage(Message message); 
	
	List<Message> retrieveMessage(String roomNumber, String page);
	
	int retrieveCount (String roomNumber);
}



