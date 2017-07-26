package chat.upload;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import chat.dao.FileDaoLogic;
import chat.domain.FileInfo;

public class Client extends Thread {

	private Socket socket;
	private String path = "c:/java142/datacenter/resources/";
	// private QueueManager queueManager;
	private FileDaoLogic fileDaoLogic;

	public Client() {
		System.out.println("Client Thread 생성");
		// queueManager = new QueueManager();
		fileDaoLogic = new FileDaoLogic();
	}

	public void run() {

		// 여기서 해야할 일이 file로 부터 값을 읽어와서 output stream으로 쏴야댐

		OutputStream out;
		InputStream in;
		DataInputStream din;
		FileInputStream fin;

		try {
			while (true) {
				System.out.println("WebServer side Client Thread 근무 중 이상 무");
				socket = QueueManager.getSocketQueue().take();

				// 가져온 큐에는 was에 로컬에 저장되어있는 파일이 필요하다는 요청이다.
				// 해당 요청은 Worker와 같이 처리 한다.

				System.out.println("Worker <-> Client Connected!");

				in = socket.getInputStream();
				out = socket.getOutputStream();
				din = new DataInputStream(in);

				// buffered in/out

				String fileStr = din.readUTF();

				// 여기서 각 값을 파싱.
				String str[] = fileStr.split(":");

				System.out.println("Web Server Side");
				for (int i = 0; i < str.length; i++) {
					System.out.println(i + " " + str[i]);
				}

				String fileName = str[1];

				byte[] buffer = new byte[8192];

				// 이제 din 닫고
				fin = new FileInputStream(path + fileName);
				//File newFile = new File(path+fileName+"_copy"); 
				//FileOutputStream fout = new FileOutputStream(newFile);

				int len = 0;
				long data = 0;

				while (true) {
					len = fin.read(buffer);
					System.out.println(len + "bytes read");
					if(len == -1) {
						break;
					}
					//fout.write(buffer, 0, len);
					out.write(buffer, 0, len);
					//out.flush();
					data += len;
					System.out.println("현재 전송량 : "+data);
				}

				System.out.println("총 전송량 : " + data);

				fin.close();
				din.close();
				in.close();
				out.close();
				
				fileDaoLogic.uploadComplete(fileName);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * if (args.length == 1) { String name = args[0]; Socket socket = new
	 * Socket("192.168.0.8", 8090); BufferedReader brc = new BufferedReader(new
	 * InputStreamReader(socket.getInputStream()));
	 * 
	 * PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
	 * pw.println(name); BufferedReader brp = new java.io.BufferedReader(new
	 * InputStreamReader(System.in)); while (true) { String readerInput =
	 * brp.readLine(); pw.println(name + ": " + readerInput);
	 * System.out.println(brc.readLine()); } } else {
	 * System.out.println("Usage: Client <name> "); } }
	 */
}