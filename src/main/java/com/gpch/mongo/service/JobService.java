package com.gpch.mongo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpch.mongo.model.Job;
import com.gpch.mongo.repository.JobRepository;

@Service
public class JobService {
	
	private JobRepository jobRepository;
	
	@Autowired
	public JobService(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}
	
	public Job saveJob(Job job){
        return jobRepository.save(job);
    }

    public Iterable<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    public void deleteAllJobs(){
        jobRepository.deleteAll();
    }

    public void deleteJobById(String id){
        jobRepository.deleteById(id);
    }

    public Optional<Job> findJobById(String id){
        return jobRepository.findById(id);
    }
    
    public void setJobId(String id, String job_id) {
    	Job tempJob = jobRepository.findById(id).get();
    	tempJob.setJobId(job_id);
    	jobRepository.deleteById(id);
    	saveJob(tempJob);
    }
    
    public void setJobType(String id, String job_type) {
    	Job tempJob = jobRepository.findById(id).get();
    	tempJob.setJobType(job_type);
    	jobRepository.deleteById(id);
    	saveJob(tempJob);
    }

}
