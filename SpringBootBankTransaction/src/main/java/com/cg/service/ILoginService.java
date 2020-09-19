package com.cg.service;

import com.cg.entity.User;
import com.cg.exceptions.LoginException;

public interface ILoginService {
	
	public User doLogin(String userID, String password) throws LoginException;
	public String encryptUser(User user);
	

}
