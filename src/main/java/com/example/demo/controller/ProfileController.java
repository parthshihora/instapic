package com.example.demo.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.context.SecurityContextHolder;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;


@Controller
@Component
public class ProfileController {
	
	
	@Autowired
	private UserRepository userRepo;
	
	@Value("#{environment.access_key}")
	public String accessKey;
	@Value("#{environment.secret_key}")
	String secretKey;

	@PostMapping(value="/user/add")
	public ModelAndView saveUser(
			@RequestParam String description,
			@RequestParam("file") MultipartFile profilePhoto,
			@RequestParam(name="name") String userName,
			HttpServletRequest request) {
	
		String id = (String) request.getSession().getAttribute("userId");
		
		User user = userRepo.findByUserId(id);
		user.setDescription(description);
		user.setUserName(userName);
		
		ModelAndView profilePage = new ModelAndView();
		BasicAWSCredentials cred = new BasicAWSCredentials(
				accessKey, secretKey);
		
		AmazonS3 s3client = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(cred))
				.withRegion(Regions.US_EAST_2)
				.build();
		
		try {
			PutObjectRequest putReq = new PutObjectRequest(
					"mytermproject",profilePhoto.getOriginalFilename(),profilePhoto.getInputStream(),new ObjectMetadata())
					.withCannedAcl(CannedAccessControlList.PublicRead);
			
			s3client.putObject(putReq);
			
			String profilePhotoURL = "http://"+ "mytermproject"+ ".s3.amazonaws.com/"+profilePhoto.getOriginalFilename();
			
			//profilePage.addObject("imgSrc",profilePhotoURL);
			//profilePage.setViewName("profilepage");
			
			user.setProfilePhoto(profilePhotoURL);
			userRepo.save(user);
			
		//	profilePage.addObject("users",user);
	//		profilePage.setViewName("profilepage");
//			return profilePage;
			return new ModelAndView("redirect:/myprofile");
			
		} catch (IOException e) {
			e.printStackTrace();
			profilePage.setViewName("error");
			return profilePage;
			
		}	

	}
	
	
}
