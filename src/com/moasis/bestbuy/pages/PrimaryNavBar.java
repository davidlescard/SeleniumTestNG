package com.moasis.bestbuy.pages;

import com.moasis.*;
import com.moasis.bestbuy.BaseAppTest;

import org.openqa.selenium.WebDriver;

/** 
 * @author      David Lescard
 * @version     1.0
 */
public class PrimaryNavBar extends AbstractPage {
	
//	/* Page URL endpoint */
//	public final String pageEndpoint1 = "";
//	
//	/* Page element strings */
//	// Page Title
//	public final String TITLE1 = "Best Buy: Expert Service. Unbeatable Price.";
//	// Links
//	public final String SIGN_IN_HOVER1 = "//*[text()='Sign In']";
//	public final String SIGN_IN1 = "Sign In";
//	public final String USERNAME1 = "div#header div.header-inner header div.primary-wrap div.profile.js-account-wrapper form.js-account-body div.profile-wrap.js-navitem.main-nav.js-forceexempt span.user";
//	// Textfields
//	// Buttons
//	public final String LOGOUT1 = "#logout-button";
//	// Logos
//	public final String SITE_LOGO1 = "a[title='BestBuy.com Homepage'] > span";
//	// Modals
//	public final String SIGN_UP_MODAL1 = "#abt-modal > div > div";
//	public final String SIGN_UP_MODAL_CLOSE1 = "#abt-modal > div > div > div.modal-header > button";
	
	/* Page URL endpoint */
	public final String pageEndpoint = "";
	
	/* Page element strings */
	// Page Title
	public final UIElement TITLE = new UIElement("TITLE","Best Buy: Expert Service. Unbeatable Price.");
	// Links
	public final UIElement SIGN_IN_HOVER = new UIElement("SIGN_IN_HOVER","//*[text()='Sign In']");
	public final UIElement SIGN_IN = new UIElement("SIGN_IN","Sign In");
	public final UIElement USERNAME = 
			new UIElement("USERNAME","div#header div.header-inner header "
					+ "div.primary-wrap div.profile.js-account-wrapper form.js-account-body "
					+ "div.profile-wrap.js-navitem.main-nav.js-forceexempt span.user");
	// Textfields
	// Buttons
	public final UIElement LOGOUT = new UIElement("LOGOUT","#logout-button");
	// Logos
	public final UIElement SITE_LOGO = new UIElement("SITE_LOGO","a[title='BestBuy.com Homepage'] > span");
	// Modals
	public final UIElement SIGN_UP_MODAL = new UIElement("SIGN_UP_MODAL","#abt-modal > div > div");
	public final UIElement SIGN_UP_MODAL_CLOSE = 
			new UIElement("SIGN_UP_MODAL_CLOSE","#abt-modal > div > div > div.modal-header > button");
	
	
	public PrimaryNavBar(WebDriver driver) {
		super(driver);
	}
	
    public PrimaryNavBar navigateTo() {
        driver.get(BaseAppTest.baseurl + pageEndpoint);
        return this;
    }
    
    public PrimaryNavBar goToLogin () {
    	if (elementIsVisible(SIGN_UP_MODAL, shortWait)) {
    		clickElement(SIGN_UP_MODAL_CLOSE);
    	}
    	moveToElement(SIGN_IN_HOVER, wait);
    	clickElement(SIGN_IN, wait);
    	return this;
    }
    
    public PrimaryNavBar logout () {
    	clickElement(USERNAME, wait);
    	clickElement(LOGOUT, shortWait);
		return this;
    }
	
	public PrimaryNavBar assertAllElementsArePresent() {
		elementIsVisible(SITE_LOGO, wait, test);
		return this;
	}
    

}
