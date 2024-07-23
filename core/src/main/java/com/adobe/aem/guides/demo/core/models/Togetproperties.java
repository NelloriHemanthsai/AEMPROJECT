package com.adobe.aem.guides.demo.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

//import org.apache.log4j.spi.LoggerFactory;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Model(adaptables=Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Togetproperties {
	private static final Logger log=LoggerFactory.getLogger(Togetproperties.class);

	@ValueMapValue
	private String firstname;
	
	@ValueMapValue
	private String lastname;
	
	@ValueMapValue(name ="jcr:createdBy")
	private String jcrcreatedby;
	
	@ValueMapValue
	private String surnames;
	
	@ValueMapValue
	private String phonenumber;
	
	@ResourcePath(path="/content/Demo/us/en/jcr:content")
	Resource pageResource;
	
	@ValueMapValue
	@Named("sling:resourceType")
	private String slingresourceType;
	
	@ChildResource
	Resource hobbies;
	
	@PostConstruct
	public void init()
	{
		log.info("to get properties is executed");
	}
	
	public String getFirstname() 
	{
		return firstname;
	}
	
	public String getLastname()
	{
		return lastname;
	}
	
	public String getPagePath()
	{
		return pageResource.getPath();
	}
	
	public String getSurnames()
	{
		return surnames;
	}
	public String getPhonenumber()
	{
		return phonenumber;
	}
	public String getSlingresourceType()
	{
		return slingresourceType;
	}
	public String getJcrcreated()
	{
		return jcrcreatedby;
	}
	public List<Map<String, String>> getMultifield()
	{
		List<Map<String, String>> mainmap = new ArrayList<>();
		try {
		if (hobbies!=null) 
		{
			Map<String, String> childmap = new HashMap<>();
			for(Resource childresourcestore : hobbies.getChildren())
			{
				childmap.put("hobbies1", childresourcestore.getValueMap().get("hobbies1", String.class));
				
				childmap.put("currentaddress", childresourcestore.getValueMap().get("currentaddress", String.class));
			
				childmap.put("correspondingaddress", childresourcestore.getValueMap().get("correspondingaddress", String.class));
				
				childmap.put("pincode", childresourcestore.getValueMap().get("pincode", String.class));
				mainmap.add(childmap);
			}
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return mainmap;
	}
	
	
	
}
