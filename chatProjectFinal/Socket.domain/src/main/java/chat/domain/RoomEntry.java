package chat.domain;

public class RoomEntry {
	
	private Room room;
	private User user;
	
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "RoomEntry [room=" + room + ", user=" + user + "]";
	}
	
	
	
}
