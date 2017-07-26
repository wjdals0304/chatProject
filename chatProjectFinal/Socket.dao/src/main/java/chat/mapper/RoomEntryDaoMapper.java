package chat.mapper;

import java.util.List;

import chat.domain.Room;
import chat.domain.RoomEntry;
import chat.domain.User;

public interface RoomEntryDaoMapper {
	
	List<RoomEntry> retrieveRoomEntryByRoomNumber(String roomNumber);
	
	List<RoomEntry> retrieveRoomEntryByLoginId(String loginId);
	
	void registerRoomEntry(RoomEntry roomEntry);
	
	void removeRoomEntry(RoomEntry roomEntry);

	void updateAdmin(Room room);
	
	List<RoomEntry> retrieveAllRoomEntry();
}
