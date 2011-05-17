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

public class MinRule extends ValidationRule<Integer> {

	private Integer min = null;
	private Boolean isInclusive = false;

	public MinRule(Integer min, Boolean isInclusive){
		super(ValidationRule.Position.Pre);
		this.min = min;
		this.isInclusive = isInclusive;
	}

	@Override
	public Message validate(Integer value) throws FieldValidationException {
		if(value != null){
			if(value < min){
				return new Message(Message.Level.Error, MessageUtil.format(" must be larger than or equal to {0}.", min));
			}
			if(!this.isInclusive && value == min){
				return new Message(Message.Level.Error, MessageUtil.format(" must be larger than {0}.", min));
			}
		}
		return null;
	}

}
