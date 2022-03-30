package com.game.space.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.space.Exception.GameAlreadyPresentInLikedListException;

import com.game.space.Exception.GameNotInUserLiked;

import com.game.space.Exception.GameNotPresentException;
import com.game.space.Exception.UserNotExistException;
import com.game.space.Model.Game;
import com.game.space.Model.User;

@Service
public class UserLikedGameImpl implements UserLikedGame {
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	GameServiceImpl gameService;

	@Override
	public List<Game> getUserLikedGames(long userId) throws UserNotExistException {
		User newUser=userService.getUser(userId);
		List<Game> userGames=newUser.getUserLikedGames();
		if(userGames==null) {
			userGames= new ArrayList<Game>();
		}
		else {
			userGames=newUser.getUserLikedGames();
		}
		return userGames;
	}

	@Override
	public Game addGameToUserLiked(long gameId, long userId) throws UserNotExistException, GameNotPresentException, GameAlreadyPresentInLikedListException {
		User newUser=userService.getUser(userId);
		List<Game> userGames;
		Game newGame=gameService.getGameByid(gameId);
		userGames=newUser.getUserLikedGames();
		if(userGames!=null) {
			if(userGames.contains(newGame)) {
				throw new GameAlreadyPresentInLikedListException("user already likes this game");
			}
			userGames.add(newGame);
		}
		else {
			userGames= new ArrayList<Game>();
			userGames.add(newGame);
		}
		newUser.setUserLikedGames(userGames);
		return newGame;
	}

	@Override
	public Game deleteFromUserLiked(long gameId, long userId) throws UserNotExistException, GameNotPresentException, GameNotInUserLiked {
		User newUser=userService.getUser(userId);
		List<Game> userGames;
		Game newGame=gameService.getGameByid(gameId);
		userGames=newUser.getUserLikedGames();
		if(userGames!=null) {
			if(userGames.contains(newGame)) {
				userGames.remove(newGame);
			}
			else {
				throw new GameNotInUserLiked("User doesn't like the Game");
			}
		}
		else {
			throw new GameNotInUserLiked("User doesn't like the Game");
		}
		newUser.setUserLikedGames(userGames);
		return newGame;
	}

	@Override
	public Boolean checkIfLiked(long gameId, long userId) throws GameNotPresentException, UserNotExistException {
		User newUser=userService.getUser(userId);
		List<Game> userGames;
		Game newGame=gameService.getGameByid(gameId);
		userGames=newUser.getUserLikedGames();
		boolean returnVal;
		if(userGames==null) {
			returnVal = false;
		}
		else {
			if(userGames.contains(newGame)) {
				returnVal = true;
			}
			else {
				returnVal=false;
			}
		}
		return returnVal;
	}

	
}
