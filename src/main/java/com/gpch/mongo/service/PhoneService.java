package com.gpch.mongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpch.mongo.model.Phone;
import com.gpch.mongo.repository.PhoneRepository;

@Service
public class PhoneService {
	
	private PhoneRepository phoneRepository;
	
	@Autowired
	public PhoneService (PhoneRepository phoneRepository) {
		this.phoneRepository = phoneRepository;
	}
	
	public Phone savePhone(Phone phone){
        return phoneRepository.save(phone);
    }

    public Iterable<Phone> getAllPhones(){
        return phoneRepository.findAll();
    }

    public void deleteAllPhones(){
        phoneRepository.deleteAll();
    }

    public void deletePhoneById(String id){
        phoneRepository.deleteById(id);
    }

    public Optional<Phone> findPhoneById(String id){
        return phoneRepository.findById(id);
    }
    
    public void addUserPhoneNumber(String id, String phone_number) {
    	Phone tempPhone = phoneRepository.findById(id).get();
    	tempPhone.addPhoneNumber(phone_number);
    	phoneRepository.deleteById(id);
    	savePhone(tempPhone);
    }
    
    public void deleteUserPhoneNumber(String id, String phone_number) {
    	Phone tempPhone = phoneRepository.findById(id).get();
    	tempPhone.removePhoneNumber(phone_number);
    	phoneRepository.deleteById(id);
    	savePhone(tempPhone);
    }
    
    public String getUserId(String id) {
    	return phoneRepository.findById(id).get().getUserId();
    }
    
    public String getAllPhoneNumbersAsStringForSpecificUser(String user_id) {
    	List<String> phoneNumbers = phoneRepository.findById(user_id).get().getPhoneNumbers();
    	String phoneString = "";
    	for(int i = 0; i < phoneNumbers.size(); i++) {
    		phoneString += phoneNumbers.get(i) + ", ";
    	}
    	return phoneString.substring(0, (phoneString.length()-2));
    }

}
