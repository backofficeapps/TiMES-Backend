package com.gpch.mongo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "email")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email {
	
	@Id
	private String user_id;
	private List<String> email_addresses;
	
	public Email(String user_id) {
		this.user_id = user_id;
	}
	
	public void addEmail(String email) {
		this.email_addresses.add(email);
	}
	
	public void deleteEmail(String email) {
		this.email_addresses.remove(email);
	}
	
	public String getUserId() {
		return this.user_id;
	}
	
	public List<String> getEmailAddresses() {
		return this.email_addresses;
	}
	
}
