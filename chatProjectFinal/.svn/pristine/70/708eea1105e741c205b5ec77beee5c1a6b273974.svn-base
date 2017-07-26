package chat.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import chat.download.DataQueueManager;
import chat.download.FileDownloadQueue;
import chat.download.FileInfoClient;
import chat.download.WASDownloadAcceptReceiver;
import chat.download.WASDownloadFileReceiver;
import chat.socket.FileInfoMapUp;
import chat.socket.FileRequestReceiveServer;
import chat.upload.Client;
import chat.upload.FileInfoThread;
import chat.upload.FileUploadQueue;
import chat.upload.QueueManager;

public class ServletListener implements ServletContextListener {

	private static QueueManager queueManager;
	private static FileUploadQueue fileQueueManager;
	private static DataQueueManager dataQueueManager;
	private static FileDownloadQueue fileDownloadQueue;
	private static FileInfoMapUp fileInfoMap;
	//private static RespWithFileInfoMap respWithFileInfoMap;
	//private static RespWithFileInfoMapQueueManager respWithFileInfoMapQueueManager;
	
	private static Client[] clients;
	private static FileInfoClient[] downClients;
	private static WASDownloadFileReceiver[] fileReceivers;
	private static FileInfoThread[] fileThreads;
	
	//private static FileHandling[] fileHandlers;
	
	private static int LIMIT_CLIENT_COUNT = 4;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		queueManager = new QueueManager();
		fileQueueManager = new FileUploadQueue();
		dataQueueManager = new DataQueueManager();
		fileDownloadQueue = new FileDownloadQueue();
		
		//respWithFileInfoMap = new RespWithFileInfoMap();
		//respWithFileInfoMapQueueManager = new RespWithFileInfoMapQueueManager();
		
		fileInfoMap = new FileInfoMapUp();
		clients = new Client[LIMIT_CLIENT_COUNT];
		fileThreads = new FileInfoThread[LIMIT_CLIENT_COUNT];
		downClients = new FileInfoClient[LIMIT_CLIENT_COUNT];
		fileReceivers = new WASDownloadFileReceiver[LIMIT_CLIENT_COUNT];

		//fileHandlers = new FileHandling[LIMIT_CLIENT_COUNT];
		
		// 생성과 동시에 일을 시킨다?
		// if (!isCreated) {
		for (int i = 0; i < LIMIT_CLIENT_COUNT; i++) {
			//upload 용
			clients[i] = new Client();
			clients[i].start();
			fileThreads[i] = new FileInfoThread();
			fileThreads[i].start();
			
			//download 용
			downClients[i] = new FileInfoClient();
			downClients[i].start();
			fileReceivers[i] = new WASDownloadFileReceiver();
			fileReceivers[i].start();
			/*
			fileHandlers[i] = new FileHandling();
			fileHandlers[i].start();*/
			
		}
		// isCreated = true;
		
		//달려라 request server
		//해당서버는 file server로 부터 요청을 받는다.
		//받은 요청은 큐에 등록되며 등록 후에는 다른 thread에 의해서 요청이 처리된다.
		FileRequestReceiveServer frrs = new FileRequestReceiveServer();
		frrs.boot();
		
		//8101 port accept용
		WASDownloadAcceptReceiver wasdar = new WASDownloadAcceptReceiver();
		wasdar.start();
		
	}
	// }
}
