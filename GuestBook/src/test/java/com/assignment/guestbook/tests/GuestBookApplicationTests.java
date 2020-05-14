package com.assignment.guestbook.tests;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * GuestBookApplicationTests, JUNIT test class for GuestBook App
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class GuestBookApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	private static final Logger LOGGER = LogManager.getLogger(GuestBookApplicationTests.class);

	/**
	 * Method test1, This JUNIT Method is used to Test the functionality of New User
	 * Registration
	 * 
	 * @return void
	 */
	@Test
	void test1() throws Exception {

		LOGGER.info("About to run JUNIT Test for New User Registartion");
		String testString = "firstName=TestUser&lastName=01&age=30&userName=test001&password=12345&address=Dummy Address Line 1";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/guestbook/registerUser?" + testString)
				.accept(MediaType.APPLICATION_JSON).content(testString).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());
	}

	/**
	 * Method test2, This JUNIT Method is used to Test the functionality of Create
	 * New User Event functionality
	 * 
	 * @return void
	 */
	@Test
	void test2() throws Exception {

		LOGGER.info("About to run JUNIT Test for Creating New User Event");
		String testString = "userId=test001&eventName=FirstTestEvent&eventDate=2020-05-12&notes=Test Notes...&firstName=TestUser&lastName=01";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/guestbook/addEvent?" + testString)
				.accept(MediaType.APPLICATION_JSON).content(testString).contentType(MediaType.MULTIPART_FORM_DATA);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());
	}

	/**
	 * Method test3, This JUNIT Method is used to Test the functionality of Update
	 * Existing User Event functionality
	 * 
	 * @return void
	 */
	@Test
	void test3() throws Exception {

		LOGGER.info("About to run JUNIT Test for Update User Event");
		String testString = "id=1&userName=test001&eventName=FirstTestEvent_UPDATED&eventDate=2020-05-12&notes=Test Notes UPDATED..&fileName=TestImage_01";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/guestbook/updateUser?" + testString)
				.accept(MediaType.APPLICATION_JSON).content(testString).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());
	}

	/**
	 * Method test4, This JUNIT Method is used to Test the functionality of Approve
	 * User Event functionality
	 * 
	 * @return void
	 */
	@Test
	void test4() throws Exception {

		String testString = "userId=1";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/guestbook/approveRecord?" + testString)
				.accept(MediaType.APPLICATION_JSON).content(testString).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());
	}

	/**
	 * Method test5, This JUNIT Method is used to Test the functionality of Delete
	 * User Event functionality
	 * 
	 * @return void
	 */
	@Test
	void test5() throws Exception {

		LOGGER.info("About to run JUNIT Test for Delete User Event");

		String testString = "userId=4";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/guestbook/deleteUser?" + testString)
				.accept(MediaType.APPLICATION_JSON).content(testString).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());
	}
}
