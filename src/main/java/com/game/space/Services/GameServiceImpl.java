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
		if(game!=null) {
			throw new GameAlreadyPresentException("Game already present");
		}
		else {
			gameRepo.save(game);
		}
		return game;
	}
	@Override
	public Game deleteGame(Long id) throws GameNotPresentException {
		Game game=gameRepo.getById(id);
		if(game==null) {
			throw new GameNotPresentException("Game not present");
		}
		else {
			gameRepo.deleteById(id);
		}
		return game;
	}
	@Override
	public Game updateGame(Game game) throws GameNotPresentException {
		if(gameRepo.findById(game.getId())==null) {
			throw new GameNotPresentException("Game not present");
		}
		else {
			gameRepo.save(game);
		}
		return game;
	}
	@Override
	public Game getGameByid(Long id) throws GameNotPresentException {
		Game game=gameRepo.getById(id);
		if(game==null) {
			throw new GameNotPresentException("game not present");
		}
		return game;
	}
	@Override
	public Integer getGameRatingByid(Long id) throws GameNotPresentException {
		Game game=gameRepo.getById(id);
		if(game==null) {
			throw new GameNotPresentException("game not present");
		}
		return game.getRating();
	}
}
