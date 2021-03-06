package chat.socket;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import chat.domain.Message;
import message.service.MessageService;
import message.service.MessageServiceLogic;

@ServerEndpoint("/broadcasting/{roomNumber}/{name}/{loginId}")
public class MessageSocket {

	MessageStaticDao staticDao = new MessageStaticDao();

	MessageService messageService = new MessageServiceLogic();

	@SuppressWarnings("unchecked")
	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		String room = session.getPathParameters().get("roomNumber");
		String nickName = session.getPathParameters().get("name");
		String loginId = session.getPathParameters().get("loginId");

		Calendar calendar = Calendar.getInstance();
		long currentTime = calendar.getTimeInMillis();
		Date today = new Date(currentTime);

		SimpleDateFormat transFormat = new SimpleDateFormat("MM-dd HH:mm");
		String todayy = transFormat.format(today);

		Set<Session> clients = staticDao.retrieve(room);

		Message messageObject = new Gson().fromJson(message, Message.class);
		messageObject.setRoomNumber(room);
		messageObject.setFrom(loginId);
		messageObject.setDate(todayy);
		messageObject.setNickName(nickName);

		messageService.registerMessage(messageObject);

		if ("TXT".equals(messageObject.getType())) {
			for (Session client : clients) {
				if (!client.equals(session)) {
					client.getBasicRemote().sendText(buildJsonMessageData(messageObject.getFrom(),
							messageObject.getNickName(), messageObject.getContents()));
				}
			}
		}
		
		
		if("file".equals(messageObject.getType())){
			for (Session client : clients) {
				if (!client.equals(session)) {
					client.getBasicRemote().sendText(fileMessage(messageObject.getType(),messageObject.getNickName(),messageObject.getFileName()));
				}
			}
		}
		
		
		Message messageLeaving = new Gson().fromJson(message, Message.class);
		if ("leaving".equals(messageLeaving.getType())) {
			for (Session toSession : clients) {
				if(toSession == toSession.getUserProperties().get(messageLeaving.getTo())){
					toSession.getBasicRemote().sendText(leavingMessage(messageLeaving.getType()));
				}
			}
		}
		
		Message messageGrant = new Gson().fromJson(message, Message.class);
		if("granting".equals(messageGrant.getType())){
			for (Session toSession : clients) {
				if(toSession == toSession.getUserProperties().get(messageLeaving.getTo())){
					toSession.getBasicRemote().sendText(grantingMessage(messageLeaving.getType()));
				}
			}
		}
		
		
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@OnOpen
	public void onOpen(Session session) {

		String room = session.getPathParameters().get("roomNumber");
		String loginId = session.getPathParameters().get("loginId");

		session.getUserProperties().put(loginId, session);

		Map<String, Set> beforeClients = staticDao.retrieveClient();

		Iterator<String> keys = beforeClients.keySet().iterator();

		while (keys.hasNext()) {
			String key = keys.next();
			if (room.equals(key)) {
				Set<Session> resultList = beforeClients.get(key);
				resultList.add(session);
				staticDao.add(room, resultList);
				return;
			}
		}
		Set<Session> clientList = new HashSet<>();
		clientList.add(session);
		staticDao.add(room, clientList);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@OnClose
	public void onClose(Session session) throws Exception {
		String room = session.getPathParameters().get("roomNumber");

		Map<String, Set> beforeClients = staticDao.retrieveClient();
		Iterator<String> keys = beforeClients.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			if (room.equals(key)) {
				Set<Session> resultList = beforeClients.get(key);
				resultList.remove(session);
				staticDao.remove(room, resultList);
			}
		}
	}

	public String buildJsonMessageData(String loginId, String nickName, String message) {
		JsonObject jsonObject = Json.createObjectBuilder().add("message", loginId + ":" + nickName + ":" + message)
				.build();
		StringWriter sw = new StringWriter();
		try (JsonWriter jsonWriter = Json.createWriter(sw)) {
			jsonWriter.write(jsonObject);
		}
		return sw.toString();
	}
	public String leavingMessage(String type) {
		JsonObject jsonObject = Json.createObjectBuilder().add("leaving", type).build();
		StringWriter sw = new StringWriter();
		try (JsonWriter jsonWriter = Json.createWriter(sw)) {
			jsonWriter.write(jsonObject);
		}
		return sw.toString();
	}
	
	public String grantingMessage(String type){
		JsonObject jsonObject = Json.createObjectBuilder().add("granting", type).build();
		StringWriter sw = new StringWriter();
		try (JsonWriter jsonWriter = Json.createWriter(sw)) {
			jsonWriter.write(jsonObject);
		}
		return sw.toString();
	}
	
	public String fileMessage(String type,String nickName,String fileName ){
		JsonObject jsonObject = Json.createObjectBuilder().add("file", type + ":" + nickName + ":" +fileName).build();
		StringWriter sw = new StringWriter();
		try (JsonWriter jsonWriter = Json.createWriter(sw)) {
			jsonWriter.write(jsonObject);
		}
		return sw.toString();
	}
	
	

}