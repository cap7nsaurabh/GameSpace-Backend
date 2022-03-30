package com.game.space.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.space.Exception.GameAlreadyPresentException;
import com.game.space.Exception.GameNotPresentException;
import com.game.space.Model.Game;
import com.game.space.Repository.GameRepo;

@Service
public class GameServiceImpl implements GameService {
	@Autowired
	GameRepo gameRepo;
	
	public List<Game> getAllGames(){
		List<Game> gamesList=new ArrayList();
		gamesList=gameRepo.findAll();
		return gamesList;
	}
	@Override
	public Game addGame(Game game) throws GameAlreadyPresentException {
		Game game1=gameRepo.findByUniquename(game.getUniquename());
		if(game1!=null) {
			throw new GameAlreadyPresentException("Game already present");
		}
		else {
			gameRepo.save(game);
		}
		return game;
	}
	@Override
	public Game deleteGame(long gameId) throws GameNotPresentException {
		Game game=gameRepo.findById(gameId).get();
		if(game==null) {
			throw new GameNotPresentException("Game not present");
		}
		else {
			gameRepo.deleteById(gameId);
		}
		return game;
	}
	@Override
	public Game updateGame(Game game) throws GameNotPresentException {
		if(gameRepo.findById(game.getId()).get()==null) {
			throw new GameNotPresentException("Game not present");
		}
		else {
			gameRepo.save(game);
		}
		return game;
	}
	@Override
	public Game getGameByid(long gameId) throws GameNotPresentException {
		Game game=gameRepo.findById(gameId).get();
		if(game==null) {
			throw new GameNotPresentException("game not present");
		}
		return game;
	}
	@Override
	public Integer getGameRatingByid(long gameId) throws GameNotPresentException {
		Game game=gameRepo.findById(gameId).get();
		if(game==null) {
			throw new GameNotPresentException("game not present");
		}
		return game.getRating();
	}
}
