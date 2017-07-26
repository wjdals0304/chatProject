package chat.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import chat.domain.Category;
import chat.domain.User;
import chat.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public Boolean login(String loginId, String password, HttpSession session){
		
		User user = userService.retrieveUser(loginId);
		
		if(user == null){
			return false;
		}else{
			if(password.equals(user.getPassword())){
				session.setAttribute("loginUser", user);	
				return true;	
			} 
			return false;
		}
		
	}
	
	@RequestMapping("/logout.do")
	public String logout(String loginId, HttpSession session){
		
		session.invalidate();

		return "redirect:/views/login.jsp";
		
		
	}
	
	@RequestMapping("/registerUser.do")
	public String register(String loginId, String password
			, String category
			, String name, String nickName, String age
			, String address, String email){
		
		User user = new User();
		user.setAddress(address);
		user.setAge(Integer.parseInt(age));
		user.setCategory(Category.getByCode(category));
		user.setEmail(email);
		user.setLoginId(loginId);
		user.setNickName(nickName);
		user.setName(name);
		user.setPassword(password);
		userService.registerUser(user);
		
		return "redirect:/views/login.jsp";
		
	}
	
	@RequestMapping("/idCheck.do")
	@ResponseBody
	public boolean idCheck(String loginId){
		User user = userService.retrieveUser(loginId);
		if(user == null){
			return true;
		}
		
		return false;
		
	}
	
	@RequestMapping("/nickNameCheck.do")
	@ResponseBody
	public boolean nickNameCheck(String nickName, String loginId){
		
		List<User> users = userService.retrieveUserAll();
		
		if(loginId == null){
			
			for (User user : users) {
				if(nickName.equals(user.getNickName())){
					return false;
				}
			}
		} else{ 
			for (User user : users) {
				if(!loginId.equals(user.getLoginId())){
					if(nickName.equals(user.getNickName())){
						return false;
					}
				}
			}
		}
		
		return true;
		
	}
	
	@RequestMapping("/modify.do")
	public String modifyUser(String userId, String password, String category
									, String name, String nickName, String age, String loginId
									, String address, String email, HttpSession session){
		
		User user = new User();
		user.setAddress(address);
		user.setAge(Integer.parseInt(age));
		user.setCategory(Category.getByCode(category));
		user.setEmail(email);
		user.setNickName(nickName);
		user.setName(name);
		user.setPassword(password);
		user.setUserId(userId);
		user.setLoginId(loginId);

		userService.modifyUser(user);
		
		User user1 = userService.retrieveUser(loginId);
		
		session.setAttribute("loginUser", user1);
		return "redirect:/views/mypage.jsp";
	}

}
