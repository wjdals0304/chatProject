package chat.mapper;

import java.util.List;

import chat.domain.FileInfo;

public interface FileDaoMapper {

	FileInfo retrieveFile(String fileName);

	FileInfo retrieveFileByModifyName(String fileName);
	
	void registerFile(FileInfo file);

	void removeFile(String fileName);

	List<FileInfo> retrieveFileAll(String roomNumber);

	void uploadComplete(String fileName);
}
