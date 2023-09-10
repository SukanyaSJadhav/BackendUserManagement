package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.UserAccount;
import com.example.service.UserAccountService;

@Controller
public class UserAccountController {

 
	@Autowired
	private UserAccountService service;
	
	@GetMapping("/")
	public String index(Model model) {   //Model is used to send data from controller to the UI
		model.addAttribute("user", new UserAccount()); //"user"->key and new UserAccount->object
		return "index";
	}
	
	@PostMapping("/save-user")
	public String handleSubmitBtn(@ModelAttribute("user") UserAccount user, Model model) {
		
		String msg= service.saveOrUpdateUserAcc(user);
		model.addAttribute("msg", msg);
		return "index";
	}
	
	
}
