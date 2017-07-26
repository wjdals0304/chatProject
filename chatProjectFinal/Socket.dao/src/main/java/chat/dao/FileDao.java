package chat.dao;

import java.util.List;

import chat.domain.FileInfo;

public interface FileDao {

	FileInfo retrieveFile(String fileName);
	
	void registerFile(FileInfo file);
	
	void removeFile(String fileName);
	
	List<FileInfo> retrieveFileAll(String roomNumber);
	
	FileInfo retrieveFileByModifyName(String fileName);
	
	void uploadComplete(String fileName);
	
}
