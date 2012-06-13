package com.nawf.client;
/*  Copyright 2011 Justin Hughey
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nawf.dom.DomField;

public class PageField<T>{

	private DomField<T> field = null;
	private String id = null;
	private String value = null;
	private Map<String, String> attributes = new HashMap<String,String>();

	public PageField(DomField<T> field, String id){
		this.field = field;
		this.id = id;
	}
	
	public DomField<T> getField(){
		return this.field;
	}

	public String getValue(){
		return this.value;
	}

	public void setValue(String value){
		this.value = value;
	}
	
	public List<Message> validate(){
		return this.validate(Collections.<String> emptySet());
	}
	
	public List<Message> validate(Set<String> overriddenRuleIds){
		return this.field.validate(this.value, overriddenRuleIds);
	}
	
	public String getId(){
		return this.id;
	}
	
	public void addAttribute(String key, String value){
		this.attributes.put(key, value);
	}
}
