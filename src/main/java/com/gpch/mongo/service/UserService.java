package com.gpch.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpch.mongo.model.Event;
import com.gpch.mongo.model.User;
import com.gpch.mongo.repository.EventRepository;
import com.gpch.mongo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * 
 * @author Gage Hoefer
 * 
 * This class functions as the primary point of contact for all service methods involving User
 * objects- it relies on the built in services offered by the UserRepository class. These services
 * include saving a user to the database, deleting a user (or users) from the database, and manipulating
 * the information stored in a User object in the database. 
 *
 */
@Service
public class UserService {
	
	// This global variable is used for all User service methods
	private UserRepository userRepository;
	
	/**
	 * @author Gage Hoefer
	 * @param userRepository
	 * @return None
	 * 
	 * This is a simple constructor for the UserService class. 
	 */
	@Autowired
	public UserService (UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param user
	 * @return User
	 * 
	 * This method is given a User object representing a specific user, 
	 * and uses an instance of the UserRepository class to save 
	 * the user in the database. 
	 */
	public User saveUser(User user){
        return userRepository.save(user);
    }

	/**
	 * @author Gage Hoefer
	 * @param None
	 * @return Iterable<User>
	 * 
	 * This method uses an instance of the UserRepository class to
	 * find all users stored in the database, returning them in the
	 * form of an iterable list of User objects. 
	 */
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * @author Gage Hoefer
     * @param None
     * @return None
     * 
     * This method uses an instance of the UserRepository class to
     * delete all users stored in the database. 
     */
    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    /**
     * @author Gage Hoefer
     * @param id
     * @return None
     * 
     * This method is given a String representing the id of a specific user
     * stored in the database, and uses an instance of the UserRepository 
     * class to delete the user based on said id. 
     */
    public void deleteUserById(String id){
        userRepository.deleteById(id);
    }

    /**
     * @author Gage Hoefer
     * @param id
     * @return Optional<User> 
     * 
     * This method is given a String representing the id of a specific user
     * stored in the database, and uses an instance of the UserRepository 
     * class to retrieve the user, returning it in the form of an Optional
     * object of the type User. 
     */
    public Optional<User> findUserById(String id){
        return userRepository.findById(id);
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @param first_name
     * @return None
     * 
     * This method is given a String representing the id for a specific user
     * stored in the database, and a String representing the first
     * name of the user; with these, it uses an instance of the UserRepository
     * class to set the first name of the user stored in the database corresponding
     * to said id. 
     */
    public void setUserFirstName (String id, String first_name) {
    	User tempUser = userRepository.findById(id).get();
    	tempUser.setUserFirstName(first_name);
    	userRepository.deleteById(id);
    	saveUser(tempUser);
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @param last_name
     * @return None
     * 
     * This method is given a String representing the id for a specific user
     * stored in the database, and a String representing the last
     * name of the user; with these, it uses an instance of the UserRepository
     * class to set the last name of the user stored in the database corresponding
     * to said id. 
     */
    public void setUserLastName (String id, String last_name) {
    	User tempUser = userRepository.findById(id).get();
    	tempUser.setUserLastName(last_name);
    	userRepository.deleteById(id);
    	saveUser(tempUser);
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @param permissions
     * @return None
     * 
     * This method is given a String representing the id for a specific user
     * stored in the database, and a String representing the permissions of the 
     * user; with these, it uses an instance of the UserRepository class to set 
     * the permissions of the user stored in the database corresponding
     * to said id. 
     */
    public void setUserPermissions (String id, String permissions) {
    	User tempUser = userRepository.findById(id).get();
    	tempUser.setUserPermissions(permissions);
    	userRepository.deleteById(id);
    	saveUser(tempUser);
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @param event_id
     * @return None
     * 
     * This method is given a String representing the id for a specific user
     * stored in the database, and a String representing an event id for an Event; 
     * with these, it uses an instance of the UserRepository class to add the event id
     * to the list of events of the user stored in the database corresponding
     * to said id. 
     */
    public void addEvent(String id, String event_id) {
    	User tempUser = userRepository.findById(id).get();
    	tempUser.addEvent(event_id);
    	userRepository.deleteById(id);
    	saveUser(tempUser);
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @param event_id
     * @return None
     * 
     * This method is given a String representing the id for a specific user
     * stored in the database, and a String representing an event id for an Event; 
     * with these, it uses an instance of the UserRepository class to delete the event id
     * from the list of events of the user stored in the database corresponding
     * to said id. 
     */
    public void deleteEvent(String id, String event_id) {
    	User tempUser = userRepository.findById(id).get();
    	tempUser.deleteEvent(event_id);
    	userRepository.deleteById(id);
    	saveUser(tempUser);
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @return List<String>
     * 
     * This method is given a String representing the id for a specific user
     * stored in the database; with this, it retrieves all events associated with
     * the user corresponding to said id, returning them in the form of a list of 
     * Strings. 
     */
    public List<String> getAllEventIdsForUser(String id) {
    	return this.userRepository.findById(id).get().getUserEvents();
    }

}
