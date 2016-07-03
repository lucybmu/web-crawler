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
package com.lucy.validator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lucy.core.UrlAndTag;

/**
 * @author lucym
 *
 */
public class NonStaticContentValidatorTest {

	private final String URL_TO_TEST = "http://www.bbc.co.uk/weather/";
	private final String TAG_NAME_A = "a";
	private final String TAG_NAME_LINK = "link";
	private final String TAG_NAME_IMG = "img";

	@Test
	public void shouldReturnTrueWhenTagIsA(){
		UrlAndTag urlAndTag = new UrlAndTag(URL_TO_TEST, TAG_NAME_A);
		NonStaticContentValidator nonStaticContentValidator = new NonStaticContentValidator();
		assertTrue(nonStaticContentValidator.validate().test(urlAndTag));
	}
	
	@Test
	public void shoudlReturnFalseWhenTagIsLink(){
		UrlAndTag urlAndTag = new UrlAndTag(URL_TO_TEST, TAG_NAME_LINK);
		NonStaticContentValidator nonStaticContentValidator = new NonStaticContentValidator();
		assertFalse(nonStaticContentValidator.validate().test(urlAndTag));
	}
	
	@Test
	public void shoudlReturnFalseWhenTagIsImg(){
		UrlAndTag urlAndTag = new UrlAndTag(URL_TO_TEST, TAG_NAME_IMG);
		NonStaticContentValidator nonStaticContentValidator = new NonStaticContentValidator();
		assertFalse(nonStaticContentValidator.validate().test(urlAndTag));
	}
}
