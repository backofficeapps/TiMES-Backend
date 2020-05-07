package com.gpch.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="jobs")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {
	
	@Id
	private String id;
	@NotBlank(message="Job Id: ")
	private String job_id;
	@NotBlank(message="Job Type: ")
	private String job_type;
	
	/**
	 * @author Gage Hoefer
	 * @param None
	 * @return String
	 * 
	 * This method returns the type for the Job object. 
	 */
	public String getJobType() {
		return this.job_type;
	}
	
	public String getJobId() {
		return this.job_id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setJobType(String type) {
		this.job_type = type;
	}
	
	public void setJobId(String id) {
		this.job_id = id;
	}

}
