package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class FriendProfileController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private CommentRepository commentRepo;

	
	@PostMapping(value="/friendprofile")
	public ModelAndView friendProfile(@RequestParam(name="friendId") String friendId,
			HttpServletRequest req) {
		ModelAndView fp = new ModelAndView();
		
			System.out.println("Start of friendprofile");
			List<Post> posts = new ArrayList<Post>();
			User friend = userRepo.findByUserId(friendId);
			posts = postRepo.findByUserObj(friend);
			fp.addObject("friend", friend);
			fp.addObject("posts", posts);
			fp.setViewName("friendprofilepage");
			System.out.println("end of friendprofile");
			return fp;
			
	}
	
	/*@RequestMapping(value="friendsearch", method = RequestMethod.GET)
	public String searchFriends(@RequestParam(value="search") String name,Model model) {
		model.addAttribute("search", userRepo.findByUserName(name));
		return "friendsearch";
		
	}*/
			
	/*	else {
			Comment commentObj = new Comment();
			
			String id = (String) req.getSession().getAttribute("userId");
			User user = userRepo.findByUserId(id);
			User friend = userRepo.findByUserId(friendId);
			List<Post> postList = new ArrayList<Post>();
			List<Comment> comments = new ArrayList<Comment>();
			
			
			postList = postRepo.findByUserObj(friend);
			System.out.println("**********"+postList);
			Integer pid = Integer.parseInt(postId);
			for(int i=0;i<postList.size();i++) {
				Post p = new Post();
				p = postList.get(i);
				System.out.println("********"+p.getPostId());
				if(p.getPostId().equals(pid)){
					commentObj.setPostObj(p);
					commentObj.setUserObj(user);
					commentObj.setComment(comment);
					System.out.println("*********in if");
					commentRepo.save(commentObj);
					comments = commentRepo.findByPostObj(p);
					break;
				}
			}
			
			
			fp.addObject("friend", friend);
			fp.addObject("posts",postList);
			fp.addObject("comments", comments);
			fp.setViewName("friendprofilepage");
			return fp;
		}*/
		

	
	
	

}
