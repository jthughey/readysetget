/*  Copyright 2013 Justin Hughey
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
package com.nawf;

import java.util.Date;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Test {

	public static void setupLog4J() {
		ConsoleAppender console = new ConsoleAppender(); // create appender
		// configure the appender
		String PATTERN = "%d [%p|%c|%C{1}] %m%n";
		console.setLayout(new PatternLayout(PATTERN));
		console.setThreshold(Level.TRACE);
		console.activateOptions();
		// add appender to any Logger (here is root)
		Logger.getRootLogger().addAppender(console);
	}

	public static void main(String[] args) {
		setupLog4J();
		Logger LOG = Logger.getLogger(Test.class);
		Client client = new Client("Justin", 29, new Date());
		client.age.get();
		LOG.info(client.age.get().toString());
		client.age.set(222);
		LOG.info(client.age.get().toString());
		client.age.set(null);
		LOG.info(client.age.column());
		
		Client unknown = new Client(33, new Date());
		LOG.info("Age: " + unknown.age.get() + ", Name: " + unknown.firstName.get());
		if (unknown.age.set(199).isPresent()) {
			LOG.info("Our set is a valid non null value.");
		}
		if (!unknown.age.set(null).isPresent()) {
			LOG.info("Our set is null!");
		}
	}

}
