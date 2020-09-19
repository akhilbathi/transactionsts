package com.cg.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.User;
import com.cg.exceptions.LoginException;
import com.cg.service.ILoginService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})

public class LoginRestController {
	@Autowired
	private ILoginService ser;
	
	Logger logger = LoggerFactory.getLogger(LoginRestController.class);
	
	private Map<String, User> authMap = new HashMap<>();
	
	@PostMapping(value = "/login")
	public String getLogin(@RequestParam("userid")String userID,
			@RequestParam("password") String password) throws LoginException{
		
		logger.info("userID" + userID);
		logger.debug("user id received");
		User user = ser.doLogin(userID, password);
		String token = ser.encryptUser(user);
		authMap.put(token, user);
		return token;
	}
	
	
	
	@GetMapping(value = "/logout")
	public String logout(@RequestHeader("tokenId") String token,HttpServletRequest req) {
		
		authMap.remove(token);
		
		return "logged out";
		
	}
	

	
	

}
