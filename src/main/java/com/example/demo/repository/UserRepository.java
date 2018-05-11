package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
import java.lang.String;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	public List<User> findAll();
	public User findByUserId(String userId);
	public boolean existsByUserId(String userId);
	public List<User> findByUserName(String userName);
}

