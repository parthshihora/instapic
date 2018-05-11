package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Notification;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class CommentController {

	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private NotificationRepository notiRepo;

	
	
	@PostMapping(value="/friendpost")
	public ModelAndView friendPost(@RequestParam(name="Id") int postId, @RequestParam(name="friendId") String fid,
	@RequestParam String comment,HttpServletRequest req) {
		
		ModelAndView fpost = new ModelAndView();
		System.out.println("*********"+postId);
		
		
		if(comment.equals("NULL")) {
		
		List<Comment> comments = new ArrayList<Comment>();
		Post p = new Post();
		p = postRepo.findById(postId);
		comments = commentRepo.findByPostObj(p);
		fpost.addObject("comments", comments);
		fpost.addObject("post",p);
		fpost.setViewName("friendspost");
		return fpost;
		}

		else {
			
			Comment commentObj = new Comment();
			String id = (String) req.getSession().getAttribute("userId");
			User user = userRepo.findByUserId(id);
			
			List<Comment> comments = new ArrayList<Comment>();
			
			Post p = new Post();
			p = postRepo.findById(postId);
			commentObj.setComment(comment);
			commentObj.setPostObj(p);
			commentObj.setUserObj(user);
			commentRepo.save(commentObj);
			
			comments = commentRepo.findByPostObj(p);
			
			User commentingUser = commentObj.getUserObj();
			String commentingUserId = commentingUser.getUserId();
			
			//Notification notify = new Notification();
			for(int i=0;i<comments.size();i++) {
				Notification notify = new Notification();
				Comment c =  comments.get(i);
				User u = c.getUserObj();
				String uid = u.getUserId();
				if(!(uid.equals(commentingUserId))) {
					notify.setPostObj(p);
					notify.setUserObj(u);
					notify.setCommentObj(commentObj);
					notiRepo.save(notify);
				}
			}
			Notification notify = new Notification();
			User f = new User();
			f = userRepo.findByUserId(fid);
			notify.setPostObj(p);
			notify.setUserObj(f);
			notify.setCommentObj(commentObj);
			notiRepo.save(notify);
			
			
			fpost.addObject("comments", comments);
			fpost.addObject("post",p);
			fpost.setViewName("friendspost");
			return fpost;
		}	
				
			/*	User userObj = userRepo.findByUserId(userId);
				postList = postRepo.findByUserObj(userObj);
				for(int i=0;i<postList.size();i++) {
					Post p = new Post();
					p = postList.get(i);
					if(p.getPostId().equals(postId)){
						
						commentObj.setComment(comment);
						commentObj.setPostObj(p);
						commentObj.setUserObj(user);
						commentRepo.save(commentObj);
				
						System.out.println("finding comment by post in else");
						comments = commentRepo.findByPostObj(p);
						System.out.println("found comment by post in else");
						fpost.addObject("comments", comments);
						fpost.addObject("post",p);
						break;
					}
				}
				fpost.setViewName("friendspost");
			return fpost; 
		}*/
		
	}
			
}
