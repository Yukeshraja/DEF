package com.altimetrik.def.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.UrlValidator;
import org.springframework.stereotype.Component;

import com.altimetrik.def.model.Response;

@Component
public abstract class DefUrlValidator {

	private static final String URL_REGEX = "^[^@]+@[^:]+:[^/]+/[^.]+\b.git$";
	private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);
	
	public Response urlValidation(String url) {
/*
		String pattern1 = "\\w{1,50}[.,:]{1}[//]{1,2}(\\w{1,500}[.]{0,}\\w{1,500}[/]{0,1}){0,}";
		String pattern2 = "\\w{1,50}[:]{1}[//]{1,2}\\w{1,50}[@]{1}\\w{1,}[.]{1}\\w{1,}[:]{1}(\\w{1,500}[/]{0,1}){0,}[.]{1}\\w{1,4}";
		String pattern3 = "\\w{1,50}[:]{1}[//]{1,2}(\\w{1,}[.]{0,}[:]{0,1}[/]{0,1}){0,}";
		String pattern4 = "\\w{1,50}[:]{1}[//]{1,2}(\\w{1,}[@]{0,1}[.]{0,}[:]{0,1}[/]{0,1}){0,}";
		String pattern5 = "\\w{1,50}[:]{1}[//]{1,2}(\\w{1,}[@]{0,1}[.]{0,}[/]{0,1}[~]{0,}[/]{0,1}){0,}";
		String pattern6 = "\\w{1,}[@]{1}\\w{1,}[.]{1}\\w{1,}[:]{1}([/]{0,1}\\w{1,}[.]{0,1}){0,}";
		String pattern7 = "\\w{1,}[.]{1}\\w{1,}[:]{1}([/]{0,1}\\w{1,}[.]{0,1}){0,}";
		String pattern8 = "\\w{1,}[@]{1}\\w{1,}[.]{1}\\w{1,}[:]{1}[~]{1}([/]{0,1}\\w{1,}[.]{0,1}){0,}";
		String pattern9 = "\\w{1,}[.]{1}\\w{1,}[:]{1}[~]{1}([/]{0,1}\\w{1,}[.]{0,1}){0,}";
		String pattern10 = "\\w{1,50}[.,:]{1}[///]{1,2}([/]{0,1}\\w{1,500}[.]{0,}){0,}";
		String pattern11 = "\\w{1,50}[.,:]{1}[//]{1,2}[~]{1}([/]{0,1}\\w{1,500}[.]{0,}){0,}";
		String pattern12 = "([/]{0,1}\\w{1,500}[.]{0,}){0,}";*/
		
		Response response = new Response();
		Date date = new Date();
		String[] protocols = { "http", "https", "ssh" };
		UrlValidator urlValidator = new UrlValidator(protocols);
		Map<String, String> errorMap = new HashMap<String, String>();
		Matcher urlMatcher  = URL_PATTERN.matcher(url);
		if (!urlMatcher.matches()) {
			
			response.setErrorMessage("URL Is Not Valid");
			response.setTimestamp(date.getTime());
			response.setStatus("FAILURE");
			errorMap.put("Error:", "Please Enter Proper Url");
		}else{
			
			response.setErrorMessage("No Error : Valid URL");
			response.setTimestamp(date.getTime());
			response.setStatus("SUCCESS");
			errorMap.put("Error:", "No Error !! The Url is Proper");
		}
		response.setError(errorMap);
		/*try{
			urlValidator.isValid(url);
			response.setErrorMessage("No Error : Valid URL");
			response.setTimestamp(date.getTime());
			response.setStatus("SUCCESS");
		}catch(Exception exception){
			response.setErrorMessage(exception.getMessage());
			response.setTimestamp(date.getTime());
			response.setStatus("FAILURE");
		}*/
		return response;

	}
}
