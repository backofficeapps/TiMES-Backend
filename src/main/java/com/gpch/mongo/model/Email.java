package com.gpch.mongo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Gage Hoefer
 * 
 * This class defines the "Email" object stored as semi-persistent data in our database.
 *
 */
@Data
@Document(collection = "email")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email {
	
	// This variable represents the user id which the Email object corresponds to
	@Id
	private String user_id;
	// This list of variables represents the email address(es) registered to a user
	private List<String> email_addresses;
	
	/**
	 * @author Gage Hoefer
	 * @param user_id
	 * 
	 * A simple constructor for the Email object. 
	 */
	public Email(String user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param email
	 * @return None
	 * 
	 * When invoked, this method is given a String representing an email
	 * address for a user, which it then adds to the list of email addresses
	 * registered to said user. 
	 */
	public void addEmail(String email) {
		this.email_addresses.add(email);
	}
	
	/**
	 * @author Gage Hoefer
	 * @param email
	 * @return None
	 * 
	 * When invoked, this method is given a String representing an email
	 * address for a user, which it then removes from the list of email
	 * addresses registered to said user. 
	 */
	public void deleteEmail(String email) {
		this.email_addresses.remove(email);
	}
	
	/**
	 * @author Gage Hoefer
	 * @param None
	 * @return String
	 * 
	 * This is a simple 'get' method which returns the user id
	 * associated with an Email object in the form of a String.
	 */
	public String getUserId() {
		return this.user_id;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param None
	 * @return List<String>
	 * 
	 * This is a simple 'get' method which returns the list
	 * of all email addresses registered to a user (which
	 * the Email object corresponds to) in the form of a 
	 * list of Strings. 
	 */
	public List<String> getEmailAddresses() {
		return this.email_addresses;
	}
	
}
