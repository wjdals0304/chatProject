package chat.domain;

public class Message {
	
	private String fileName;
	private String to;
	private String from;
	private String contents;
	private String roomNumber;
	private String type;
	private String nickName;
	private String date;
	private int messageNumber;
	
	public int getMessageNumber(){
		return messageNumber;
		
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setMessageNumber(int messageNumber){
		this.messageNumber=messageNumber;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "Message [to=" + to + ", from=" + from + ", contents=" + contents + ", roomNumber=" + roomNumber
				+ ", type=" + type + ", nickName=" + nickName + ", date=" + date + "]";
	}
	
	
	
}
