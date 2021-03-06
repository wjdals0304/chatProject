package chat.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import chat.domain.User;
import chat.factory.ChatSqlSessionFactory;
import chat.mapper.UserDaoMapper;

@Repository
public class UserDaoLogic implements UserDao {

	@Override
	public User retrieveUser(String loginId) {
		
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		UserDaoMapper mapper = session.getMapper(UserDaoMapper.class);
		
		User user = mapper.retrieveUser(loginId);
		
		session.close();
		
		return user;

	}

	@Override
	public List<User> retrieveUserAll() {
		
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		UserDaoMapper mapper = session.getMapper(UserDaoMapper.class);
		
		List<User> users = mapper.retrieveUserAll();
		
		session.close();
		
		return users;
	}

	@Override
	public void registerUser(User user) {
		
		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		UserDaoMapper mapper = session.getMapper(UserDaoMapper.class);
		
		mapper.registerUser(user);
		
		session.commit();
		session.close();
		
	}

	@Override
	public void modifyUser(User user) {

		SqlSession session = ChatSqlSessionFactory.createInstance().createSqlSession();
		UserDaoMapper mapper = session.getMapper(UserDaoMapper.class);
		
		mapper.modifyUser(user);
		
		session.commit();
		session.close();
		
	}


}
