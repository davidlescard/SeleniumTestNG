package com.moasis.bestbuy.pages;

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
			clickElement("logout-button");
			return this;
		} catch (NoSuchElementException e) {
			return this;
		}
	}
	
}
