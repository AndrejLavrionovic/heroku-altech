package ie.gmit.sw.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HollidaysController {


	@RequestMapping("/hollidays")
	public String showCreate(Model model, Principal principal){
		
		String username = null;
		try{
			username = principal.getName();
			model.addAttribute("username", username);
		}
		catch(NullPointerException ex){
			model.addAttribute("username", null);
		}
		return "hollidays";
	}
}
