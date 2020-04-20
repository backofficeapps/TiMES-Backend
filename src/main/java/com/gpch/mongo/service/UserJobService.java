package com.gpch.mongo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpch.mongo.model.UserJob;
import com.gpch.mongo.repository.UserJobRepository;

@Service
public class UserJobService {
	
	private UserJobRepository userJobRepository;
	
	@Autowired
	public UserJobService (UserJobRepository userJobRepository) {
		this.userJobRepository = userJobRepository;
	}

	public UserJob saveUserJob(UserJob userJob){
        return userJobRepository.save(userJob);
    }

    public Iterable<UserJob> getAllUserJobs(){
        return userJobRepository.findAll();
    }

    public void deleteAllUserJobs(){
        userJobRepository.deleteAll();
    }

    public void deleteUserJobById(String id){
        userJobRepository.deleteById(id);
    }

    public Optional<UserJob> findUserJobById(String id){
        return userJobRepository.findById(id);
    }
    
    public void setUserJobUserId(String id, String user_id) {
    	UserJob tempUserJob = userJobRepository.findById(id).get();
    	tempUserJob.setUserId(user_id);
    	userJobRepository.deleteById(id);
    	saveUserJob(tempUserJob);
    }
    
    public void setUserJobJobId(String id, String job_id) {
    	UserJob tempUserJob = userJobRepository.findById(id).get();
    	tempUserJob.setJobId(job_id);
    	userJobRepository.deleteById(id);
    	saveUserJob(tempUserJob);
    }
}
