package com.gpch.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Gage Hoefer
 * 
 * This class defines the "Job" object stored as semi-persistent data in our database. 
 *
 */
@Data
@Document(collection="jobs")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {
	
	// This variable represents the unique id for the Job object
	@Id
	private String id;
	// This variable represents the job id which corresponds to the
	// job type, for the Job object
	@NotBlank(message="Job Id: ")
	private String job_id;
	// This variable represents the job type for the Job object
	@NotBlank(message="Job Type: ")
	private String job_type;
	
	/**
	 * @author Gage Hoefer
	 * @return String
	 * 
	 * This is a simple 'get' method which returns the type for the Job object. 
	 */
	public String getJobType() {
		return this.job_type;
	}
	
	/**
	 * @author Gage Hoefer
	 * @return String
	 * 
	 * This is a simple 'get' method which returns the job id for the Job object; this is 
	 * distinct from the id for the Job object, as the id refers to the unique id for the 
	 * object (and not the id corresponding to the job type).
	 */
	public String getJobId() {
		return this.job_id;
	}
	
	/**
	 * @author Gage Hoefer
	 * @return String
	 * 
	 * This is a simple 'get' method which returns the unique id for the Job object.
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param type String
	 * 
	 * This is a simple 'set' method which, given a String describing the job type,
	 * sets the type for the Job object.
	 */
	public void setJobType(String type) {
		this.job_type = type;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param id String
	 * 
	 * This is a simple 'set' method which, given a String describing the job id
	 * which corresponds to the job type, sets the id for the Job object. 
	 */
	public void setJobId(String id) {
		this.job_id = id;
	}

}
