package com.gpch.mongo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpch.mongo.model.Email;
import com.gpch.mongo.repository.EmailRepository;

/**
 * 
 * @author Gage Hoefer
 * 
 * This class functions as the primary point of contact for all service methods involving Email
 * objects- it relies on the built in services offered by the EmailRepository class. These services
 * include saving email objects in the database, deleting email objects both in general and by a 
 * specific id, and manipulating email objects in various ways. 
 *
 */
@Service
public class EmailService {
	
	// This global variable is used for all service methods
	private EmailRepository emailRepository;
	
	/**
	 * @author Gage Hoefer
	 * @param emailRepository
	 * @return None
	 * 
	 * This is a simple constructor for the EmailService class. 
	 */
	@Autowired
	public EmailService(EmailRepository emailRepository) {
		this.emailRepository = emailRepository;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param email
	 * @return Email
	 * 
	 * This method is given an Email object, and uses an instance of the EmailRepository
	 * class to save the email in the database. 
	 */
	public Email saveEmail(Email email){
        return emailRepository.save(email);
    }

	/**
	 * @author Gage Hoefer
	 * @param None
	 * @return Iterable<Email>
	 * 
	 * This method uses an instance of the EmailRepository class to
	 * retrieve all emails saved in the database, and returns them
	 * in an iterable list of Email objects. 
	 */
    public Iterable<Email> getAllEmails(){
        return emailRepository.findAll();
    }

    /**
     * @author Gage Hoefer
     * @param None
     * @return None
     * 
     * This method uses an instance of the EmailRepository class to
     * delete all emails saved in the database. 
     */
    public void deleteAllEmail(){
        emailRepository.deleteAll();
    }

    /**
     * @author Gage Hoefer
     * @param id
     * @return None
     * 
     * This method is given a String representing an Email object id, 
     * and uses an instance of the EmailRepository class to delete the
     * email from the database based on said id.
     */
    public void deleteEmailById(String id){
        emailRepository.deleteById(id);
    }

    /**
     * @author Gage Hoefer
     * @param id
     * @return Optional<Email>
     * 
     * This method is given a String representing an Email object id, 
     * and uses an instance of the EmailRepository class to find the 
     * email in the database, returning the email in the form of an
     * Optional object of the type Email. 
     */
    public Optional<Email> findEmailById(String id){
        return emailRepository.findById(id);
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @param email_address
     * @return None
     * 
     * This method is given a String representing an Email object id, 
     * and a String representing a specific email address; with these
     * Strings, it uses an instance of the EmailRepository class to 
     * add the email address to the corresponding Email object. 
     */
    public void addUserEmailAddress(String id, String email_address) {
    	Email tempEmail = emailRepository.findById(id).get();
    	tempEmail.addEmail(email_address);
    	emailRepository.deleteById(id);
    	saveEmail(tempEmail);
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @param email_address
     * @return None
     * 
     * This method is given a String representing an Email object id, 
     * and a String representing a specific email address; with these
     * Strings, it uses an instance of the EmailRepository class to
     * find the Email object associated with the email id, and deletes
     * the email address from the list of email addresses corresponding
     * to that specific Email object. 
     */
    public void deleteUserEmailAddress(String id, String email_address) {
    	Email tempEmail = emailRepository.findById(id).get();
    	tempEmail.deleteEmail(email_address);
    	emailRepository.deleteById(id);
    	saveEmail(tempEmail);
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @return String
     * 
     * This method is given a String representing an Email object id, 
     * and uses an instance of the EmailRepository class to find the
     * Email object in the database associated with said id, returning
     * it in the form of an Email object. 
     */
    public String getEmailUserId(String id) {
    	return emailRepository.findById(id).get().getUserId();
    }

}
