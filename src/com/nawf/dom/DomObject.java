package com.nawf.dom;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nawf.client.Message;
import com.nawf.dom.parse.FieldParseException;

public class DomObject {
	private final Map<String, DomField<?>> fieldByName = new HashMap<String, DomField<?>>();

	public DomObject(){}

	public DomObject(List<DomField<?>> fields) throws FieldExistsException{
		for(DomField<?> field : fields){
			addField(field);
		}
	}

	public final void addField(DomField<?> field) throws FieldExistsException{
		if(!fieldByName.containsKey(field.getName())){
			this.fieldByName.put(field.getName(), field);
		}else{
			throw new FieldExistsException("A field with name '"+field.getName()+"' already exists.");
		}
	}

	public final DomField<?> getField(String name) throws FieldNotFoundException{
		if(fieldByName.containsKey(name)){
			return fieldByName.get(name);
		}
		throw new FieldNotFoundException("Field with requested name '"+name+"' does not exist.");
	}

	public final Map<String, DomField<?>> fieldMap(){
		return Collections.unmodifiableMap(fieldByName);
	}

	public final List<String> validateState(){
		return new ArrayList<String>();
	}
	
	public final List<Message> setField(DomField<?> field, String value) throws FieldNotFoundException{
		return setField(field, value, false);
	}

	public final List<Message> setField(DomField<?> field, String value, Boolean overrideValidation) throws FieldNotFoundException{
		if(this.fieldByName.containsValue(field)){
			List<Message> messages = new ArrayList<Message>();
			try{
				messages.addAll(field.validate(value, overrideValidation));
				if(messages.isEmpty()){
					field.setValue(value);
				}
			}catch(FieldParseException fpe){
				messages.add(new Message(Message.Level.Error, fpe.getMessage()));
			}
			return messages;
		}else{
			throw new FieldNotFoundException("Field with '"+field.getName()+"' is not part of this DomObject.");
		}
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(DomField<?> field : this.fieldMap().values()){
			sb.append(field.getName()).append(":").append(field.getValue()).append(",");
		}
		return sb.toString();
	}
}
