package com.moasis.apptemplate.tests.acceptance;

import org.testng.annotations.*;

import com.moasis.apptemplate.BaseAppTest;
import com.moasis.apptemplate.model.User;
import com.moasis.apptemplate.pages.LoginPage;
import com.moasis.apptemplate.pages._PageUtils;

/** 
 * @author      David Lescard
 * @version     1.0
 */
public class LoginTests extends BaseAppTest {
	
	@BeforeMethod
	public void beforeMethod() {
		LoginPage lp = new LoginPage(driver);
		lp.open();
	}
	
	@Test
	public void loginPageLoads() {
		LoginPage lp = new LoginPage(driver);
		lp.assertNavigatedToLoginPage();
	}
	
	@Test
	public void noUsernameEnteredSaysRequired() {
		stepNum(1);
		LoginPage lp = new LoginPage(driver);
		lp.open();
		stepNum(2);
		lp.loginAs(User.NO_USERNAME);
		stepNum(3);
		lp.elementIsVisible(lp.LBL_USERNAME_IS_REQUIRED, test, wait);
	}
	
	@Test
	public void noPasswordEnteredSaysRequired() {
		stepNum(1);
		LoginPage lp = new LoginPage(driver);
		lp.open();
		stepNum(2);
		lp.loginAs(User.NO_PASSWORD);
		stepNum(3);
		lp.elementIsVisible(lp.LBL_PASSWORD_IS_REQUIRED, test, wait);
	}
	
	@Test 
	public void allElementsArePresentOnPage() {
		stepNum(1);
		LoginPage lp = new LoginPage(driver);
		lp.open();
		stepNum(2);
		lp.assertAllElementsArePresent();
	}
	
	@Test
	public void logoutReturnsToLogin() {
		stepNum(1);
		LoginPage lp = new LoginPage(driver);
		lp.open();
		stepNum(2);
		lp.loginAs(User.REG);
		stepNum(3);
		_PageUtils utils = new _PageUtils(driver);
		utils.logOut();
		stepNum(5);
		lp.pageTitleIsVisible(lp.TITLE, test, wait);
	}
}
