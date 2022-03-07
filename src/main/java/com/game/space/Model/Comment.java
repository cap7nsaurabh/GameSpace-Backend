package com.game.space.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Comment {
	@Id @GeneratedValue
	private	Long id;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private User commentBy;
	private String commentval;
	
	
	public Comment(Long id, User commentBy, String commentval) {
		super();
		this.id = id;
		this.commentBy = commentBy;
		this.commentval = commentval;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getCommentBy() {
		return commentBy;
	}
	public void setCommentBy(User commentBy) {
		this.commentBy = commentBy;
	}
	public String getCommentval() {
		return commentval;
	}
	public void setCommentval(String commentval) {
		this.commentval = commentval;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", commentBy=" + commentBy + ", commentval=" + commentval + "]";
	}
	
}
