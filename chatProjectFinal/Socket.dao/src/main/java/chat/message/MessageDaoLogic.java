package chat.message;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

import chat.domain.Message;


public class MessageDaoLogic implements MessageDao {

	@SuppressWarnings("deprecation")
	@Override
	public void registerMessage(Message message) {
		// TODO Auto-generated method stub
		MongoClient mongoClient = null;
		try{
			mongoClient = new MongoClient("localhost",27017);
			WriteConcern w = new WriteConcern(1,2000);
			mongoClient.setWriteConcern(w);
			DB db = mongoClient.getDB("chat");
			DBCollection coll = db.getCollection("chat");
			DBObject doc = new BasicDBObject();
			
			doc.put("roomNumber",message.getRoomNumber());
			doc.put("type", message.getType());
			doc.put("contents", message.getContents());
			doc.put("from", message.getFrom());
			doc.put("setTo", message.getTo());
			doc.put("date",message.getDate());
			doc.put("nickName", message.getNickName());
			doc.put("seq", message.getMessageNumber());
			coll.insert(doc);
	
		}catch(Exception e){
			System.out.println(e.getMessage());
		} finally{
			mongoClient.close();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Message> retrieveMessage(String roomNumber, String page) {
		
		MongoClient mongoClient = null;
	
		List<Message> messageList = new ArrayList<>();
		try{
			mongoClient = new MongoClient("localhost",27017);
			WriteConcern w = new WriteConcern(1,2000);
			mongoClient.setWriteConcern(w);
			
			DB db = mongoClient.getDB("chat");
			DBCollection coll = db.getCollection("chat");

			DBObject doc = new BasicDBObject();
			doc.put("roomNumber",roomNumber);
			doc.put("type" ,"TXT" );
			doc.put("setTo", "all");
			
			DBObject k = new BasicDBObject();
				k.put("_id", 0);
			
			DBObject o = new BasicDBObject();
			o.put("seq", -1);
			
			int pageInt=Integer.parseInt(page);
			
			DBCursor cursor = coll.find(doc,k).sort(o).skip((pageInt-1)*30).limit(30);
            while(cursor.hasNext()){
            	DBObject a = cursor.next();
            	Message message = new Message();
            	message.setContents((String)a.get("contents"));
            	message.setNickName((String)a.get("nickName"));
            	message.setFrom((String)a.get("from"));
            	message.setDate((String)a.get("date"));
            	messageList.add(message);
            }
        
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			mongoClient.close();
		}
		return messageList;
	}
	
	public int retrieveCount(String roomNumber){
		MongoClient mongoClient = null;
		
		int cursor =0;
		try{
			mongoClient = new MongoClient("localhost",27017);
			WriteConcern w = new WriteConcern(1,2000);
			mongoClient.setWriteConcern(w);
			
			DB db = mongoClient.getDB("chat");
		
			DBCollection coll = db.getCollection("chat");
			
			DBObject doc = new BasicDBObject();
			
			doc.put("roomNumber",roomNumber);
			doc.put("type" ,"TXT" );
			doc.put("setTo", "all");
			
			DBObject k = new BasicDBObject();
				k.put("_id", 0);
			
			cursor = coll.find(doc,k).count();
     
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
		
			mongoClient.close();
		}
		
		
		return cursor;
		
	}
}
