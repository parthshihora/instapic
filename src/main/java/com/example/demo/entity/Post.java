package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Post{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String photoURL;
	private String text;
	private String audioURL;
	

	@ManyToOne
	@JoinColumn(name="userId")
	private User userObj;
	
	@OneToMany(mappedBy="postObj", cascade = CascadeType.ALL)
	private List<Comment> commentList;
	
	@OneToMany(mappedBy="postObj", cascade = CascadeType.ALL)
	private List<Notification> notificationList;
	
	
	public Post() {
		super();
	}
	
	public Post(int id, String photoURL, String text, User userObj, String audioURL,List<Comment> commentList,List<Notification> notificationList ) {
		super();
		this.id = id;
		this.photoURL = photoURL;
		this.text = text;
		this.userObj = userObj;
		this.audioURL = audioURL;
		this.commentList = commentList;
		this.notificationList = notificationList ;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public List<Notification> getNotificationList() {
		return notificationList;
	}

	public void setNotificationList(List<Notification> notificationList) {
		this.notificationList = notificationList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	
	public String getAudioURL() {
		return audioURL;
	}
	public void setAudioURL(String audioURL) {
		this.audioURL = audioURL;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public User getUserObj() {
		return userObj;
	}
	public void setUserObj(User userObj) {
		this.userObj = userObj;
	}
	
	
	/*@Override
	public String toString() {
		return "Post [postId=" + postId + ", photoURL=" + photoURL + ", text=" + text + ", audioURL=" + audioURL
				+ ", userObj=" + userObj + ", commentList=" + commentList + "]";
	}*/

}
