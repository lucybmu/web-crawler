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

import java.util.function.Predicate;

import com.lucy.core.UrlAndTag;

/**
 * @author lucym
 *
 */
public class NonStaticContentValidator implements Validator {

	@Override
	public Predicate<UrlAndTag> validate() {
		//We only treat tag <a> as a non-static link that can be clicked to load another page. 
		return p -> p.getTagName().equalsIgnoreCase("a") ? true : false;
	}

}
