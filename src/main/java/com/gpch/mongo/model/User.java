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

@Data
@Document(collection = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	private String user_id;
	private String user_first_name;
	private String user_last_name;
	private String user_permissions;
	private List<String> user_events;
	
	public User(String user_first_name, String user_last_name, String user_permissions, List<String> user_events) {
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_permissions = user_permissions;
		this.user_events = user_events;
	}
	
	public void setUserEvents(List<String> user_events) {
		this.user_events = user_events;
	}
	
	public List<String> getUserEvents() {
		return this.user_events;
	}
	
	public void addEvent(String event_id) {
		this.user_events.add(event_id);
	}
	
	public void deleteEvent(String event_id) {
		this.user_events.remove(event_id);
	}
	
	public void setUserFirstName(String user_first_name) {
		this.user_first_name = user_first_name;
	}
	
	public String getUserFirstName() {
		return this.user_first_name;
	}
	
	public void setUserLastName(String user_last_name) {
		this.user_last_name = user_last_name;
	}
	
	public String getUserLastName() {
		return this.user_last_name;
	}
	
	public String getUserId() {
		return this.user_id;
	}
	
	public String getUserPermissions() {
		return this.user_permissions;
	}
	
	public void setUserPermissions(String user_permissions) {
		this.user_permissions = user_permissions;
	}

}
