package com.game.space.Services;

import java.util.List;

import com.game.space.Model.Game;

public interface GameService {
	
	public List<Game> getAllGames();
	public Game addGame(Game game);
	public Game deleteGame(Long id);
	public Game updateGame(Game game);
	public Game getGameByid(Long id);
	public Game getGameRatingByid(Long id);
}
