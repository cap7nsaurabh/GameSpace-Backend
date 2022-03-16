package com.game.space.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.game.space.Exception.GameAlreadyPresentInplayedListException;
import com.game.space.Exception.GameAlreadyPresentInplayedListException;

import com.game.space.Exception.GameNotInUserplayed;
import com.game.space.Exception.GameNotInUserplayed;
import com.game.space.Exception.GameNotPresentException;
import com.game.space.Exception.UserNotExistException;
import com.game.space.Model.Game;
import com.game.space.Model.User;

@Service
public class UserPlayedGameImpl implements UserPlayedGame {
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	GameServiceImpl gameService;

	@Override
	public List<Game> getUserplayedGames(long userId) throws UserNotExistException {
		User newUser=userService.getUser(userId);
		List<Game> userGames;
		if(newUser.getUserPlayedGames()==null) {
			userGames= new ArrayList<Game>();
		}
		else {
			userGames=newUser.getUserPlayedGames();
		}
		return userGames;
	}

	@Override
	public Game addGameToUserplayed(Long gameId, long userId) throws UserNotExistException, GameNotPresentException, GameAlreadyPresentInplayedListException {
		User newUser=userService.getUser(userId);
		List<Game> userGames;
		Game newGame=gameService.getGameByid(gameId);
		userGames=newUser.getUserPlayedGames();
		if(userGames!=null) {
			if(userGames.contains(newGame)) {
				throw new GameAlreadyPresentInplayedListException("user already plays this game");
			}
			userGames.add(newGame);
		}
		else {
			userGames= new ArrayList<Game>();
			userGames.add(newGame);
		}
		newUser.setUserPlayedGames(userGames);
		return newGame;
	}

	@Override
	public Game deleteFromUserplayed(long gameId, long userId) throws UserNotExistException, GameNotPresentException, GameNotInUserplayed {
		User newUser=userService.getUser(userId);
		List<Game> userGames;
		Game newGame=gameService.getGameByid(gameId);
		userGames=newUser.getUserPlayedGames();
		if(userGames!=null) {
			if(userGames.contains(newGame)) {
				userGames.remove(newGame);
			}
			else {
				throw new GameNotInUserplayed("User doesn't play the Game");
			}
		}
		else {
			throw new GameNotInUserplayed("User doesn't play the Game");
		}
		newUser.setUserPlayedGames(userGames);
		return newGame;
	}

	@Override
	public Boolean checkIfplays(long gameId, long userId) throws UserNotExistException, GameNotPresentException {
		User newUser=userService.getUser(userId);
		List<Game> userGames;
		Game newGame=gameService.getGameByid(gameId);
		userGames=newUser.getUserPlayedGames();
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
