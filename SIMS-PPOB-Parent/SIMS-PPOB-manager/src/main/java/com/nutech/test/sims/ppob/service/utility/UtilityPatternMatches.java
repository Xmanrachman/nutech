package com.nutech.test.sims.ppob.service.utility;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class UtilityPatternMatches {

	public boolean patternMatches(String emailAddress, String regexPattern) {

		return Pattern.compile(regexPattern).matcher(emailAddress).matches();
	}
	
}
