package chat.download;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import chat.domain.FileInfo;

public class FileDownloadQueue {
	private static BlockingQueue<FileInfo> fileInfoQueue;
	private static final int Q_SIZE = 20;

	public FileDownloadQueue() {

		fileInfoQueue = new ArrayBlockingQueue<>(Q_SIZE);
		
	}

	public void currentQueueSize() {
	}

	public static BlockingQueue<FileInfo> getFileInfoQueue() {
		return fileInfoQueue;
	}

	public static void setFileInfoQueue(BlockingQueue<FileInfo> fileInfoQueue) {
		FileDownloadQueue.fileInfoQueue = fileInfoQueue;
	}
}
