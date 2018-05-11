package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
	

	public List<Post> findByUserObj(User userObj);
	public Post findById(int Id);

}
