package com.adobe.aem.guides.demo.core.models;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

@Model(adaptables = {Resource.class,SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewsCategoryModel extends SlingAllMethodsServlet{
	private static final Logger log=LoggerFactory.getLogger(Finaltogetproperties.class);
	@ValueMapValue
	private String title;
	
	
	@ValueMapValue
	@Default(values ="project:Categories")
	private String categoryTag;
	
	@SlingObject
	ResourceResolver resolver;
	
	Map<String, Long> categoryChildTags;

	
	@PostConstruct
	public void init() {
		log.info("all the properties get successfully");
		
		TagManager tagManager = resolver.adaptTo(TagManager.class);
		Tag categoryTagObj = tagManager.resolve(categoryTag);
		Iterator<Tag> childTags = categoryTagObj.listChildren();
		while(childTags.hasNext())
		{
			Tag tag = childTags.next();
			categoryChildTags.put(tag.getTitle(), tag.getCount());
		}
	}

	public void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException
	{
		
	}
	
	
	
	
	
	public String getTitle() {
		return title;
	}

	public Map<String, Long> getCategoryChildTags() {
		return categoryChildTags;
	}
	

}
