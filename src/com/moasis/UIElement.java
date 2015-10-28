package com.moasis;

public class UIElement {
	private String name;
	private String selector;
	
	public UIElement (String name, String selector) {
		this.name = name;
		this.selector = selector;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSelector() {
		return selector;
	}
}
