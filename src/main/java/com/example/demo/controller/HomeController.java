package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demo.entity.Friend;
import com.example.demo.entity.Notification;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.FriendRepository;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;



@Controller
@Component
public class HomeController {
	
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private FriendRepository friendRepo;
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private NotificationRepository notiRepo;
	
	@Value("#{environment.access_key}")
	public String accessKey;
	@Value("#{environment.secret_key}")
	String secretKey;
	
	
	@GetMapping(value="/")
	public ModelAndView renderFB() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("homepage");
		System.out.println("in home page*************");
		return mv;
	}
	

	@PostMapping(value="/redirectForm")
	public ModelAndView handleRedirect(
			@RequestParam(name="myId") String userId,
			@RequestParam(name="myName") String userName,
			@RequestParam(name="myEmail") String userEmail,
			@RequestParam(name="myFriends") String friendList,
			HttpServletRequest request) {
		
		//User user = new User(userId,userName,userEmail,null,null,null);
		request.getSession().setAttribute("userId", userId);
		System.out.println("******userId"+userId);
		User user;
		user = userRepo.findByUserId(userId);
	//	User user = new User();
			
		if(userId.equals("1847183121987895")){
			return new ModelAndView("redirect:/allusers");
		}
		
		if(userRepo.existsByUserId(userId) || user!=null) {
			
			
			
			System.out.println("In if part of redirect");
			
		/*	ModelAndView profilePage = new ModelAndView();
			List<Post> posts = new ArrayList<Post>();
			
			user = userRepo.findByUserId(userId);
			posts = postRepo.findByUserObj(user);
			//user.setPostList(posts);
			profilePage.addObject("users",user);
			profilePage.addObject("posts",posts);
			profilePage.setViewName("profilepage");*/
			return new ModelAndView("redirect:/myprofile");
		}
		else {
			

			System.out.println("In else part of redirect");
			user =new User();
			user.setUserName(userName);
			user.setUserEmail(userEmail);
			user.setUserId(userId);
			
			List<Friend> friends = new ArrayList<Friend>();
			//user.setFriendList(friends);
			
			JSONArray array = new JSONArray(friendList);
			for(int i=0;i<array.length();i++) {
				Friend friendObj = new Friend();
				JSONObject friend = (JSONObject)array.get(i);
				String friendId = friend.getString("id");
				friendObj.setFriendId(friendId);
				friendObj.setUserObj(user);
				friends.add(friendObj);
				//user.getFriendList().add(friendObj);
			}
			user.setFriendList(friends);
			userRepo.save(user);
			System.out.println("user saved");
			return new ModelAndView("redirect:/createprofile");
		}
				
	}
	
	@RequestMapping(value="/myfriends", method = RequestMethod.GET)
	public ModelAndView viewFriends (HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		String id = (String) request.getSession().getAttribute("userId");
		User user = userRepo.findByUserId(id);
		
		List<Friend> friends = new ArrayList<Friend>();
		List<User> userFriend = new ArrayList<User>();
		
		friends = friendRepo.findByUserObj(user);
		
		for(int i=0;i<friends.size();i++) {
			Friend f = friends.get(i);
			String fid = f.getFriendId();
			User uf = userRepo.findByUserId(fid);
			if(uf!=null)
				userFriend.add(uf);
		}
		
		mv.addObject("userFriend",userFriend);
		mv.setViewName("friendlist");
		return mv;
	}

	@RequestMapping(value="/editprofile",method = RequestMethod.GET)
	public ModelAndView editProfile(HttpServletRequest request) {
		ModelAndView ep = new ModelAndView();
		ep.setViewName("profileCreation");
		return ep;
		
	}
	
	@RequestMapping(value="/myprofile", method = RequestMethod.GET)
	public ModelAndView myProfile(HttpServletRequest request) {
		ModelAndView profilePage = new ModelAndView();
		List<Post> posts = new ArrayList<Post>();
		String id = (String) request.getSession().getAttribute("userId");
		User user = userRepo.findByUserId(id);
		
		posts = postRepo.findByUserObj(user);
		user.setPostList(posts);
	//	userRepo.save(user);
		profilePage.addObject("users",user);
		profilePage.addObject("posts",posts);
//For Notification count
 //		String id = (String) request.getSession().getAttribute("userId");
	//	User user = userRepo.findByUserId(id);
	//	List<Notification> notiList = new ArrayList<Notification>();
//		notiList = notiRepo.findByUserObj(user);
		
		long  notiCount = notiRepo.countByUserObj(user);
		System.out.println("Count"+notiCount);
		profilePage.addObject("count",notiCount);
	
 
		
		profilePage.setViewName("profilepage");
		return profilePage;
		
		
	}
	
	@RequestMapping(value="/postcreation",method = RequestMethod.GET)
	public ModelAndView createPost(HttpServletRequest request) {
		ModelAndView cp = new ModelAndView();
		cp.setViewName("postcreation");
		return cp;
	}
	
	@RequestMapping(value="/createprofile", method = RequestMethod.GET)
	public ModelAndView createProfile() {
		System.out.println("In create profile");
		ModelAndView cp = new ModelAndView();
		cp.setViewName("profileCreation");
		return cp;
		
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public RedirectView logout(HttpSession session) {
		session.invalidate();
		return new RedirectView("/");
		
	}
	
	@RequestMapping(value="/allusers", method = RequestMethod.GET)
		public ModelAndView allUsers() {
		
		ModelAndView au = new ModelAndView();
		List<User> users = new ArrayList<User>();
		users = userRepo.findAll();
		au.addObject("users", users);
		au.setViewName("AllUsers");
		return au;
			
		}
	
	

}
