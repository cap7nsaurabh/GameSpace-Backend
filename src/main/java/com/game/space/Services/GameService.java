package com.game.space.Services;

import java.util.List;

import com.game.space.Exception.GameAlreadyPresentException;
import com.game.space.Exception.GameNotPresentException;
import com.game.space.Model.Game;

public interface GameService {
	
	public List<Game> getAllGames();
	public Game addGame(Game game)  throws GameAlreadyPresentException;
	public Game deleteGame(Long id) throws GameNotPresentException;
	public Game updateGame(Game game)throws GameNotPresentException;
	public Game getGameByid(Long id) throws GameNotPresentException;
	public Integer getGameRatingByid(Long id) throws GameNotPresentException;
}
