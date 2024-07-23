package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

public class QuickLinks {
	
	@ValueMapValue
	public String navigationlink;
	
	
	@ValueMapValue
	public String text;


	public String getNavigationlink() {
		return navigationlink;
	}


	public String getText() {
		return text;
	}
	
	

}
