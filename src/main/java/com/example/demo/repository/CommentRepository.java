package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Friend;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
	
	public List<Comment> findByPostObj(Post postObj);

}
