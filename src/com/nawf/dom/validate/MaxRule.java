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
import com.nawf.util.MessageUtil;

public class MaxRule extends ValidationRule<Integer> {

	private Integer max = null;
	private Boolean isInclusive = false;

	public MaxRule(Integer max, Boolean isInclusive){
		super(ValidationRule.Position.Pre);
		this.max = max;
		this.isInclusive = isInclusive;
	}

	@Override
	public Message validate(Integer value) throws FieldValidationException {
		if(value != null){
			if(value > max){
				return new Message(Message.Level.Error, MessageUtil.format(" must be less than or equal to {0}.", max));
			}
			if(!this.isInclusive && value == max){
				return new Message(Message.Level.Error, MessageUtil.format(" must be less than {0}.", max));
			}
		}
		return null;
	}

}