package chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chat.dao.RoomDao;
import chat.dao.RoomEntryDao;
import chat.dao.UserDao;
import chat.domain.Category;
import chat.domain.Room;
import chat.domain.RoomEntry;
import chat.domain.User;

/**
 * Room 에 대한 처리를 하는 서비스
 * @author Hyesung park
 *
 */
@Service
public class RoomServiceLogic implements RoomService {

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoomEntryDao roomEntryDao;
	
	@Override
	public Room retrieveRoom(String roomNumber) {
		//

		Room room = roomDao.retrieveRoom(roomNumber);
                
		User user = userDao.retrieveUser(room.getAdmin().getLoginId());
		
		room.setAdmin(user);
		// 해당 방에 속한 유저목록 조회
		if (room != null) {
			List<RoomEntry> entryList = roomEntryDao.retrieveRoomEntryByRoomNumber(room.getRoomNumber());

			room.setUsers(entryList);
		}

		return room;
	}

	@Override
	public Room retrieveRoomByAdmin(String loginId) {
		//

		Room room = roomDao.retrieveRoomByAdmin(loginId);

		if (room != null) {
			List<RoomEntry> entryList = roomEntryDao.retrieveRoomEntryByRoomNumber(room.getRoomNumber());

			room.setUsers(entryList);
		}

		return room;
	}

	@Override
	public List<Room> retrieveRoomAllByRowNum(String more) {
		//

		/*
		 * 현재 페이지에 보여줄 데이터의 시작 번호 = (현재 페이지 – 1) * 출력 개수 + 1 현재 페이지에 보여줄 데이터의 종료
		 * 번호 = 시작 번호 + 출력개수 - 1
		 */

		int size = 10;
		int endNum = 10;
		int startNum = 0;

		if (more != null) {
			int index = Integer.parseInt(more);
			endNum = (index * size);
			startNum = endNum - 9;
		}

		String start = Integer.toString(startNum);
		String end = Integer.toString(endNum);
		List<Room> roomList = roomDao.retrieveRoomAllByRowNum(start, end);

		for (Room rooms : roomList) {
			rooms.setAdmin( userDao.retrieveUser(rooms.getAdmin().getLoginId())); 
			List<RoomEntry> entryList = roomEntryDao.retrieveRoomEntryByRoomNumber(rooms.getRoomNumber());
			rooms.setUsers(entryList);
		}
		return roomList;
	}

	@Override
	public List<Room> retrieveRoomByCategory(Category category, String more) {
		//
		int size = 10;
		int endNum = 10;
		int startNum = 0;

		if (more != null) {
			int index = Integer.parseInt(more);
			endNum = (index * size);
			startNum = endNum - 9;
		}

		String start = Integer.toString(startNum);
		String end = Integer.toString(endNum);

		System.out.println("페이징 부분 : " + category + "와" + "ALL 이 같나?");

		if ("ALL".equals(category.getCode())) {

			List<Room> roomList = roomDao.retrieveRoomAllByRowNum(start, end);

			if (roomList != null) {
				for (Room rooms : roomList) {
					rooms.setAdmin( userDao.retrieveUser(rooms.getAdmin().getLoginId())); 
					List<RoomEntry> entryList = roomEntryDao.retrieveRoomEntryByRoomNumber(rooms.getRoomNumber());
					rooms.setUsers(entryList);
				}
			}

			return roomList;

		} else {

			System.out.println("ALL 시 여기가 수행되나?");

			List<Room> roomList = roomDao.retrieveRoomByCategory(category, start, end);

			if (roomList != null) {
				for (Room rooms : roomList) {
					rooms.setAdmin( userDao.retrieveUser(rooms.getAdmin().getLoginId())); 
					List<RoomEntry> entryList = roomEntryDao.retrieveRoomEntryByRoomNumber(rooms.getRoomNumber());
					rooms.setUsers(entryList);
				}
			}

			return roomList;

		}

	}

	@Override
	public List<Room> retrieveRoomByTitleForSearch(String keyword, String more) {
		//
		int size = 10;
		int endNum = 10;
		int startNum = 0;

		if (more != null) {
			int index = Integer.parseInt(more);
			endNum = (index * size);
			startNum = endNum - 9;
		}

		String start = Integer.toString(startNum);
		String end = Integer.toString(endNum);

		System.out.println("keyword:"+keyword);
		List<Room> roomList = roomDao.retrieveRoomByTitleForSearch(keyword, start, end);

		if (roomList != null) {
			for (Room rooms : roomList) {
				rooms.setAdmin( userDao.retrieveUser(rooms.getAdmin().getLoginId())); 
				List<RoomEntry> entryList = roomEntryDao.retrieveRoomEntryByRoomNumber(rooms.getRoomNumber());
				rooms.setUsers(entryList);
				System.out.println(rooms.getTitle());
			}
		}
		return roomList;
	}

	@Override
	public void registerRoom(Room room) {
		//
		roomDao.registerRoom(room);

	}

	@Override
	public void modifyRoom(Room room) {
		//
		roomDao.modifyRoom(room);

	}

}
