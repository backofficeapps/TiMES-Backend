package com.gpch.mongo.service;

import com.gpch.mongo.model.Event;
import com.gpch.mongo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
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
}