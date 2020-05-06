package com.gpch.mongo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gpch.mongo.service.UserService;
import com.gpch.mongo.model.User;

@Controller
public class UserThymeleafController {
	private UserService userService;
	
	@Autowired
	public UserThymeleafController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users-ui")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/delete-user/{id}")
    public String removeUser(@PathVariable("id") String id, Model model) {
        userService.deleteUserById(id);
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = {"/edit-add-user/{id}", "/edit-add-user"})
    public String editUser(@PathVariable("id") Optional<String> id, Model model) {
        User user = id.isPresent() ?
                userService.findUserById(id.get()).get() : new User();
        model.addAttribute("user", user);
        return "add-edit";
    }

    @PostMapping("/save-user")
    public String editUser(@ModelAttribute("user") @Valid User user,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-edit";
        }
        userService.saveUser(user);
        return "redirect:events-ui";
    }
}
