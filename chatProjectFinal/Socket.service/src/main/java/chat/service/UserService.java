package chat.service;

import java.util.List;

import chat.domain.User;

public interface UserService {
	
	User retrieveUser(String loginId);
	List<User> retrieveUserAll();
	void registerUser(User user);
	void modifyUser(User user);
	
	
}
