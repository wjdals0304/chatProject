package chat.domain;

public class UserToUser {
	
	private User fromUser;
	private User toUser;
	
	public User getFromUser() {
		return fromUser;
	}
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	public User getToUser() {
		return toUser;
	}
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	@Override
	public String toString() {
		return "UserToUser [fromUser=" + fromUser + ", toUser=" + toUser + "]";
	}
	
	
}
