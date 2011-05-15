package com.nawf.client;
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
public class Message implements Jsonable{

	private String id = null;
	private String message = null;
	private Level level = null;

	public enum Level {Info, Warning, Error, System}

	public Message(Level level, String message){
		this.level = level;
		this.message = message;
	}
	
	public Message(Level level, String message, String id){
		this(level, message);
		this.id = id;
	}

	public String toJson(){
		StringBuilder sb = new StringBuilder();
		sb.append("{")
		  .append("\"id\"")
		  .append(":")
		  .append("\"")
		  .append(this.id)
		  .append("\",")
		  .append("\"level\"")
		  .append(":")
		  .append("\"")
		  .append(this.level)
		  .append("\",")
		  .append("\"message\"")
		  .append(":")
		  .append("\"")
		  .append(this.message)
		  .append("\"")
		  .append("}");
		return sb.toString();
	}
	
	public String getId(){
		return this.id;
	}
}
