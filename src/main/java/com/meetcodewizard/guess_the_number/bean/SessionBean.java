package com.meetcodewizard.guess_the_number.bean;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SessionBean {

	
	private Integer userid;
	
	@NotNull(message = "Firstname field is mandatory to fill")
	@Pattern(regexp = "^[a-zA-Z\\s'-]+$", message = "Invalid first name")
	private String firstname;
	
	@NotEmpty(message = "Email field is mandatory to fill")
	@Email(message = "Please Enter Valid Email Address")
	private String email;
	
	@NotEmpty(message = "Password field is mandatory to fill")
	private String password;
	
	private Integer master_credit;
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getMaster_credit() {
		return master_credit;
	}
	public void setMaster_credit(Integer master_credit) {
		this.master_credit = master_credit;
	}
	
	
	
}
