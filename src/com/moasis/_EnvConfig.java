package com.moasis;

/** 
 * @author      David Lescard
 * @version     1.0
 */
public class _EnvConfig {

	/*
	 * Modify the properties below to suit your environment
	 * and anticipated performance
	 */
	
	private static final String PATH_TO_CHROMEDRIVER = 
			"C:/Users/dlescard/Projects/chromedriver_win32/chromedriver.exe";
	
	// max wait time for elements anticipated
	private static final int SECONDS_TIMEOUT_STANDARD_DURATION = 10; 
	// max wait time for elements that should not be present
	private static final int SECONDS_TIMEOUT_SHORT_DURATION = 3; 
	
	
	/*
	 * Getters/Setters for the above private members.
	 * Add any as necessary for additional config vars
	 */
	
	public static String getPathToChromedriver(){
		return PATH_TO_CHROMEDRIVER;
	}
	
	public static int getWaitTime(){
		return SECONDS_TIMEOUT_STANDARD_DURATION;
	}
	
	public static int getWaitTime_short(){
		return SECONDS_TIMEOUT_SHORT_DURATION;
	}
	
	
}
