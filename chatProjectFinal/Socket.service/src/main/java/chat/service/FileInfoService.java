package chat.service;

import java.util.List;

import chat.domain.FileInfo;

public interface FileInfoService {

	FileInfo retrieveFile(String fileName);

	void registerFile(FileInfo file);

	void removeFile(String fileName);

	List<FileInfo> retrieveFileAll(String roomNumber);
	
	FileInfo retrieveFileByModifyName(String fileName);

	void uploadComplete(String fileName);
	
}
