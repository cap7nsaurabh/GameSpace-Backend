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

@Entity
public class Comment {
	 
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private	String id;
	@Column(nullable=false) 
	private String commentByUserid;
	private String commentval;
	
	
	public Comment(String commentByUserid, String commentval) {
		super();
		this.commentByUserid = commentByUserid;
		this.commentval = commentval;
	}
	public Comment() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommentByUserid() {
		return commentByUserid;
	}
	public void setCommentBy(String commentBy) {
		this.commentByUserid = commentBy;
	}
	public String getCommentval() {
		return commentval;
	}
	public void setCommentval(String commentval) {
		this.commentval = commentval;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", commentBy=" + commentByUserid + ", commentval=" + commentval + "]";
	}
	
}
