package chat.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import chat.domain.Category;
import chat.domain.Room;
import chat.factory.ChatSqlSessionFactory;
import chat.mapper.RoomDaoMapper;

/**
 * Room 에 대한 처리를 하는 DAO
 * @author Hyesung park
 *
 */
@Repository
public class RoomDaoLogic implements RoomDao {

	@Override
	public Room retrieveRoom(String roomNumber) {
		//
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		RoomDaoMapper mapper = session.getMapper(RoomDaoMapper.class);

		Room room = mapper.retrieveRoom(roomNumber);

		session.close();

		return room;
	}

	@Override
	public List<Room> retrieveRoomAllByRowNum(String startNum, String endNum) {
		//
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		RoomDaoMapper mapper = session.getMapper(RoomDaoMapper.class);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);

		List<Room> roomList = mapper.retrieveRoomAllByRowNum(map);

		session.close();

		return roomList;
	}

	@Override
	public List<Room> retrieveRoomByCategory(Category category, String startNum, String endNum) {
		//
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		RoomDaoMapper mapper = session.getMapper(RoomDaoMapper.class);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("category", category.getCode());
		
		List<Room> roomList = mapper.retrieveRoomByCategory(map);

		session.close();

		return roomList;
	}

	@Override
	public Room retrieveRoomByAdmin(String loginId) {
		//
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		RoomDaoMapper mapper = session.getMapper(RoomDaoMapper.class);

		Room room = mapper.retrieveRoomByAdmin(loginId);

		session.close();

		return room;
	}

	@Override
	public List<Room> retrieveRoomByTitleForSearch(String keyword, String startNum, String endNum) {
		//
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		RoomDaoMapper mapper = session.getMapper(RoomDaoMapper.class);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("title", keyword);

		List<Room> roomList = mapper.retrieveRoomByTitleForSearch(map);

		session.close();

		return roomList;
	}

	@Override
	public void registerRoom(Room room) {
		//
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		RoomDaoMapper mapper = session.getMapper(RoomDaoMapper.class);
		
		mapper.registerRoom(room);
		
		session.commit();
		session.close();

	}

	@Override
	public void modifyRoom(Room room) {
		//
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		RoomDaoMapper mapper = session.getMapper(RoomDaoMapper.class);
		
		mapper.modifyRoom(room);
		
		session.commit();
		session.close();
	}


}
