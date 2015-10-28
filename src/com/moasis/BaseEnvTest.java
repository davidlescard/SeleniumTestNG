package com.moasis;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

/** 
 * @author      David Lescard
 * @version     1.0
 */
public class BaseEnvTest {

	private String pathToChromeDriver = _EnvConfig.getPathToChromedriver();
	protected WebDriver driver;
	public static String browser;
	public static String baseurl;
	protected static String testSuite;
	protected static String testTest;
	protected static String testClass;
	protected static String testMethod;
	protected static String stepNum;
	public final static String wait = "wait";
	public final static String test = "test";
	
	protected BaseEnvTest() {
		System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
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
	
	@BeforeSuite
	public void app_beforeSuite(ITestContext ctx) {
		 testSuite = ctx.getCurrentXmlTest().getSuite().getName();
		Log.logEntry(Log.beginSuite());
	}
	
	@BeforeClass
	public void app_beforeClass(ITestContext ctx) {
		testClass = this.getClass().getSimpleName();
		Log.logEntry(Log.beginClass());
	}
	
	@AfterClass
	public void app_afterClass() {
		Log.logEntry(Log.endClass());
	}
	
	@AfterSuite
	public void app_afterSuite() {
		Log.logEntry(Log.endSuite());
	}
	
	/*
	 * Helper methods
	 */
	
	public void stepNum(int i) {
		stepNum = Integer.toString(i);
	}
	
}
