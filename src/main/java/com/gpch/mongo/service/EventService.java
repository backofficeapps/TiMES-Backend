package com.gpch.mongo.service;

import com.gpch.mongo.model.Event;
import com.gpch.mongo.model.UserJob;
import com.gpch.mongo.repository.EventRepository;
import com.gpch.mongo.repository.UserJobRepository;
import com.gpch.mongo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 
 * @author Gage Hoefer
 * 
 * This class functions as the primary point of contact for all service methods involving Event
 * objects- it relies on the built in services offered by the EventRepository class. These services
 * include saving an event to the database, deleting a event (or events) from the database, and manipulating
 * the information stored in an Event object in the database.
 *
 */
@Service
public class EventService {

	// This global variable is used in all service methods for an EventService object
    private EventRepository eventRepository;
    // This global variable is used in some of the service methods for an EventService object
    private UserJobRepository userJobRepository;

    /**
     * @author Gage Hoefer
     * @param eventRepository
     * @param userJobRepository
     *  
     * 
     * This is a simple constructor for the EventService class. 
     */
    @Autowired
    public EventService(EventRepository eventRepository, UserJobRepository userJobRepository) {
        this.eventRepository = eventRepository;
        this.userJobRepository = userJobRepository;
    }

    /**
     * @author Gage Hoefer
     * @param event
     * @return Event
     * 
     * This method is given an Event object, and uses an instance of the
     * EventRepository class to save the Event to the database. 
     */
    public Event saveEvent(Event event){
        return eventRepository.save(event);
    }

    /**
     * @author Gage Hoefer
     *  
     * @return Iterable
     * 
     * This method uses an instance of the EventRepository class to
     * retrieve all Events stored in the database, returning them in 
     * the form of an iterable list of Events. 
     */
    public Iterable<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    /**
     * @author Gage Hoefer
     *  
     *  
     * 
     * This method uses an instance of the EventRepository class to 
     * delete all Events stored in the database. 
     */
    public void deleteAllEvents(){
        eventRepository.deleteAll();
    }

    /**
     * @author Gage Hoefer
     * @param id
     *  
     * 
     * This method is given a String representing the id for a 
     * specific Event object, and uses an instance of the EventRepository
     * class to delete the Event corresponding to said id. 
     */
    public void deleteEventById(String id){
        eventRepository.deleteById(id);
    }

    /**
     * @author Gage Hoefer
     * @param id
     * @return Optional
     * 
     * This method is given a String representing the id for a 
     * specific Event object, and uses an instance of the EventRepository
     * class to retrieve the event corresponding to said id, returning
     * it in the form of an Optional object of type Event. 
     */
    public Optional<Event> findEventById(String id){
        return eventRepository.findById(id);
    }
    
    /**
     * @author Gage Hoefer
     * @param event_id
     * @param user_id
     * @param job_id
     *  
     * 
     * This method is given a String representing the event id for 
     * a specific Event object, a String representing a user id, and 
     * a String representing a job id; with these, the method uses an 
     * instance of the EventRepository class to create a UserJob object, 
     * and adds it to the list of UserJobs associated with the Event
     * corresponding to said event id. 
     */
    public void addUserJob(String event_id, String user_id, String job_id) {
    	UserJob tempUserJob = new UserJob(event_id, user_id, job_id);
    	userJobRepository.save(tempUserJob);
    	Event tempEvent = eventRepository.findById(event_id).get();
    	tempEvent.addUserJob(tempUserJob);
    	eventRepository.deleteById(event_id);
    	saveEvent(tempEvent);
    }
    
    /**
     * @author Gage Hoefer
     * @param event_id
     * @param user_job
     *  
     * 
     * This method is given a String representing the event id for a 
     * specific Event stored in the database, and a UserJob object; with
     * these, it uses an instance of the EventRepository class to delete
     * the UserJob from the list of UserJobs associated with the Event
     * corresponding to said event id. 
     */
    public void deleteUserJob(String event_id, UserJob user_job) {
    	Event tempEvent = eventRepository.findById(event_id).get();
    	tempEvent.removeUserJob(user_job);
    	userJobRepository.deleteById(user_job.getId());
    	//eventRepository.deleteById(event_id);
    	saveEvent(tempEvent);
    }
    
    /**
     * @author Gage Hoefer
     * @param event_id
     * @param new_id
     *  
     * 
     * This method is given a String representing the event id for a
     * specific Event stored in the database, and a String representing
     * a new event id; with these, the method uses an instance of the 
     * EventRepository class to set the new event id of the Event corresponding
     * to said event id. 
     */
    public void setEventId(String event_id, String new_id) {
    	Event tempEvent = eventRepository.findById(event_id).get();
    	tempEvent.setEventId(new_id);
    	eventRepository.deleteById(event_id);
    	saveEvent(tempEvent);
    }
    
    /**
     * @author Gage Hoefer
     * @param event_id
     * @return List
     * 
     * This method is given a String representing the event id for a 
     * specific Event stored in the database, and uses an instance of the
     * EventRepository class to retrieve all UserJobs associated with the
     * Event corresponding to said event it; it returns these in the form of
     * a list of UserJobs. 
     */
    public List<UserJob> getAllUserJobsForEvent(String event_id) {
    	return eventRepository.findById(event_id).get().getUserJobs();
    }
    
    /**
     * @author Gage Hoefer
     * @param event_id
     * @param event_name
     *  
     * 
     * This method is given a String representing the event id for a
     * specific Event stored in the database, and a String representing
     * an event name; with these, the method uses an instance of the 
     * EventRepository class to set the event name of the Event
     * corresponding to said event id. 
     */
    public void setEventName(String event_id, String event_name) {
    	Event tempEvent = eventRepository.findById(event_id).get();
    	tempEvent.setEventName(event_name);
    	eventRepository.deleteById(event_id);
    	saveEvent(tempEvent);
    }
    
    /**
     * @author Gage Hoefer
     * @param event_id
     * @param event_type
     *  
     * 
     * This method is given a String representing the event id for a
     * specific Event stored in the database, and a String representing
     * an event type; with these, the method uses an instance of the 
     * EventRepository class to set the event type of the Event 
     * corresponding to said event id. 
     */
    public void setEventType(String event_id, String event_type) {
    	Event tempEvent = eventRepository.findById(event_id).get();
    	tempEvent.setEventType(event_type);
    	eventRepository.deleteById(event_id);
    	saveEvent(tempEvent);
    }
    
    /**
     * @author Gage Hoefer
     * @param event_id
     * @param event_street_name
     * @param event_street_number
     *  
     * 
     * This method is given a String representing the event id for a
     * specific Event stored in the database, a String representing
     * an event street name, and a String representing an event street number; 
     * with these, the method uses an instance of the 
     * EventRepository class to set the location of the Event 
     * corresponding to said event id. 
     */
    public void setEventLocation(String event_id, String event_street_name, String event_street_number) {
    	Event tempEvent = eventRepository.findById(event_id).get();
    	tempEvent.setEventLocation(event_street_number, event_street_name);
    	eventRepository.deleteById(event_id);
    	saveEvent(tempEvent);
    }
    
    /**
     * @author Gage Hoefer
     * @param event_id
     * @param event_time
     *  
     * 
     * This method is given a String representing the event id for a
     * specific Event stored in the database, and a String representing
     * an event time; with these, the method uses an instance of the 
     * EventRepository class to set the event time of the Event 
     * corresponding to said event id. 
     */
    public void setEventTime(String event_id, String event_time) {
    	Event tempEvent = eventRepository.findById(event_id).get();
    	tempEvent.setEventTime(event_time);
    	eventRepository.deleteById(event_id);
    	saveEvent(tempEvent);
    }
    
    /**
     * @author Gage Hoefer
     * @param event_id
     * @param event_date
     *  
     * 
     * This method is given a String representing the event id for a
     * specific Event stored in the database, and a LocalDateTime object
     * representing an event date; with these, the method uses an instance of the 
     * EventRepository class to set the event date of the Event 
     * corresponding to said event id. 
     */
    public void setEventDate(String event_id, LocalDateTime event_date) {
    	Event tempEvent = eventRepository.findById(event_id).get();
    	tempEvent.setEventDate(event_date);
    	eventRepository.deleteById(event_id);
    	saveEvent(tempEvent);
    }
    
}