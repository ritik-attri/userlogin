package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.repositories.UserRepository;
import java.util.Random;;

public class OtpGenService {
	@Autowired
    private UserRepository userRepo;
	
	public String OtpGenerator() {
		return String.valueOf(new Random().nextInt(100000));
	}
}
