package com.gpch.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "userjobs")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJob {
	
	@Id
	private String id;
	private String event_id;
	private String user_id;
	private String job_id;
	
	public UserJob(String event_id, String user_id, String job_id) {
		this.event_id = event_id;
		this.user_id = user_id;
		this.job_id = job_id;
	}
	
	public void setUserId(String user_id) {
		this.user_id = user_id;
	}
	
	public void setJobId(String job_id) {
		this.job_id = job_id;
	}
	
	public void setEventId(String event_id) {
		this.event_id = event_id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getUserId() {
		return this.user_id;
	}
	
	public String getJobId() {
		return this.job_id;
	}
	
	public String getEventId() {
		return this.event_id;
	}

}
