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
 * This class defines the "Phone" object stored as semi-persistent data in our database. 
 *
 */
@Data
@Document(collection = "phones")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
	
	// This variable represents the user id for the user the Phone object corresponds to
	@Id
	private String user_id;
	// This variable represents the list of phone number(s) registered to a user
	private List<String> phone_numbers;
	
	/**
	 * @author Gage Hoefer
	 * @param user_id
	 * 
	 * A simple constructor for the Phone object.
	 */
	public Phone(String user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param phone_number
	 * @return None
	 * 
	 * When invoked, this method is given a String representing a 
	 * phone number for a user, which it then adds to the list of
	 * phone numbers registered to said user. 
	 */
	public void addPhoneNumber(String phone_number) {
		this.phone_numbers.add(phone_number);
	}
	
	/**
	 * @author Gage Hoefer
	 * @param phone_number
	 * @return None
	 * 
	 * When invoked, this method is given a String representing a 
	 * phone number for a user, which it then removes from the list
	 * of phone numbers registered for said user. 
	 */
	public void removePhoneNumber(String phone_number) {
		this.phone_numbers.remove(phone_number);
	}
	
	/**
	 * @author Gage Hoefer
	 * @param None
	 * @return String
	 * 
	 * This is a simple 'get' method which returns the user id for the Phone object
	 * in the form of a String. 
	 */
	public String getUserId() {
		return this.user_id;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param None
	 * @return List<String>
	 * 
	 * This is a simple 'get' method which returns the phone numbers registered
	 * to a user's account in the form of a list of Strings. 
	 */
	public List<String> getPhoneNumbers() {
		return this.phone_numbers;
	}
	
}

