package com.example.models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user_profile")
public class UserProfile {
	public UserProfile() {
		this.email = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
	}
	public UserProfile(String email,String  password,String  firstName,String  lastName,String otp, long otp_created_at) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.otp = otp;
		this.otp_creation_time = otp_created_at;
	}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;
    
    @NotBlank
    @Column(name = "otp")
    private String otp;
    
    @Column(name = "otp_creation_time")
    private long otp_creation_time;
    
    @Column(name = "login_time")
    private long login_time;

    // getters and setters

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}
	
	public String getOtp() {
		return this.otp;
	}
	public long getOtpCreationTime() {
		return this.otp_creation_time;
	}
	public long getLoginTime() {
		return this.login_time;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public void setOtpCreationTime(long otp_creation_time) {
		this.otp_creation_time = otp_creation_time;
	}
	public void setLoginTime(long login_time) {
		this.login_time = login_time;
	}
}
