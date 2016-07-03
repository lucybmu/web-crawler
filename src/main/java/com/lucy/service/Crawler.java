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
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.lucy.core.UrlAndTag;

/**
 * @author lucym
 *
 */
public class Crawler {

	private String baseUrl;
	private final int MAX_PAGE_TO_VISIT; 
	
    private final Set<String> urlsVisited = new HashSet<String>();
    private final Set<String> urlsNotVisited = new HashSet<String>();

	private final Set<String> urlsToVisit = new HashSet<String>();
	
	public Crawler(String url){
		this(url, -1); //visit the whole web site.
	}
	
	public Crawler(String url, int maxPageToVisist){
		this.baseUrl = url;
		urlsToVisit.add(url);
		MAX_PAGE_TO_VISIT = maxPageToVisist;
	}
	
	public void visit(Function<String, Set<UrlAndTag>> f,  Predicate<UrlAndTag> p){
		int pageVisited = 0;
		while (urlsToVisit.size()>0){
			String url = urlsToVisit.iterator().next();
			if (urlsVisited.contains(url)){
				urlsToVisit.remove(url);
				continue;
			}
			Set<UrlAndTag> allUrls = f.apply(url);
			urlsVisited.add(url);
			System.out.println("url has been visited: "+url);
			if (++pageVisited==MAX_PAGE_TO_VISIT)
				break;
			for(UrlAndTag aUrl:allUrls){
				if(p.test(aUrl))
					urlsToVisit.add(aUrl.getUrl());
				else
					System.out.println("url will not be visited: "+aUrl.getUrl());
					urlsNotVisited.add(aUrl.getUrl());
			}
		}
	}
	
	public void printSiteMap (Consumer<String> consumer){
		String label1 = "We have visited these urls in the site: "+baseUrl;
		String label2 = "The following static and extenal links have been found in the site: "+baseUrl;
		consumer.accept("\n"+label1);
		urlsVisited.forEach(consumer);
		consumer.accept("\n"+label2);
		urlsNotVisited.forEach(consumer);
	}
}
