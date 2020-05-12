package com.assignment.guestbook.entities;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USER_EVENTS")
public class UserEventsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	@NotNull
	private Integer id;

	@Column(name = "USER_ID", nullable = false, length = 100)
	private String userName;

	@Column(name = "EVENT_NAME", nullable = true, length = 50)
	private String eventName;

	@Column(name = "EVENT_DATE", nullable = true, length = 500)
	private Date eventDate;

	@Column(name = "NOTES", length = 500)
	private String notes;
	
	@Column(name = "FILE_NAME", length = 100)
	private String fileName;

	@Column(name = "picByte", length = 100000)
	private byte[] picByte;

	@Column(name = "IS_APPROVED", length = 50)
	private String isApproved;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

}
