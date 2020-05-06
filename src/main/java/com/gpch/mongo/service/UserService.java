package com.gpch.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpch.mongo.model.Event;
import com.gpch.mongo.model.User;
import com.gpch.mongo.repository.EventRepository;
import com.gpch.mongo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService (UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User saveUser(User user){
        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    public void deleteUserById(String id){
        userRepository.deleteById(id);
    }

    public Optional<User> findUserById(String id){
        return userRepository.findById(id);
    }
    
    public void setUserFirstName (String id, String first_name) {
    	User tempUser = userRepository.findById(id).get();
    	tempUser.setUserFirstName(first_name);
    	userRepository.deleteById(id);
    	saveUser(tempUser);
    }
    
    public void setUserLastName (String id, String last_name) {
    	User tempUser = userRepository.findById(id).get();
    	tempUser.setUserLastName(last_name);
    	userRepository.deleteById(id);
    	saveUser(tempUser);
    }
    
    public void setUserPermissions (String id, String permissions) {
    	User tempUser = userRepository.findById(id).get();
    	tempUser.setUserPermissions(permissions);
    	userRepository.deleteById(id);
    	saveUser(tempUser);
    }
    
    public void addEvent(String id, String event_id) {
    	User tempUser = userRepository.findById(id).get();
    	tempUser.addEvent(event_id);
    	userRepository.deleteById(id);
    	saveUser(tempUser);
    }
    
    public void deleteEvent(String id, String event_id) {
    	User tempUser = userRepository.findById(id).get();
    	tempUser.deleteEvent(event_id);
    	userRepository.deleteById(id);
    	saveUser(tempUser);
    }
    
    public List<String> getAllEventIdsForUser(String id) {
    	return this.userRepository.findById(id).get().getUserEvents();
    }

}
