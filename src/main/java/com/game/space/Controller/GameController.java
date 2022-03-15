package com.game.space.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.game.space.Services.GameServiceImpl;

@RestController
public class GameController {
	@Autowired
	GameServiceImpl gameService;
	

}
