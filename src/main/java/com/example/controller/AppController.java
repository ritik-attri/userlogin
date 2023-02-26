package com.example.controller;

import java.time.LocalDateTime;
import java.util.Date;

import com.example.services.OtpGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.models.*;
import com.example.repositories.UserRepository;
import com.example.service.EmailService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")

@RestController
@RequestMapping("/")
public class AppController {
	@Autowired
    private UserRepository userRepo;
	@Autowired
	private EmailService emailService;
     
    @PostMapping("signup")
    public ResponseEntity<UserProfile> signup(@RequestParam(required = true) String email, String password, String firstName, String lastName) {
    	System.out.println(email + password +  firstName +  lastName);
    	UserProfile user = userRepo.findByEmail(email);
    	if(user != null) {
    		return new ResponseEntity<UserProfile>(user, HttpStatus.ACCEPTED);
    	}
    	OtpGenService otpService = new OtpGenService();
    	String otp = otpService.OtpGenerator();
    	long otp_created_at = new Date().getTime();
    	EmailDetails emailToSend = new EmailDetails(email,"Your otp is:- "+ otp, "Otp for login", "No Attachment");
    	emailService.sendSimpleMail(emailToSend);
        user = userRepo.save(new UserProfile(email, password, firstName, lastName, otp, otp_created_at));
        return new ResponseEntity<UserProfile>(user, HttpStatus.ACCEPTED);        
    }
    @GetMapping("login")
    @ResponseBody
    public ResponseEntity<UserProfile> signin(@RequestParam(required = true) String email, String password) {
    	try {
    		System.out.println(email + password);
    		UserProfile user = userRepo.findByEmail(email);
    		System.out.println(user.getOtp().equals("0") && user.getPassword().equals(password));
    		if(user.getOtp().equals("0") && user.getPassword().equals(password)) {
    			user.setLoginTime(new Date().getTime());
    			return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    		}
    		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    	} catch (Exception e) {
    		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    	} 
    }
    @PostMapping("validate")
    @ResponseBody
    public ResponseEntity<UserProfile> validate(@RequestParam(required = true)String email, String otp){
    	try {
    		System.out.println(email+otp);
    		UserProfile user = userRepo.findByEmail(email);
    		if(user.getOtp().equals(otp)) {
    			user.setOtp("0");
    			user.setOtpCreationTime(0);
    			user.setLoginTime(new Date().getTime());
    			userRepo.save(user);
    			return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    		}
    		return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
    	}catch (Exception e) {
    		return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
    	}
    }
    @PostMapping("validate_session")
    @ResponseBody
    public ResponseEntity<Boolean> validate_session(@RequestParam(required = true)String email){
    	try {
    		UserProfile user = userRepo.findByEmail(email);
    		long time = new Date().getTime();
    		if(time - user.getLoginTime() > 60* 6 * 1000) { //if current time more than 60 mins from login time session times out.
    			user.setLoginTime(0);
    			return new ResponseEntity<>(false,HttpStatus.UNAUTHORIZED);
    		}
    		return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
    	}catch (Exception e){
    		return new ResponseEntity<>(false,HttpStatus.UNAUTHORIZED);
    	}
    }
}
