package com.gpch.mongo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gpch.mongo.model.UserJob;

/**
 * 
 * @author Gage Hoefer
 * 
 * This interface is used to manage UserJob objects, where it inherits 
 * all of the services offered by the generic "Repository" class imported from the SpringFramework library. 
 *
 */
@Repository
public interface UserJobRepository extends CrudRepository<UserJob, String>{
}
