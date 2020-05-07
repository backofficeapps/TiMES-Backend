package com.gpch.mongo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.gpch.mongo.model.User;

/**
 * 
 * @author Gage Hoefer
 * 
 * This interface is used to manage User objects, where it inherits
 * all of the services offered by the generic "Repository" class imported from the SpringFramework library. 
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
