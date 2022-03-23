package com.game.space.Services;

import java.util.List;

import com.game.space.Exception.GameAlreadyPresentInLikedListException;

import com.game.space.Exception.GameNotInUserLiked;

import com.game.space.Exception.GameNotPresentException;
import com.game.space.Exception.UserNotExistException;
import com.game.space.Model.Game;

public interface UserLikedGame {
	public List<Game> getUserLikedGames(String userId) throws UserNotExistException;
	public Game addGameToUserLiked(String gameId,String userId) throws UserNotExistException, GameNotPresentException, GameAlreadyPresentInLikedListException;
	public Game deleteFromUserLiked(String gameId,String userId) throws UserNotExistException, GameNotPresentException, GameNotInUserLiked;
	public Boolean checkIfLiked(String gameId,String userId) throws GameNotPresentException, UserNotExistException;

}
