package chat.download;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import chat.dao.FileDaoLogic;
import chat.domain.FileInfo;

public class FileInfoClient extends Thread {

	private FileDaoLogic fileDaoLogic;
	private FileInfo fileInfo;
	private Socket socket;
	
	public FileInfoClient(){
		fileDaoLogic = new FileDaoLogic();
	}
	
	public void run() {
		
		try{
			while(true){
				
				FileInfo tempInfo
					= FileDownloadQueue.getFileInfoQueue().take();
				
				
				fileInfo
					= fileDaoLogic.retrieveFileByModifyName(tempInfo.getFileName());
				
				socket = new Socket("192.168.0.8", 8100);
				//밑에서 해당 정보를 요청으로 쏜다.
				OutputStream out = socket.getOutputStream();
				DataOutputStream dout = new DataOutputStream(out);
				//요 스트림으로 UTF로 딱 파일인포만 보낸당
				
				dout.writeUTF(fileInfo.fileInfoToString());
				
				dout.close();
				out.close();
				socket.close();
				
			}
			
		}catch(InterruptedException | IOException e){
			e.printStackTrace();
		}
		
	}
}
