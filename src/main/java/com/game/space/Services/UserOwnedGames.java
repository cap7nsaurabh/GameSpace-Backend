package com.game.space.Services;

import java.util.List;

import com.game.space.Exception.GameAlreadyPresentInOwnedListException;
import com.game.space.Exception.GameNotInUserOwned;
import com.game.space.Exception.GameNotPresentException;
import com.game.space.Exception.UserNotExistException;
import com.game.space.Model.Game;

public interface UserOwnedGames {
	public List<Game> getUserOwnedGames(long userId) throws UserNotExistException;
	public Game addGameToUserOwned(long gameId,long userId) throws UserNotExistException, GameNotPresentException, GameAlreadyPresentInOwnedListException;
	public Game deleteFromUserOwned(long gameId,long userId) throws UserNotExistException, GameNotPresentException, GameNotInUserOwned;
	public Boolean checkIfOwns(long gameId,long userId) throws UserNotExistException, GameNotPresentException;
}