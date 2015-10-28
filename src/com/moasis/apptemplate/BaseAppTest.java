package com.moasis.apptemplate;

import java.lang.reflect.Method;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.*;

import com.moasis.AbstractPage;
import com.moasis.BaseEnvTest;
import com.moasis.Log;
import com.moasis._EnvConfig;
import com.moasis.apptemplate._AppConfig;

/** 
 * @author      David Lescard
 * @version     1.0
 */
public class BaseAppTest extends BaseEnvTest {
	
	protected BaseAppTest() {
		driver = super.driver;
    	AbstractPage.wait_standardLength = 
    			_AppConfig.getWaitTime() == -1 ? 
    			_EnvConfig.getWaitTime() :
    			_AppConfig.getWaitTime();
    	AbstractPage.wait_shortLength = 
				_AppConfig.getWaitTime_short() == -1 ? 
				_EnvConfig.getWaitTime_short() :
				_AppConfig.getWaitTime_short();
	}
	
	
	/*
	 * Annotated Methods
	 * 
	 * Note: 
	 * TestNG Execution order (for @Before/After)
	 * - Suite
	 * - Test
	 * - Class
	 * - Method
	 */
	
	@Parameters({ "selenium.browser", "selenium.url" })
	@BeforeMethod
	public void app_beforeMethod(Method method,
			@Optional("perAppConfig") String brwsr,
			@Optional("perAppConfig") String bsurl) {
		stepNum = null;
		testMethod = method.getName();
		
		setBrowserAndURL(brwsr, bsurl);

		Log.logEntry(Log.browserInfo());
		Log.logEntry(Log.urlInfo());
		Log.logEntry(Log.launchBrowser());
		
		switch (browser) {
			case "chrome": 
				driver = new ChromeDriver();
				break;
			case "firefox": 
				driver = new FirefoxDriver();
				break;
			case "htmlunit": 
				driver = new HtmlUnitDriver();
				break;
			default: 
				driver = new ChromeDriver();
				break;
		}
		
		Log.logEntry(Log.getURL());
		driver.get(baseurl);
		Log.logEntry(Log.beginTest());
	}

	@AfterMethod
	public void app_afterMethod() {
		Log.logEntry(Log.endTest());
		driver.quit();
		Log.logEntry(Log.closeBrowser());
		stepNum = null;
	}
	
	/*
	 * Helper methods
	 */
	private void setBrowserAndURL(String brow, String URL) {
		browser = brow.equals("perAppConfig") ?  _AppConfig.getBrowser() : brow;
		baseurl = URL.equals("perAppConfig") ? _AppConfig.getBaseURL() : URL;
	}
}
