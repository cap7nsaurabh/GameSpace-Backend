package com.game.space.Model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Game {
	@Id @GeneratedValue
	private long id;
	private String name;
	private String logo;
	private String rating;
	
	@ManyToMany(mappedBy="userOwnedGames")
	private List<User> OwnedbyUsers;
	
	@ManyToMany(mappedBy="userLikedGames")
	private List<User> LikedbyUsers;
	
	@ManyToMany(mappedBy="userPlayedGames")
	private List<User> PlayedbyUsers;
	

}
