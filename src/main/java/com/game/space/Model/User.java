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
	private String username;
	private String ppic;
	private String fname;
	private String Email;
	private String lname;
	private String phash;
	@ManyToMany
	@JoinTable(name="user_owned_games",
			joinColumns= {@JoinColumn(name="userId")},
			inverseJoinColumns= {@JoinColumn(name="gameId")})
	private List<Game> userOwnedGames;
	
	@JoinTable(name="user_liked_games",
			joinColumns= {@JoinColumn(name="userId")},
			inverseJoinColumns= {@JoinColumn(name="gameId")})
	private List<Game> userLikedGames;
	
	@JoinTable(name="user_played_games",
			joinColumns= {@JoinColumn(name="userId")},
			inverseJoinColumns= {@JoinColumn(name="gameId")})
	private List<Game> userPlayedGames;
	
	
	
	public User(long id, String username, String ppic, String fname, String email, String lname, String phash,
			List<Game> userOwnedGames, List<Game> userLikedGames, List<Game> userPlayedGames) {
		super();
		this.id = id;
		this.username = username;
		this.ppic = ppic;
		this.fname = fname;
		Email = email;
		this.lname = lname;
		this.phash = phash;
		this.userOwnedGames = userOwnedGames;
		this.userLikedGames = userLikedGames;
		this.userPlayedGames = userPlayedGames;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public String getPpic() {
		return ppic;
	}

	public void setPpic(String ppic) {
		this.ppic = ppic;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPhash() {
		return phash;
	}

	public void setPhash(String phash) {
		this.phash = phash;
	}

	public List<Game> getUserOwnedGames() {
		return userOwnedGames;
	}

	public void setUserOwnedGames(List<Game> userOwnedGames) {
		this.userOwnedGames = userOwnedGames;
	}

	public List<Game> getUserLikedGames() {
		return userLikedGames;
	}

	public void setUserLikedGames(List<Game> userLikedGames) {
		this.userLikedGames = userLikedGames;
	}

	public List<Game> getUserPlayedGames() {
		return userPlayedGames;
	}

	public void setUserPlayedGames(List<Game> userPlayedGames) {
		this.userPlayedGames = userPlayedGames;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fname=" + fname + ", Email=" + Email + ", lname="
				+ lname + ", phash=" + phash + ", userOwnedGames=" + userOwnedGames + ", userLikedGames="
				+ userLikedGames + ", userPlayedGames=" + userPlayedGames + "]";
	}
	

	
}
