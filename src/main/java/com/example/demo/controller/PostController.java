package com.example.demo.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.Base64.Decoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Friend;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.entity.Notification;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UploadToS3;

@Controller
public class PostController {
	
	@Autowired
	UploadToS3 upload;
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private NotificationRepository notiRepo;
	@Autowired
	private CommentRepository commentRepo;
	
	@PostMapping(value="/base64Audio")
	public ModelAndView saveAudio(HttpServletRequest req,
			@RequestParam("recording") String recording,
			@RequestParam("capture") String photo,
			@RequestParam String aboutPhoto)
			throws Exception{
		
		//ModelAndView post = new ModelAndView();
		Decoder decoder = Base64.getDecoder();
		byte[] decodeByte = decoder.decode(recording.split(",")[1]);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("MyAudio.webm");
			fos.write(decodeByte);
			fos.close();
		}
		catch(IOException e){
			e.printStackTrace();
			
		}
		
		byte[] imdecodeByte = decoder.decode(photo.split(",")[1]);
		FileOutputStream imfos;
		try {
			imfos = new FileOutputStream("Myphoto.png");
			imfos.write(imdecodeByte);
			imfos.close();
		}
		catch(IOException e){
			e.printStackTrace();
			
		}
		
		String userId = (String)req.getSession().getAttribute("userId");
		User user = userRepo.findByUserId(userId);
		
		Random rand = new Random();
		int ri = rand.nextInt(10000);
		int imri = rand.nextInt(10000);
		
		String audioURL = upload.upload(userId + ri +".webm", new FileInputStream("MyAudio.webm"));
		//String photoURL = upload.uploadPhoto(photo.getOriginalFilename(), photo.getInputStream());
		String photoURL = upload.uploadPhoto(userId + imri +".png", new FileInputStream("Myphoto.png"));
		
		Post postObj = new Post();
		postObj.setAudioURL(audioURL);
		postObj.setPhotoURL(photoURL);
		postObj.setText(aboutPhoto);
		postObj.setUserObj(user);
		System.out.println("saving post");
		postRepo.save(postObj);
		
		
		List<Friend> friends = new ArrayList<Friend>();
		friends = user.getFriendList();
		System.out.println("got the friends*********"+friends);
		
		for(int i=0;i<friends.size();i++) {
			Notification notify = new Notification();
			User u = new User();
			Friend f = friends.get(i);
			String id = f.getFriendId();
			u = userRepo.findByUserId(id);
			System.out.println("user object*********"+u);
			if(u!=null) {
				notify.setUserObj(u);
				notify.setPostObj(postObj);
				System.out.println("Saving notifiction***********");
				notiRepo.save(notify);
			}
		}
		
		return new ModelAndView("redirect:/myprofile");
		
	}

	
	@PostMapping(value="/mypost")
	public ModelAndView myPost(@RequestParam(name="Id") int id) {
		ModelAndView mp = new ModelAndView();
		Post p =  new Post();
		List<Comment> comments = new ArrayList<Comment>();
		p = postRepo.findById(id);
		comments = commentRepo.findByPostObj(p);	
		mp.addObject("post",p);
		mp.addObject("comments",comments);
		mp.setViewName("MyPost");
		return mp;
	}
	
	@PostMapping(value="/userpost")
	public ModelAndView userPost(@RequestParam(name="userId") String id) {
		ModelAndView up = new ModelAndView();
		List<Post> posts = new ArrayList<Post>();
		User user = new User();
		user = userRepo.findByUserId(id);
		posts = postRepo.findByUserObj(user);
		up.addObject("posts",posts);
		up.setViewName("UserPost");
		return up;
		
	}
	
	@PostMapping(value="/deletepost")
	public ModelAndView deletePost(@RequestParam(name="postId") int id,@RequestParam(name="userId") String userId) {
		ModelAndView up = new ModelAndView();
		Post p = new Post();
		p = postRepo.findById(id);
		postRepo.delete(p);
		
		List<Post> posts = new ArrayList<Post>();
		User user = new User();
		user = userRepo.findByUserId(userId);
		posts = postRepo.findByUserObj(user);
		up.addObject("posts",posts);
		up.setViewName("UserPost");
		return up;

		
	}
	
}
