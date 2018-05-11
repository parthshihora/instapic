package com.example.demo.entity;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;



@Entity
@IdClass(UserFriendCompositeKey.class)
public class Friend {
	
	@Id
	@ManyToOne
	@JoinColumn(name="userId")
	private User userObj;
	
	@Id
	private String friendId;
	public Friend() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Friend(User userObj, String friendId) {
		super();
		this.userObj = userObj;
		this.friendId = friendId;
	}

	public User getUserObj() {
		return userObj;
	}

	public void setUserObj(User userObj) {
		this.userObj = userObj;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	/*@Override
	public String toString() {
		return "Friend [userObj=" + userObj + ", friendId=" + friendId + "]";
	}*/
	
	

	
}
