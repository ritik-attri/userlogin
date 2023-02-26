// Java Program to Illustrate Creation Of
// Service Interface

package com.example.service;

// Importing required classes
import com.example.models.EmailDetails;

// Interface
public interface EmailService {

	// Method
	// To send a simple email
	String sendSimpleMail(EmailDetails details);
}
