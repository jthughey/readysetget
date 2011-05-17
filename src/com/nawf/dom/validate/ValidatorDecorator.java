package com.nawf.dom.validate;
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
import java.util.List;

import com.nawf.client.Message;


//TODO: Add concept of "overrides" which allow a user to override a select validation rule.

public class ValidatorDecorator<T> implements Validator<T>{

	protected Validator<T> validator = new DefaultValidator<T>();
	protected ValidationRule<T> validationRule = new DefaultValidationRule<T>();

	public ValidatorDecorator(){}
	
	public ValidatorDecorator(Validator<T> validatorDecorator){
		this.validator = validatorDecorator;
	}

	public final void validate(List<Message> messages, T value, Boolean overriden) throws FieldValidationException{

		if(this.validationRule.isPre()){
			doValidation(messages, value, overriden);
		}

		this.validator.validate(messages, value, overriden);

		if(this.validationRule.isPost()){
			doValidation(messages, value, overriden);
		}

	}
	
	private void doValidation(List<Message> messages, T value, Boolean overriden) throws FieldValidationException{
		Message message = null;
		if(this.validationRule.isOverrideable()){
			if(!overriden){
				message = this.validationRule.validate(value);
			}
		}else{
			message = this.validationRule.validate(value);

		}
		if(message != null){
			messages.add(message);
		}
	}

	public final void setValidationRule(ValidationRule<T> validationRule){
		this.validationRule = validationRule;
	}

}
