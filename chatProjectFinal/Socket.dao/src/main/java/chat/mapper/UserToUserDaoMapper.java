package chat.mapper;

import java.util.List;

import chat.domain.UserToUser;

public interface UserToUserDaoMapper {

	List<UserToUser> retrieveUserToUserByFromUser(String fromId);
	
	List<UserToUser>retrieveUserToUserByToUser(String toId);
	
	void registerUserToUser(UserToUser userToUser); 
	
	void removeUserToUser(UserToUser userToUser);
	
}
