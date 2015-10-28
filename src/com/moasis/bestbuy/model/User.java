package com.moasis.bestbuy.model;

/** 
 * @author      David Lescard
 * @version     1.0
 */
public enum User {
	 
	/*
	 * List of users - update as required 
	 * for testing configurations
	 */
    REG("seleniumapptester@gmail.com", "SeleniumIsTheBest1!"),
    BAD_USER_AND_PASS("wrongusername", "wrongpassword"),
    BAD_USERNAME("wrongusername", "SeleniumIsTheBest1!"),
    BAD_PASS("seleniumapptester@gmail.com", "wrongpassword"),
    NO_USERNAME("", "SeleniumIsTheBest1!"),
    NO_PASSWORD("seleniumapptester@gmail.com", "");
 
    /*
     * Enum properties for retrieving users - no 
     * modification necessary
     */
    private String username;
    private String password;
 
    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
 
    public String getUsername() {
        return this.username;
    }
 
    public String getPassword() {
        return this.password;
    }
}
