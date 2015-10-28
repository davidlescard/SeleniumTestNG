package com.moasis;

/** 
 * @author      David Lescard
 * @version     1.0
 */
public class Log {
	
	private static String leftBrack = "[";
	private static String rightBrack = "]";
	private static String info = "[INFO]";
	private static String warn = "[WARN]";
	private static String error = "[ERROR]";
	private static String step = "[Step ";
	private static String browserIs = " Browser set to: ";
	private static String baseUrlIs = " Base URL set to: ";
	private static String launchBrowser = " Launching browser: ";
	private static String closeBrowser = " Closing browser ";
	private static String getURL = " Requesting URL: ";
	private static String beginSuite = " ##### Begin test suite #####";
	private static String endSuite = " ##### Test suite end #####";
	private static String beginClass = " +++++ Begin test class +++++";
	private static String endClass = " +++++ Test class end +++++";
	private static String beginTest = " ***** Begin test *****";
	private static String endTest = " ***** Test end *****";
	private static String elemFound = " Element found by: ";
	private static String elemNotFound = " Element not found: ";
	private static String pageTitleFound = " Page title found ";
	private static String clickElement = " Click element: ";
	private static String sendKeysToElement = " Send keys to element: ";
	private static String moveToElement = " Move to element: ";
	private static String returnElement = " Return element: ";
	private static String elementIsVisible = " Verify visibility of element: ";
	private static String pageTitleIsVisible = " Verify page title is found: ";
	
	public static void logEntry(String text) {
		System.out.println(text);
	}
	
	protected static String processWithOrWithoutStepNum (String WOstep, String Wstep) {
		if (BaseEnvTest.stepNum == null) {
			return WOstep;
		} else {
			return Wstep;
		}
	}
	
	/*
	 * 
	 * 	BaseSeleniumTest setup/teardown method statements
	 * 
	 */
	public static String browserInfo () {
		return browser_info ();
	}
	
	public static String urlInfo () {
		return url_info ();
	}
	
	public static String launchBrowser () {
		return launchBrowser_info ();
	}
	
	public static String closeBrowser () {
		return closeBrowser_info ();
	}
	
	public static String beginSuite () {
		return beginSuite_info ();
	}
	
	public static String endSuite () {
		return endSuite_info ();
	}
	
	public static String beginClass () {
		return beginClass_info ();
	}
	
	public static String endClass () {
		return endClass_info ();
	}
	
	public static String beginTest () {
		return beginTest_info ();
	}
	
	public static String endTest () {
		return endTest_info ();
	}
	
	public static String getURL () {
		return getURL_info ();
	}
	
	
	/*
	 * 
	 * 	AbstractPage wrapper method info statements 
	 * 	- call after Selenium find attempt
	 * 
	 */
	protected static String elemFound (String selectorType) {
		return processWithOrWithoutStepNum(
				elemFound_info_WOStep (selectorType), 
				elemFound_info_WStep (selectorType));
	}
	
	protected static String elemNotFound_error (String selectorType) {
		return processWithOrWithoutStepNum(
				elemNotFound_error_WOStep (selectorType), 
				elemNotFound_error_WStep (selectorType));
	}
	
	protected static String elemNotFound_warn (String selectorType) {
		return processWithOrWithoutStepNum(
				elemNotFound_warn_WOStep (selectorType), 
				elemNotFound_warn_WStep (selectorType));
	}
	
	protected static String pageTitleFound () {
		return processWithOrWithoutStepNum(
				pageTitleFound_info_WOStep (), 
				pageTitleFound_info_WStep ());
	}
	
	/*
	 * 
	 * 	AbstractPage wrapper method info statements 
	 * 	- call prior to Selenium find attempt
	 * 
	 */
	protected static String clickElem (String selectorString) {
		return processWithOrWithoutStepNum(
				clickElem_info_WOStep (selectorString), 
				clickElem_info_WStep (selectorString));
	}
	
	protected static String sendKeysToElem (String selectorString) {
		return processWithOrWithoutStepNum(
				sendKeysToElem_info_WOStep (selectorString), 
				sendKeysToElem_info_WStep (selectorString));
	}
	
	protected static String moveToElem (String selectorString) {
		return processWithOrWithoutStepNum(
				moveToElem_info_WOStep (selectorString), 
				moveToElem_info_WStep (selectorString));
	}
	
	protected static String returnElem (String selectorString) {
		return processWithOrWithoutStepNum(
				returnElem_info_WOStep (selectorString), 
				returnElem_info_WStep (selectorString));
	}
	
	protected static String verifyElemVisible (String selectorString) {
		return processWithOrWithoutStepNum(
				verifyElemVisible_info_WOStep (selectorString), 
				verifyElemVisible_info_WStep (selectorString));
	}
	
	protected static String verifyPageTitleVisible (String selectorString) {
		return processWithOrWithoutStepNum(
				verifyPageTitleVisible_info_WOStep (selectorString), 
				verifyPageTitleVisible_info_WStep (selectorString));
	}
	
	/*
	 * 
	 * 	Element found/not found confirmation strings
	 * 
	 */
	private static String elemFound_info_WOStep (String selectorType) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + elemFound + selectorType;
	}
	
	private static String elemFound_info_WStep (String selectorType) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + step + BaseEnvTest.stepNum 
				+ rightBrack + elemFound + selectorType;
	}
	
	private static String elemNotFound_error_WOStep (String selectorString) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + error + elemNotFound + selectorString;
	}
	
	private static String elemNotFound_error_WStep (String selectorString) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + error + step + BaseEnvTest.stepNum 
				+ rightBrack + elemNotFound + selectorString;
	}
	
	private static String elemNotFound_warn_WOStep (String selectorString) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + warn + elemNotFound + selectorString;
	}
	
	private static String elemNotFound_warn_WStep (String selectorString) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + warn + step + BaseEnvTest.stepNum 
				+ rightBrack + elemNotFound + selectorString;
	}
	
	private static String pageTitleFound_info_WOStep () {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + pageTitleFound;
	}
	
	private static String pageTitleFound_info_WStep () {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + step + BaseEnvTest.stepNum 
				+ rightBrack + pageTitleFound;
	}
	
	/*
	 * 
	 * 	Setup/teardown strings
	 * 
	 */
	private static String browser_info () {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + browserIs + BaseEnvTest.browser;
	}
	
	private static String url_info () {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + baseUrlIs + BaseEnvTest.baseurl;
	}

	private static String launchBrowser_info () {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + launchBrowser
				+ BaseEnvTest.browser;
	}
	
	private static String closeBrowser_info () {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + closeBrowser;
	}
	
	private static String beginSuite_info () {
		return leftBrack + BaseEnvTest.testSuite + rightBrack + info + beginSuite;
	}
	
	private static String endSuite_info () {
		return leftBrack + BaseEnvTest.testSuite + rightBrack + info + endSuite;
	}
	
	private static String beginClass_info () {
		return leftBrack + BaseEnvTest.testClass + rightBrack + info + beginClass;
	}
	
	private static String endClass_info () {
		return leftBrack + BaseEnvTest.testClass + rightBrack + info + endClass;
	}
	
	private static String beginTest_info () {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + beginTest;
	}
	
	private static String endTest_info () {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + endTest;
	}
	
	private static String getURL_info () {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + getURL + BaseEnvTest.baseurl;
	}
	
	/*
	 * 
	 * 	Selenium find strings
	 *
	 */
	private static String clickElem_info_WOStep (String selectorString) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + clickElement + selectorString;
	}

	private static String clickElem_info_WStep (String selectorString) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + step + BaseEnvTest.stepNum 
				+ rightBrack + clickElement + selectorString;
	}
	
	private static String sendKeysToElem_info_WOStep (String selectorString) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + sendKeysToElement + selectorString;
	}
	
	private static String sendKeysToElem_info_WStep (String selectorString) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + step + BaseEnvTest.stepNum 
				+ rightBrack + sendKeysToElement + selectorString;
	}
	
	private static String moveToElem_info_WOStep (String selectorString) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + moveToElement + selectorString;
	}
	
	private static String moveToElem_info_WStep (String selectorString) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + step + BaseEnvTest.stepNum 
				+ rightBrack + moveToElement + selectorString;
	}
	
	private static String returnElem_info_WOStep (String selectorString) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + returnElement + selectorString;
	}
	
	private static String returnElem_info_WStep (String selectorString) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + step + BaseEnvTest.stepNum 
				+ rightBrack + returnElement + selectorString;
	}
	
	private static String verifyElemVisible_info_WOStep (String selectorString) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + elementIsVisible + selectorString;
	}
	
	private static String verifyElemVisible_info_WStep (String selectorString) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + step + BaseEnvTest.stepNum 
				+ rightBrack + elementIsVisible + selectorString;
	}
	
	private static String verifyPageTitleVisible_info_WOStep (String selectorString) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + pageTitleIsVisible + selectorString;
	}
	
	private static String verifyPageTitleVisible_info_WStep (String selectorString) {
		return leftBrack + BaseEnvTest.testMethod + rightBrack + info + step + BaseEnvTest.stepNum 
				+ rightBrack + pageTitleIsVisible + selectorString;
	}
}
