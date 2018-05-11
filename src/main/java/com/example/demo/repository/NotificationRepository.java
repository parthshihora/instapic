package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Notification;
import com.example.demo.entity.User;


@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer>  {
	
	public List<Notification> findByUserObj(User userObj);
	public long countByUserObj(User userObj);

}
