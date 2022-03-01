package com.game.space.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id @GeneratedValue
	private long id;
	private String fname;
	private String Email;
	private String lname;
	private String password;
	
	
	

}
