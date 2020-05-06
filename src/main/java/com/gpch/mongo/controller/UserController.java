package com.gpch.mongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpch.mongo.model.User;
import com.gpch.mongo.service.UserService;

@RestController
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public Iterable<User> getAllUsers() {
		return this.userService.getAllUsers();
	}

}
