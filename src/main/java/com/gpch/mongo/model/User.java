package com.gpch.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.gpch.mongo.repository.EventRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 * @author Gage Hoefer
 * 
 * This class defines the "User" object stored as semi-persistent data in our database.
 * The class contains all information about a user/worker that the application needs to operate. 
 *
 */
@Data
@Document(collection = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	// This variable represents the unique user id for the User object
	@Id
	private String user_id;
	// This variable represents the first name for a user
	private String user_first_name;
	// This variable represents the last name for a user
	private String user_last_name;
	// This variable represents the permissions a user has
	private String user_permissions;
	// This variable represents all events the user is registered to work
	private List<String> user_events;
	
	/**
	 * @author Gage Hoefer
	 * @param user_first_name
	 * @param user_last_name
	 * @param user_permissions
	 * @param user_events
	 * @return None
	 * 
	 * This is a simple constructor for a User object. 
	 */
	public User(String user_first_name, String user_last_name, String user_permissions, List<String> user_events) {
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_permissions = user_permissions;
		this.user_events = user_events;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param user_events
	 * @return None
	 * 
	 * This is a simple 'set' method which sets the list of events a user
	 * is registered to work, given a list of Strings when represents 
	 * said event list. 
	 */
	public void setUserEvents(List<String> user_events) {
		this.user_events = user_events;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param None
	 * @return List<String>
	 * 
	 * This is a simple 'get' method which returns all events a user
	 * is registered to work in the form of a list of Strings. 
	 */
	public List<String> getUserEvents() {
		return this.user_events;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param event_id
	 * @return None
	 * 
	 * When invoked, this method is given a String representing
	 * an event id for a user job, which it then adds to the list
	 * of events a user is registered to work. 
	 */
	public void addEvent(String event_id) {
		this.user_events.add(event_id);
	}
	
	/**
	 * @author Gage Hoefer
	 * @param event_id
	 * @return None
	 * 
	 * When invoked, this method is given a String representing
	 * an event id for a user job, which it then removes from the
	 * list of events a user is registered to work. 
	 */
	public void deleteEvent(String event_id) {
		this.user_events.remove(event_id);
	}
	
	/**
	 * @author Gage Hoefer
	 * @param user_first_name
	 * @return None
	 * 
	 * This is a simple 'set' method which sets the first name of
	 * a user, given a String representing the first name of said
	 * user. 
	 */
	public void setUserFirstName(String user_first_name) {
		this.user_first_name = user_first_name;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param None
	 * @return String
	 * 
	 * This is a simple 'get' method which returns the first name 
	 * for a user in the form of a String. 
	 */
	public String getUserFirstName() {
		return this.user_first_name;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param user_last_name
	 * @return None
	 * 
	 * This is a simple 'set' method which sets the last name for
	 * a user, given a String which represents the last name for
	 * said user. 
	 */
	public void setUserLastName(String user_last_name) {
		this.user_last_name = user_last_name;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param None
	 * @return String
	 * 
	 * This is a simple 'get' method which returns the last name
	 * for a user in the form of a String. 
	 */
	public String getUserLastName() {
		return this.user_last_name;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param None
	 * @return String
	 * 
	 * This is a simple 'get' method which returns the user id for
	 * a User object in the form of a String. 
	 */
	public String getUserId() {
		return this.user_id;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param None
	 * @return String
	 * 
	 * This is a simple 'get' method which returns the permissions
	 * for a user account in the form of a String.
	 */
	public String getUserPermissions() {
		return this.user_permissions;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param user_permissions
	 * @return None
	 * 
	 * This is a simple 'set' method which sets the permissions for
	 * a user account, given a String which represents the permissions
	 * for said user. 
	 */
	public void setUserPermissions(String user_permissions) {
		this.user_permissions = user_permissions;
	}

}
