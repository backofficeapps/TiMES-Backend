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

@Data
@Document(collection = "events")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    private String id;
    @NotBlank(message="Event id: ")
    private String event_id;
    @NotBlank(message="Event name: ")
    private String event_name;
    @NotBlank(message="Event type: ")
    private String event_type;
    private String location_street_number;
    private String location_street_name;
    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    @NotNull(message="Please provide a date with the format dd-MM-yyyy HH:mm")
    private LocalDateTime date;
    @NotNull(message="Event time: ")
    private String event_time;
    private List<UserJob> userJobs;
    
    public Event(String event_id, String event_name, String event_type) {
    	this.event_id = event_id;
    	this.event_name = event_name;
    	this.event_type = event_type;
    }
    
    public void addUserJob (UserJob user_job) {
    	this.userJobs.add(user_job);
    }
    
    public void removeUserJob(UserJob user_job) {
    	this.userJobs.remove(user_job);
    	for(int i = 0; i < this.userJobs.size(); i++) {
    		this.userJobs.get(i).setEventId(this.id);
    	}
    }
    
    public List<String> getUserIdsFromUserJobs() {
    	List<String> userIds = new ArrayList<String>();
    	for(int i = 0; i < userJobs.size(); i++) {
    		userIds.add(userJobs.get(i).getId());
    	}
    	return userIds;
    }
    
    public String getId() {
    	return this.id;
    }
    
    public String getEventId() {
    	return this.event_id;
    }
    
    public String getEventName() {
    	return this.event_name;
    }
    
    public String getEventType() {
    	return this.event_type;
    }
    
    public String getEventLocation() {
    	return this.location_street_number +  " " + this.location_street_name;
    }
    
    public LocalDateTime getEventDate() {
    	return this.date;
    }
    
    public String getEventTime() {
    	return this.event_time;
    }
    
    public void setId(String id) {
    	this.id = id;
    }
    
    public void setEventId(String event_id) {
    	this.event_id = event_id;
    }
    
    public void setEventName(String event_name) {
    	this.event_name = event_name;
    }
    
    public void setEventType(String event_type) {
    	this.event_type = event_type;
    }
    
    public void setEventLocation(String location_street_number, String location_street_name) {
    	this.location_street_number = location_street_number;
    	this.location_street_name = location_street_name;
    }
    
    public void setEventDate(LocalDateTime date) {
    	this.date = date;
    }
    
    public void setEventTime(String event_time) {
    	this.event_time = event_time;
    }
    
    
    
}
