package com.nawf.dom.validate;

import com.nawf.client.Message;

public class ValidationMessage extends Message {

	private String ruleId;

	public ValidationMessage(Level level, String message, String ruleId) {
		super(level, message);
		this.ruleId = ruleId;
	}

	public ValidationMessage(Message message, String ruleId){
		this(message.getLevel(), message.getMessage(), ruleId);
		
	}

	public String getRuleId(){
		return ruleId;
	}

	public String toJson(){
		return "{\"rule\":\""+ruleId+"\",\"level\":\""+this.getLevel()+"\",\"message\":\""+this.getMessage()+"\"}";
	}
	
	public String toString(){
		return this.toJson();
	}

}
