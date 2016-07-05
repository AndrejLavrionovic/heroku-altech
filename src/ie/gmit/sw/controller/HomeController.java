package ie.gmit.sw.controller;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	private static Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping("/")
	public String showHome(Model model, Principal principal){
		
		String username = null;
		try{
			username = principal.getName();
			model.addAttribute("username", username);
		}
		catch(NullPointerException ex){
			model.addAttribute("username", null);
		}
		
		logger.info("Showing home page...");
		return "home";
	}
	
}
