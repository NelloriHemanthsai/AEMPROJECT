package com.adobe.aem.guides.demo.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables =  Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class Sidebarproperties {
	@ValueMapValue
	public String logomobileimage;
	
	@ValueMapValue
	public String country;


	//	
	@ValueMapValue
	public String logolink;

	@ValueMapValue
	public String  logopath;
	
	@ValueMapValue
	public String enableswitch;
	
	
	@ChildResource
	public List<Details> details;
	

	@ChildResource
	public List<Multifieldtab3> mani;


	
	public List<Multifieldtab3> getMani() {
		return mani;
	}

	public List<Details> getDetails() {
		return details;
	}

	public String getLogomobileimage() {
		return logomobileimage;
	}

	public String getCountry() {
		return country;
	}

	public String getLogolink() {
		return logolink;
	}

	public String getLogopath() {
		return logopath;
	}

	public String getEnableswitch() {
		return enableswitch;
	}
	
}
