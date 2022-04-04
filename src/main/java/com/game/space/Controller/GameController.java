package com.game.space.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.space.Exception.GameAlreadyPresentException;
import com.game.space.Exception.GameNotPresentException;
import com.game.space.Model.Game;
import com.game.space.Services.GameService;
import com.game.space.Services.GameServiceImpl;

@RestController
@RequestMapping("/game")
public class GameController {
	@Autowired
	GameService gameService;
	
	ResponseEntity<?> responseEntity;
	
	@PostMapping("/save_game")
	public ResponseEntity<?>addGame(@RequestBody Game game){
		HashMap<String,String> respBody=new HashMap<String,String>();
		Game savedGame;
		try {
			savedGame=gameService.addGame(game);
			responseEntity=new ResponseEntity<>(game,HttpStatus.CREATED);
		} catch (GameAlreadyPresentException e) {
			respBody.put("message",e.getMessage());
			responseEntity=new ResponseEntity<>(respBody,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	@GetMapping("/get_all_games")
	public ResponseEntity<?> getAllGames(){
		HashMap<String,String> respBody=new HashMap<String,String>();
		List<Game> gameList;
		gameList=gameService.getAllGames();
		responseEntity=new ResponseEntity<>(gameList,HttpStatus.ACCEPTED);
		return responseEntity;
	}
	@DeleteMapping("/delete_game/{gameid}")
	public ResponseEntity<?> deleteGame(@PathVariable String gameid) {
		long newGameId=-1;
		HashMap<String,String> respBody=new HashMap<String,String>();
		newGameId=Integer.parseInt(gameid);
		try {
			Game game=gameService.deleteGame(newGameId);
			responseEntity=new ResponseEntity<>(game,HttpStatus.ACCEPTED);
		} 
		catch (GameNotPresentException e) {
			respBody.put("message", e.getMessage());
			responseEntity=new ResponseEntity<>(respBody,HttpStatus.NOT_FOUND);
		}
		catch(NullPointerException|NumberFormatException e) {
			respBody.put("message", e.getMessage());
			responseEntity=new ResponseEntity<>(respBody,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	

}
