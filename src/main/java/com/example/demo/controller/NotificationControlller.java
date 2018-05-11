package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Notification;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class NotificationControlller {
	
	@Autowired
	private NotificationRepository notiRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PostRepository postRepo;
	
	@RequestMapping(value="/notification", method = RequestMethod.GET)
	public ModelAndView myNotification(HttpServletRequest request) {
		
		ModelAndView nv = new ModelAndView();
		String id = (String) request.getSession().getAttribute("userId");
		User user = userRepo.findByUserId(id);
		
	
		/*List<Notification> notiList = new ArrayList<Notification>();
		notiList = notiRepo.findByUserObj(user);
		long  notiCount = notiRepo.count();
		nv.addObject(notiCount);
		nv.addObject("notifications", notiList);
		nv.setViewName("NotificationPage");
		return nv;*/
		
		List<Post> userPost = new ArrayList<Post>();
		userPost = postRepo.findByUserObj(user);
		List<Notification> notiList = new ArrayList<Notification>();
		notiList = notiRepo.findByUserObj(user);
		
		List<Notification> directNoti = new ArrayList<Notification>();
		List<Notification> indirectNoti = new ArrayList<Notification>();
		
		for(int i=0;i<notiList.size();i++) {
			boolean f=false;
			Notification n = new Notification();
			n = notiList.get(i);
			Post np = new Post();
			np = n.getPostObj();
			Integer pid = np.getId();
			for(int j=0;i<userPost.size();i++) {
				f=false;
				Post up = new Post();
				up = userPost.get(j);
				Integer upid = up.getId();
				
				if(upid.equals(pid)) {
					
					System.out.println("in direct");
					directNoti.add(n);
					f=true;
					break;
				}
			}
			if(!(f)) {
				System.out.println("in indirect");
				indirectNoti.add(n);
			}
		}

		
		System.out.println("Indirect"+indirectNoti);
		long  notiCount = notiRepo.count();
		nv.addObject(notiCount);
		nv.addObject("directNoti", directNoti);
		nv.addObject("indirectNoti", indirectNoti);
		nv.addObject("notifications", notiList);
		nv.setViewName("NotificationPage");
		return nv;
		
		
	}
	
	

}
