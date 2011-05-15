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
import com.nawf.util.MessageUtil;

public abstract class NawfException extends Exception {

	private static final long serialVersionUID = 7650622573789602226L;
	private String message = null;
	
	public NawfException() {}
	
	public NawfException(String message){
		super(message);
		this.message = message;
	}

	public NawfException(Throwable throwable){
		super(throwable);
		this.message = throwable.getMessage();
	}

	public NawfException(String message, Throwable throwable){
		super(throwable);
		this.message = message;
	}

	public NawfException(Throwable throwable, String messageTemplate, Object... args){
		this(throwable);
		this.message = MessageUtil.format(messageTemplate, args); 
	}
	
	@Override
	public String getMessage(){
		return message;
	}



}
