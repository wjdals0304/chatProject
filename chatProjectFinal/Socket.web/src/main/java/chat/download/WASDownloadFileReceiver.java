package chat.download;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import chat.domain.FileInfo;

public class WASDownloadFileReceiver extends Thread {

	private Socket socket;
	private FileInfo fileInfo;
	private String path = "c:/java142/datacenter/resources/";
	
	@Override
	public void run() {
		
		try{

			while(true){
				
				socket = DataQueueManager.getSocketQueue().take();
				
				InputStream in = socket.getInputStream();
				DataInputStream din = new DataInputStream(in);
				
				String fileStr = din.readUTF();
				
				String strTok[] = fileStr.split(":");
				
				fileInfo = new FileInfo();
				fileInfo.setFileName(strTok[1]);
				fileInfo.setUploader(strTok[2]);
				fileInfo.setRoomNumber(strTok[3]);
				fileInfo.setRealFileName(strTok[4]);
				fileInfo.setFileSize(strTok[5]);
				fileInfo.setDownloader(strTok[6]);
				
				
				BufferedInputStream bin = new BufferedInputStream(in);
				FileOutputStream fout 
					= new FileOutputStream(new File(path+fileInfo.getFileName()));
				
				int len = 0;
				byte[] buffer = new byte[8192];
				int data = 0;
				
				while((len = bin.read(buffer))>0){
					fout.write(buffer, 0, len);
					fout.flush();
					data += len;
				}
				
				fout.close();
				bin.close();
				din.close();
				in.close();
			}
			
		}catch(InterruptedException | IOException e){
			e.printStackTrace();
		}
	}
}
