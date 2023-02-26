// Java Program to Illustrate EmailDetails Class

package com.example.models;

// Importing required classes
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

// Class
public class EmailDetails {
	public EmailDetails() {}
	public EmailDetails(String recipient, String msgBody, String subject, String attachment) {
		this.recipient = recipient;
		this.msgBody = msgBody;
		this.subject = subject;
		this.attachment = attachment;
	}

	// Class data members
	private String recipient;
	private String msgBody;
	private String subject;
	private String attachment;
	
	public String getRecipient() {
		return this.recipient;
	}
	public String getMsgBody() {
		return this.msgBody;
	}
	public String getSubject() {
		return this.subject;
	}
	public String getAttachment() {
		return this.attachment;
	}
}
