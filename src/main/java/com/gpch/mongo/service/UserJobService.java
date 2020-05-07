package com.gpch.mongo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpch.mongo.model.UserJob;
import com.gpch.mongo.repository.UserJobRepository;

/**
 * 
 * @author Gage Hoefer
 * 
 * This class functions as the primary point of contact for all service methods involving UserJob
 * objects- it relies on the built in services offered by the UserJobRepository class. These services
 * include saving UserJob objects in the database, deleting UserJob objects both in general and by a 
 * specific id, and manipulating UserJob objects in various ways.
 *
 */
@Service
public class UserJobService {
	
	// This global variable is used in all service methods for a UserJob object
	private UserJobRepository userJobRepository;
	
	/**
	 * @author Gage Hoefer
	 * @param userJobRepository
	 * @return None
	 * 
	 * This is a simple constructor for the UserJobService class. 
	 */
	@Autowired
	public UserJobService (UserJobRepository userJobRepository) {
		this.userJobRepository = userJobRepository;
	}

	/**
	 * @author Gage Hoefer
	 * @param userJob
	 * @return UserJob
	 * 
	 * This method is given a UserJob object, and uses an 
	 * instance of the UserJobRepository class to save said
	 * object in the database. 
	 */
	public UserJob saveUserJob(UserJob userJob){
        return userJobRepository.save(userJob);
    }

	/**
	 * @author Gage Hoefer
	 * @param None
	 * @return Iterable<UserJob>
	 * 
	 * This method uses an instance of the UserJobRepository class to
	 * retrieve all UserJob objects stored in the database, returning 
	 * them in the form of an iterable list of UserJob objects. 
	 */
    public Iterable<UserJob> getAllUserJobs(){
        return userJobRepository.findAll();
    }

    /**
     * @author Gage Hoefer
     * @param None
     * @return None
     * 
     * This method uses an instance of the UserJobRepository class to
     * delete all UserJobs stored in the database. 
     */
    public void deleteAllUserJobs(){
        userJobRepository.deleteAll();
    }

    /**
     * @author Gage Hoefer
     * @param id
     * @return None
     * 
     * This method is given a String representing the id for a
     * specific UserJob object stored in the database, and uses an 
     * instance of the UserJobRepository class to delete the UserJob
     * based on said id. 
     */
    public void deleteUserJobById(String id){
        userJobRepository.deleteById(id);
    }

    /**
     * @author Gage Hoefer
     * @param id
     * @return Optional<UserJob> 
     * 
     * This method is given a String representing the id for a 
     * specific UserJob object stored in the database, and uses an
     * instance of the UserJobRepository class to retrieve the UserJob,
     * returning it in the form of an Optional object of type UserJob. 
     */
    public Optional<UserJob> findUserJobById(String id){
        return userJobRepository.findById(id);
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @param user_id
     * @return None
     * 
     * This method is given a String representing the id for a 
     * specific UserJob object, and a String representing a user id;
     * with these, the method uses an instance of the UserJobRepository 
     * class to set the user id for the UserJob.
     */
    public void setUserJobUserId(String id, String user_id) {
    	UserJob tempUserJob = userJobRepository.findById(id).get();
    	tempUserJob.setUserId(user_id);
    	userJobRepository.deleteById(id);
    	saveUserJob(tempUserJob);
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @param job_id
     * @return None
     * 
     * This method is given a String representing the id for a 
     * specific UserJob object, and a String representing a job id;
     * with these, the method uses an instance of the UserJobRepository
     * class to set the job id for the UserJob. 
     */
    public void setUserJobJobId(String id, String job_id) {
    	UserJob tempUserJob = userJobRepository.findById(id).get();
    	tempUserJob.setJobId(job_id);
    	userJobRepository.deleteById(id);
    	saveUserJob(tempUserJob);
    }
}
