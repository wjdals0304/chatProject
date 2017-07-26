package chat.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import chat.domain.UserToUser;
import chat.factory.ChatSqlSessionFactory;
import chat.mapper.UserToUserDaoMapper;
@Repository
public class UserToUserDaoLogic implements UserToUserDao {

	@Override
	public List<UserToUser> retrieveUserToUserByFromUser(String fromId) {
		// TODO Auto-generated method stub
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		UserToUserDaoMapper mapper = session.getMapper(UserToUserDaoMapper.class);
		
		List<UserToUser> userToUserList = mapper.retrieveUserToUserByFromUser(fromId);
		session.commit();
		session.close();
		return userToUserList;
	
	}

	@Override
	public List<UserToUser> retrieveUserToUserByToUser(String toId) {
		
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		UserToUserDaoMapper mapper = session.getMapper(UserToUserDaoMapper.class);
	
		List<UserToUser> userToUserList =mapper.retrieveUserToUserByToUser(toId);
		session.commit();
		session.close();
		return userToUserList;
		
	}

	@Override
	public void registerUserToUser(UserToUser userToUser) {
		// TODO Auto-generated method stub
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		UserToUserDaoMapper mapper = session.getMapper(UserToUserDaoMapper.class);
		 mapper.registerUserToUser(userToUser);
		 session.commit();
		 session.close();
	}

	@Override
	public void removeUserToUser(UserToUser userToUser) {
		// TODO Auto-generated method stub
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		UserToUserDaoMapper mapper = session.getMapper(UserToUserDaoMapper.class);
		mapper.removeUserToUser(userToUser);
		session.commit();
		session.close();
	}

}
