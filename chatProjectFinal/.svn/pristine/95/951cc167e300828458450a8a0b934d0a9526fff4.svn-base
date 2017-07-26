package chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chat.dao.UserDao;
import chat.dao.UserToUserDao;
import chat.domain.User;
import chat.domain.UserToUser;

@Service
public class UserToUserServiceLogic implements UserToUserService {

	@Autowired
	private UserToUserDao userToUserDao;

	@Autowired
	private UserDao userDao;

	@Override
	public List<UserToUser> retrieveUserToUserByFromUser(String fromId) {
		// TODO Auto-generated method stub
		List<UserToUser> userToUserList = userToUserDao.retrieveUserToUserByFromUser(fromId);
		
		for (UserToUser userToUser : userToUserList) {
			User userFrom = userDao.retrieveUser(userToUser.getFromUser().getLoginId());
			User userTo = userDao.retrieveUser(userToUser.getToUser().getLoginId());
			userToUser.setFromUser(userFrom);
			userToUser.setToUser(userTo);
		}
		return userToUserList;
	}

	@Override
	public List<UserToUser> retrieveUserToUserByToUser(String toId) {
		// TODO Auto-generated method stub
		List<UserToUser> userToUserList = userToUserDao.retrieveUserToUserByToUser(toId);
		
		for (UserToUser userToUser : userToUserList) {
			User userFrom = userDao.retrieveUser(userToUser.getFromUser().getLoginId());
			User userTo = userDao.retrieveUser(userToUser.getToUser().getLoginId());
			userToUser.setFromUser(userFrom);
			userToUser.setToUser(userTo);
		}
		return userToUserList;
	}

	@Override
	public void registerUserToUser(UserToUser userToUser) {
		// TODO Auto-generated method stub
		userToUserDao.registerUserToUser(userToUser);
	}

	@Override
	public void removeUserToUser(UserToUser userToUser) {
		// TODO Auto-generated method stub
		userToUserDao.removeUserToUser(userToUser);
	}

}
