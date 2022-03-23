package com.game.space.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.space.Exception.IncompleteDataException;
import com.game.space.Exception.UserNotExistException;
import com.game.space.Exception.UserPassNotMatchException;
import com.game.space.Model.User;

import com.game.space.Services.UserServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	ResponseEntity<?> responseEntity;
	User user;
	@Autowired
	UserServiceImpl userService;
	
	@PostMapping("/login")
	ResponseEntity<?> loginUser(@RequestBody User user ){
		HashMap<String,String> map=new HashMap<String,String>();
		String email,username,passh;
		passh=user.getPhash();
		if(passh==null) {
			String message="Please provide password";
			map.put("message", message);
			responseEntity=new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}
		else if(user.getUsername()==null) {
			String message="Please provide username or email";
			map.put("message", message);
			responseEntity=new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}
		else {
			username=user.getUsername();
			try {
				user=userService.getUserByUsernameandPasshash(username, passh);
				responseEntity=new ResponseEntity<>(user,HttpStatus.OK);
			}
			catch(UserNotExistException e) {
				String message=e.getMessage();
				map.put("message", message);
				responseEntity=new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
				
			} catch (UserPassNotMatchException e) {
				String message=e.getMessage();
				map.put("message", message);
				responseEntity=new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
				e.printStackTrace();
			}
			catch(IncompleteDataException e) {
				String message=e.getMessage();
				map.put("message", message);
				responseEntity=new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
				e.printStackTrace();
			}
		}
		return responseEntity;
	}
	

}
