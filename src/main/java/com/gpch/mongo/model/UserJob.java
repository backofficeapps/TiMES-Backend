package com.gpch.mongo.model;

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
 * This class defines the "UserJob" object stored as semi-persistent data in our database.
 * The class contains a variable for an event id, a user id, and a job id, tying all three
 * together in one entity representing what job a user will work at an event. 
 *
 */
@Data
@Document(collection = "userjobs")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJob {
	
	// This variable represents the unique id associated with the UserJob object
	@Id
	private String id;
	// This variable represents the event id for an entry 
	private String event_id;
	// This variable represents the user id for an entry
	private String user_id;
	// This variable represents the job id for an entry
	private String job_id;
	
	/**
	 * @author Gage Hoefer
	 * @param event_id
	 * @param user_id
	 * @param job_id
	 * 
	 * This is a simple constructor for a UserJob object. 
	 */
	public UserJob(String event_id, String user_id, String job_id) {
		this.event_id = event_id;
		this.user_id = user_id;
		this.job_id = job_id;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param user_id
	 * 
	 * This is a simple 'set' method which sets the user id
	 * for a UserJob object, when given a String which represents
	 * said user id. 
	 */
	public void setUserId(String user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param job_id
	 *   
	 * 
	 * This is a simple 'set' method which sets the job id
	 * for a UserJob object, when given a String which represents
	 * said job id.
	 */
	public void setJobId(String job_id) {
		this.job_id = job_id;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param event_id
	 *   
	 * 
	 * This is a simple 'set' method which sets the event it
	 * for a UserJob object, when given a String which represents
	 * said event id.
	 */
	public void setEventId(String event_id) {
		this.event_id = event_id;
	}
	
	/**
	 * @author Gage Hoefer
	 *  
	 * @return String
	 * 
	 * This is a simple 'get' method which returns the unique
	 * id for a UserJob object in the form of a String. 
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * @author Gage Hoefer
	 *  
	 * @return String
	 * 
	 * This is a simple 'get' method which returns the user id
	 * for a UserJob object in the form of a String. 
	 */
	public String getUserId() {
		return this.user_id;
	}
	
	/**
	 * @author Gage Hoefer
	 *  
	 * @return String
	 * 
	 * This is a simple 'get' method which returns the job id
	 * for a UserJob object in the form of a String. 
	 */
	public String getJobId() {
		return this.job_id;
	}
	
	/**
	 * @author Gage Hoefer
	 *  
	 * @return String
	 * 
	 * This is a simple 'get' method which returns the event id
	 * for a UserJob object in the form of a String. 
	 */
	public String getEventId() {
		return this.event_id;
	}

}
