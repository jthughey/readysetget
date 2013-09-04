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
package com.nawf.jpa;

import com.nawf.EntityFieldInstance;
import com.nawf.FieldInstance;


public class DatabaseFieldInstance<T> implements FieldInstance<T> {
	private EntityFieldInstance<T> entityFieldInstance;
	private String table;
	private String column;
	private Type type;
	
	public enum Type {
		NUMBER, BOOLEAN, VARCHAR, CHAR, DATE, TIMESTAMP
	}

	public DatabaseFieldInstance (String table, String column, Type type){
		this.table = table;
		this.column = column;
		this.type = type;
		this.entityFieldInstance = EntityFieldInstance.empty();
	}

	public DatabaseFieldInstance(String table, String column, Type type, T value){
		this(table, column, type);
		this.entityFieldInstance = new EntityFieldInstance<T>(value);
	}

	public String table() {
		return table;
	}

	public String column() {
		return column;
	}

	public Type type(){
		return type;
	}

	public T get() {
		return entityFieldInstance.get();
	}

	public FieldInstance<T> set(T value) {
		entityFieldInstance.set(value);
		return this;
	}

	public boolean isPresent() {
		return entityFieldInstance.isPresent();
	}

	public static <S> DatabaseFieldInstance<S> empty(String table, String column, Type type) {
		return new DatabaseFieldInstance<S>(table, column, type);
	}

	public static <S> DatabaseFieldInstance<S> build(String table, String column, Type type, S value) {
		return new DatabaseFieldInstance<S>(table, column, type, value);
	}
}
