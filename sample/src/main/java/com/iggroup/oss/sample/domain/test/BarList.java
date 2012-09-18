/*
 * #%L
 * restdoc-E
 * %%
 * Copyright (C) 2012 IG Group
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.iggroup.oss.sample.domain.test;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * List of Es.
 */
@SuppressWarnings("serial")
@XmlRootElement
public class BarList<E> extends ArrayList<E> {

	/*
	 * Note this class is only really required if you're using JAXB since it does not handle
	 * Lists
	 */

	/**
	 * Default Constructor
	 */
	public BarList() {
	}

	/**
	 * Constructor
	 * 
	 * @param Es list of Es
	 */
	public BarList(List<E> Es) {
		super();
		this.addAll(Es);
	}

	@XmlElement(name = "E")
	public List<E> getEs() {
		return this;
	}

	/**
	 * Setter
	 * 
	 * @param Es list of Es
	 */
	public void setEs(List<E> Es) {
		this.addAll(Es);
	}

}
