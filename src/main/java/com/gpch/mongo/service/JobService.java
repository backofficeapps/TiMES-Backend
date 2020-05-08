package com.gpch.mongo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpch.mongo.model.Job;
import com.gpch.mongo.repository.JobRepository;

/**
 * 
 * @author Gage Hoefer
 * 
 * This class functions as the primary point of contact for all service methods involving Job
 * objects- it relies on the built in services offered by the JobRepository class. These services
 * include saving job objects in the database, deleting job objects both in general and by a 
 * specific id, and manipulating job objects in various ways. 
 *
 */
@Service
public class JobService {
	
	// This global variable is used for all service methods for a Job object
	private JobRepository jobRepository;
	
	/**
	 * @author Gage Hoefer
	 * @param jobRepository
	 *  
	 * 
	 * This is a simple constructor for the JobService class. 
	 */
	@Autowired
	public JobService(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param job
	 *  
	 * 
	 * This method is given a Job object, and uses an instance of 
	 * the JobRepository class to save the Job to the database. 
	 */
	public Job saveJob(Job job){
        return jobRepository.save(job);
    }

	/**
	 * @author Gage Hoefer
	 *  
	 * @return Iterable
	 * 
	 * This method uses an instance of the JobRepository class to retrieve
	 * all Job objects stored in the database, returning them in the form
	 * of an iterable list of Jobs. 
	 */
    public Iterable<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    /**
     * @author Gage Hoefer
     *  
     *  
     * 
     * This method uses an instance of the JobRepository class to 
     * delete all Jobs stored in the database. 
     */
    public void deleteAllJobs(){
        jobRepository.deleteAll();
    }

    /**
     * @author Gage Hoefer
     * @param id
     *  
     * 
     * This method is given a String representing an id for a specific
     * Job stored in the database, and uses an instance of the JobRepository 
     * class to delete the job based on said id. 
     */
    public void deleteJobById(String id){
        jobRepository.deleteById(id);
    }

    /**
     * @author Gage Hoefer
     * @param id
     * @return Optional
     * 
     * This method is given a String representing an id for a specific
     * Job stored in the database, and uses an instance of the JobRepository
     * class to retrieve the job based on said id; it returns the Job
     * in the form of an Optional object of type Job. 
     */
    public Optional<Job> findJobById(String id){
        return jobRepository.findById(id);
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @param job_id
     *  
     * 
     * This method is given a String representing an id for a specific Job
     * stored in the database, and a String representing a job id; with these,
     * it uses an instance of the JobRepository class to set the job id of the 
     * Job object. 
     */
    public void setJobId(String id, String job_id) {
    	Job tempJob = jobRepository.findById(id).get();
    	tempJob.setJobId(job_id);
    	jobRepository.deleteById(id);
    	saveJob(tempJob);
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @param job_type
     *  
     * 
     * This method is given a String representing an id for a specific Job
     * stored in the database, and a String representing a type of job; with 
     * these, it uses an instance of the JobRepository class to set the job
     * type of the Job corresponding to said id. 
     */
    public void setJobType(String id, String job_type) {
    	Job tempJob = jobRepository.findById(id).get();
    	tempJob.setJobType(job_type);
    	jobRepository.deleteById(id);
    	saveJob(tempJob);
    }

}
