package com.test.common;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="iteration") //iteration
public class Iteration {
	private String id,	appurl,	browser, username, password,  currentPassword, newPassword, 
	confirmNewPassword, password1, password2, password3, email;
	
	
	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getId() {
		return id;
	}
	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}
	
	@XmlElement
	public String getAppurl() {
		return appurl;
	}
	public void setAppurl(String appurl) {
		this.appurl = appurl;
	}
	@XmlElement
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	@XmlElement
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@XmlElement
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@XmlElement
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	
	@XmlElement
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@XmlElement
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	@XmlElement
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	@XmlElement
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	@XmlElement
	public String getPassword3() {
		return password3;
	}
	public void setPassword3(String password3) {
		this.password3 = password3;
	}
	
	
}