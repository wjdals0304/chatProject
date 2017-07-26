package chat.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

import chat.domain.Message;

public class MessageDaoLogicTest {

	@Test
	public void registerMessage() {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient("localhost", 27017);
			WriteConcern w = new WriteConcern(1, 2000);
			mongoClient.setWriteConcern(w);
			DB db = mongoClient.getDB("chat");
			DBCollection coll = db.getCollection("chat");
			DBObject doc = new BasicDBObject();
			Message message = new Message();
			message.setContents("a");
			message.setTo("all");
			message.setFrom("wjdals");
			message.setType("TXT");
			message.setRoomNumber("70");
			
			doc.put("roomNumber", message.getRoomNumber());
			doc.put("type", message.getType());
			doc.put("contents", message.getContents());
			doc.put("from", message.getFrom());
			doc.put("setTo", message.getTo());
			doc.put("messageDate", message.getDate());
			doc.put("messageNumber", message.getMessageNumber());
			
			coll.insert(doc);
			
			assert(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			mongoClient.close();
		}
	}

	@Test
	public void retrieve() {

		MongoClient mongoClient = null;
	
		List<Message> messageList = new ArrayList<>();
		try{
			mongoClient = new MongoClient("localhost",27017);
			WriteConcern w = new WriteConcern(1,2000);
			mongoClient.setWriteConcern(w);
			
			DB db = mongoClient.getDB("chat");
			DBCollection coll = db.getCollection("chat");

			DBObject doc = new BasicDBObject();
			doc.put("roomNumber","70");
			doc.put("type" ,"TXT" );
			doc.put("setTo", "all");
			
			DBObject k = new BasicDBObject();
				k.put("_id", 0);
			DBObject o = new BasicDBObject();
			o.put("seq", -1);
			int pageInt=1;
			DBCursor cursor = coll.find(doc,k).sort(o).skip((pageInt-1)*10).limit(10);
            while(cursor.hasNext()){
            	DBObject a = cursor.next();
            	Message message = new Message();
            	message.setContents((String)a.get("contents"));
            	message.setNickName((String)a.get("nickName"));
            	message.setFrom((String)a.get("from"));
            	message.setDate((String)a.get("date"));
            	System.out.println(message.toString());
            	messageList.add(message);
            }
            assertEquals(10,messageList.size());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			mongoClient.close();
		}
		
	}

	@Test
	public void retrieveCount(){
		MongoClient mongoClient = null;
		
		int cursor =0;
		try{
			mongoClient = new MongoClient("localhost",27017);
			WriteConcern w = new WriteConcern(1,2000);
			mongoClient.setWriteConcern(w);
			
			DB db = mongoClient.getDB("chat");
		
			DBCollection coll = db.getCollection("chat");
			
			DBObject doc = new BasicDBObject();
			
			doc.put("roomNumber","70");
			doc.put("type" ,"TXT" );
			doc.put("setTo", "all");
			
			DBObject k = new BasicDBObject();
				k.put("_id", 0);
			
			cursor = coll.find(doc,k).count();
			
			assertEquals(31,cursor);
     
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
		
			mongoClient.close();
		}
		
	
		
	}
	
	
	
}
