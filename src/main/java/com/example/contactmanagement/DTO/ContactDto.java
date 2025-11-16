package com.example.contactmanagement.DTO;


import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ContactDto {
   
   private Long id;
   
   private String firstName;
   
   private String lastName;
   
   private String title;
   
   private List<String> emails = new ArrayList<>();
   
   private List<String> phones = new ArrayList<>();

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
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
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
	
	
	   
	   
	}
