package ie.gmit.sw.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.gmit.sw.dao.User;
import ie.gmit.sw.service.UsersService;

@Controller
public class UsersController {
	
	private static Logger logger = Logger.getLogger(UsersController.class);
	
	private UsersService userService;
	private List<User> users = null;
	private String error = null;
	private String message = null;
	
	@Autowired
	public void setUserService(UsersService userService) {
		this.userService = userService;
	}

	private String getUsername(Principal principal){
		return principal.getName();
	}
	
	
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// USERS.JSP PAGE EXECUTION
	// POPULATES TABLE WITH USERS
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@RequestMapping("/users")
	public String showUsers(Model model, Principal principal){
		try{
			model.addAttribute("username", getUsername(principal)); // is user logged in
			
			model.addAttribute("users", null);
			model.addAttribute("search", new User());
			
			return "users";
		}
		catch(NullPointerException ex){
			model.addAttribute("username", null);
			
			return "login";
		}
	}
	

	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// CREATE.JSP PAGE EXECUTION
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@RequestMapping("/create")
	public String createUser(Model model, Principal principal){
		model.addAttribute("user", new User());
		
		String username = null;
		try{
			username = principal.getName();
			model.addAttribute("username", username);
		}
		catch(NullPointerException ex){
			model.addAttribute("username", null);
		}
		
		return "create";
	}

	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// CREATE USER FORM EXECUTION
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String doCreate(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, Principal principal){
		
		
		try{
			model.addAttribute("username", principal.getName());
		}
		catch(NullPointerException ex){
			model.addAttribute("username", null);
			return "login";
		}
		try{
			user.setDob();
			user.setTel(user.getTelPrefix(), user.getTelNumber());
		}catch(NullPointerException ex){
			logger.info("---> DOB: " + user.getDob().toString());
			return "create";
		}
		
		// Validation #1
		//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		if(result.hasErrors()){
			logger.info("DATA IS NOT VALID");
			logger.warn("Object name --> " + result.getObjectName());
			logger.warn("Errors --> " + result.getErrorCount());
			List<String> fields = new ArrayList<String>();
			List<FieldError> f = result.getFieldErrors();
			for(FieldError fe : f){
				if(!fields.contains(fe.getField())){
					fields.add(fe.getField());
				}
				
			}
			logger.info("Wrong fields -> " + fields.size());
			for(String fld : fields){
				logger.info("--> " + fld);
			}
			
			Integer selectFields = new Integer(1);
			
			model.addAttribute("selctFields", selectFields);
			
			return "create";
		}
		
		try { // if username allready exists
			userService.create(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.users.username", "This username allready exists!");
			return "create";
		}
		
		return "usercreated";
	}
	
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// RETRIEVE USER BY ITS PARAMS
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@RequestMapping(value="/getusers", method=RequestMethod.POST)
	public String showUserById(Model model, User search, Principal principal){
		
		String signedUser = null;
		User user = null;
		boolean found = false;
		
		// Searcing parameters
		Integer empnum = search.getEmpnum();
		String username = search.getUsername();
		String firstname = search.getFirstname();
		String lastname = search.getLastname();
		String email = search.getEmail();
		
		logger.info("Users email ---> " + email);
		
		// check if user is signed in.
		try{
			signedUser = principal.getName();
			model.addAttribute("username", signedUser);
		}
		catch(NullPointerException ex){
			model.addAttribute("username", null);
			return "login";
		}
		
		if(empnum == null &&
		   username.isEmpty() &&
		   firstname.isEmpty() &&
		   lastname.isEmpty() &&
		   email.isEmpty()){
			found = false;
			error = "Searching parameters are not provided.";
		}
		else{
			users = new ArrayList<User>();
			// if empnum is entered.
			if(empnum != null && empnum > 100000){
				user = userService.getUserByEmpnum(empnum);
				
				if(user != null){
					found = true;
					users.add(user);
					model.addAttribute("users", users);
					model.addAttribute("error", null);
					model.addAttribute("message", "Result: 1 user is found.");
					model.addAttribute("search", new User());
					
					return "users";
				}
			}
			if(!username.isEmpty() && username != null){ // if username is entered
				user = userService.getUserByUsername(username);
				
				if(user != null){
					found = true;
					users.add(user);
					model.addAttribute("users", users);
					model.addAttribute("error", null);
					model.addAttribute("message", "Result: 1 user is found.");
					model.addAttribute("search", new User());
					
					return "users";
				}
			}
			if(firstname != null && !firstname.isEmpty()){ // if firstname is entered
				users = userService.getUsersByFirstname(firstname);
				
				if(users.size() > 0){
					if(users.size() == 1)
						message = "Result: 1 user is found.";
					else {
						message = "Result: " + users.size() + " users are found.";
					}
					found = true;
					model.addAttribute("users", users);
					model.addAttribute("error", null);
					model.addAttribute("message", message);
					model.addAttribute("search", new User());
					
					return "users";
				}
			}
			if(lastname != null && !lastname.isEmpty()){ // if lastname was entered
				users = userService.getUsersByLastname(lastname);
				
				if(users.size() > 0){
					if(users.size() == 1)
						message = "Result: 1 user is found.";
					else {
						message = "Result: " + users.size() + " users are found.";
					}
					found = true;
					users = userService.getUsersByLastname(lastname);
					model.addAttribute("users", users);
					model.addAttribute("error", null);
					model.addAttribute("message", message);
					model.addAttribute("search", new User());
					
					return "users";
				}
			}
			if(!email.isEmpty() && email != null){ // if email is entered
				user = userService.getUserByEmail(email);
				
				if(user != null){
					found = true;
					users.add(user);
					model.addAttribute("users", users);
					model.addAttribute("error", null);
					model.addAttribute("message", "Result: 1 user is found.");
					model.addAttribute("search", new User());
					
					return "users";
				}
			}
		}
		
		if(found == false)
			error = "The user is not found.";
			
		model.addAttribute("users", null);
		model.addAttribute("error", error);
		model.addAttribute("message", null);
		model.addAttribute("search", new User());
		
		return "users";
	}
	
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// PROCEED TO THE EDIT USER FORM
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@RequestMapping(value="/edituser", method=RequestMethod.GET)
	public String goToUpdateUserForm(HttpServletRequest request, Model model, Principal principal){

		try{
			model.addAttribute("username", getUsername(principal)); // is user logged in
		}
		catch(NullPointerException ex){
			model.addAttribute("username", null);
			
			return "login";
		}
		
		try{
			
			model.addAttribute("user", userService.getUserByUsername(request.getParameter("u")));
			
			return "edituserform";
		}
		catch(NullPointerException ex){
			
			error = "Some issues with retrieving user's data.";
			return "users";
		}
	}
	
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// UPDATE EXISTING USER
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@RequestMapping(value="/doupdateuser", method=RequestMethod.POST)
	public String doUpdateUser(@Valid User user, BindingResult result, Model model, Principal principal){

		logger.info(user.toString());
		// Validation #1
		//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		/*
		if(result.hasErrors()){
			System.out.println("NOT VALID");
			
			model.addAttribute("user", user);
			return "edituserform";
		}
		*/
		
		try{
			model.addAttribute("username", principal.getName());
		}
		catch(NullPointerException ex){
			model.addAttribute("username", null);
			return "login";
		}
		
		if(userService.update(user) == 1){
			model.addAttribute("message", "User have being udated.");
			model.addAttribute("users", null);
			model.addAttribute("error", null);
			model.addAttribute("search", new User());
			
			return "users";
		}
		else{
			model.addAttribute("message", null);
			model.addAttribute("users", null);
			model.addAttribute("error", "Users havn't being updated.");
			model.addAttribute("search", new User());
		}
		
		return "users";
	}
	
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// DELETE EXISTING USER
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@RequestMapping(value="/deleteuser", method=RequestMethod.GET)
	public String deleteUser(HttpServletRequest request, Model model, Principal principal){

		try{
			model.addAttribute("username", getUsername(principal)); // is user logged in
		}
		catch(NullPointerException ex){
			model.addAttribute("username", null);
			
			return "login";
		}
			
		if(userService.deleteUser(request.getParameter("u"))){
			model.addAttribute("message", "The user was permanently deleted.");
			model.addAttribute("search", new User());
			model.addAttribute("users", null);
			model.addAttribute("error", null);
			
			return "users";
		}
		
		model.addAttribute("message", null);
		model.addAttribute("search", new User());
		model.addAttribute("users", null);
		model.addAttribute("error", "Deletion is failed. User havn't being deleted.");
		
		return "users";
	}
	
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// USERINFO.JSP
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@RequestMapping(value="/userinfo", method=RequestMethod.GET)
	public String showUserinfo(HttpServletRequest request, Model model, Principal principal){

		User user = null;
		String username;
		
		try{
			model.addAttribute("username", principal.getName());
		}
		catch(NullPointerException ex){
			model.addAttribute("username", null);
			return "login";
		}
		
		username = request.getParameter("u");
		
		try{
			if(!username.isEmpty() && username != null){ // if username is entered
				user = userService.getUserByUsername(username);
				model.addAttribute("user", user);
			}
			else{
				model.addAttribute("message", null);
				model.addAttribute("search", new User());
				model.addAttribute("users", null);
				model.addAttribute("error", "Error: System couldn't retrieve user's info. Try again later.");
				
				return "users";
			}
		}
		catch(NullPointerException ex){
			model.addAttribute("message", null);
			model.addAttribute("search", new User());
			model.addAttribute("users", null);
			model.addAttribute("error", "Error: System couldn't retrieve user's info. Try again later.");
			
			return "users";
		}
		
		return "userinfo";
	}
}
