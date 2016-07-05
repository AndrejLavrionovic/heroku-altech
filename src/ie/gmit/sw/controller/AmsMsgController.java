package ie.gmit.sw.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.gmit.sw.dao.AmsMessage;
import ie.gmit.sw.dao.User;
import ie.gmit.sw.service.AmsMsgService;
import ie.gmit.sw.service.AmsMsgServiceImpl;
import ie.gmit.sw.service.UsersService;

@Controller
public class AmsMsgController {
	
	private AmsMsgService amsMsgService;
	private UsersService userService;
	
	@Autowired
	public void setAmsMsgService(AmsMsgService amsMsgService) {
		this.amsMsgService = amsMsgService;
	}
	
	@Autowired
	public void setUsersService(UsersService userService){
		this.userService = userService;
	}
	
	@RequestMapping(value="/createamsmsg", method=RequestMethod.POST)
	public String doCreateMsg(Model model, AmsMessage amsMessage, Principal principal){
		
		String username = principal.getName();
		
		amsMessage.getUser().setUsername(username);
		
		amsMsgService.createMsg(amsMessage);
		
		model.addAttribute("username", username);
		
		return "amsmsgcreated";
	}
	
	@RequestMapping("/showmsgs")
	public String showMsgs(Model model, Principal principal){
		
		List<AmsMessage> msgs = amsMsgService.getCurrent();
		
		model.addAttribute("msgs", msgs);
		
		String username = null;
		try{
			username = principal.getName();
			model.addAttribute("username", username);
		}
		catch(NullPointerException ex){
			model.addAttribute("username", null);
		}
		
		return "showmsgs";
	}
}
