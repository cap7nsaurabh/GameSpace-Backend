package com.game.space.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.*;

@Entity
public class User {
	@Id @GeneratedValue
	private long id;
	private String fname;
	private String Email;
	private String lname;
	private String password;
	@ManyToMany
	@JoinTable(name="user_owned_games",
			joinColumns= {@JoinColumn(name="user_id")},
			inverseJoinColumns= {@JoinColumn(name="game_id")})
	private List<Game> userOwnedGames;
	
	@JoinTable(name="user_liked_games",
			joinColumns= {@JoinColumn(name="user_id")},
			inverseJoinColumns= {@JoinColumn(name="game_id")})
	private List<Game> userLikedGames;
	
	@JoinTable(name="user_played_games",
			joinColumns= {@JoinColumn(name="user_id")},
			inverseJoinColumns= {@JoinColumn(name="game_id")})
	private List<Game> userPlayedGames;
	

}
