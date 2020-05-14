package com.assignment.guestbook.controllers;

import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.assignment.guestbook.entities.GuestBookEntity;
import com.assignment.guestbook.entities.UserEventsEntity;
import com.assignment.guestbook.repositories.GuestBookRepository;
import com.assignment.guestbook.repositories.UserEventsRepository;

/**
 * GuestBookController, Controller Class for GuestBook Spring boot app
 * @author Manish
 *
 */

@Controller
@RequestMapping(path = "/guestbook")
public class GuestBookController {
	@Autowired
	private GuestBookRepository guestBookRepository;

	@Autowired
	private UserEventsRepository userEventsRepository;

	private static final Logger LOGGER = LogManager.getLogger(GuestBookController.class);

	/** Method showLoginPag, This controller API is used to Create Administrator User 
	 * at app start up and render Login Page
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView showLoginPage(Model model) {

		LOGGER.info("Inside showLoginPage method");
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
		} catch (Exception e) {
			LOGGER.error("Exception caught while rendering Login Page"+e.getMessage());
		}
		return new ModelAndView("login");
	}

	/**
	 * Method addUser, This controller API is used to render registration form New users 
	 * at app start up and render Login Page
	 * @param model
	 * @return
	 */
	@GetMapping("/addUser")
	public ModelAndView addUser(Model model) {
		LOGGER.info("Inside addUser method");
		model.addAttribute("message", "Please Enter the Following Details");
		return new ModelAndView("Register");
	}

	/**
	 * Method deleteUser, This controller API is used to delete User Events from DB
	 * @param model
	 * @param userId
	 * @return
	 */
	@PostMapping("/deleteUser")
	public ModelAndView deleteUser(Model model, @RequestParam String userId) {

		LOGGER.info("Inside deleteUser method");
		
		userEventsRepository.deleteById(Integer.parseInt(userId));
		LOGGER.debug("User Event record Deleeted from DB successfully !");
		
		List<UserEventsEntity> allUsers = (List<UserEventsEntity>) userEventsRepository.findAll();
		model.addAttribute("allUsersList", allUsers);
		model.addAttribute("message", "Record Deleted Successfully !!");
		model.addAttribute("firstName", "Admin");
		return new ModelAndView("dashboard");
	}

	/**
	 * Method registerUser, This controller API is used to add Newly registered User to DB
	 * @param model
	 * @param firstName
	 * @param lastName
	 * @param userName
	 * @param password
	 * @param address
	 * @param age
	 * @return
	 */
	@PostMapping(path = "/registerUser")
	public ModelAndView registerUser(Model model, @RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String userName, @RequestParam String password, @RequestParam String address,
			@RequestParam String age) {

		LOGGER.info("Inside registerUser method");
		GuestBookEntity n = new GuestBookEntity();

		n.setFirstName(firstName);
		n.setLastName(lastName);
		n.setUserName(userName);
		n.setPassword(password);
		n.setAddress(address);
		n.setAge(Integer.parseInt(age));

		guestBookRepository.save(n);
		LOGGER.debug("New User record added in DB successfully !");
		
		model.addAttribute("userName", userName);
		return new ModelAndView("thankyou");
	}

	/**
	 * Method backToLogin, This controller API is used to Redirect user back to Login
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/back")
	public ModelAndView backToLogin(Model model) {
		
		LOGGER.info("Inside backToLogin method");
		return new ModelAndView("login");
	}

	/**
	 * Method backToDashboard, This controller API is used to Redirect Admin back to Dashboard
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/backToDashboard")
	public ModelAndView backToDashboard(Model model) {

		LOGGER.info("Inside backToDashboard method");
		List<UserEventsEntity> allUsers = (List<UserEventsEntity>) userEventsRepository.findAll();
		model.addAttribute("allUsersList", allUsers);
		model.addAttribute("firstName", "Admin");
		return new ModelAndView("dashboard");
	}

	/**
	 * Method editUser, This controller API is used to Update the User Event in DB
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(path = "/editUser")
	public ModelAndView editUser(Model model, @RequestParam String userId) {

		LOGGER.info("Inside editUser method");
		UserEventsEntity userDetails = userEventsRepository.fetchUserById(Integer.parseInt(userId));
		model.addAttribute("userDetails", userDetails);
		return new ModelAndView("EditUser");
	}

	/**
	 * Method readImage, This controller API is used to read the uploaded image from DB
	 * @param model
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/readImage")
	public ModelAndView readImage(Model model, @RequestParam String userId) throws Exception {

		LOGGER.info("Inside readImage method");
		
		Blob blobImage = userEventsRepository.readImage(Integer.parseInt(userId));
		LOGGER.debug("Image retrieved from DB successfully !");
		
		String imgString = Base64.encodeBase64String((blobImage.getBytes(1, (int) blobImage.length())));

		model.addAttribute("binaryData", imgString);
		model.addAttribute("message", "Image retrieved from DB.");

		return new ModelAndView("RenderImage");
	}

	/**
	 * Method approveRecord, This controller API is used to approve the user Event in DB
	 * @param model
	 * @param userId
	 * @return
	 */
	@PostMapping(path = "/approveRecord")
	public ModelAndView approveRecord(Model model, @RequestParam String userId) {

		LOGGER.info("Inside approveRecord method");
		
		int count = userEventsRepository.updateApprovedStatus("Approved", Integer.parseInt(userId));
		if (count > 0) {

			List<UserEventsEntity> allUsers = (List<UserEventsEntity>) userEventsRepository.findAll();
			model.addAttribute("allUsersList", allUsers);
			model.addAttribute("message", "Record Status Updated Successfully !!");
			model.addAttribute("firstName", "Admin");
			return new ModelAndView("dashboard");
		} else {
			return new ModelAndView("SystemError");
		}
	}

	/**
	 * Method approveRecord, This controller API is used to Update the existing user Events in DB
	 * @param model
	 * @param id
	 * @param userName
	 * @param eventName
	 * @param eventDate
	 * @param notes
	 * @param fileName
	 * @return
	 */
	@PostMapping(path = "/updateUser")
	public ModelAndView updateUser(Model model, @RequestParam String id, @RequestParam String userName,
			@RequestParam String eventName, @RequestParam String eventDate, @RequestParam String notes,
			@RequestParam String fileName) {

		LOGGER.info("Inside updateUser method");
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(eventDate);
		} catch (ParseException e) {
			LOGGER.error("Exception caught while updating User Event"+e);
		}

		int count = userEventsRepository.updateUserById(Integer.parseInt(id), userName, eventName, date, notes,
				fileName);

			LOGGER.debug("User Event Updated in DB successfully !");
		if (count > 0) {
			model.addAttribute("updateMessage", "User Details Updated Successfully !!");
			return new ModelAndView("UpdateSuccess");
		} else {
			return new ModelAndView("SystemError");
		}

	}

	/**
	 * Method addEvent, This controller API is used to Add a new User Event in DB
	 * @param model
	 * @param userId
	 * @param eventName
	 * @param eventDate
	 * @param notes
	 * @param img
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	@RequestMapping(path = "/addEvent")
	public ModelAndView addEvent(Model model, @RequestParam String userId, @RequestParam String eventName,
			@RequestParam String eventDate, @RequestParam String notes, @RequestParam("img") MultipartFile img,
			@RequestParam String firstName, @RequestParam String lastName) {

		LOGGER.info("Inside addEvent method");
		
		UserEventsEntity uEvents = new UserEventsEntity();
		uEvents.setUserName(userId);
		uEvents.setEventName(eventName);

		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(eventDate);
			uEvents.setEventDate(date);
			uEvents.setNotes(notes);

			if (img.getSize() > 0) {

				Blob bImage = null;

				bImage = new SerialBlob((img.getBytes()));

				uEvents.setPicByte(bImage);
				uEvents.setFileName(img.getOriginalFilename());
			} else {
				uEvents.setPicByte(null);
				uEvents.setFileName(null);
			}

			uEvents.setIsApproved("Not Approved");
		} catch (Exception e1) {
			LOGGER.error("Exception caught while adding user event"+e1);
		}

		userEventsRepository.save(uEvents);
		LOGGER.debug("User Event saved in DB successfully !");
		
		model.addAttribute("userMessage", "Event Posted Successfully !!");

		model.addAttribute("firstName", firstName);
		model.addAttribute("lastName", lastName);
		model.addAttribute("userName", userId);

		List<UserEventsEntity> allUserEvents = (List<UserEventsEntity>) userEventsRepository.fetchUserEvent(userId);
		model.addAttribute("allUserEvents", allUserEvents);

		return new ModelAndView("UserDashboard");
	}

	/**
	 * Method validateUser, This controller API is used to Validate the user at Login to authorize the user's identity
	 * @param model
	 * @param userName
	 * @param password
	 * @return
	 */
	@PostMapping(path = "/validateUser")
	public ModelAndView validateUser(Model model, @RequestParam String userName, @RequestParam String password) {

		LOGGER.info("Inside validateUser method");
		GuestBookEntity ge = guestBookRepository.fetchUser(userName);

		if (null != ge && ge.getPassword().equals(password)) {

			if (!userName.equals("admin")) {
				@SuppressWarnings("unchecked")
				List<UserEventsEntity> allEvents = (List<UserEventsEntity>) userEventsRepository
						.fetchUserEvent(userName);
				model.addAttribute("allUserEvents", allEvents);
				model.addAttribute("firstName", ge.getFirstName());
				model.addAttribute("lastName", ge.getLastName());
				model.addAttribute("userName", userName);

				return new ModelAndView("UserDashboard");
			} else {
				List<UserEventsEntity> allUsers = (List<UserEventsEntity>) userEventsRepository.findAll();
				model.addAttribute("allUsersList", allUsers);
				model.addAttribute("firstName", "Admin");

				return new ModelAndView("dashboard");
			}
		} else if (null != ge)
			return new ModelAndView("loginError");
		else
			return new ModelAndView("systemError");
	}

}
