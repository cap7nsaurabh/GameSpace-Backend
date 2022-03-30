package com.game.space.Services;

import java.util.List;

import com.game.space.Exception.GameAlreadyPresentInplayedListException;
import com.game.space.Exception.GameNotInUserplayed;
import com.game.space.Exception.GameNotPresentException;
import com.game.space.Exception.UserNotExistException;
import com.game.space.Model.Game;

public interface UserPlayedGame {
	public List<Game> getUserplayedGames(long userId) throws UserNotExistException;
	public Game addGameToUserplayed(long gameId,long userId) throws UserNotExistException, GameNotPresentException, GameAlreadyPresentInplayedListException;
	public Game deleteFromUserplayed(long gameId,long userId) throws UserNotExistException, GameNotPresentException, GameNotInUserplayed;
	public Boolean checkIfplays(long gameId,long userId) throws UserNotExistException, GameNotPresentException;
}
