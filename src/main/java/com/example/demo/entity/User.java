package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
public class User {
	
	@Id
	private String userId;
	private String userName;
	private String userEmail;
	private String profilePhoto;
	private String description;
	
	
	@OneToMany(mappedBy="userObj",cascade = CascadeType.ALL)
	private List<Post> postList;
	
	@OneToMany(mappedBy="userObj")
	private List<Comment> commentList;
	
	@OneToMany(mappedBy="userObj",cascade = CascadeType.ALL)
	private List<Friend> friendList;
	
	@OneToMany(mappedBy="userObj",cascade = CascadeType.ALL)
	private List<Notification> notificationList;
	
	


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public User(String userId, String userName, String userEmail, String profilePhoto, String description,
			 List<Friend> friendList, List<Post> postList, List<Comment> commentList, List<Notification> notificationList ) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.profilePhoto = profilePhoto;
		this.description = description;
		this.postList = postList;
		this.friendList = friendList;
		this.commentList = commentList;
		this.notificationList = notificationList;
	}
	
	
	
	public List<Notification> getNotificationList() {
		return notificationList;
	}


	public void setNotificationList(List<Notification> notificationList) {
		this.notificationList = notificationList;
	}


	public List<Comment> getCommentList() {
		return commentList;
	}


	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getProfilePhoto() {
		return profilePhoto;
	}
	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Post> getPostList() {
		return postList;
	}
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
	public List<Friend> getFriendList() {
		return friendList;
	}
	public void setFriendList(List<Friend> friendList) {
		this.friendList = friendList;
	}
	
	
	
/*	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", profilePhoto="
				+ profilePhoto + ", description=" + description + "]";
	}*/
	

}
