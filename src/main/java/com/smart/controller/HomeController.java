package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/") // home handler
	public String home(Model model) {
		model.addAttribute("title", "Home - Smart Contact Manager");
		return "home";
	}

	@RequestMapping("/about") // about handler
	public String about(Model model) {
		model.addAttribute("title", "About - Smart Contact Manger");
		return "about";
	}

	@RequestMapping("/signup") // sign up handler
	public String signup(Model model) {
		model.addAttribute("title", "Register - Smart Contact Manger");
		model.addAttribute("user", new User());
		return "signup";
	}

	// this handler for registering user
	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session) {

	try {
		
		if (!agreement) {
			System.out.println("You have not agreed the terms and conditions");
			throw new Exception("You have not agreed to the terms and condition");
		}
		
		user.setRole("ROLE_USER");
		user.setEnabled(true);
		user.setImageUrl("default.png");
		
		System.out.println("Agreement " + agreement);
		System.out.println("USER " + user);
		
		User result = this.userRepository.save(user);
		
		
		
		model.addAttribute("user", new User());
		session.setAttribute("message", new Message("Successfully Registered !!", "alert-success"));
		return "signup";
	}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something went wrong"+e.getMessage(), "alert-error"));
			return "signup";
		}
	}
}
