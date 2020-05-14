package com.assignment.guestbook.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * GuestBookEntity, Entity class for GUEST_BOOK_DETAILS table
 */

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "GUEST_BOOK_DETAILS")
public class GuestBookEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "GUEST_ID")
	@NotNull
	private Integer id;

	@Column(name = "FIRST_NAME", nullable=false, length=50)
	private String firstName;
	
	@Column(name = "LAST_NAME", nullable=false, length=50)
	private String lastName;
	
	@Column(name = "USER_ID", nullable=false, length=100, unique = true)
	private String userName;
	
	@Column(name = "PASSWORD", nullable=false, length=50)
	private String password;

	@Column(name = "ADDRESS", nullable=true, length=500)
	private String address;
	
	@Column(name = "AGE", length=3)
	private Integer age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
