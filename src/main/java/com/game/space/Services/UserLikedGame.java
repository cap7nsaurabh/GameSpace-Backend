package com.game.space.Services;

import java.util.List;

import com.game.space.Exception.GameAlreadyPresentInLikedListException;

import com.game.space.Exception.GameNotInUserLiked;

import com.game.space.Exception.GameNotPresentException;
import com.game.space.Exception.UserNotExistException;
import com.game.space.Model.Game;

public interface UserLikedGame {
	public List<Game> getUserLikedGames(long userId) throws UserNotExistException;
	public Game addGameToUserLiked(long gameId,long userId) throws UserNotExistException, GameNotPresentException, GameAlreadyPresentInLikedListException;
	public Game deleteFromUserLiked(long gameId,long userId) throws UserNotExistException, GameNotPresentException, GameNotInUserLiked;
	public Boolean checkIfLiked(long gameId,long userId) throws GameNotPresentException, UserNotExistException;

}
