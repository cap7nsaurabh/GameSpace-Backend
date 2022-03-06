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
	
	

	public Game(long id, String name, String logo, String rating, List<User> ownedbyUsers, List<User> likedbyUsers,
			List<User> playedbyUsers, List<Comment> gameComment) {
		super();
		this.id = id;
		this.name = name;
		this.logo = logo;
		this.rating = rating;
		this.ownedbyUsers = ownedbyUsers;
		this.likedbyUsers = likedbyUsers;
		this.playedbyUsers = playedbyUsers;
		this.gameComment = gameComment;
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

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
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
