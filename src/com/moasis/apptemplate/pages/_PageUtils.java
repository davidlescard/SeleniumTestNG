package com.moasis.apptemplate.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.moasis.AbstractPage;

/** 
 * @author      David Lescard
 * @version     1.0
 */
public class _PageUtils extends AbstractPage {

	public _PageUtils(WebDriver driver) {
		super(driver);
	}
	
	public _PageUtils logOut() {
		try {
			clickElement("Sign Out");
			return this;
		} catch (NoSuchElementException e) {
			return this;
		}
		
	}
	
}
