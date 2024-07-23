package com.adobe.aem.guides.demo.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewComponent {
	
	private static final Logger log=LoggerFactory.getLogger(Finaltogetproperties.class);
	
	@Inject
	private String path;
	
	@ValueMapValue
	private String lastname;
	
	
	@ValueMapValue
	private String checkbox;
	
	@ChildResource
	public List<aboutMySelf> aboutMySelf;


	
	@PostConstruct
	public void init()
	{
		log.info("all the properties get successfully");
	}
	
	
	public String getFirstname()
	{
		return path;
	}
	
	public String getLastname()
	{
		return lastname;
	}
	
	public String getCheckbox()
	{
		return checkbox;
	}
	
	public List<aboutMySelf> getAboutMySelf() {
		return aboutMySelf;
	}

	
	

	
//	public List<Map<String, String>> getMultifield()
//	{
//		List<Map<String, String>> mainmap = new ArrayList<>();
//		try {
//		if (aboutMySelf!=null) 
//		{
//			Map<String, String> childmap = new HashMap<>();
//			for(Resource childresourcestore : aboutMySelf.getChildren())
//			{
//				childmap.put("hobbies", childresourcestore.getValueMap().get("hobbies", String.class));
//				
//				childmap.put("address", childresourcestore.getValueMap().get("address", String.class));
//			
//				
//				mainmap.add(childmap);
//			}
//		}
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		return mainmap;
//	}

}
