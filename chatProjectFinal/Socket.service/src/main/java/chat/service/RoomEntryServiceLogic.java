package chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chat.dao.RoomDao;
import chat.dao.RoomEntryDao;
import chat.dao.UserDao;
import chat.domain.Room;
import chat.domain.RoomEntry;
import chat.domain.User;

@Service
public class RoomEntryServiceLogic implements RoomEntryService {

	@Autowired
	private RoomDao roomDao;
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoomEntryDao roomEntryDao;

	@Autowired
	private RoomService roomService;
	
	 
	@Override
	public List<RoomEntry> retrieveRoomEntryByRoomNumber(String roomNumber) {
		//
		List<RoomEntry> roomEntryList = roomEntryDao.retrieveRoomEntryByRoomNumber(roomNumber);

		for (RoomEntry roomEntry : roomEntryList) {

			Room room = roomService.retrieveRoom(roomEntry.getRoom().getRoomNumber());
			User user = userDao.retrieveUser(roomEntry.getUser().getLoginId());

			roomEntry.setRoom(room);
			roomEntry.setUser(user);

		}
		return roomEntryList;
	}

	@Override
	public List<RoomEntry> retrieveRoomEntryByLoginId(String loginId) {
		//

		List<RoomEntry> roomEntryList = roomEntryDao.retrieveRoomEntryByLoginId(loginId);

		for (RoomEntry roomEntry : roomEntryList) {

			Room room = roomService.retrieveRoom(roomEntry.getRoom().getRoomNumber());
			User user = userDao.retrieveUser(roomEntry.getUser().getLoginId());
	
			roomEntry.setRoom(room);
			roomEntry.setUser(user);
		}
		
		return roomEntryList;

	}

	@Override
	public void registerRoomEntry(RoomEntry roomEntry) {
		//
		roomEntryDao.registerRoomEntry(roomEntry);

	}

	@Override
	public void removeRoomEntry(RoomEntry roomEntry) {
		//
		roomEntryDao.removeRoomEntry(roomEntry);

	}

	@Override
	public boolean checkedRoomUserByRoomNumber(String roomNumber, String loginId) {
		//
		List<RoomEntry> roomEntryList = roomEntryDao.retrieveRoomEntryByRoomNumber(roomNumber);

		for (RoomEntry roomEntry : roomEntryList) {
			if (loginId.equals(roomEntry.getUser().getLoginId())) {
				return true;
			}
			
		}

		return false;
	}

	@Override
	public void updateAdmin(Room room) {
		//
		System.out.println("updateadmin");
		roomEntryDao.updateAdmin(room);
		
	}

	@Override
	public List<RoomEntry> retrieveAllRoomEntry() {
		// TODO Auto-generated method stub
		return roomEntryDao.retrieveAllRoomEntry();
	}

}
