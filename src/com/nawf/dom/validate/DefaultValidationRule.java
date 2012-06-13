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
import com.nawf.client.Message;


public class DefaultValidationRule<T> extends ValidationRule<T> {

	public DefaultValidationRule(ValidationRule.Position position) {
		super(position);
	}
	
	public DefaultValidationRule(){
		super(ValidationRule.Position.Before);
	}

	@Override
	public Message validate(T value) throws FieldValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}
