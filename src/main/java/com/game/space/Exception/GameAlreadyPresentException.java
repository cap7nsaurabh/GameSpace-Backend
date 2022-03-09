package com.game.space.Exception;

import net.bytebuddy.implementation.bind.annotation.Super;

public class GameAlreadyPresentException extends Exception {
	
	public GameAlreadyPresentException(String message){
		super(message);
	}

}
