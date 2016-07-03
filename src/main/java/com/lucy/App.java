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
package com.lucy;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.lucy.service.Crawler;
import com.lucy.service.JsoupParser;
import com.lucy.service.UrlParser;
import com.lucy.validator.NonStaticContentValidator;
import com.lucy.validator.SameDomainValidator;
import com.lucy.validator.Validator;

/**
 * @author lucym
 *
 */
public class App {

	public static void main(String [] args) {
		String url = args.length>0 && args[0]!=null ? args[0] : "http://www.hipp.co.uk";
		Crawler crawler;
		if(args.length>1&&args[1]!=null)
			crawler = new Crawler(url, Integer.valueOf(args[1]));
		else
			crawler = new Crawler(url);
		
		UrlParser urlParser = new JsoupParser();
		Validator validator1 = new NonStaticContentValidator();
		Validator validator2 = new SameDomainValidator(url);
		
		crawler.visit(urlParser::getAllUrlsOnThePage, validator1.validate().and(validator2.validate()));
		crawler.printSiteMap(s->{
			try{
				FileUtils.writeStringToFile(new File("site_map.txt"), s+"\n", "UTF-8", true);
			}catch(IOException e){
				System.out.println("Can write to file with the string: "+s);
			}			
		});
	}
}
