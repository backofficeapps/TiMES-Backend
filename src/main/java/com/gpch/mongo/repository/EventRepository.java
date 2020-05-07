package com.gpch.mongo.repository;

import com.gpch.mongo.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Gage Hoefer
 * 
 * This interface is used to manage Event objects, where it inherits
 * all of the services offered by the generic "Repository" class imported from the SpringFramework library. 
 *
 */
@Repository
public interface EventRepository extends CrudRepository<Event, String> {
}