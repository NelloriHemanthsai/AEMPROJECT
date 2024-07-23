package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

public class Sociallinks {
	
	@ValueMapValue
	public String icon;
	
	@ValueMapValue
	public String navigationpath;

	public String getIcon() {
		return icon;
	}

	public String getNavigationpath() {
		return navigationpath;
	}

}
