package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserFriendCompositeKey implements Serializable {
	
	  
	  private User userObj;
	  private String friendId;

}
