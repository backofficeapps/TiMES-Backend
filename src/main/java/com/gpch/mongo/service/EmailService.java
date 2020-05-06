package com.gpch.mongo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpch.mongo.model.Email;
import com.gpch.mongo.repository.EmailRepository;

@Service
public class EmailService {
	
	private EmailRepository emailRepository;
	
	@Autowired
	public EmailService(EmailRepository emailRepository) {
		this.emailRepository = emailRepository;
	}
	
	public Email saveEmail(Email email){
        return emailRepository.save(email);
    }

    public Iterable<Email> getAllEmails(){
        return emailRepository.findAll();
    }

    public void deleteAllEmail(){
        emailRepository.deleteAll();
    }

    public void deleteEmailById(String id){
        emailRepository.deleteById(id);
    }

    public Optional<Email> findEmailById(String id){
        return emailRepository.findById(id);
    }
    
    public void addUserEmailAddress(String id, String email_address) {
    	Email tempEmail = emailRepository.findById(id).get();
    	tempEmail.addEmail(email_address);
    	emailRepository.deleteById(id);
    	saveEmail(tempEmail);
    }
    
    public void deleteUserEmailAddress(String id, String email_address) {
    	Email tempEmail = emailRepository.findById(id).get();
    	tempEmail.deleteEmail(email_address);
    	emailRepository.deleteById(id);
    	saveEmail(tempEmail);
    }
    
    public String getEmailUserId(String id) {
    	return emailRepository.findById(id).get().getUserId();
    }

}
