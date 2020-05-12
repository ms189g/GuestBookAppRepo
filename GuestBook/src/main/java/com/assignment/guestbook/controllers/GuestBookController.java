package com.assignment.guestbook.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.assignment.guestbook.entities.GuestBookEntity;
import com.assignment.guestbook.entities.UserEventsEntity;
import com.assignment.guestbook.repositories.GuestBookRepository;
import com.assignment.guestbook.repositories.UserEventsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@RequestMapping(path = "/guestbook")
public class GuestBookController {
	@Autowired
	private GuestBookRepository guestBookRepository;

	@Autowired
	private UserEventsRepository userEventsRepository;
	
	private static final Logger LOGGER = LogManager.getLogger(GuestBookController.class);
	
	@RequestMapping("/")
	public ModelAndView showLoginPage(Model model) {
		
		LOGGER.info("Inside showLoginPage");
		model.addAttribute("message", "Welcome to GuestBook Application");
		
		try {
		GuestBookEntity n = new GuestBookEntity();

		n.setFirstName("Administrator");
		n.setLastName("Administrator");
		n.setUserName("admin");
		n.setPassword("admin");
		n.setAddress("DummyAddr");
		n.setAge(100);

		guestBookRepository.save(n);
		}
		catch(Exception e) {
		}
		return new ModelAndView("login");
	}

	@GetMapping("/addUser")
	public ModelAndView addUser(Model model) {
		model.addAttribute("message", "Please Enter the Following Details");
		return new ModelAndView("Register");
	}
	
	@PostMapping("/deleteUser")
	public ModelAndView deleteUser(Model model, @RequestParam String userId) {

		userEventsRepository.deleteById(Integer.parseInt(userId));

		List<UserEventsEntity> allUsers = (List<UserEventsEntity>)userEventsRepository.findAll();
		model.addAttribute("allUsersList", allUsers);
		model.addAttribute("message", "Record Deleted Successfully !!");
		model.addAttribute("firstName", "Admin");
		return new ModelAndView("dashboard");
	}

	@PostMapping(path = "/registerUser")
	public ModelAndView registerUser(Model model, @RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String userName, @RequestParam String password, @RequestParam String address , @RequestParam String age) {

		GuestBookEntity n = new GuestBookEntity();

		n.setFirstName(firstName);
		n.setLastName(lastName);
		n.setUserName(userName);
		n.setPassword(password);
		n.setAddress(address);
		n.setAge(Integer.parseInt(age));

		guestBookRepository.save(n);

		System.out.println("Record svaed to DB...");
		model.addAttribute("userName", userName);
		return new ModelAndView("thankyou");
	}

	@RequestMapping(path = "/back")
	public ModelAndView backToLogin(Model model) {
		return new ModelAndView("login");
	}
	
	@RequestMapping(path = "/backToDashboard")
	public ModelAndView backToDashboard(Model model) {
		
		List<UserEventsEntity> allUsers = (List<UserEventsEntity>)userEventsRepository.findAll();
		model.addAttribute("allUsersList", allUsers);
		model.addAttribute("firstName", "Admin");
		return new ModelAndView("dashboard");
	}
	
	@RequestMapping(path = "/editUser")
	public ModelAndView editUser(Model model, @RequestParam String userId) {
		
		UserEventsEntity userDetails = userEventsRepository.fetchUserById(Integer.parseInt(userId));
		model.addAttribute("userDetails", userDetails);
		System.out.println("User id: "+ userDetails);
		return new ModelAndView("EditUser");
	}

	@PostMapping(path = "/approveRecord")
	public ModelAndView approveRecord(Model model, @RequestParam String userId) {
		
		int count = userEventsRepository.updateApprovedStatus("Approved", Integer.parseInt(userId));
		
		if (count > 0) {
			
			List<UserEventsEntity> allUsers = (List<UserEventsEntity>)userEventsRepository.findAll();
			model.addAttribute("allUsersList", allUsers);
			model.addAttribute("message", "Record Status Updated Successfully !!");
			model.addAttribute("firstName", "Admin");
			return new ModelAndView("dashboard");
		} else {
			return new ModelAndView("SystemError");
		}
	}
	
	@PostMapping(path = "/updateUser")
	public ModelAndView updateUser(Model model, @RequestParam String id, @RequestParam String userName, @RequestParam String eventName,
			@RequestParam String eventDate, @RequestParam String notes, @RequestParam String fileName) {

		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(eventDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int count = userEventsRepository.updateUserById(Integer.parseInt(id), userName, eventName, date, notes,
				fileName);

		System.out.println("Record Updated in DB...");

		if (count > 0) {
			model.addAttribute("updateMessage", "User Details Updated Successfully !!");
			return new ModelAndView("UpdateSuccess");
		} else {
			return new ModelAndView("SystemError");
		}

	}
	
	@RequestMapping(path = "/addEvent")
	public ModelAndView addEvent(Model model, @RequestParam String userId, @RequestParam String eventName, @RequestParam String eventDate,
			@RequestParam String notes, @RequestParam("img") MultipartFile img, @RequestParam String firstName, @RequestParam String lastName) {
		
		UserEventsEntity uEvents = new UserEventsEntity();
		
		uEvents.setUserName(userId);
		uEvents.setEventName(eventName);
		
		try {
			Date date=new SimpleDateFormat("yyyy-MM-dd").parse(eventDate);
			uEvents.setEventDate(date);
			uEvents.setNotes(notes);
			
			if(img.getSize() > 0) {
				uEvents.setPicByte(img.getBytes());
				uEvents.setFileName(img.getOriginalFilename());
			}
			else {
				uEvents.setPicByte(null);
				uEvents.setFileName(null);
			}
			
			System.out.println("Image byte: "+img.getSize()+"------"+img.getBytes());
			uEvents.setIsApproved("Not Approved");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		userEventsRepository.save(uEvents);
		
		model.addAttribute("userMessage", "Event Posted Successfully !!");
		
		model.addAttribute("firstName", firstName);
		model.addAttribute("lastName", lastName);
		model.addAttribute("userName", userId);
		
		List<UserEventsEntity> allUserEvents = (List<UserEventsEntity>)userEventsRepository.fetchUserEvent(userId);
		model.addAttribute("allUserEvents", allUserEvents);
		
		return new ModelAndView("UserDashboard");
	}
	
	@PostMapping(path = "/validateUser")
	public ModelAndView validateUser(Model model, @RequestParam String userName, 
			@RequestParam String password) {

		System.out.println("password svaed to DB..." + password);
		GuestBookEntity ge = guestBookRepository.fetchUser(userName);

		if (null != ge && ge.getPassword().equals(password)) {

			if(!userName.equals("admin")) {
				@SuppressWarnings("unchecked")
				List<UserEventsEntity> allEvents = (List<UserEventsEntity>)userEventsRepository.fetchUserEvent(userName);
				model.addAttribute("allUserEvents", allEvents);
				
				model.addAttribute("firstName", ge.getFirstName());
				model.addAttribute("lastName", ge.getLastName());
				model.addAttribute("userName", userName);
				
				return new ModelAndView("UserDashboard");
			}
			else {
				List<UserEventsEntity> allUsers = (List<UserEventsEntity>)userEventsRepository.findAll();
				model.addAttribute("allUsersList", allUsers);
				model.addAttribute("firstName", "Admin");
				
				return new ModelAndView("dashboard");
			}
		}
		else if (null != ge)
			return new ModelAndView("loginError");
		else
			return new ModelAndView("systemError");
	}

}
