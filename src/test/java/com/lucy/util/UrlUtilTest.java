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

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * @author lucym
 *
 */
public class UrlUtilTest {

	private final String URL_TO_TEST = "http://www.bbc.co.uk/weather/";
	private final String SUBDOMAIN_URL_TO_TEST = "https://tuclothing.sainsburys.co.uk/?INITD=GNav-SHP-TuHP";
	private final String URL_TO_TEST_EXCEPTION = "www.bbc.co.uk/weather/";

	@Test
	public void shouldGetDomainForUrlStartWithHttp(){
		String result = UrlUtil.getDomainForUrlStartWithHttp(URL_TO_TEST);
		assertEquals("bbc.co.uk", result);
	}

	@Test
	public void shouldGetDomainForUrlStartWithHttpSubdomain(){
		String result = UrlUtil.getDomainForUrlStartWithHttp(SUBDOMAIN_URL_TO_TEST);
		assertEquals("sainsburys.co.uk", result);
	}


	@Test
	public void shouldNotFormattedUrlReturnEmptyString(){
		String result = UrlUtil.getDomainForUrlStartWithHttp(URL_TO_TEST_EXCEPTION);
		assertEquals("", result);
	}
	
}
