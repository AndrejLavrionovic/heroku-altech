package ie.gmit.sw.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.gmit.sw.dao.User;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String showLogin(){
		return "login";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedout(){
		
		return "loggedout";
	}
	
	@RequestMapping("/denied")
	public String showDenied(Model model, Principal principal){
		
		String username = null;
		try{
			username = principal.getName();
			model.addAttribute("username", username);
		}
		catch(NullPointerException ex){
			model.addAttribute("username", null);
		}
		
		return "denied";
	}
}
