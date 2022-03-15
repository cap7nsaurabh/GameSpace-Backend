package com.game.space.Model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class User {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable=false,unique=true) 
	private String username;
	private String ppic;
	private String fname;
	@Column(nullable=false,unique=true) 
	private String email;
	private String lname;
	@Column(nullable=false,unique=true) 
	private String phash;
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="user_owned_games",
			joinColumns= {@JoinColumn(name="userId")},
			inverseJoinColumns= {@JoinColumn(name="gameId")})
	private List<Game> userOwnedGames;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="user_liked_games",
			joinColumns= {@JoinColumn(name="userId")},
			inverseJoinColumns= {@JoinColumn(name="gameId")})
	private List<Game> userLikedGames;
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="user_played_games",
			joinColumns= {@JoinColumn(name="userId")},
			inverseJoinColumns= {@JoinColumn(name="gameId")})
	private List<Game> userPlayedGames;
	
	public User() {
		super();
	}
	public User(String username, String ppic, String fname, String email, String lname, String phash) {
		super();
		this.username = username;
		this.ppic = ppic;
		this.fname = fname;
		this.email = email;
		this.lname = lname;
		this.phash = phash;
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
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return "User [username=" + username + ", ppic=" + ppic + ", fname=" + fname + ", email=" + email + ", lname="
				+ lname + ", phash=" + phash + "]";
	}


	

	
}
