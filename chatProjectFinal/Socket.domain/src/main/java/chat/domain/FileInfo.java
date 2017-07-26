package chat.domain;

import java.io.Serializable;

public class FileInfo implements Serializable {

	private static final long serialVersionUID = -4619386170878682021L;
	//fileName, uploader
	private String fileName;
	private String uploader;
	private String roomNumber;
	private String realFileName;
	private String downloader;
	private String fileSize;
	//파일의 무결성 체크
	private boolean integrity; 
	
	public boolean isIntegrity() {
		return integrity;
	}
	public void setIntegrity(boolean integrity) {
		this.integrity = integrity;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getDownloader() {
		return downloader;
	}
	public void setDownloader(String downloader) {
		this.downloader = downloader;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getRealFileName() {
		return realFileName;
	}
	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}
	//전송용 to string
	public String fileInfoToString(){
		
		String fileStr = "FileInfo:";
		
		fileStr += fileName+":";
		fileStr += uploader+":";
		fileStr += roomNumber+":";
		fileStr += realFileName+":";
		fileStr += fileSize +":";
		fileStr += downloader+":";
		
		return fileStr;
	}
	
}