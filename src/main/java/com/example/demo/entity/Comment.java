package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Comment{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="id")
	private Post postObj;
	
	//@OneToOne(mappedBy="commentObj",cascade = CascadeType.ALL)
	//private Notification notification;
	
	@OneToMany(mappedBy="commentObj",cascade = CascadeType.ALL)
	private List<Notification> notificationList;
	
	//private Integer postObj;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User userObj;

	public Comment(Long commentId, String comment, Post postObj, User userObj, List<Notification> notificationList) {
		super();
		this.commentId = commentId;
		this.comment = comment;
		this.postObj = postObj;
		this.userObj = userObj;
		this.notificationList = notificationList;
	}

	public List<Notification> getNotificationList() {
		return notificationList;
	}

	public void setNotification(List<Notification> notification) {
		this.notificationList = notificationList;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Post getPostObj() {
		return postObj;
	}

	public void setPostObj(Post postObj) {
		this.postObj = postObj;
	}

	public User getUserObj() {
		return userObj;
	}

	public void setUserObj(User userObj) {
		this.userObj = userObj;
	}
	
	
	
	
	

}
