package com.smart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/") // home handler
	public String home(Model model) 
	{
		model.addAttribute("title","Home - Smart Contact Manager");
		return "home";
	}
	
	@RequestMapping("/about") // about handler
	public String about(Model model)
	{
		model.addAttribute("title", "About - Smart Contact Manger");
		return "about";
	}
	
	@RequestMapping("/signup") // sign up handler
	public String signup(Model model)
	{
		model.addAttribute("title", "Register - Smart Contact Manger");
		return "signup";
	}
	
	
}
