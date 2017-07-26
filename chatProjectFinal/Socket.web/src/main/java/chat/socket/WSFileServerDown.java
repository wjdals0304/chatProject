package chat.socket;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import chat.dao.UserDaoLogic;
import chat.domain.FileInfo;
import chat.domain.User;
import chat.download.FileDownloadQueue;

@ServerEndpoint("/download")
public class WSFileServerDown {

	private BufferedInputStream bis;
	private String path = "C:/java142/datacenter/download/";
	private FileInfo fileInfo;
	private UserDaoLogic userDaoLogic;

	public WSFileServerDown() {
		userDaoLogic = new UserDaoLogic();
	}

	@OnMessage
	public void onMessage(Session session, String msg) throws IOException, InterruptedException {

		String[] strTok = msg.split(":");
		// 쪼개서 Message 스타일로 변환
		for (int i = 0; i < strTok.length; i++) {
			System.out.println(i + ":" + strTok[i]);
		}
		// Message 형태를 정의해보면
		// FDW:fileName:값:loginId:값:roomNumber:값:이런식으로 요청이 들어온다.
		if ("FDW".equals(strTok[0])) {
			
			String fileName = strTok[2];

			User user = userDaoLogic.retrieveUser(strTok[4]);

			// 여기서 객체를 만들고 put을 하고 thread가 일을 하도록 지시
			fileInfo = new FileInfo();
			fileInfo.setRealFileName(fileName);
			fileInfo.setRoomNumber(strTok[6]);
			fileInfo.setDownloader(strTok[4]);
			FileDownloadQueue.getFileInfoQueue().put(fileInfo);
		}

		else if (!"end".equals(msg)) {
			
			String fileName = strTok[1];
			
			// 파일 객체 생성
			File file = new File(path + fileName);

			System.out.println("filePath" + file.getPath());
			// 파일을 담을 바이트 배열
			byte[] fileBytes = new byte[(int) file.length()];

			try {
				// 파일로 연결된 스트림 생성
				bis = new BufferedInputStream(new FileInputStream(file));

				// 바이트 배열에 파일 저장
				bis.read(fileBytes);

			} catch (IOException e) {
				e.printStackTrace();
			}

			// ByteBuffer에 바이트 배열을 담는다
			ByteBuffer buf = ByteBuffer.wrap(fileBytes);

			// ByteBuffer 를 클라이언트로 보낸다.
			session.getBasicRemote().sendBinary(buf);
		} else {
			bis.close();
			session.close();
		}
	}

	@OnOpen
	public void open(Session session) {
		System.out.println("WebSocket File Server Open......");
	}

	@OnError
	public void error(Session session, Throwable t) {
		System.out.println("error.......");
	}

	@OnClose
	public void closedConnection(Session session) {
		System.out.println("연결종료........");
	}

}
