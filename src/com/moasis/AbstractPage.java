package com.moasis;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/** 
 * @author      David Lescard
 * @version     1.0
 */
public class AbstractPage {

	private final String id = "id";
	private final String name = "name";
	private final String className = "className";
	private final String linkText = "linkText";
	private final String xpath = "xpath";
	private final String cssSelector = "cssSelector";
    protected WebDriver driver;
	public final static String wait = "wait";
	public final static String shortWait = "shortWait";
	public final static String test = "test";
	public static int wait_standardLength = 
			_EnvConfig.getWaitTime() ; 
	public static int wait_shortLength = 
			_EnvConfig.getWaitTime_short() ;
	

    public AbstractPage(WebDriver driver) {
        this.driver = driver;

    }
    
    
    /*
     * 
     	Working with the AbstractPage wrapper methods
		When working with the wrapper methods for commonly used Selenium functions, 
		no specification for selector type is needed - the methods walk thru each of the standard 
		Selenium locator search types (below) in order to find the desired element:  
		
		- id
		- name
		- className
		- linkText
		- xpath
		- cssSelector
			   
		"Options..." parameter:
		for "options", any number of strings can be entered, but what the method is 
		looking for is either "wait" and/or "test". 
		  
		wait:
		here, the method will invoke a WebDriverWait instance that provides time 
		(referenced from _EnvConfig.java) for the element to appear. WebDriverWait 
		polls until the element is found or timer elapsed. 
		  
		test:
		passing in this value, the method adds a failed assert to the final catch, 
		thus intentionally failing your test case if the sought element is not located. 
		
		Note: both options may be used together, or in isolation. 
		Note: static strings are provided with values by the same name of the options, 
		eliminating the need to use quotes. 
			   
		Example usage: clickElement("div.required", wait, test)
			    
		Included :
		- clickElement
		- sendKeysToElement
		- moveToElement
		- returnElement
		- elementIsVisible
		- pageTitleIsVisible
	 *
     */
    
    private void finalCatch (String selectorString, String...args) {
    	if (Arrays.asList(args).contains("test")) {
    		Log.logEntry(Log.elemNotFound_error(selectorString));
			Assert.fail(Log.elemNotFound_error(selectorString));
		} else {
			Log.logEntry(Log.elemNotFound_warn(selectorString));
		}
    }
    
	public AbstractPage clickElement(String selectorString, String...args) {
		Log.logEntry(Log.clickElem(selectorString));
		if(Arrays.asList(args).contains(wait)) {
			int waitTime = wait_standardLength;
			return clickElement_processWithWait(selectorString, waitTime, args);
		} else if(Arrays.asList(args).contains(shortWait)) {
			int waitTime = wait_shortLength;
			return clickElement_processWithWait(selectorString, waitTime, args);
		} else {
			return clickElement_processWithoutWait(selectorString, args);
		}
	}
	
	public AbstractPage clickElement(UIElement elem, String...args) {
		Log.logEntry(Log.clickElem(elem.getName()));
		if(Arrays.asList(args).contains(wait)) {
			int waitTime = wait_standardLength;
			return clickElement_processWithWait(elem, waitTime, args);
		} else if(Arrays.asList(args).contains(shortWait)) {
			int waitTime = wait_shortLength;
			return clickElement_processWithWait(elem, waitTime, args);
		} else {
			return clickElement_processWithoutWait(elem, args);
		}
	}
	
	private AbstractPage clickElement_processWithWait(UIElement elem, int waitDuration, String...args) {
		String selectorString = elem.getSelector();
		String elemName = elem.getName();
		for (int i = 0; i <= waitDuration; i++) {
			try {
				WebElement element = new WebDriverWait(driver, 0)
					  .until(ExpectedConditions.visibilityOfElementLocated(
							  By.id(selectorString)));
				Log.logEntry(Log.elemFound(id));
				element.click();
				return this;
			} catch (WebDriverException ex) {
				try {
					WebElement element = new WebDriverWait(driver, 0)
						  .until(ExpectedConditions.visibilityOfElementLocated(
								  By.name(selectorString)));
					Log.logEntry(Log.elemFound(name));
					element.click();
					return this;
				} catch (WebDriverException ex2) {
					try {
						WebElement element = new WebDriverWait(driver, 0)
							  .until(ExpectedConditions.visibilityOfElementLocated(
									  By.className(selectorString)));
						Log.logEntry(Log.elemFound(className));
						element.click();
						return this;
					} catch (WebDriverException ex3) {
						try {
							WebElement element = new WebDriverWait(driver, 0)
								  .until(ExpectedConditions.visibilityOfElementLocated(
										  By.linkText(selectorString)));
							Log.logEntry(Log.elemFound(linkText));
							element.click();
							return this;
						} catch (WebDriverException ex4) {
							try {
								WebElement element = new WebDriverWait(driver, 0)
									  .until(ExpectedConditions.visibilityOfElementLocated(
											  By.xpath(selectorString)));
								Log.logEntry(Log.elemFound(xpath));
								element.click();
								return this;
							} catch (WebDriverException ex5) {
								try {
									WebElement element = new WebDriverWait(driver, 1)
										  .until(ExpectedConditions.visibilityOfElementLocated(
												  By.cssSelector(selectorString)));
									Log.logEntry(Log.elemFound(cssSelector));
									element.click();
									return this;
								} catch (WebDriverException ex6) {
									if (i == waitDuration) {
										finalCatch(elemName, args);
										return this;
									}
								}
							}
						}
					}
				}
			}
		}
		return this;
	}
	
	private AbstractPage clickElement_processWithoutWait (UIElement elem, String...args) {
		String selectorString = elem.getSelector();
		String elemName = elem.getName();
		WebElement element;
		try {
			element = driver.findElement(By.id(selectorString));
			Log.logEntry(Log.elemFound(id));
			element.click();
			return this;
		} catch (WebDriverException ex) {
			try {
				element = driver.findElement(By.name(selectorString));
				Log.logEntry(Log.elemFound(name));
				element.click();
				return this;
			} catch (WebDriverException ex2) {
				try {
					element = driver.findElement(By.className(selectorString));
					Log.logEntry(Log.elemFound(className));
					element.click();
					return this;
				} catch (WebDriverException ex3) {
					try {
						element = driver.findElement(By.linkText(selectorString));
						Log.logEntry(Log.elemFound(linkText));
						element.click();
						return this;
					} catch (WebDriverException ex4) {
						try {
							element = driver.findElement(By.xpath(selectorString));
							Log.logEntry(Log.elemFound(xpath));
							element.click();
							return this;
						} catch (WebDriverException ex5) {
							try {
								element = driver.findElement(By.cssSelector(selectorString));
								Log.logEntry(Log.elemFound(cssSelector));
								element.click();
								return this;
							} catch (WebDriverException ex6) {
								finalCatch(elemName, args);
								return this;
							}
						}
					}
				}
			}
		}
	}
	
	private AbstractPage clickElement_processWithWait(String selectorString, int waitDuration, String...args) {
		for (int i = 0; i <= waitDuration; i++) {
			try {
				WebElement element = new WebDriverWait(driver, 0)
					  .until(ExpectedConditions.visibilityOfElementLocated(
							  By.id(selectorString)));
				Log.logEntry(Log.elemFound(id));
				element.click();
				return this;
			} catch (WebDriverException ex) {
				try {
					WebElement element = new WebDriverWait(driver, 0)
						  .until(ExpectedConditions.visibilityOfElementLocated(
								  By.name(selectorString)));
					Log.logEntry(Log.elemFound(name));
					element.click();
					return this;
				} catch (WebDriverException ex2) {
					try {
						WebElement element = new WebDriverWait(driver, 0)
							  .until(ExpectedConditions.visibilityOfElementLocated(
									  By.className(selectorString)));
						Log.logEntry(Log.elemFound(className));
						element.click();
						return this;
					} catch (WebDriverException ex3) {
						try {
							WebElement element = new WebDriverWait(driver, 0)
								  .until(ExpectedConditions.visibilityOfElementLocated(
										  By.linkText(selectorString)));
							Log.logEntry(Log.elemFound(linkText));
							element.click();
							return this;
						} catch (WebDriverException ex4) {
							try {
								WebElement element = new WebDriverWait(driver, 0)
									  .until(ExpectedConditions.visibilityOfElementLocated(
											  By.xpath(selectorString)));
								Log.logEntry(Log.elemFound(xpath));
								element.click();
								return this;
							} catch (WebDriverException ex5) {
								try {
									WebElement element = new WebDriverWait(driver, 1)
										  .until(ExpectedConditions.visibilityOfElementLocated(
												  By.cssSelector(selectorString)));
									Log.logEntry(Log.elemFound(cssSelector));
									element.click();
									return this;
								} catch (WebDriverException ex6) {
									if (i == waitDuration) {
										finalCatch(selectorString, args);
										return this;
									}
								}
							}
						}
					}
				}
			}
		}
		return this;
	}
	
	private AbstractPage clickElement_processWithoutWait (String selectorString, String...args) {
		WebElement element;
		
		try {
			element = driver.findElement(By.id(selectorString));
			Log.logEntry(Log.elemFound(id));
			element.click();
			return this;
		} catch (WebDriverException ex) {
			try {
				element = driver.findElement(By.name(selectorString));
				Log.logEntry(Log.elemFound(name));
				element.click();
				return this;
			} catch (WebDriverException ex2) {
				try {
					element = driver.findElement(By.className(selectorString));
					Log.logEntry(Log.elemFound(className));
					element.click();
					return this;
				} catch (WebDriverException ex3) {
					try {
						element = driver.findElement(By.linkText(selectorString));
						Log.logEntry(Log.elemFound(linkText));
						element.click();
						return this;
					} catch (WebDriverException ex4) {
						try {
							element = driver.findElement(By.xpath(selectorString));
							Log.logEntry(Log.elemFound(xpath));
							element.click();
							return this;
						} catch (WebDriverException ex5) {
							try {
								element = driver.findElement(By.cssSelector(selectorString));
								Log.logEntry(Log.elemFound(cssSelector));
								element.click();
								return this;
							} catch (WebDriverException ex6) {
								finalCatch(selectorString, args);
								return this;
							}
						}
					}
				}
			}
		}
	}
	
	public AbstractPage sendKeysToElement(String selectorString, String keys, String...args) {
		Log.logEntry(Log.sendKeysToElem(selectorString));
		if(Arrays.asList(args).contains(wait)) {
			int waitTime = wait_standardLength;
			return sendKeysToElement_processWithWait(selectorString, keys, waitTime, args);
		} else if(Arrays.asList(args).contains(shortWait)) {
			int waitTime = wait_shortLength;
			return sendKeysToElement_processWithWait(selectorString, keys, waitTime, args);
		} else {
			return sendKeysToElement_processWithoutWait(selectorString, keys, args);
		}
	}
	
	public AbstractPage sendKeysToElement(UIElement elem, String keys, String...args) {
		Log.logEntry(Log.sendKeysToElem(elem.getName()));
		if(Arrays.asList(args).contains(wait)) {
			int waitTime = wait_standardLength;
			return sendKeysToElement_processWithWait(elem, keys, waitTime, args);
		} else if(Arrays.asList(args).contains(shortWait)) {
			int waitTime = wait_shortLength;
			return sendKeysToElement_processWithWait(elem, keys, waitTime, args);
		} else {
			return sendKeysToElement_processWithoutWait(elem, keys, args);
		}
	}
	
	private AbstractPage sendKeysToElement_processWithWait(UIElement elem, String keys, int waitTime, String...args) {
		String selectorString = elem.getSelector();
		String elemName = elem.getName();
		for (int i = 0; i <= waitTime; i++) {
			try {
				WebElement element = new WebDriverWait(driver, 0)
					  .until(ExpectedConditions.visibilityOfElementLocated(
							  By.id(selectorString)));
				Log.logEntry(Log.elemFound(id));
				element.clear();
				element.sendKeys(keys);
				element.clear();
				element.sendKeys(keys);
				return this;
			} catch (WebDriverException ex) {
				try {
					WebElement element = new WebDriverWait(driver, 0)
						  .until(ExpectedConditions.visibilityOfElementLocated(
								  By.name(selectorString)));
					Log.logEntry(Log.elemFound(name));
					element.clear();
					element.sendKeys(keys);
					element.clear();
					element.sendKeys(keys);
					return this;
				} catch (WebDriverException ex2) {
					try {
							WebElement element = new WebDriverWait(driver, 0)
								  .until(ExpectedConditions.visibilityOfElementLocated(
										  By.className(selectorString)));
							Log.logEntry(Log.elemFound(className));
							element.clear();
							element.sendKeys(keys);
							element.clear();
							element.sendKeys(keys);
							return this;
						} catch (WebDriverException ex3) {
							try {
							WebElement element = new WebDriverWait(driver, 0)
								  .until(ExpectedConditions.visibilityOfElementLocated(
										  By.linkText(selectorString)));
							Log.logEntry(Log.elemFound(linkText));
							element.clear();
							element.sendKeys(keys);
							element.clear();
							element.sendKeys(keys);
							return this;
						} catch (WebDriverException ex4) {
 							try {
 								WebElement element = new WebDriverWait(driver, 0)
 									  .until(ExpectedConditions.visibilityOfElementLocated(
 											  By.xpath(selectorString)));
 								Log.logEntry(Log.elemFound(xpath));
 								element.clear();
 								element.sendKeys(keys);
 								element.clear();
 								element.sendKeys(keys);
 								return this;
 							} catch (WebDriverException ex5) {
 				 				try {
				 					WebElement element = new WebDriverWait(driver, 1)
				 						  .until(ExpectedConditions.visibilityOfElementLocated(
				 								  By.cssSelector(selectorString)));
				 					Log.logEntry(Log.elemFound(cssSelector));
				 					element.clear();
				 					element.sendKeys(keys);
				 					element.clear();
				 					element.sendKeys(keys);
				 					return this;
			 					} catch (WebDriverException ex6) {
	 								if (i == waitTime) {
	 									finalCatch(elemName, args);
	 									return this;
										}
									}
								}
							}
						}
					}
				}
			}
			return this;
	}
	
	private AbstractPage sendKeysToElement_processWithoutWait (UIElement elem, String keys, String...args) {
		String selectorString = elem.getSelector();
		String elemName = elem.getName();
		WebElement element;
		try {
			element = driver.findElement(By.id(selectorString));
			Log.logEntry(Log.elemFound(id));
			element.clear();
			element.sendKeys(keys);
			element.clear();
			element.sendKeys(keys);
			return this;
		} catch (WebDriverException ex) {
			try {
				element = driver.findElement(By.name(selectorString));
				Log.logEntry(Log.elemFound(name));
				element.clear();
				element.sendKeys(keys);
				element.clear();
				element.sendKeys(keys);
				return this;
			} catch (WebDriverException ex2) {
				try {
					element = driver.findElement(By.className(selectorString));
					Log.logEntry(Log.elemFound(className));
					element.clear();
					element.sendKeys(keys);
					element.clear();
					element.sendKeys(keys);
					return this;
				} catch (WebDriverException ex3) {
					try {
						element = driver.findElement(By.linkText(selectorString));
						Log.logEntry(Log.elemFound(linkText));
						element.clear();
						element.sendKeys(keys);
						element.clear();
						element.sendKeys(keys);
						return this;
					} catch (WebDriverException ex4) {
						try {
							element = driver.findElement(By.xpath(selectorString));
							Log.logEntry(Log.elemFound(xpath));
							element.clear();
							element.sendKeys(keys);
							element.clear();
							element.sendKeys(keys);
							return this;
						} catch (WebDriverException ex5) {
							try {
								element = driver.findElement(By.cssSelector(selectorString));
								Log.logEntry(Log.elemFound(cssSelector));
								element.clear();
								element.sendKeys(keys);
								element.clear();
								element.sendKeys(keys);
								return this;
							} catch (WebDriverException ex6) {
								finalCatch(elemName, args);
								return this;
							}
						}
					}
				}
			}
		}
	}
	
	private AbstractPage sendKeysToElement_processWithWait(String selectorString, String keys, int waitTime, String...args) {
		for (int i = 0; i <= waitTime; i++) {
			try {
				WebElement element = new WebDriverWait(driver, 0)
					  .until(ExpectedConditions.visibilityOfElementLocated(
							  By.id(selectorString)));
				Log.logEntry(Log.elemFound(id));
				element.clear();
				element.sendKeys(keys);
				element.clear();
				element.sendKeys(keys);
				return this;
			} catch (WebDriverException ex) {
				try {
					WebElement element = new WebDriverWait(driver, 0)
						  .until(ExpectedConditions.visibilityOfElementLocated(
								  By.name(selectorString)));
					Log.logEntry(Log.elemFound(name));
					element.clear();
					element.sendKeys(keys);
					element.clear();
					element.sendKeys(keys);
					return this;
				} catch (WebDriverException ex2) {
					try {
							WebElement element = new WebDriverWait(driver, 0)
								  .until(ExpectedConditions.visibilityOfElementLocated(
										  By.className(selectorString)));
							Log.logEntry(Log.elemFound(className));
							element.clear();
							element.sendKeys(keys);
							element.clear();
							element.sendKeys(keys);
							return this;
						} catch (WebDriverException ex3) {
							try {
							WebElement element = new WebDriverWait(driver, 0)
								  .until(ExpectedConditions.visibilityOfElementLocated(
										  By.linkText(selectorString)));
							Log.logEntry(Log.elemFound(linkText));
							element.clear();
							element.sendKeys(keys);
							element.clear();
							element.sendKeys(keys);
							return this;
						} catch (WebDriverException ex4) {
 							try {
 								WebElement element = new WebDriverWait(driver, 0)
 									  .until(ExpectedConditions.visibilityOfElementLocated(
 											  By.xpath(selectorString)));
 								Log.logEntry(Log.elemFound(xpath));
 								element.clear();
 								element.sendKeys(keys);
 								element.clear();
 								element.sendKeys(keys);
 								return this;
 							} catch (WebDriverException ex5) {
 				 				try {
				 					WebElement element = new WebDriverWait(driver, 1)
				 						  .until(ExpectedConditions.visibilityOfElementLocated(
				 								  By.cssSelector(selectorString)));
				 					Log.logEntry(Log.elemFound(cssSelector));
				 					element.clear();
				 					element.sendKeys(keys);
				 					element.clear();
				 					element.sendKeys(keys);
				 					return this;
			 					} catch (WebDriverException ex6) {
	 								if (i == waitTime) {
	 									finalCatch(selectorString, args);
	 									return this;
										}
									}
								}
							}
						}
					}
				}
			}
			return this;
	}
	
	private AbstractPage sendKeysToElement_processWithoutWait (String selectorString, String keys, String...args) {
		WebElement element;
		try {
			element = driver.findElement(By.id(selectorString));
			Log.logEntry(Log.elemFound(id));
			element.clear();
			element.sendKeys(keys);
			element.clear();
			element.sendKeys(keys);
			return this;
		} catch (WebDriverException ex) {
			try {
				element = driver.findElement(By.name(selectorString));
				Log.logEntry(Log.elemFound(name));
				element.clear();
				element.sendKeys(keys);
				element.clear();
				element.sendKeys(keys);
				return this;
			} catch (WebDriverException ex2) {
				try {
					element = driver.findElement(By.className(selectorString));
					Log.logEntry(Log.elemFound(className));
					element.clear();
					element.sendKeys(keys);
					element.clear();
					element.sendKeys(keys);
					return this;
				} catch (WebDriverException ex3) {
					try {
						element = driver.findElement(By.linkText(selectorString));
						Log.logEntry(Log.elemFound(linkText));
						element.clear();
						element.sendKeys(keys);
						element.clear();
						element.sendKeys(keys);
						return this;
					} catch (WebDriverException ex4) {
						try {
							element = driver.findElement(By.xpath(selectorString));
							Log.logEntry(Log.elemFound(xpath));
							element.clear();
							element.sendKeys(keys);
							element.clear();
							element.sendKeys(keys);
							return this;
						} catch (WebDriverException ex5) {
							try {
								element = driver.findElement(By.cssSelector(selectorString));
								Log.logEntry(Log.elemFound(cssSelector));
								element.clear();
								element.sendKeys(keys);
								element.clear();
								element.sendKeys(keys);
								return this;
							} catch (WebDriverException ex6) {
								finalCatch(selectorString, args);
								return this;
							}
						}
					}
				}
			}
		}
	}
	
	public AbstractPage moveToElement(String selectorString, String...args) {
		Log.logEntry(Log.moveToElem(selectorString));
		if(Arrays.asList(args).contains(wait)) {
			int waitTime = wait_standardLength;
			return moveToElement_processWithWait(selectorString, waitTime, args);
		} else if(Arrays.asList(args).contains(shortWait)) {
			int waitTime = wait_shortLength;
			return moveToElement_processWithWait(selectorString, waitTime, args);
		} else {
			return moveToElement_processWithoutWait(selectorString, args);
		}
	}
	
	public AbstractPage moveToElement(UIElement elem, String...args) {
		Log.logEntry(Log.moveToElem(elem.getName()));
		if(Arrays.asList(args).contains(wait)) {
			int waitTime = wait_standardLength;
			return moveToElement_processWithWait(elem, waitTime, args);
		} else if(Arrays.asList(args).contains(shortWait)) {
			int waitTime = wait_shortLength;
			return moveToElement_processWithWait(elem, waitTime, args);
		} else {
			return moveToElement_processWithoutWait(elem, args);
		}
	}
	
	private AbstractPage moveToElement_processWithWait(UIElement elem, int waitTime, String...args) {
		String selectorString = elem.getSelector();
		String elemName = elem.getName();
		Actions actions = new Actions(driver);
		for (int i = 0; i <= waitTime; i++) {
			try {
				WebElement element = new WebDriverWait(driver, 0)
					  .until(ExpectedConditions.visibilityOfElementLocated(
							  By.id(selectorString)));
				Log.logEntry(Log.elemFound(id));
				actions.moveToElement(element);
				return this;
			} catch (WebDriverException ex) {
				try {
					WebElement element = new WebDriverWait(driver, 0)
						  .until(ExpectedConditions.visibilityOfElementLocated(
								  By.name(selectorString)));
					Log.logEntry(Log.elemFound(name));
					actions.moveToElement(element);
					return this;
				} catch (WebDriverException ex2) {
					try {
						WebElement element = new WebDriverWait(driver, 0)
							  .until(ExpectedConditions.visibilityOfElementLocated(
									  By.className(selectorString)));
						Log.logEntry(Log.elemFound(className));
						actions.moveToElement(element);
						return this;
					} catch (WebDriverException ex3) {
						try {
							WebElement element = new WebDriverWait(driver, 0)
								  .until(ExpectedConditions.visibilityOfElementLocated(
										  By.linkText(selectorString)));
							Log.logEntry(Log.elemFound(linkText));
							actions.moveToElement(element);
							return this;
						} catch (WebDriverException ex4) {
							try {
								WebElement element = new WebDriverWait(driver, 0)
									  .until(ExpectedConditions.visibilityOfElementLocated(
											  By.xpath(selectorString)));
								Log.logEntry(Log.elemFound(xpath));
								actions.moveToElement(element);
								return this;
							} catch (WebDriverException ex5) {
								try {
									WebElement element = new WebDriverWait(driver, 1)
										  .until(ExpectedConditions.visibilityOfElementLocated(
												  By.cssSelector(selectorString)));
									Log.logEntry(Log.elemFound(cssSelector));
									actions.moveToElement(element);
									return this;
								} catch (WebDriverException ex6) {
									if (i == waitTime) {
										finalCatch(elemName, args);
										return this;
									}
								}
							}
						}
					}
				}
			}
		}
		return this;
	}
	
	private AbstractPage moveToElement_processWithoutWait(UIElement elem, String...args) {
		String selectorString = elem.getSelector();
		String elemName = elem.getName();
		Actions actions = new Actions(driver);
		WebElement element;
		try {
			element = driver.findElement(By.id(selectorString));
			Log.logEntry(Log.elemFound(id));
			actions.moveToElement(element);
			return this;
		} catch (WebDriverException ex) {
			try {
				element = driver.findElement(By.name(selectorString));
				Log.logEntry(Log.elemFound(name));
				actions.moveToElement(element);
				return this;
			} catch (WebDriverException ex2) {
				try {
					element = driver.findElement(By.className(selectorString));
					Log.logEntry(Log.elemFound(className));
					actions.moveToElement(element);
					return this;
				} catch (WebDriverException ex3) {
					try {
						element = driver.findElement(By.linkText(selectorString));
						Log.logEntry(Log.elemFound(linkText));
						actions.moveToElement(element);
						return this;
					} catch (WebDriverException ex4) {
						try {
							element = driver.findElement(By.xpath(selectorString));
							Log.logEntry(Log.elemFound(xpath));
							actions.moveToElement(element);
							return this;
						} catch (WebDriverException ex5) {
							try {
								element = driver.findElement(By.cssSelector(selectorString));
								Log.logEntry(Log.elemFound(cssSelector));
								actions.moveToElement(element);
								return this;
							} catch (WebDriverException ex6) {
								finalCatch(elemName, args);
								return this;
							}
						}
					}
				}
			}
		}
	}
	
	
	private AbstractPage moveToElement_processWithWait(String selectorString, int waitTime, String...args) {
		Actions actions = new Actions(driver);
		for (int i = 0; i <= waitTime; i++) {
			try {
				WebElement element = new WebDriverWait(driver, 0)
					  .until(ExpectedConditions.visibilityOfElementLocated(
							  By.id(selectorString)));
				Log.logEntry(Log.elemFound(id));
				actions.moveToElement(element);
				return this;
			} catch (WebDriverException ex) {
				try {
					WebElement element = new WebDriverWait(driver, 0)
						  .until(ExpectedConditions.visibilityOfElementLocated(
								  By.name(selectorString)));
					Log.logEntry(Log.elemFound(name));
					actions.moveToElement(element);
					return this;
				} catch (WebDriverException ex2) {
					try {
						WebElement element = new WebDriverWait(driver, 0)
							  .until(ExpectedConditions.visibilityOfElementLocated(
									  By.className(selectorString)));
						Log.logEntry(Log.elemFound(className));
						actions.moveToElement(element);
						return this;
					} catch (WebDriverException ex3) {
						try {
							WebElement element = new WebDriverWait(driver, 0)
								  .until(ExpectedConditions.visibilityOfElementLocated(
										  By.linkText(selectorString)));
							Log.logEntry(Log.elemFound(linkText));
							actions.moveToElement(element);
							return this;
						} catch (WebDriverException ex4) {
							try {
								WebElement element = new WebDriverWait(driver, 0)
									  .until(ExpectedConditions.visibilityOfElementLocated(
											  By.xpath(selectorString)));
								Log.logEntry(Log.elemFound(xpath));
								actions.moveToElement(element);
								return this;
							} catch (WebDriverException ex5) {
								try {
									WebElement element = new WebDriverWait(driver, 1)
										  .until(ExpectedConditions.visibilityOfElementLocated(
												  By.cssSelector(selectorString)));
									Log.logEntry(Log.elemFound(cssSelector));
									actions.moveToElement(element);
									return this;
								} catch (WebDriverException ex6) {
									if (i == waitTime) {
										finalCatch(selectorString, args);
										return this;
									}
								}
							}
						}
					}
				}
			}
		}
		return this;
	}
	
	private AbstractPage moveToElement_processWithoutWait(String selectorString, String...args) {
		Actions actions = new Actions(driver);
		WebElement element;
		try {
			element = driver.findElement(By.id(selectorString));
			Log.logEntry(Log.elemFound(id));
			actions.moveToElement(element);
			return this;
		} catch (WebDriverException ex) {
			try {
				element = driver.findElement(By.name(selectorString));
				Log.logEntry(Log.elemFound(name));
				actions.moveToElement(element);
				return this;
			} catch (WebDriverException ex2) {
				try {
					element = driver.findElement(By.className(selectorString));
					Log.logEntry(Log.elemFound(className));
					actions.moveToElement(element);
					return this;
				} catch (WebDriverException ex3) {
					try {
						element = driver.findElement(By.linkText(selectorString));
						Log.logEntry(Log.elemFound(linkText));
						actions.moveToElement(element);
						return this;
					} catch (WebDriverException ex4) {
						try {
							element = driver.findElement(By.xpath(selectorString));
							Log.logEntry(Log.elemFound(xpath));
							actions.moveToElement(element);
							return this;
						} catch (WebDriverException ex5) {
							try {
								element = driver.findElement(By.cssSelector(selectorString));
								Log.logEntry(Log.elemFound(cssSelector));
								actions.moveToElement(element);
								return this;
							} catch (WebDriverException ex6) {
								finalCatch(selectorString, args);
								return this;
							}
						}
					}
				}
			}
		}
	}
	
	public WebElement returnElement(String selectorString, String...args) {
		Log.logEntry(Log.returnElem(selectorString));
		if(Arrays.asList(args).contains(wait)) {
			int waitTime = wait_standardLength;
			return returnElement_processWithWait(selectorString, waitTime, args);
		} else if(Arrays.asList(args).contains(shortWait)) {
			int waitTime = wait_shortLength;
			return returnElement_processWithWait(selectorString, waitTime, args);
		} else {
			return returnElement_processWithoutWait(selectorString, args);
		}
	}
	
	public WebElement returnElement(UIElement elem, String...args) {
		Log.logEntry(Log.returnElem(elem.getName()));
		if(Arrays.asList(args).contains(wait)) {
			int waitTime = wait_standardLength;
			return returnElement_processWithWait(elem, waitTime, args);
		} else if(Arrays.asList(args).contains(shortWait)) {
			int waitTime = wait_shortLength;
			return returnElement_processWithWait(elem, waitTime, args);
		} else {
			return returnElement_processWithoutWait(elem, args);
		}
	}
	
	private WebElement returnElement_processWithWait(UIElement elem, int waitTime, String...args) {
		String selectorString = elem.getSelector();
		String elemName = elem.getName();
		for (int i = 0; i <= waitTime; i++) {
			try {
				WebElement element = new WebDriverWait(driver, 0)
					  .until(ExpectedConditions.visibilityOfElementLocated(
							  By.id(selectorString)));
				Log.logEntry(Log.elemFound(id));
				return element;
			} catch (WebDriverException ex) {
				try {
					WebElement element = new WebDriverWait(driver, 0)
						  .until(ExpectedConditions.visibilityOfElementLocated(
								  By.name(selectorString)));
					Log.logEntry(Log.elemFound(name));
					return element;
				} catch (WebDriverException ex2) {
					try {
						WebElement element = new WebDriverWait(driver, 0)
							  .until(ExpectedConditions.visibilityOfElementLocated(
									  By.className(selectorString)));
						Log.logEntry(Log.elemFound(className));
						return element;
					} catch (WebDriverException ex3) {
						try {
							WebElement element = new WebDriverWait(driver, 0)
								  .until(ExpectedConditions.visibilityOfElementLocated(
										  By.linkText(selectorString)));
							Log.logEntry(Log.elemFound(linkText));
							return element;
						} catch (WebDriverException ex4) {
							try {
								WebElement element = new WebDriverWait(driver, 0)
									  .until(ExpectedConditions.visibilityOfElementLocated(
											  By.xpath(selectorString)));
								Log.logEntry(Log.elemFound(xpath));
								return element;
							} catch (WebDriverException ex5) {
								try {
									WebElement element = new WebDriverWait(driver, 1)
										  .until(ExpectedConditions.visibilityOfElementLocated(
												  By.cssSelector(selectorString)));
									Log.logEntry(Log.elemFound(cssSelector));
									return element;
								} catch (WebDriverException ex6) {
									if (i == waitTime) {
										finalCatch(elemName, args);
										return null;
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	private WebElement returnElement_processWithoutWait(UIElement elem, String...args) {
		String selectorString = elem.getSelector();
		String elemName = elem.getName();
		try {
			WebElement element = driver.findElement(
						  By.id(selectorString));
			Log.logEntry(Log.elemFound(id));
			return element;
		} catch (WebDriverException ex) {
			try {
				WebElement element = driver.findElement(
							  By.name(selectorString));
				Log.logEntry(Log.elemFound(name));
				return element;
			} catch (WebDriverException ex2) {
				try {
					WebElement element = driver.findElement(
								  By.className(selectorString));
					Log.logEntry(Log.elemFound(className));
					return element;
				} catch (WebDriverException ex3) {
					try {
						WebElement element = driver.findElement(
									  By.linkText(selectorString));
						Log.logEntry(Log.elemFound(linkText));
						return element;
					} catch (WebDriverException ex4) {
						try {
							WebElement element = driver.findElement(
										  By.xpath(selectorString));
							Log.logEntry(Log.elemFound(xpath));
							return element;
						} catch (WebDriverException ex5) {
							try {
								WebElement element = driver.findElement(
											  By.cssSelector(selectorString));
								Log.logEntry(Log.elemFound(cssSelector));
								return element;
							} catch (WebDriverException ex6) {
								finalCatch(elemName, args);
								return null;
							}
						}
					}
				}
			}
		}
	}
	

	private WebElement returnElement_processWithWait(String selectorString, int waitTime, String...args) {
		for (int i = 0; i <= waitTime; i++) {
			try {
				WebElement element = new WebDriverWait(driver, 0)
					  .until(ExpectedConditions.visibilityOfElementLocated(
							  By.id(selectorString)));
				Log.logEntry(Log.elemFound(id));
				return element;
			} catch (WebDriverException ex) {
				try {
					WebElement element = new WebDriverWait(driver, 0)
						  .until(ExpectedConditions.visibilityOfElementLocated(
								  By.name(selectorString)));
					Log.logEntry(Log.elemFound(name));
					return element;
				} catch (WebDriverException ex2) {
					try {
						WebElement element = new WebDriverWait(driver, 0)
							  .until(ExpectedConditions.visibilityOfElementLocated(
									  By.className(selectorString)));
						Log.logEntry(Log.elemFound(className));
						return element;
					} catch (WebDriverException ex3) {
						try {
							WebElement element = new WebDriverWait(driver, 0)
								  .until(ExpectedConditions.visibilityOfElementLocated(
										  By.linkText(selectorString)));
							Log.logEntry(Log.elemFound(linkText));
							return element;
						} catch (WebDriverException ex4) {
							try {
								WebElement element = new WebDriverWait(driver, 0)
									  .until(ExpectedConditions.visibilityOfElementLocated(
											  By.xpath(selectorString)));
								Log.logEntry(Log.elemFound(xpath));
								return element;
							} catch (WebDriverException ex5) {
								try {
									WebElement element = new WebDriverWait(driver, 1)
										  .until(ExpectedConditions.visibilityOfElementLocated(
												  By.cssSelector(selectorString)));
									Log.logEntry(Log.elemFound(cssSelector));
									return element;
								} catch (WebDriverException ex6) {
									if (i == waitTime) {
										finalCatch(selectorString, args);
										return null;
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	private WebElement returnElement_processWithoutWait(String selectorString, String...args) {
		try {
			WebElement element = driver.findElement(
						  By.id(selectorString));
			Log.logEntry(Log.elemFound(id));
			return element;
		} catch (WebDriverException ex) {
			try {
				WebElement element = driver.findElement(
							  By.name(selectorString));
				Log.logEntry(Log.elemFound(name));
				return element;
			} catch (WebDriverException ex2) {
				try {
					WebElement element = driver.findElement(
								  By.className(selectorString));
					Log.logEntry(Log.elemFound(className));
					return element;
				} catch (WebDriverException ex3) {
					try {
						WebElement element = driver.findElement(
									  By.linkText(selectorString));
						Log.logEntry(Log.elemFound(linkText));
						return element;
					} catch (WebDriverException ex4) {
						try {
							WebElement element = driver.findElement(
										  By.xpath(selectorString));
							Log.logEntry(Log.elemFound(xpath));
							return element;
						} catch (WebDriverException ex5) {
							try {
								WebElement element = driver.findElement(
											  By.cssSelector(selectorString));
								Log.logEntry(Log.elemFound(cssSelector));
								return element;
							} catch (WebDriverException ex6) {
								finalCatch(selectorString, args);
								return null;
							}
						}
					}
				}
			}
		}
	}
	
	public boolean elementIsVisible(String selectorString, String...args) {
		Log.logEntry(Log.verifyElemVisible(selectorString));
		if(Arrays.asList(args).contains(wait)) {
			int waitTime = wait_standardLength;
			return elementIsVisible_processWithWait(selectorString, waitTime, args);
		} else if(Arrays.asList(args).contains(shortWait)) {
			int waitTime = wait_shortLength;
			return elementIsVisible_processWithWait(selectorString, waitTime, args);
		} else {
			return elementIsVisible_processWithoutWait(selectorString, args);
		}
	}
	
	public boolean elementIsVisible(UIElement elem, String...args) {
		Log.logEntry(Log.verifyElemVisible(elem.getName()));
		if(Arrays.asList(args).contains(wait)) {
			int waitTime = wait_standardLength;
			return elementIsVisible_processWithWait(elem, waitTime, args);
		} else if(Arrays.asList(args).contains(shortWait)) {
			int waitTime = wait_shortLength;
			return elementIsVisible_processWithWait(elem, waitTime, args);
		} else {
			return elementIsVisible_processWithoutWait(elem, args);
		}
	}
	
	private boolean elementIsVisible_processWithWait(UIElement elem, int waitTime, String...args) {
		String selectorString = elem.getSelector();
		String elemName = elem.getName();
		for (int i = 0; i <= waitTime; i++) {
			try {
				new WebDriverWait(driver, 0)
				  .until(ExpectedConditions.visibilityOfElementLocated(
						  By.id(selectorString)));
				Log.logEntry(Log.elemFound(id));
				return true;
			} catch (WebDriverException ex) {
				try {
					new WebDriverWait(driver, 0)
					  .until(ExpectedConditions.visibilityOfElementLocated(
							  By.name(selectorString)));
					Log.logEntry(Log.elemFound(name));
					return true;
				} catch (WebDriverException ex2) {
					try {
						new WebDriverWait(driver, 0)
						  .until(ExpectedConditions.visibilityOfElementLocated(
								  By.className(selectorString)));
						Log.logEntry(Log.elemFound(className));
						return true;
					} catch (WebDriverException ex3) {
						try {
							new WebDriverWait(driver, 0)
							  .until(ExpectedConditions.visibilityOfElementLocated(
									  By.linkText(selectorString)));
							Log.logEntry(Log.elemFound(linkText));
							return true;
						} catch (WebDriverException ex4) {
							try {
								new WebDriverWait(driver, 0)
								  .until(ExpectedConditions.visibilityOfElementLocated(
										  By.xpath(selectorString)));
								Log.logEntry(Log.elemFound(xpath));
								return true;
							} catch (WebDriverException ex5) {
								try {
									new WebDriverWait(driver, 1)
									  .until(ExpectedConditions.visibilityOfElementLocated(
											  By.cssSelector(selectorString)));
									Log.logEntry(Log.elemFound(cssSelector));
									return true;
								} catch (WebDriverException ex6) {
									if (i == waitTime) {
										finalCatch(elemName, args);
										return false;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean elementIsVisible_processWithoutWait(UIElement elem, String...args) {
		String selectorString = elem.getSelector();
		String elemName = elem.getName();
		try {
			driver.findElement(By.id(selectorString));
			Log.logEntry(Log.elemFound(id));
			return true;
		} catch (WebDriverException ex) {
			try {
				driver.findElement(By.name(selectorString));
				Log.logEntry(Log.elemFound(name));
				return true;
			} catch (WebDriverException ex2) {
				try {
					driver.findElement(By.className(selectorString));
					Log.logEntry(Log.elemFound(className));
					return true;
				} catch (WebDriverException ex3) {
					try {
						driver.findElement(By.linkText(selectorString));
						Log.logEntry(Log.elemFound(linkText));
						return true;
					} catch (WebDriverException ex4) {
						try {
							driver.findElement(By.xpath(selectorString));
							Log.logEntry(Log.elemFound(xpath));
							return true;
						} catch (WebDriverException ex5) {
							try {
								driver.findElement(By.cssSelector(selectorString));
								Log.logEntry(Log.elemFound(cssSelector));
								return true;
							} catch (WebDriverException ex6) {
								finalCatch(elemName, args);
								return false;
							}
						}
					}
				}
			}
		}
	}
	
	private boolean elementIsVisible_processWithWait(String selectorString, int waitTime, String...args) {
		for (int i = 0; i <= waitTime; i++) {
			try {
				new WebDriverWait(driver, 0)
				  .until(ExpectedConditions.visibilityOfElementLocated(
						  By.id(selectorString)));
				Log.logEntry(Log.elemFound(id));
				return true;
			} catch (WebDriverException ex) {
				try {
					new WebDriverWait(driver, 0)
					  .until(ExpectedConditions.visibilityOfElementLocated(
							  By.name(selectorString)));
					Log.logEntry(Log.elemFound(name));
					return true;
				} catch (WebDriverException ex2) {
					try {
						new WebDriverWait(driver, 0)
						  .until(ExpectedConditions.visibilityOfElementLocated(
								  By.className(selectorString)));
						Log.logEntry(Log.elemFound(className));
						return true;
					} catch (WebDriverException ex3) {
						try {
							new WebDriverWait(driver, 0)
							  .until(ExpectedConditions.visibilityOfElementLocated(
									  By.linkText(selectorString)));
							Log.logEntry(Log.elemFound(linkText));
							return true;
						} catch (WebDriverException ex4) {
							try {
								new WebDriverWait(driver, 0)
								  .until(ExpectedConditions.visibilityOfElementLocated(
										  By.xpath(selectorString)));
								Log.logEntry(Log.elemFound(xpath));
								return true;
							} catch (WebDriverException ex5) {
								try {
									new WebDriverWait(driver, 1)
									  .until(ExpectedConditions.visibilityOfElementLocated(
											  By.cssSelector(selectorString)));
									Log.logEntry(Log.elemFound(cssSelector));
									return true;
								} catch (WebDriverException ex6) {
									if (i == waitTime) {
										finalCatch(selectorString, args);
										return false;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean elementIsVisible_processWithoutWait(String selectorString, String...args) {
		try {
			driver.findElement(By.id(selectorString));
			Log.logEntry(Log.elemFound(id));
			return true;
		} catch (WebDriverException ex) {
			try {
				driver.findElement(By.name(selectorString));
				Log.logEntry(Log.elemFound(name));
				return true;
			} catch (WebDriverException ex2) {
				try {
					driver.findElement(By.className(selectorString));
					Log.logEntry(Log.elemFound(className));
					return true;
				} catch (WebDriverException ex3) {
					try {
						driver.findElement(By.linkText(selectorString));
						Log.logEntry(Log.elemFound(linkText));
						return true;
					} catch (WebDriverException ex4) {
						try {
							driver.findElement(By.xpath(selectorString));
							Log.logEntry(Log.elemFound(xpath));
							return true;
						} catch (WebDriverException ex5) {
							try {
								driver.findElement(By.cssSelector(selectorString));
								Log.logEntry(Log.elemFound(cssSelector));
								return true;
							} catch (WebDriverException ex6) {
								finalCatch(selectorString, args);
								return false;
							}
						}
					}
				}
			}
		}
	}
	
	public boolean pageTitleIsVisible(String expectedTitle, String...args) {
		Log.logEntry(Log.verifyPageTitleVisible(expectedTitle));
		if(Arrays.asList(args).contains(wait)) {
			try {
				new WebDriverWait(driver, wait_standardLength)
					  .until(ExpectedConditions.titleIs(expectedTitle));
				Log.logEntry(Log.pageTitleFound());
				return true;
			}catch (TimeoutException ex) {
				finalCatch(expectedTitle, args);
				return false;
			}
		} else if(Arrays.asList(args).contains(shortWait)) {
			try {
				new WebDriverWait(driver, wait_shortLength)
					  .until(ExpectedConditions.titleIs(expectedTitle));
				Log.logEntry(Log.pageTitleFound());
				return true;
			}catch (TimeoutException ex) {
				finalCatch(expectedTitle, args);
				return false;
			}
		} else {
			try {
				String s = driver.getTitle();
				s.equals(expectedTitle);
				Log.logEntry(Log.pageTitleFound());
				return true;
			}catch (TimeoutException ex) {
				finalCatch(expectedTitle, args);
				return false;
			}
		}
	}
	
	public boolean pageTitleIsVisible(UIElement elem, String...args) {
		String expectedTitle = elem.getSelector();
		String titleName = elem.getName();
		Log.logEntry(Log.verifyPageTitleVisible(expectedTitle));
		if(Arrays.asList(args).contains(wait)) {
			try {
				new WebDriverWait(driver, wait_standardLength)
					  .until(ExpectedConditions.titleIs(expectedTitle));
				Log.logEntry(Log.pageTitleFound());
				return true;
			}catch (TimeoutException ex) {
				finalCatch(titleName, args);
				return false;
			}
		} else if(Arrays.asList(args).contains(shortWait)) {
			try {
				new WebDriverWait(driver, wait_shortLength)
					  .until(ExpectedConditions.titleIs(expectedTitle));
				Log.logEntry(Log.pageTitleFound());
				return true;
			}catch (TimeoutException ex) {
				finalCatch(titleName, args);
				return false;
			}
		} else {
			try {
				String s = driver.getTitle();
				s.equals(expectedTitle);
				Log.logEntry(Log.pageTitleFound());
				return true;
			}catch (TimeoutException ex) {
				finalCatch(titleName, args);
				return false;
			}
		}
	}

}