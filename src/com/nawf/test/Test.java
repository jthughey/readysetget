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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nawf.client.Message;
import com.nawf.dom.DomField;
import com.nawf.dom.FieldExistsException;
import com.nawf.dom.FieldNotFoundException;
import com.nawf.dom.format.IntegerFormatter;
import com.nawf.dom.parse.IntegerParser;

public class Test {
	public static void main(String[] args) {

		List<Message> messages = new ArrayList<Message>();
		Client client = null;
		try{
			client = new Client("Justin", 29, new Date(System.currentTimeMillis()));
			System.out.println(client);
			messages.addAll(client.setField(client.getField("birthDate"), "12/07/1981"));
			messages.addAll(client.setField(client.getField("birthDate"), "1a/07/1981"));
			messages.addAll(client.setField(client.getField("birthDate"), "12/07/2012"));
			messages.addAll(client.setField(client.getField("birthDate"), "04/10/2011"));
			System.out.println(client);
			messages.addAll(client.setField(client.getField("firstName"), "Jeremy"));
			System.out.println(client);
			messages.addAll(client.setField(client.getField("firstName"), "1"));
			System.out.println(client);
			messages.addAll(client.setField(client.getField("age"), "500"));
			System.out.println(client);
			messages.addAll(client.setField(client.getField("age"), null));
			System.out.println(client);
			messages.addAll(client.setField(client.getField("age"), "-1"));
			System.out.println(client);
//need to figure out how to uniquely identify a validation rule on a field and override it
			//messages.addAll(client.setField(client.getField("age"), "-1", true));
			System.out.println("Message tests completed.");
		} catch (FieldExistsException e) {
			System.err.println(e);
			return;
		} catch (FieldNotFoundException e) {
			messages.add(new Message(Message.Level.System, e.getMessage()));
			if(e.getCause() != null){
				messages.add(new Message(Message.Level.System, e.getMessage()));
			}
		} catch (Exception e){
			System.err.println("Unknown error: "+String.valueOf(e));
		}

		try {
			client.getField("lastName");
		} catch (FieldNotFoundException e) {
			messages.add(new Message(Message.Level.System, e.getMessage()));
			if(e.getCause() != null){
				messages.add(new Message(Message.Level.System, e.getMessage()));
			}
		}

		try {
			client.addField(new DomField<Integer>("age",
					10,
					new IntegerParser(),
					new IntegerFormatter()));
		} catch (FieldExistsException e) {
			messages.add(new Message(Message.Level.System, e.getMessage()));
		}

		System.out.println("ERRORS");
		for(Message message : messages){
			System.out.println(message);
		}
	}

}
