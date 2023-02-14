package fitnesscenter.models;

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
	
	

}
