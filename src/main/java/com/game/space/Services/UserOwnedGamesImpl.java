package com.game.space.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.space.Exception.GameAlreadyPresentInOwnedListException;
import com.game.space.Exception.GameNotInUserOwned;
import com.game.space.Exception.GameNotPresentException;
import com.game.space.Exception.UserNotExistException;
import com.game.space.Model.Game;
import com.game.space.Model.User;

@Service
public class UserOwnedGamesImpl implements UserOwnedGames {
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	GameServiceImpl gameService;

	@Override
	public List<Game> getUserOwnedGames(long userId) throws UserNotExistException {
		User newUser=userService.getUser(userId);
		List<Game> userGames;
		if(newUser.getUserOwnedGames()==null) {
			userGames= new ArrayList<Game>();
		}
		else {
			userGames=newUser.getUserOwnedGames();
		}
		return userGames;
	}

	@Override
	public Game addGameToUserOwned(Long gameId, long userId) throws UserNotExistException, GameNotPresentException, GameAlreadyPresentInOwnedListException {
		User newUser=userService.getUser(userId);
		List<Game> userGames;
		Game newGame=gameService.getGameByid(gameId);
		userGames=newUser.getUserOwnedGames();
		if(userGames!=null) {
			if(userGames.contains(newGame)) {
				throw new GameAlreadyPresentInOwnedListException("user already owns this game");
			}
			userGames.add(newGame);
		}
		else {
			userGames= new ArrayList<Game>();
			userGames.add(newGame);
		}
		newUser.setUserOwnedGames(userGames);
		return newGame;
	}

	@Override
	public Game deleteFromUserOwned(long gameId, long userId) throws UserNotExistException, GameNotPresentException, GameNotInUserOwned {
		User newUser=userService.getUser(userId);
		List<Game> userGames;
		Game newGame=gameService.getGameByid(gameId);
		userGames=newUser.getUserOwnedGames();
		if(userGames!=null) {
			if(userGames.contains(newGame)) {
				userGames.remove(newGame);
			}
			else {
				throw new GameNotInUserOwned("User doesn't own the Game");
			}
		}
		else {
			throw new GameNotInUserOwned("User doesn't own the Game");
		}
		newUser.setUserOwnedGames(userGames);
		return newGame;
	}

	@Override
	public Boolean checkIfOwns(long gameId, long userId) throws UserNotExistException, GameNotPresentException {
		User newUser=userService.getUser(userId);
		List<Game> userGames;
		Game newGame=gameService.getGameByid(gameId);
		userGames=newUser.getUserOwnedGames();
		boolean returnval;
		if(userGames==null) {
			returnval = false;
		}
		else {
			if(userGames.contains(newGame)) {
				returnval = true;
			}
			else {
				returnval=false;
			}
		}
		return returnval;
	}

}
