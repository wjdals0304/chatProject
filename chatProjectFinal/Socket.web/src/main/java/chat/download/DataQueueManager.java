package chat.download;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DataQueueManager {

	private static BlockingQueue<Socket> socketQueue;
	private static final int Q_SIZE = 20;
	
	public DataQueueManager() {

		socketQueue = new ArrayBlockingQueue<>(Q_SIZE);
		
	}
	
	public static BlockingQueue<Socket> getSocketQueue() {
		return socketQueue;
	}
	public static void setSocketQueue(BlockingQueue<Socket> socketQueue) {
		DataQueueManager.socketQueue = socketQueue;
	}
}
