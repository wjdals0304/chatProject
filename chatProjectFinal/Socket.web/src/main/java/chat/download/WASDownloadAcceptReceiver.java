package chat.download;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WASDownloadAcceptReceiver extends Thread {

	private ServerSocket serverSocket;
	private Socket socket;
	
	@Override
	public void run() {

		//이 아이는 accept만 해주는 아이입니다. 포트는 8101입니다.
		try{

			serverSocket = new ServerSocket(8101);
			
			while(true){
				
				socket = serverSocket.accept();
				
				DataQueueManager.getSocketQueue().put(socket);
				
			}
			
		}catch(IOException | InterruptedException e){
			e.printStackTrace();
		}
	}
}
