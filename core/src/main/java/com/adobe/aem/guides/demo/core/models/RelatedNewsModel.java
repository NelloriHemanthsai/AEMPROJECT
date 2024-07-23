package com.adobe.aem.guides.demo.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class RelatedNewsModel {

	@ScriptVariable
	ValueMap pageProperties;
	
	@SlingObject
	ResourceResolver resolver;
	
	
	List<ArticleDetailModel> articleList;
	
	
	@PostConstruct
	public void init() {
		String[] tags = pageProperties.get("cq:tags", String[].class);
		String categoryTag = getCategoryTag(tags);
		if (categoryTag != null) {
			articleList = new ArrayList<>();
			TagManager tagManager = resolver.adaptTo(TagManager.class);
			Tag categoryTagObj = tagManager.resolve(categoryTag);
			Iterator<Resource> reference = categoryTagObj.find();
			while(reference.hasNext()) {
				Resource resource = reference.next();
				Resource articleResource = resolver.getResource(resource.getPath()+"/root/container/article_grid/left-container/article_details");
				if (articleResource != null) {
					ArticleDetailModel articleModel = articleResource.adaptTo(ArticleDetailModel.class);
					if (articleModel != null) 
						articleList.add(articleModel);
					
				}
			}
		}
		
	}
	
	public String getCategoryTag(String[] tags)
	{
		for(String tag: tags)
		{
			if(tag.startsWith("project:Categories"))
			{
				return tag;
			}
		}
		return null;
	}

	public List<ArticleDetailModel> getArticleList() {
		return articleList;
	}
	
}
