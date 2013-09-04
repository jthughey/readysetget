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

import com.nawf.jpa.DatabaseFieldInstance;
import com.nawf.jpa.DatabaseFieldInstance.Type;

public class Client {

	public final DatabaseFieldInstance<String> firstName = DatabaseFieldInstance
			.build("CLIENT", "FIRST_NAME", Type.VARCHAR, "Unknown");
	public final DatabaseFieldInstance<Integer> age = DatabaseFieldInstance
			.empty("CLIENT", "AGE", Type.NUMBER);
	public final DatabaseFieldInstance<Date> birthDate = DatabaseFieldInstance
			.empty("CLIENT", "BIRTH_DATE", Type.DATE);

	public Client(String firstName, Integer age, Date birthDate) {
		this.firstName.set(firstName);
		this.age.set(age);
		this.birthDate.set(birthDate);
	}

	public Client(Integer age, Date birthDate) {
		this.age.set(age);
		this.birthDate.set(birthDate);
	}
}
