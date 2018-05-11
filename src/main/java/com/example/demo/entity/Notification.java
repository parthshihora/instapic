package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int notiId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User userObj;
	
	@ManyToOne
	@JoinColumn(name="id")
	private Post postObj;
	
//	@OneToOne
	@ManyToOne
	@JoinColumn(name="commentId")
	private Comment commentObj;
	
	

	public Notification(int notiId, User userObj, Post postObj, Comment commentObj) {
		super();
		this.notiId = notiId;
		this.userObj = userObj;
		this.postObj = postObj;
		this.commentObj = commentObj;
	}

	public Comment getCommentObj() {
		return commentObj;
	}

	public void setCommentObj(Comment commentObj) {
		this.commentObj = commentObj;
	}

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return notiId;
	}

	public void setId(int notiId) {
		this.notiId = notiId;
	}

	public User getUserObj() {
		return userObj;
	}

	public void setUserObj(User userObj) {
		this.userObj = userObj;
	}

	public Post getPostObj() {
		return postObj;
	}

	public void setPostObj(Post postObj) {
		this.postObj = postObj;
	}
	
	
	
}
