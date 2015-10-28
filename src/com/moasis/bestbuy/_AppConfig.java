package com.moasis.bestbuy;

/** 
 * @author      David Lescard
 * @version     1.0
 */
public class _AppConfig {
	
	/*
	 * Modify the properties below to suit your environment
	 * and anticipated performance
	 */
	private static final String BASE_URL = "http://www.bestbuy.com";
	private static final String BROWSER = "chrome";
	
	// (**** set to override _EnvConfig values ****)
	// max wait time for elements anticipated 
	private static final int SECONDS_TIMEOUT_STANDARD_DURATION = -1; 
	// max wait time for elements that should not be present
	private static final int SECONDS_TIMEOUT_SHORT_DURATION = -1; 
	
	
	/*
	 * Getters/Setters for the above private members.
	 * Add any as necessary for additional config vars
	 */
	public static String getBaseURL () {
		return BASE_URL;
	}
	
	public static String getBrowser () {
		return BROWSER;
	}
	
	public static int getWaitTime(){
		return SECONDS_TIMEOUT_STANDARD_DURATION;
	}
	
	public static int getWaitTime_short(){
		return SECONDS_TIMEOUT_SHORT_DURATION;
	}
}
