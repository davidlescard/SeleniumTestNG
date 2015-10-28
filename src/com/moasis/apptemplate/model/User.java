package com.moasis.apptemplate.model;

/** 
 * @author      David Lescard
 * @version     1.0
 */
public enum User {
	 
	/*
	 * List of users - update as required 
	 * for testing configurations
	 */
    REG("someusername", "somepassword"),
    BAD_USER_AND_PASS("wrongusername", "wrongpassword"),
    BAD_USERNAME("wrongusername", "somepassword"),
    BAD_PASS("someusername", "wrongpassword"),
    NO_USERNAME("", "somepassword"),
    NO_PASSWORD("someusername", "");
 
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
