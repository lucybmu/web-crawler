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
package com.lucy.util;

import java.net.URI;

import com.google.common.net.InternetDomainName;

/**
 * @author lucym
 *
 */
public class UrlUtil {

	/**
	 * @param url
	 * @return
	 */
	public static String getDomainForUrlStartWithHttp(String url) {
		try {
			if (!(url!=null && url.length()>4 && url.substring(0, 4).equalsIgnoreCase("http")))
				throw new IllegalArgumentException("Expecting the url start with http.");
			return InternetDomainName.from(new URI(url).getHost()).topPrivateDomain().toString();
		} catch (Exception e) {
			System.out.println("There is a problem to get the domain with the url: "+url);
			// ignore it so that the crawler can continue.
			return "";
		}	
	}

}
