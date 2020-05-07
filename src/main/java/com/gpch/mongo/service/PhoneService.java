package com.gpch.mongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpch.mongo.model.Phone;
import com.gpch.mongo.repository.PhoneRepository;

/**
 * 
 * @author Gage Hoefer
 * 
 * This class functions as the primary point of contact for all service methods involving Phone
 * objects- it relies on the built in services offered by the PhoneRepository class. These services
 * include saving a Phone object to the database, deleting a Phone object (or objects) from the database, 
 * and manipulating the information stored in a Phone object in the database.
 *
 */
@Service
public class PhoneService {
	
	// This global variable is used in all service methods for the PhoneService object
	private PhoneRepository phoneRepository;
	
	/**
	 * @author Gage Hoefer
	 * @param phoneRepository
	 * @return None
	 * 
	 * This is a simple constructor for the PhoneService class. 
	 */
	@Autowired
	public PhoneService (PhoneRepository phoneRepository) {
		this.phoneRepository = phoneRepository;
	}
	
	/**
	 * @author Gage Hoefer
	 * @param phone
	 * @return Phone
	 * 
	 * This method is given a Phone object, and uses an instance of
	 * the PhoneRepository class to save the Phone to the database. 
	 */
	public Phone savePhone(Phone phone){
        return phoneRepository.save(phone);
    }

	/**
	 * @author Gage Hoefer
	 * @param None
	 * @return Iterable<Phone>
	 * 
	 * This method uses an instance of the PhoneRepository class
	 * to retrieve all Phone objects stored in the database, returning
	 * them in the form of an iterable list of Phone objects. 
	 */
    public Iterable<Phone> getAllPhones(){
        return phoneRepository.findAll();
    }

    /**
     * @author Gage Hoefer
     * @param None
     * @return None
     * 
     * This method uses an instance of the PhoneRepository class
     * to delete all Phone objects stored in the database. 
     */
    public void deleteAllPhones(){
        phoneRepository.deleteAll();
    }

    /**
     * @author Gage Hoefer
     * @param id
     * @return None
     * 
     * This method is given a String representing the id for a 
     * specific Phone object, and uses an instance of the PhoneRepository class
     * to delete the Phone object corresponding to said id. 
     */
    public void deletePhoneById(String id){
        phoneRepository.deleteById(id);
    }

    /**
     * @author Gage Hoefer
     * @param id
     * @return Optional<Phone>
     * 
     * This method is given a String representing the id for a
     * specific Phone object, and uses an instance of the PhoneRepository class
     * to retrieve the Phone object based on said id, returning it in the form
     * of an Optional object of type Phone. 
     */
    public Optional<Phone> findPhoneById(String id){
        return phoneRepository.findById(id);
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @param phone_number
     * @return None
     * 
     * This method is given a String representing the id for a 
     * specific Phone object, and a String representing a phone number;
     * using these, it uses an instance of the PhoneRepository class
     * to add the phone number to the list of phone numbers associated with
     * the Phone object corresponding to said id.
     */
    public void addUserPhoneNumber(String id, String phone_number) {
    	Phone tempPhone = phoneRepository.findById(id).get();
    	tempPhone.addPhoneNumber(phone_number);
    	phoneRepository.deleteById(id);
    	savePhone(tempPhone);
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @param phone_number
     * @return None
     * 
     * This method is given a String representing the id for a 
     * specific Phone object, and a String representing a phone number;
     * using these, it uses an instance of the PhoneRepository class
     * to delete the phone number from the list of phone numbers associated with
     * the Phone object corresponding to said id.
     */
    public void deleteUserPhoneNumber(String id, String phone_number) {
    	Phone tempPhone = phoneRepository.findById(id).get();
    	tempPhone.removePhoneNumber(phone_number);
    	phoneRepository.deleteById(id);
    	savePhone(tempPhone);
    }
    
    /**
     * @author Gage Hoefer
     * @param id
     * @return String
     * 
     * This method is given a String representing the id for a 
     * specific Phone object, and uses an instance of the PhoneRepository
     * class to retrieve the user id associated with the Phone object
     * corresponding to said id, returning it in the form of a String. 
     */
    public String getUserId(String id) {
    	return phoneRepository.findById(id).get().getUserId();
    }
    
    /**
     * @author Gage Hoefer
     * @param user_id
     * @return String
     * 
     * This method is given a String representing a user id, and uses an
     * instance of the PhoneRepository class to find all phone numbers associated
     * to said user id, returning them in the form of a single String. 
     */
    public String getAllPhoneNumbersAsStringForSpecificUser(String user_id) {
    	List<String> phoneNumbers = phoneRepository.findById(user_id).get().getPhoneNumbers();
    	String phoneString = "";
    	for(int i = 0; i < phoneNumbers.size(); i++) {
    		phoneString += phoneNumbers.get(i) + ", ";
    	}
    	return phoneString.substring(0, (phoneString.length()-2));
    }

}
