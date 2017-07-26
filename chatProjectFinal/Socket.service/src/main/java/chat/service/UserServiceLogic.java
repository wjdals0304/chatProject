package chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chat.dao.UserDao;
import chat.domain.User;


@Service
public class UserServiceLogic implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User retrieveUser(String loginId) {

		return userDao.retrieveUser(loginId);
	}

	@Override
	public List<User> retrieveUserAll() {

		return userDao.retrieveUserAll();
	}

	@Override
	public void registerUser(User user) {

		userDao.registerUser(user);
		
	}

	@Override
	public void modifyUser(User user) {

		userDao.modifyUser(user);
	}

}
