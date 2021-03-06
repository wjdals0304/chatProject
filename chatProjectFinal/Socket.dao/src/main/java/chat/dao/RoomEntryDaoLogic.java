package chat.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import chat.domain.Room;
import chat.domain.RoomEntry;
import chat.factory.ChatSqlSessionFactory;
import chat.mapper.RoomEntryDaoMapper;

@Repository
public class RoomEntryDaoLogic implements RoomEntryDao {

	@Override
	public List<RoomEntry> retrieveRoomEntryByRoomNumber(String roomNumber) {
		//
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		RoomEntryDaoMapper mapper= session.getMapper(RoomEntryDaoMapper.class);
		
		List<RoomEntry> roomEntryList = mapper.retrieveRoomEntryByRoomNumber(roomNumber);
		session.close();
		return roomEntryList;
	}

	@Override
	public List<RoomEntry> retrieveRoomEntryByLoginId(String loginId) {
		//
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		RoomEntryDaoMapper mapper= session.getMapper(RoomEntryDaoMapper.class);
		
		List<RoomEntry> roomEntryList = mapper.retrieveRoomEntryByLoginId(loginId);
		session.close();
		
		return roomEntryList;
	}
	

	@Override
	public void registerRoomEntry(RoomEntry roomEntry) {
		//
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		RoomEntryDaoMapper mapper= session.getMapper(RoomEntryDaoMapper.class);
		
		mapper.registerRoomEntry(roomEntry);
		session.commit();
		session.close();

	}

	@Override
	public void removeRoomEntry(RoomEntry roomEntry) {
		// 
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		RoomEntryDaoMapper mapper= session.getMapper(RoomEntryDaoMapper.class);
		
		mapper.removeRoomEntry(roomEntry);
		
		session.commit();
		session.close();
	}

	@Override
	public void updateAdmin(Room room) {
		//
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		RoomEntryDaoMapper mapper= session.getMapper(RoomEntryDaoMapper.class);
		
		mapper.updateAdmin(room);
		session.commit();
		session.close();
		
	}

	@Override
	public List<RoomEntry> retrieveAllRoomEntry() {
		// TODO Auto-generated method stub
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		RoomEntryDaoMapper mapper= session.getMapper(RoomEntryDaoMapper.class);
		List<RoomEntry> roomEntryList = mapper.retrieveAllRoomEntry();
		
		
		session.commit();
		session.close();
		
		return roomEntryList;
		
		
	}

}
