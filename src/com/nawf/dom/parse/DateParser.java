package com.nawf.dom.parse;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateParser implements Parser<Date>{
	public static final String MMDDYYYY_DATE_FORMAT = "MM/dd/yyyy";
	public static final String DDMMYYYY_DATE_FORMAT = "dd/MM/yyyy";
	private SimpleDateFormat sdf = new SimpleDateFormat(MMDDYYYY_DATE_FORMAT);

	public DateParser(String dateFormat){
		this.sdf = new SimpleDateFormat(dateFormat);
	}
	
	public Date parse(String value) throws FieldParseException {
		try {
			return sdf.parse(value);
		} catch (ParseException pe) {
			throw new FieldParseException(pe, "\"{0}\" is not a valid date, application date format is {1}.", value, MMDDYYYY_DATE_FORMAT);
		}
	}	
}

