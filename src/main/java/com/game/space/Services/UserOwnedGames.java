package com.game.space.Services;

import java.util.List;

import com.game.space.Exception.GameAlreadyPresentInOwnedListException;
import com.game.space.Exception.GameNotInUserOwned;
import com.game.space.Exception.GameNotPresentException;
import com.game.space.Exception.UserNotExistException;
import com.game.space.Model.Game;

public interface UserOwnedGames {
	public List<Game> getUserOwnedGames(String userId) throws UserNotExistException;
	public Game addGameToUserOwned(String gameId,String userId) throws UserNotExistException, GameNotPresentException, GameAlreadyPresentInOwnedListException;
	public Game deleteFromUserOwned(String gameId,String userId) throws UserNotExistException, GameNotPresentException, GameNotInUserOwned;
	public Boolean checkIfOwns(String gameId,String userId) throws UserNotExistException, GameNotPresentException;
}