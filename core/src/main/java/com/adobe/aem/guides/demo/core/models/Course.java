package com.adobe.aem.guides.demo.core.models;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;



@Model(adaptables = {Resource.class,SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Course {
	 @ValueMapValue
	    public String maintitle;
	 @ChildResource
	    public List<CourseMultifield> multifield;
	 
	  public String getMaintitle() {
			return maintitle;
		}

		public List<CourseMultifield> getMultifield() {
			return multifield;
		}

}
