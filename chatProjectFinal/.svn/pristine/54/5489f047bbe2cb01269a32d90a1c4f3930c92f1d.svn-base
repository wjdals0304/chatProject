package chat.upload;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import chat.domain.FileInfo;

public class FileUploadQueue {
	private static BlockingQueue<FileInfo> fileInfoQueue;
	private static final int Q_SIZE = 20;

	public FileUploadQueue() {

		fileInfoQueue = new ArrayBlockingQueue<>(Q_SIZE);
		
	}

	public void currentQueueSize() {
		System.out.println("현재 FileInfoQueue size... " + fileInfoQueue.size());
	}

	public static BlockingQueue<FileInfo> getFileInfoQueue() {
		return fileInfoQueue;
	}

	public static void setFileInfoQueue(BlockingQueue<FileInfo> fileInfoQueue) {
		FileUploadQueue.fileInfoQueue = fileInfoQueue;
	}
}
