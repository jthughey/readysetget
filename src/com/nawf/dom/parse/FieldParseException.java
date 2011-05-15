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
import com.nawf.dom.NawfException;

public class FieldParseException extends NawfException {

	private static final long serialVersionUID = -6816041944465219754L;
	
	public FieldParseException(Throwable throwable){
		super(throwable);
	}

	public FieldParseException(String message){
		super(message);
	}

	public FieldParseException(Throwable throwable, String messageTemplate, Object... args){
		super(throwable, messageTemplate, args);
	}

	public FieldParseException(String messageTemplate, Object... args){
		super();
	}
}
