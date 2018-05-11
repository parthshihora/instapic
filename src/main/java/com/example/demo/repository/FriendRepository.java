package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserFriendCompositeKey;
import com.example.demo.entity.Friend;


@Repository
public interface FriendRepository extends CrudRepository<Friend, UserFriendCompositeKey>  {
	
	public List<Friend> findByUserObj(User userObj);

}
