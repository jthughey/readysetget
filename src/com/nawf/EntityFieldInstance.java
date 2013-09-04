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

public class EntityFieldInstance<T> implements FieldInstance<T> {
	/* Getter and setter are exposed if for some reason you wanted
	 * to call them from outside the field instance and class.
	 * An example would be to pass a list of setters to an event
	 * handler that would iterate over all of them and call the
	 * set method of each to update an unknown object's field to
	 * a new value of the required type.
	 */
	public final Getter<T> getter;
	public final Setter<T> setter;
	private Instance<T> instance;

	public EntityFieldInstance() {
		this(null);
		this.instance = NullInstance.<T>reference();
	}

	public EntityFieldInstance(final T val) {
		this.getter = new Getter<T>() {
			public T get() {
				return instance.value();
			}
		};
		this.setter = new Setter<T>() {
			public void set(final T val) {
				if (val == null) {
					instance = NullInstance.reference();
				} else {
					instance = new Instance<T>() {
						private T value = val;

						public T value() {
							return this.value;
						}

						public boolean isPresent() {
							return true;
						}
					};
				}
			}
		};
		set(val);
	}

	public T get() {
		return getter.get();
	}

	public FieldInstance<T> set(final T value) {
		setter.set(value);
		return this;
	}

	public boolean isPresent() {
		return this.instance.isPresent();
	}

	public static <S> EntityFieldInstance<S> empty() {
		return new EntityFieldInstance<S>();
	}
}
