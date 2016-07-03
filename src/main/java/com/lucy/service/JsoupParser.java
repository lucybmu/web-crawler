/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lucy.service;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lucy.core.UrlAndTag;

/**
 * @author lucym
 *
 */
public class JsoupParser implements UrlParser{

	private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";

	@Override
	public Set<UrlAndTag> getAllUrlsOnThePage(String pageUrl) {
		try{
			Document doc = Jsoup.connect(pageUrl).userAgent(USER_AGENT).get();
	        Elements hrefs = doc.select("a[href]");
	        Elements media = doc.select("[src]");
	        Elements links = doc.select("link[href]");
	        Set<UrlAndTag> urlAndTags = new HashSet<UrlAndTag>();
	        for(Element e:hrefs)
	        	urlAndTags.add(new UrlAndTag(e.attr("abs:href"),e.tagName()));
	        
	        for(Element e:media)
	        	urlAndTags.add(new UrlAndTag(e.attr("abs:src"),e.tagName()));
	        
	        for(Element e:links)
	        	urlAndTags.add(new UrlAndTag(e.attr("abs:href"),e.tagName()));
	        
	        //change local url to the full url
	        urlAndTags.forEach(u -> {
	        				if (u.getUrl().length()>0){
	        					if (!u.getUrl().startsWith("http")){
		        					String newUrl = pageUrl+u.getUrl();
		        					u.setUrl(newUrl);
		        				}
	        				}	        					        					
	        			});
			return urlAndTags;
		}catch(Exception e){
			System.out.println("There is a problem loading the url: "+pageUrl);
			//return the empty set so that the whole process can continue if one link fails.
			return new HashSet<UrlAndTag>(); 
		}
	}

}
