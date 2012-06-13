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
import java.util.List;
import java.util.Set;

import com.nawf.client.Message;
import com.nawf.dom.format.Formatter;
import com.nawf.dom.parse.FieldParseException;
import com.nawf.dom.parse.Parser;
import com.nawf.dom.validate.FieldValidationException;
import com.nawf.dom.validate.ValidationRule;
import com.nawf.dom.validate.Validator;
import com.nawf.dom.validate.ValidatorDecorator;

public final class DomField<T> implements Field {

	private T value = null;
	private String name = null;
	private Parser<T> parser = null;
	private Validator<T> validator = new ValidatorDecorator<T>();
	private Formatter<T> formatter = null;

	public DomField(String name, T value, Parser<T> parser, Formatter<T> formatter){
		if(name == null){
			throw new NullPointerException("Field must have a non-null value for name.");
		}
		this.name = name;
		this.value = value;
		if(parser == null){
			throw new NullPointerException("DomField object must have a valid parser.");
		}
		this.parser = parser;
		if(formatter == null){
			throw new NullPointerException("DomField object must have a valid formatter.");
		}
		this.formatter = formatter;
	}

	public final String getName(){
		return this.name;
	}

	protected final void setValue(String value) throws FieldParseException{
		this.value = this.parse(value);
	}

	public final String getValue(){
		return this.formatter.format(this.value);
	}
	
	public T parse(String value) throws FieldParseException{
		if(value == null || value.trim().isEmpty()){
			return null;
		}
		return this.parser.parse(value);
	}

	public final List<Message> validate(String value, Set<String> overridenRuleIds){
		List<Message> messages = new ArrayList<Message>();
		try{
			T tValue = this.parse(value);
			this.validator.validate(messages, tValue, overridenRuleIds);
		}catch(FieldParseException fpe){
			messages.add(new Message(Message.Level.Error, fpe.getMessage()));
		} catch (FieldValidationException fve) {
			messages.add(new Message(Message.Level.Error, fve.getMessage()));
		}
		return messages;
	}

	public final DomField<T> addValidation(ValidationRule<T> validationRule){
		ValidatorDecorator<T> validatorDecorator = new ValidatorDecorator<T>(this.validator);
		validatorDecorator.setValidationRule(validationRule);
		this.validator = validatorDecorator;
		return this;
	}
}