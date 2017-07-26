package chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chat.dao.FileDaoLogic;
import chat.domain.FileInfo;

@Service
public class FileInfoServiceLogic implements FileInfoService {

	@Autowired
	private FileDaoLogic fileDaoLogic;
	
	@Override
	public FileInfo retrieveFile(String fileName) {
		return fileDaoLogic.retrieveFile(fileName);
	}

	@Override
	public void registerFile(FileInfo file) {
		fileDaoLogic.registerFile(file);
	}

	@Override
	public void removeFile(String fileName) {
		fileDaoLogic.removeFile(fileName);
	}

	@Override
	public List<FileInfo> retrieveFileAll(String roomNumber) {
		return fileDaoLogic.retrieveFileAll(roomNumber);
	}

	@Override
	public FileInfo retrieveFileByModifyName(String fileName) {
		return fileDaoLogic.retrieveFileByModifyName(fileName);
	}

	@Override
	public void uploadComplete(String fileName) {
		fileDaoLogic.uploadComplete(fileName);
	}

}
