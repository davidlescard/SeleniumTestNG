package com.moasis.bestbuy.pages;

import com.moasis.*;
import com.moasis.bestbuy.model.User;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/** 
 * @author      David Lescard
 * @version     1.0
 */
public class LoginPage extends AbstractPage {
	
	/* Page URL endpoint */
	public final UIElement pageEndpoint = new UIElement("pageEndpoint", "");
	
	/* Page element strings */
	// Page Title
	public final UIElement TITLE = new UIElement("TITLE", "Sign In to BestBuy.com");
	// Textfields
	public final UIElement TF_USERNAME = new UIElement("TF_USERNAME","fld-e");
	public final UIElement TF_PASSWORD = new UIElement("TF_PASSWORD","fld-p1");
	// Buttons
	public final UIElement BTN_LOGIN = 
			new UIElement("BTN_LOGIN",".cia-form__submit-button.js-submit-button");
	// Labels
	public final UIElement LBL_USERNAME = 
			new UIElement("LBL_USERNAME","//label[text()='E-Mail Address']");
	public final UIElement LBL_PASSWORD = 
			new UIElement("LBL_PASSWORD","//label[text()='Password']");
	public final UIElement LBL_BAD_AUTH = 
			new UIElement("LBL_BAD_AUTH","div.alert-header__text.js-alert-header");
	public final UIElement LBL_USERNAME_IS_REQUIRED = 
			new UIElement("LBL_USERNAME_IS_REQUIRED","//span[text()='Please enter a valid e-mail address.']");
	public final UIElement LBL_PASSWORD_IS_REQUIRED = 
			new UIElement("LBL_PASSWORD_IS_REQUIRED","//span[text()='Please enter your password.']");
	// Logos
	public final UIElement SITE_LOGO = new UIElement ("SITE_LOGO", "span.cia-header__logo");
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
    public LoginPage navigateTo() {
    	PrimaryNavBar hp = new PrimaryNavBar(driver);
    	hp.goToLogin();
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
	
	public LoginPage assertAuthenticationFailed() {
		Assert.assertTrue(elementIsVisible(LBL_USERNAME_IS_REQUIRED, shortWait)
				|| elementIsVisible(LBL_PASSWORD_IS_REQUIRED, shortWait) 
				|| elementIsVisible(LBL_BAD_AUTH, shortWait));
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
