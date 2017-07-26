package chat.upload;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import chat.domain.FileInfo;

public class FileInfoThread extends Thread {

	private static String upServerRequestIp = "192.168.0.8";
	private static int upServerRequestPort = 8090;
	private static FileInfo dataFile;

	public void FileInfoThread() {
		System.out.println("WAS server side fileInfo Thread 근무 중 이상 무");
	}

	public void run() {
		// upload용 FileServer에 요청 전송용 메소드
		try {
			while (true) {
				dataFile = FileUploadQueue.getFileInfoQueue().take();

				System.out.println(dataFile.fileInfoToString() + "보내집니까? 그렇습니까?");

				Socket socket = new Socket("192.168.0.8", 8090);
				OutputStream os = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);

				dos.writeUTF(dataFile.fileInfoToString());

				dos.flush();
				dos.close();
				os.close();
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
}

/*
 * System.out.println(upServerRequestIp + "와" + upServerRequestPort +
 * "를 통해서 파일 서버에 업로드 파일 있음 요청을 보냅니다."); String[] strTok = msg.split(":");
 * 
 * // FileInfo dataFile = new FileInfo();
 * 
 * for (int i = 0; i < strTok.length; i++) { System.out.println(i + ":" +
 * strTok[i]); // 2번이 파일명, 4번이 loginId, 6번이 룸넘버인데 (undefined)뜸... }
 * 
 * long startTime = System.currentTimeMillis(); System.out.println(startTime);
 * String loginId = strTok[4]; String roomNumber = strTok[6]; //fileName =
 * roomNumber + "_" + loginId + "_" + startTime + "_" + strTok[2];
 * dataFile.setFileName(fileName); dataFile.setRoomNumber(roomNumber);
 * dataFile.setUploader(loginId); dataFile.setRealFileName(strTok[2]);
 * dataFile.setFileSize(Long.parseLong(strTok[8]));
 * fileDaoLogic.registerFile(dataFile);
 * 
 * File file = new File(path + dataFile.getFileName());
 * System.out.println(dataFile.getFileName() + "은 " +dataFile.getFileSize()+
 * "byte입니다.");
 * 
 * dataFile.setFileSize(file.length());
 */
