package com.gpch.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Gage Hoefer
 * 
 * This class defines the "Event" object stored as semi-persistent data in our database.
 * The class contains all necessary information about an Event the application needs to operate,
 * including their requirements used during the scheduling process. 
 *
 */
@Data
@Document(collection = "events")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
	
	// This variable represents the unique id for an Event object
    @Id
    private String id;
    // This variable represents the event id (distinct from the unique Event id)
    @NotBlank(message="Event id: ")
    private String event_id;
    // This variable represents the name for an Event object
    @NotBlank(message="Event name: ")
    private String event_name;
    // This variable represents the type for an Event object
    @NotBlank(message="Event type: ")
    private String event_type;
    // This variable represents the street number of the location the event takes place at
    private String location_street_number;
    // This variable represents the street name of the location the event takes place at
    private String location_street_name;
    // This variable represents the date the event takes place
    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    @NotNull(message="Please provide a date with the format dd-MM-yyyy HH:mm")
    private LocalDateTime date;
    // This variable represents the time the event takes place
    @NotNull(message="Event time: ")
    private String event_time;
    // This variable represents the list of users working the event, and the jobs assigned 
    // to each worker
    private List<UserJob> userJobs;
    
    /**
     * @author Gage Hoefer
     * @param event_id
     * @param event_name
     * @param event_type
     * @return None
     * 
     * This is a simple constructor for an Event object
     */
    public Event(String event_id, String event_name, String event_type) {
    	this.event_id = event_id;
    	this.event_name = event_name;
    	this.event_type = event_type;
    }
    
    /**
     * @author Gage Hoefer
     * @param user_job
     * @return None
     * 
     * When invoked, this method is given a UserJob object and
     * adds it to the list of UserJob objects associated with this event.
     */
    public void addUserJob (UserJob user_job) {
    	this.userJobs.add(user_job);
    }
    
    /**
     * @author Gage Hoefer
     * @param user_job
     * @return None
     * 
     * When invoked, this method is given a UserJob object and
     * removes it from the list of UserJob objects associated with this event. 
     */
    public void removeUserJob(UserJob user_job) {
    	this.userJobs.remove(user_job);
    	for(int i = 0; i < this.userJobs.size(); i++) {
    		this.userJobs.get(i).setEventId(this.id);
    	}
    }
    
    /**
     * @author Gage Hoefer
     * @param None
     * @return List<String>
     * 
     * This is a simple 'get' method which returns all user ids
     * from the list of users registered to work the event in 
     * the form of a list of Strings. 
     */
    public List<String> getUserIdsFromUserJobs() {
    	List<String> userIds = new ArrayList<String>();
    	for(int i = 0; i < userJobs.size(); i++) {
    		userIds.add(userJobs.get(i).getId());
    	}
    	return userIds;
    }
    
    /**
     * @author Gage Hoefer
     * @param None
     * @return String
     * 
     * This is a simple 'get' method which returns the
     * unique id for the Event object in the form of a
     * String. 
     */
    public String getId() {
    	return this.id;
    }
    
    /**
     * @author Gage Hoefer
     * @param None
     * @return String
     * 
     * This is a simple 'get' method which returns the event
     * id in the form of a String. 
     */
    public String getEventId() {
    	return this.event_id;
    }
    
    /**
     * @author Gage Hoefer
     * @param None
     * @return String
     * 
     * This is a simple 'get' method which returns the event name
     * in the form of a String. 
     */
    public String getEventName() {
    	return this.event_name;
    }
    
    /**
     * @author Gage Hoefer
     * @param None
     * @return String
     * 
     * This is a simple 'get' method which returns the type of
     * the event in the form of a String. 
     */
    public String getEventType() {
    	return this.event_type;
    }
    
    /**
     * @author Gage Hoefer
     * @param None
     * @return String
     * 
     * This is a simple 'get' method which returns the location
     * of the event in the form of a String. 
     */
    public String getEventLocation() {
    	return this.location_street_number +  " " + this.location_street_name;
    }
    
    /**
     * @author Gage Hoefer
     * @param None
     * @return LocalDateTime
     * 
     * This is a simple 'get' method which returns the date of
     * the event in the form of a LocalDateTime object. 
     */
    public LocalDateTime getEventDate() {
    	return this.date;
    }
    
    /**
     * @author Gage Hoefer
     * @param None
     * @return String
     * 
     * This is a simple 'get' method which returns the time the
     * event takes place in the form of a String. 
     */
    public String getEventTime() {
    	return this.event_time;
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @return None
     * 
     * This is a simple 'set' method which sets the id for the Event
     * object, given a String which represents said id. 
     */
    public void setId(String id) {
    	this.id = id;
    }
    
    /**
     * @author Gage Hoefer
     * @param event_id
     * @return None
     * 
     * This is a simple 'set' method which sets the event id for an
     * event, given a String which represents said event id. 
     */
    public void setEventId(String event_id) {
    	this.event_id = event_id;
    }
    
    /**
     * @author Gage Hoefer
     * @param event_name
     * @return None
     * 
     * This is a simple 'set' method which sets the name of an event,
     * given a String which represents said event name. 
     */
    public void setEventName(String event_name) {
    	this.event_name = event_name;
    }
    
    /**
     * @author Gage Hoefer
     * @param event_type
     * @return None
     * 
     * This is a simple 'set' method which sets the type of an event,
     * given a String which represents said event type.
     */
    public void setEventType(String event_type) {
    	this.event_type = event_type;
    }
    
    /**
     * @author Gage Hoefer
     * @param location_street_number
     * @param location_street_name
     * @return None
     * 
     * This is a simple 'set' method which sets the location of an event,
     * given two Strings which represent the street number and street name 
     * at which the event takes place. 
     */
    public void setEventLocation(String location_street_number, String location_street_name) {
    	this.location_street_number = location_street_number;
    	this.location_street_name = location_street_name;
    }
    
    /**
     * @author Gage Hoefer
     * @param date
     * @return None
     * 
     * This is a simple 'set' method which sets the date an event takes place,
     * given a LocalDateTime object which represents said date. 
     */
    public void setEventDate(LocalDateTime date) {
    	this.date = date;
    }
    
    /**
     * @author Gage Hoefer
     * @param event_time
     * @return None
     * 
     * This is a simple 'set' method which sets the time an event takes place,
     * given a String which represents said event time. 
     */
    public void setEventTime(String event_time) {
    	this.event_time = event_time;
    }
    
    
    
}
