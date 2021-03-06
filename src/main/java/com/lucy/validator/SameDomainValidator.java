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
import com.lucy.util.UrlUtil;

/**
 * @author lucym
 *
 */
public class SameDomainValidator implements Validator {

	private String domain;
	
	public SameDomainValidator(String url) {
		this.domain = UrlUtil.getDomainForUrlStartWithHttp(url);
	}

	@Override
	public Predicate<UrlAndTag> validate() {
		return p -> {
			if(domain.equalsIgnoreCase(UrlUtil.getDomainForUrlStartWithHttp(p.getUrl())))
				return true;
			return false;
		};
	}
}
