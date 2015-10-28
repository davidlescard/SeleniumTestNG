package com.moasis.bestbuy.tests.acceptance;

import org.testng.annotations.*;

import com.moasis.bestbuy.BaseAppTest;
import com.moasis.bestbuy.model.User;
import com.moasis.bestbuy.pages.LoginPage;
import com.moasis.bestbuy.pages.PrimaryNavBar;

/** 
 * @author      David Lescard
 * @version     1.0
 */
public class LoginTests extends BaseAppTest {
	
	@BeforeMethod
	public void beforeMethod() {
		LoginPage lp = new LoginPage(driver);
		lp.navigateTo();
	}
	
	@Test
	public void loginPageLoads() {
		LoginPage lp = new LoginPage(driver);
		stepNum(1);
		lp.assertNavigatedToLoginPage();
	}
	
	@Test 
	public void badUserLoginFails() {
		LoginPage lp = new LoginPage(driver);
		stepNum(1);
		lp.loginAs(User.BAD_USERNAME);
		stepNum(2);
		lp.assertAuthenticationFailed();
	}
	
	@Test 
	public void badPassLoginFails() {
		LoginPage lp = new LoginPage(driver);
		stepNum(1);
		lp.loginAs(User.BAD_PASS);
		stepNum(2);
		lp.assertAuthenticationFailed();
	}
	
	@Test 
	public void badUserAndPassLoginFails() {
		LoginPage lp = new LoginPage(driver);
		stepNum(1);
		lp.loginAs(User.BAD_USER_AND_PASS);
		stepNum(2);
		lp.assertAuthenticationFailed();
	}
	
	@Test
	public void noUsernameEnteredSaysRequired() {
		LoginPage lp = new LoginPage(driver);
		stepNum(1);
		lp.loginAs(User.NO_USERNAME);
		stepNum(2);
		lp.elementIsVisible(lp.LBL_USERNAME_IS_REQUIRED, test, wait);
	}
	
	@Test
	public void noPasswordEnteredSaysRequired() {
		LoginPage lp = new LoginPage(driver);
		stepNum(1);
		lp.loginAs(User.NO_PASSWORD);
		stepNum(2);
		lp.elementIsVisible(lp.LBL_PASSWORD_IS_REQUIRED, test, wait);
	}
	
	@Test 
	public void allElementsArePresentOnPage() {
		LoginPage lp = new LoginPage(driver);
		stepNum(1);
		lp.assertAllElementsArePresent();
	}
	
	@Test
	public void logoutReturnsToLogin() {
		LoginPage lp = new LoginPage(driver);
		stepNum(1);
		lp.loginAs(User.REG);
		stepNum(2);
		PrimaryNavBar pnav = new PrimaryNavBar(driver);
		pnav.logout();
		stepNum(3);
		pnav.elementIsVisible(pnav.SIGN_IN_HOVER, wait, test);
	}
}
