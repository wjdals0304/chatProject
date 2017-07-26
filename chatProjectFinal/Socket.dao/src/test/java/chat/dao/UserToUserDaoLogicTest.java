package chat.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import chat.domain.User;
import chat.domain.UserToUser;

public class UserToUserDaoLogicTest {

	@Test
	public void retrieveUserToUserByFromUser() {
		UserToUserDao dao = new UserToUserDaoLogic();

		List<UserToUser> userToUserList = dao.retrieveUserToUserByFromUser("wjdals");
		assertEquals(2, userToUserList.size());
	}

	@Test
	public void retrieveUserToUserByToUser() {
		UserToUserDao dao = new UserToUserDaoLogic();
		List<UserToUser> userToUserList = dao.retrieveUserToUserByToUser("son");
		assertEquals(2, userToUserList.size());
	}

	@Test
	public void registerUserToUser() {
		UserToUserDao dao = new UserToUserDaoLogic();

		UserToUser userToUser = new UserToUser();
		User fromUser = new User();
		User toUser = new User();
		fromUser.setLoginId("son");
		toUser.setLoginId("wjdals");

		userToUser.setFromUser(fromUser);
		userToUser.setToUser(toUser);

		dao.registerUserToUser(userToUser);
	}

	@Test
	public void remove() {

		UserToUserDao dao = new UserToUserDaoLogic();

		UserToUser userToUser = new UserToUser();
		User fromUser = new User();
		User toUser = new User();
		fromUser.setLoginId("son");
		toUser.setLoginId("wjdals");

		userToUser.setFromUser(fromUser);
		userToUser.setToUser(toUser);

		dao.removeUserToUser(userToUser);
	}

}
