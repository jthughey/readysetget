package com.nawf.test;
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
import java.util.Calendar;
import java.util.Date;

import com.nawf.client.Message;
import com.nawf.dom.DomField;
import com.nawf.dom.DomObject;
import com.nawf.dom.FieldExistsException;
import com.nawf.dom.format.DateFormatter;
import com.nawf.dom.format.IntegerFormatter;
import com.nawf.dom.format.StringFormatter;
import com.nawf.dom.parse.DateParser;
import com.nawf.dom.parse.IntegerParser;
import com.nawf.dom.parse.StringParser;
import com.nawf.dom.validate.FieldValidationException;
import com.nawf.dom.validate.MaxRule;
import com.nawf.dom.validate.MinRule;
import com.nawf.dom.validate.RequiredRule;
import com.nawf.dom.validate.ValidationRule;
import com.nawf.util.MessageUtil;

public class Client extends DomObject {

	public Client(String firstName, Integer age, Date birthDate) throws FieldExistsException {

		this.addField(new DomField<String>("firstName",
											firstName,
											new StringParser(),
											new StringFormatter())
											.addValidation(new FirstNameRule()));

		this.addField(new DomField<Integer>("age",
											age,
											new IntegerParser(),
											new IntegerFormatter())
											.addValidation(new RequiredRule<Integer>())
											.addValidation(new MinRule(0, true))
											.addValidation(new MaxRule(131, false)));

		this.addField(new DomField<Date>("birthDate", 
										birthDate, 
										new DateParser(DateParser.MMDDYYYY_DATE_FORMAT), 
										new DateFormatter(DateParser.MMDDYYYY_DATE_FORMAT))
										.addValidation(new BirthDateRule()));
	}

	private class FirstNameRule extends ValidationRule<String>{

		public FirstNameRule() {
			super(ValidationRule.Position.Pre, true);
		}

		@Override
		public Message validate(String value)
		throws FieldValidationException {
			if("1".equals(value)){
				return new Message(Message.Level.Error, MessageUtil.format("{0} is not a valid first name.", value));
			}
			return null;
		}
	}

	private class BirthDateRule extends ValidationRule<Date>{

		public BirthDateRule() {
			super(ValidationRule.Position.Pre, true);
		}

		@Override
		public Message validate(Date value) throws FieldValidationException {
			Calendar now = Calendar.getInstance();
			if(now.getTime().before(value)){
				return new Message(Message.Level.Error, " must be on or before today's date.");
			}
			return null;
		}
		
	}
}
