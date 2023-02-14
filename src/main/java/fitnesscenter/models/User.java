package fitnesscenter.models;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import fitnesscenter.enums.ERole;

public class User {
	
	private String id;
	private String phoneNumber;
	private String address;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String ccNumber;
	private Language mainLanguage;
	private List<Language> allLanguages;
	private ERole role;
	private TimeZone timezone;
	
	public User() {
		this.allLanguages = new ArrayList<Language>();		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public Language getMainLanguage() {
		return mainLanguage;
	}

	public void setMainLanguage(Language mainLanguage) {
		this.mainLanguage = mainLanguage;
	}

	public List<Language> getAllLanguages() {
		return allLanguages;
	}

	public void setAllLanguages(List<Language> allLanguages) {
		this.allLanguages = allLanguages;
	}

	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}

	public TimeZone getTimezone() {
		return timezone;
	}

	public void setTimezone(TimeZone timezone) {
		this.timezone = timezone;
	}
	
	
	
	

}
