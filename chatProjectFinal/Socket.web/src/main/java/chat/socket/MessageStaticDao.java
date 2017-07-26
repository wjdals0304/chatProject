package chat.socket;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.websocket.Session;

public class MessageStaticDao {

    @SuppressWarnings("rawtypes")
	public static Map<String,Set> sClients = new HashMap<>();

	@SuppressWarnings("rawtypes")
	public Set retrieve(String room) {
		// TODO Auto-generated method stub
		return sClients.get(room);
	}
	
	@SuppressWarnings("rawtypes")
	public Map<String,Set> retrieveClient(){
		return sClients;
	}
	public void add(String room, Set<Session> clientList) {
			sClients.put(room,clientList);
	}
	public void remove(String room, Set<Session> resultList) {
		// TODO Auto-generated method stub
		sClients.put(room, resultList);
	}
	
}
