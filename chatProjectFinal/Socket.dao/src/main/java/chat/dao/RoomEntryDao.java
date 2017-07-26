package chat.dao;

import java.util.List;

import chat.domain.Room;
import chat.domain.RoomEntry;

public interface RoomEntryDao {

	List<RoomEntry> retrieveRoomEntryByRoomNumber(String roomNumber);
	
	List<RoomEntry> retrieveRoomEntryByLoginId(String loginId);
	
	void registerRoomEntry(RoomEntry roomEntry);
	
	void removeRoomEntry(RoomEntry roomEntry);
	
	void updateAdmin(Room room);
	
	List<RoomEntry> retrieveAllRoomEntry();
}

