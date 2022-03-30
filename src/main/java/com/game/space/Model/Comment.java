package com.game.space.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Comment {
	 
	
	@Id  @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable=false) 
	private long commentedByUserid;
	private String commentval;
	
	
	public Comment(long commentByUserid, String commentval) {
		super();
		this.commentedByUserid = commentByUserid;
		this.commentval = commentval;
	}
	public Comment() {
		super();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCommentedByUserid() {
		return commentedByUserid;
	}
	public void setCommentedByUserid(long commentBy) {
		this.commentedByUserid = commentBy;
	}
	public String getCommentval() {
		return commentval;
	}
	public void setCommentval(String commentval) {
		this.commentval = commentval;
	}
	
}
