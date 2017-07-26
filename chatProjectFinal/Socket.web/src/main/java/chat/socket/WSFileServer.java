package chat.socket;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import chat.dao.FileDaoLogic;
import chat.domain.FileInfo;
import chat.upload.FileUploadQueue;

@ServerEndpoint("/upload")
public class WSFileServer {

	private BufferedOutputStream bos;
	private String path = "C:/java142/datacenter/resources/";
	private FileInfo dataFile;
	private FileDaoLogic fileDaoLogic;
	private static String fileName;

	private static String upServerRequestIp = "192.168.0.78";
	private static int upServerRequestPort = 8090;

	public WSFileServer() {
		//dataFile = new FileInfo();
		fileDaoLogic = new FileDaoLogic();

	}

	@OnMessage
	public void onMessage(Session session, String msg) throws UnknownHostException, IOException, InterruptedException {

		// 우선 여기서 session에 userproperties에 값이 loginId로 저장되어 있어야 진행이 됨.
		String[] strTok = msg.split(":");

		// FileInfo dataFile = new FileInfo();

		if ("FUP".equals(strTok[0])) {
			
			long startTime = System.currentTimeMillis();
			String loginId = strTok[4];
			String roomNumber = strTok[6];
			fileName = roomNumber + "_" + loginId + "_" + startTime + "_" + strTok[2];
			FileInfo dataFile = new FileInfo();
			dataFile.setFileName(fileName);
			dataFile.setRealFileName(strTok[2]);
			dataFile.setUploader(strTok[4]);
			dataFile.setRoomNumber(roomNumber);
			dataFile.setFileSize(strTok[8]);
			dataFile.setIntegrity(false);
			FileInfoMapUp.getFileMap().put(strTok[2], dataFile);
			fileDaoLogic.registerFile(dataFile);

		}

		if (!strTok[0].equals("end")) {
			
			File file = new File(path + fileName);
			try {
				bos = new BufferedOutputStream(new FileOutputStream(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else {

			//꺼내고,
			dataFile = FileInfoMapUp.getFileMap().get(strTok[2]);
			//꺼낸값 지우고
			FileInfoMapUp.getFileMap().remove(strTok[2]);
			
			// 만약 파일 업로드 확정이라면 바로 파일 서버로 쏜다.
			FileUploadQueue.getFileInfoQueue().put(dataFile);
			
			try {
				bos.flush();
				bos.close();
				session.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	@OnMessage
	public void processUpload(ByteBuffer msg, boolean last, Session session) {
		while (msg.hasRemaining()) {
			try {
				bos.write(msg.get());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@OnOpen
	public void open(Session session) {
		System.out.println("file 소켓 연결 ");
	}

	@OnClose
	public void closed(Session session) {
		System.out.println("file 연결 종료 ");
	}

}
