package chat.service;

import java.util.List;

import chat.domain.Room;
import chat.domain.RoomEntry;

public interface RoomEntryService {

	List<RoomEntry> retrieveRoomEntryByRoomNumber(String roomNumber);

	List<RoomEntry> retrieveRoomEntryByLoginId(String loginId);

	void registerRoomEntry(RoomEntry roomEntry);

	void removeRoomEntry(RoomEntry roomEntry);

	boolean checkedRoomUserByRoomNumber(String roomNumber, String loginId);

	void updateAdmin(Room room);

	List<RoomEntry> retrieveAllRoomEntry();

}
