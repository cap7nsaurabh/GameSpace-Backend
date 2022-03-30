package com.game.space.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.space.Exception.EmailNotGivenException;
import com.game.space.Exception.IncompleteDataException;
import com.game.space.Exception.UserExistException;
import com.game.space.Exception.UserNotExistException;
import com.game.space.Exception.UsernameNotGivenException;
import com.game.space.Model.User;
import com.game.space.Services.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	ResponseEntity<?> responseEntity;
	@Autowired
	UserServiceImpl userService;
	
	@PostMapping("/saveuser")
	public ResponseEntity<?> registerUser(@RequestBody User user){
		//System.out.print("88888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
		//System.out.print(user.toString());
		HashMap<String,String> respBody=new HashMap<String,String>();
		User letUser;
		try {
			letUser=userService.saveUser(user);
			responseEntity = new ResponseEntity<>(letUser, HttpStatus.CREATED);
		}
		catch(UsernameNotGivenException e) {
			String message=e.getMessage();
			respBody.put("message",message);
			responseEntity = new ResponseEntity<>(respBody, HttpStatus.BAD_REQUEST);
			
		}
		catch(EmailNotGivenException e) {
			String message=e.getMessage();
			respBody.put("message",message);
			responseEntity = new ResponseEntity<>(respBody, HttpStatus.BAD_REQUEST);
		}
		catch(UserExistException e) {
			String message=e.getMessage();
			respBody.put("message",message);
			responseEntity = new ResponseEntity<>(respBody, HttpStatus.CONFLICT);
		} catch (IncompleteDataException e) {
			String message=e.getMessage();
			respBody.put("message",message);
			responseEntity = new ResponseEntity<>(respBody, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	@GetMapping("/getall")
	public ResponseEntity<?>getAllUser(){
		List<User> newList=new ArrayList<User>();
		newList=userService.getAllUsers();
		responseEntity =new  ResponseEntity<>(newList,HttpStatus.ACCEPTED);
		return responseEntity;
	}
	@PutMapping("/updateuser/username/{userId}")
	public ResponseEntity<?> updateUserUsername(@RequestBody User user,@PathVariable String userId) {
		HashMap<String,String> respBody=new HashMap<String,String>();
		User letUser=new User();
		long userID;
		try {
			userID=Integer.parseInt(userId);
			letUser=userService.UpdateUserUsername(user,userID);
			System.out.print(letUser.toString());
			responseEntity = new ResponseEntity<>(letUser, HttpStatus.ACCEPTED);
			System.out.println("456");
		} catch (UserExistException | UsernameNotGivenException | UserNotExistException e) {
			System.out.println("789");
			String message=e.getMessage();
			respBody.put("message",message);
			responseEntity = new ResponseEntity<>(respBody, HttpStatus.BAD_REQUEST);
			System.out.println("012");
		}
		catch(NullPointerException|NumberFormatException e) {
			respBody.put("message","illegal id format");
			responseEntity = new ResponseEntity<>(respBody, HttpStatus.BAD_REQUEST);
		}
		System.out.println("response me dikkat chhe");
		return responseEntity;
	}
	
	

}
