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
import com.game.space.Exception.GameAlreadyPresentInOwnedListException;
import com.game.space.Exception.GameAlreadyPresentInplayedListException;
import com.game.space.Exception.GameNotPresentException;
import com.game.space.Exception.IncompleteDataException;
import com.game.space.Exception.UserExistException;
import com.game.space.Exception.UserNotExistException;
import com.game.space.Exception.UsernameNotGivenException;
import com.game.space.Model.Game;
import com.game.space.Model.User;
import com.game.space.Services.UserOwnedGames;
import com.game.space.Services.UserOwnedGamesImpl;
import com.game.space.Services.UserPlayedGame;
import com.game.space.Services.UserPlayedGameImpl;
import com.game.space.Services.UserService;
import com.game.space.Services.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	ResponseEntity<?> responseEntity;
	@Autowired
	UserService userService;
	
	@Autowired
	UserPlayedGame userPlayedGameService;
	
	@Autowired
	UserOwnedGames userOwnedGameService;
	
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
			//System.out.println("456");
		} catch (UserExistException | UsernameNotGivenException | UserNotExistException e) {
			//System.out.println("789");
			String message=e.getMessage();
			respBody.put("message",message);
			responseEntity = new ResponseEntity<>(respBody, HttpStatus.NOT_FOUND);
			//System.out.println("012");
		}
		catch(NullPointerException|NumberFormatException e) {
			respBody.put("message","illegal id format");
			responseEntity = new ResponseEntity<>(respBody, HttpStatus.BAD_REQUEST);
		}
		System.out.println("response me dikkat chhe");
		return responseEntity;
	}
	@PostMapping("/addto/{userid}/playedgames/{gameId}")
	public ResponseEntity<?> addToPlayedGame(@PathVariable String gameid,@PathVariable String userid){
		HashMap<String,String> respBody=new HashMap<String,String>();
		try {
			long gameId=Integer.parseInt(gameid);
			long userId=Integer.parseInt(userid);
			Game newGame=userPlayedGameService.addGameToUserplayed(gameId, userId);
			responseEntity=new ResponseEntity<>(newGame,HttpStatus.ACCEPTED);
			
		} catch (UserNotExistException | GameNotPresentException | GameAlreadyPresentInplayedListException e) {
			respBody.put("message", e.getMessage());
			responseEntity = new ResponseEntity<>(respBody, HttpStatus.BAD_REQUEST);
			
		}catch(NullPointerException|NumberFormatException e) {
			respBody.put("message", e.getMessage());
			responseEntity = new ResponseEntity<>(respBody, HttpStatus.BAD_REQUEST);	
		}
		return responseEntity;
	}
	@PostMapping("/addto/{userid}/ownedgames/{gameId}")
	public ResponseEntity<?> addToOwnedGame(@PathVariable String gameid,@PathVariable String userid){
		HashMap<String,String> respBody=new HashMap<String,String>();
			try {
				long gameId=Integer.parseInt(gameid);
				long userId=Integer.parseInt(userid);
				Game newGame;
				newGame = userOwnedGameService.addGameToUserOwned(gameId, userId);
				responseEntity=new ResponseEntity<>(newGame,HttpStatus.ACCEPTED);
			} catch (UserNotExistException | GameNotPresentException | GameAlreadyPresentInOwnedListException e) {
				respBody.put("message", e.getMessage());
				responseEntity = new ResponseEntity<>(respBody, HttpStatus.BAD_REQUEST);
			}catch(NullPointerException|NumberFormatException e) {
				respBody.put("message", e.getMessage());
				responseEntity = new ResponseEntity<>(respBody, HttpStatus.BAD_REQUEST);
			}
			return responseEntity;
	}
	
	

}
