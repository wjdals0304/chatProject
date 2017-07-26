package chat.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import chat.domain.FileInfo;
import chat.factory.ChatSqlSessionFactory;
import chat.mapper.FileDaoMapper;

@Repository
public class FileDaoLogic implements FileDao {

	@Override
	public FileInfo retrieveFile(String fileName) {
		
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		FileDaoMapper mapper = session.getMapper(FileDaoMapper.class);
		
		FileInfo file = mapper.retrieveFile(fileName);
		
		return file;
	}

	@Override
	public List<FileInfo> retrieveFileAll(String roomNumber) {

		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		FileDaoMapper mapper = session.getMapper(FileDaoMapper.class);
		
		List<FileInfo> files = mapper.retrieveFileAll(roomNumber);
		
		return files;
	}

	@Override
	public void registerFile(FileInfo file) {
		
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		FileDaoMapper mapper = session.getMapper(FileDaoMapper.class);
		
		mapper.registerFile(file);
		session.commit();
		session.close();
		
	}

	@Override
	public void removeFile(String fileName) {
		
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		FileDaoMapper mapper = session.getMapper(FileDaoMapper.class);
		
		mapper.removeFile(fileName);
		session.commit();
		session.close();
		
	}

	@Override
	public FileInfo retrieveFileByModifyName(String fileName) {
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		FileDaoMapper mapper = session.getMapper(FileDaoMapper.class);
		
		FileInfo fileInfo = mapper.retrieveFileByModifyName(fileName);
		
		return fileInfo;
	}

	@Override
	public void uploadComplete(String fileName) {
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		FileDaoMapper mapper = session.getMapper(FileDaoMapper.class);
		
		mapper.uploadComplete(fileName);
		session.commit();
		session.close();
	}

}
