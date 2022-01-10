package com.citius.tech.commons;

import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

public class ScryptHashing  {
	
	SCryptPasswordEncoder scrypt = new SCryptPasswordEncoder();
	
	public static void main(String args[])
	{
		ScryptHashing sc = new ScryptHashing();
		String passwordToBeHashed = "1234";
		
		//Hashing
		String hashedPassword = sc.hashTheGivenString(passwordToBeHashed);
		System.out.println("hashedPassword :: "+hashedPassword);
		
		//Validating 
		boolean isValid = sc.validateTheGiveString(hashedPassword,passwordToBeHashed);
		System.out.println("Is valid :: "+isValid);
		
		
	}

	private boolean validateTheGiveString(String hashedPassword,String rawPassword) {
		System.out.println("hashedPassword :: "+hashedPassword);
		return scrypt.matches(rawPassword,hashedPassword);
	}

	private String hashTheGivenString(String rawPassword) {
		return scrypt.encode(rawPassword);
	}

}
