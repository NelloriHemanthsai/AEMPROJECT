package com.adobe.aem.guides.demo.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

@Model(adaptables = {Resource.class,SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TagsModel {
	
	@ValueMapValue
	private String title;
	
	@ScriptVariable
	ValueMap pageProperties;
	
	@SlingObject
	ResourceResolver resolver;
	
	List<String> tagsList;
	
	@PostConstruct
	public void init() {
		String[] tags = pageProperties.get("cq:tags",String[].class);
		if (tags != null && tags.length>0) {
			 tagsList = new ArrayList<>();
			TagManager tagManager = resolver.adaptTo(TagManager.class);
			for(String tag: tags)
			{
				Tag tagObj = tagManager.resolve(tag);
				tagsList.add(tagObj.getTitle());
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public List<String> getTagsList() {
		return tagsList;
	}
	
	

}
