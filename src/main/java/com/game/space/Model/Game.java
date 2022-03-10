package com.game.space.Model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Game {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable=false,unique=true) 
	private String Uniquename;
	private String name;
	private String logo;
	private Integer rating;
	
	@ManyToMany(mappedBy="userOwnedGames")
	private List<User> ownedbyUsers;
	
	@ManyToMany(mappedBy="userLikedGames")
	private List<User> likedbyUsers;
	
	@ManyToMany(mappedBy="userPlayedGames")
	private List<User> playedbyUsers;
	
	@ManyToMany
	@JoinTable(name="Game_Comments",
			joinColumns= {@JoinColumn(name="gameId")},
			inverseJoinColumns= {@JoinColumn(name="commentID")})
	private List<Comment> gameComment;
	
	

	public Game(String uniquename, String name, String logo) {
		super();
		Uniquename = uniquename;
		this.name = name;
		this.logo = logo;
	}
	
	public String getUniquename() {
		return Uniquename;
	}

	public void setUniquename(String uniquename) {
		Uniquename = uniquename;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public List<User> getOwnedbyUsers() {
		return ownedbyUsers;
	}

	public void setOwnedbyUsers(List<User> ownedbyUsers) {
		this.ownedbyUsers = ownedbyUsers;
	}

	public List<User> getLikedbyUsers() {
		return likedbyUsers;
	}

	public void setLikedbyUsers(List<User> likedbyUsers) {
		this.likedbyUsers = likedbyUsers;
	}

	public List<User> getPlayedbyUsers() {
		return playedbyUsers;
	}

	public void setPlayedbyUsers(List<User> playedbyUsers) {
		this.playedbyUsers = playedbyUsers;
	}

	public List<Comment> getGameComment() {
		return gameComment;
	}

	public void setGameComment(List<Comment> gameComment) {
		this.gameComment = gameComment;
	}

}
