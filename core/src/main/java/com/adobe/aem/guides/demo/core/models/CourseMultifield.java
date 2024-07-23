package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


@Model(adaptables = {Resource.class,SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CourseMultifield {
	@ValueMapValue
    public String title;
	
	
   
	@ValueMapValue
    public String image;

    @ValueMapValue
    public String alttext;
    

	@ValueMapValue
    public String ni;


	@ValueMapValue
    public String textarea;

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getAltText() {
        return alttext;
    }

    public String getTextarea() {
        return textarea;
    }
    

    public String getNi() {
		return ni;
	}
    
  

}





