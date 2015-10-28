package com.moasis.apptemplate.pages;

import com.moasis.*;
import com.moasis.apptemplate.BaseAppTest;
import com.moasis.apptemplate.model.User;

import org.openqa.selenium.WebDriver;

/** 
 * @author      David Lescard
 * @version     1.0
 */
public class LoginPage extends AbstractPage {
	
	/* Page URL endpoint */
	public final UIElement pageEndpoint = new UIElement("pageEndpoint", "");
	
	/* Page element strings */
	// Page Title
	public final UIElement TITLE = new UIElement("TITLE", "selectorString");
	// Textfields
	public final UIElement TF_USERNAME = new UIElement("TF_USERNAME","selectorString");
	public final UIElement TF_PASSWORD = new UIElement("TF_PASSWORD","selectorString");
	// Buttons
	public final UIElement BTN_LOGIN = 
			new UIElement("BTN_LOGIN","selectorString");
	// Labels
	public final UIElement LBL_USERNAME = 
			new UIElement("LBL_USERNAME","selectorString");
	public final UIElement LBL_PASSWORD = 
			new UIElement("LBL_PASSWORD","selectorString");
	public final UIElement LBL_BAD_AUTH = 
			new UIElement("LBL_BAD_AUTH","selectorString");
	public final UIElement LBL_USERNAME_IS_REQUIRED = 
			new UIElement("LBL_USERNAME_IS_REQUIRED","selectorString");
	public final UIElement LBL_PASSWORD_IS_REQUIRED = 
			new UIElement("LBL_PASSWORD_IS_REQUIRED","selectorString");
	// Logos
	public final UIElement SITE_LOGO = new UIElement ("SITE_LOGO", "selectorString");
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
    public LoginPage open() {
        driver.get(BaseAppTest.baseurl + pageEndpoint);
        return this;
    }

    public LoginPage loginAs(User user) {
        sendKeysToElement(TF_USERNAME, user.getUsername(), wait);
        sendKeysToElement(TF_PASSWORD, user.getPassword());
        clickElement(BTN_LOGIN);
        return this;
    }
    
    public LoginPage enterUsername(String user) {
    	sendKeysToElement(TF_USERNAME, user);
    	return this;
    }
    
    public LoginPage enterPassword(String pass) {
    	sendKeysToElement(TF_USERNAME, pass);
    	return this;
    }
    
    public LoginPage clickLogin() {
    	clickElement(BTN_LOGIN, wait);
    	return this;
    }
    
	public LoginPage assertNavigatedToLoginPage() {
		pageTitleIsVisible(TITLE, wait, test);
		return this;
	}
	
	public LoginPage assertAllElementsArePresent() {
		elementIsVisible(TF_USERNAME, wait, test);
		elementIsVisible(TF_PASSWORD, wait, test);
		elementIsVisible(BTN_LOGIN, wait, test);
		elementIsVisible(LBL_USERNAME, wait, test);
		elementIsVisible(LBL_PASSWORD, wait, test);
		elementIsVisible(SITE_LOGO, wait, test);
		return this;
	}
    
    
    

}
