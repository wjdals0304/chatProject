package chat.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import chat.upload.QueueManager;


public class FileRequestThread extends Thread {

	private Socket socket;

	public FileRequestThread() {
		}

	public void run() {


		ServerSocket upServer;
		try {
			upServer = new ServerSocket(8091);
			// 서버 열고, 대기 중인데 Accept는 지속적으로 진행
			while (true) {

				Socket client = upServer.accept();
				// 소켓만 넣어 놓고 이제 소켓 넣어놓은 Queue를 Client가 참조한다.
				QueueManager.getSocketQueue().put(client);

			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
