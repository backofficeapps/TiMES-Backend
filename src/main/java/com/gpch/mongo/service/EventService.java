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

@Service
public class EventService {

    private EventRepository eventRepository;
    private UserJobRepository userJobRepository;

    @Autowired
    public EventService(EventRepository eventRepository, UserJobRepository userJobRepository) {
        this.eventRepository = eventRepository;
        this.userJobRepository = userJobRepository;
    }

    public Event saveEvent(Event event){
        return eventRepository.save(event);
    }

    public Iterable<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public void deleteAllEvents(){
        eventRepository.deleteAll();
    }

    public void deleteEventById(String id){
        eventRepository.deleteById(id);
    }

    public Optional<Event> findEventById(String id){
        return eventRepository.findById(id);
    }
    
    public void addUserJob(String event_id, String user_id, String job_id) {
    	UserJob tempUserJob = new UserJob(event_id, user_id, job_id);
    	userJobRepository.save(tempUserJob);
    	Event tempEvent = eventRepository.findById(event_id).get();
    	tempEvent.addUserJob(tempUserJob);
    	eventRepository.deleteById(event_id);
    	saveEvent(tempEvent);
    }
    
    public void deleteUserJob(String event_id, UserJob user_job) {
    	Event tempEvent = eventRepository.findById(event_id).get();
    	tempEvent.removeUserJob(user_job);
    	userJobRepository.deleteById(user_job.getId());
    	//eventRepository.deleteById(event_id);
    	saveEvent(tempEvent);
    }
    
    public void setEventId(String event_id, String new_id) {
    	Event tempEvent = eventRepository.findById(event_id).get();
    	tempEvent.setEventId(new_id);
    	eventRepository.deleteById(event_id);
    	saveEvent(tempEvent);
    }
    
    public List<UserJob> getAllUserJobsForEvent(String event_id) {
    	return eventRepository.findById(event_id).get().getUserJobs();
    }
    
    public void setEventName(String event_id, String event_name) {
    	Event tempEvent = eventRepository.findById(event_id).get();
    	tempEvent.setEventName(event_name);
    	eventRepository.deleteById(event_id);
    	saveEvent(tempEvent);
    }
    
    public void setEventType(String event_id, String event_type) {
    	Event tempEvent = eventRepository.findById(event_id).get();
    	tempEvent.setEventType(event_type);
    	eventRepository.deleteById(event_id);
    	saveEvent(tempEvent);
    }
    
    public void setEventLocation(String event_id, String event_street_name, String event_street_number) {
    	Event tempEvent = eventRepository.findById(event_id).get();
    	tempEvent.setEventLocation(event_street_number, event_street_name);
    	eventRepository.deleteById(event_id);
    	saveEvent(tempEvent);
    }
    
    public void setEventTime(String event_id, String event_time) {
    	Event tempEvent = eventRepository.findById(event_id).get();
    	tempEvent.setEventTime(event_time);
    	eventRepository.deleteById(event_id);
    	saveEvent(tempEvent);
    }
    
    public void setEventDate(String event_id, LocalDateTime event_date) {
    	Event tempEvent = eventRepository.findById(event_id).get();
    	tempEvent.setEventDate(event_date);
    	eventRepository.deleteById(event_id);
    	saveEvent(tempEvent);
    }
    
}