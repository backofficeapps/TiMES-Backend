package com.gpch.mongo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "phones")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
	
	//@Id
	//private String id;
	@Id
	private String user_id;
	private List<String> phone_numbers;
	
	public Phone(String user_id) {
		this.user_id = user_id;
	}
	
	public void addPhoneNumber(String phone_number) {
		this.phone_numbers.add(phone_number);
	}
	
	public void removePhoneNumber(String phone_number) {
		this.phone_numbers.remove(phone_number);
	}
	
	public String getUserId() {
		return this.user_id;
	}
	
	public List<String> getPhoneNumbers() {
		return this.phone_numbers;
	}
	
}

