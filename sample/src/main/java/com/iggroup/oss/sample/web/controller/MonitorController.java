/*
 * #%L restdoc-sample %% Copyright (C) 2012 IG Group %% Licensed under the
 * Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License
 * at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable
 * law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License. #L%
 */
package com.iggroup.oss.sample.web.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;

/**
 * Monitor Controller for accessing Jamon monitor results.
 */
@Component
@Controller("monitorController")
public class MonitorController extends BaseController {

	private static final Logger LOGGER = Logger
			.getLogger(MonitorController.class);


	/**
	 * Return all monitor results
	 * 
	 * @return all monitor results
	 */
	@RequestMapping(value = "/monitor", method = { RequestMethod.GET })
	@ResponseBody
	public String findAll() {

		LOGGER.info("findAll ");

		return MonitorFactory.getReport();

	}

	/**
	 * Return the monitor results for the given key
	 * 
	 * @param monitor key
	 */
	@RequestMapping(value = "/monitor/{key}", method = { RequestMethod.GET })
	@ResponseBody
	public String findByKey(@PathVariable String key) {

		LOGGER.info("findByKey " + key);

		return MonitorFactory.getMonitor(key, "ms.").toString();

	}

	/**
	 * Return the monitor results for the given key
	 * 
	 * @param monitor key
	 */
	@RequestMapping(value = "/monitor/keys", method = { RequestMethod.GET })
	@ResponseBody
	public ArrayList<String> findKeys() {

		LOGGER.info("findKeys ");

		ArrayList<String> keys = new ArrayList<String>();

		Iterator iter = MonitorFactory.getFactory().iterator();

		while (iter.hasNext()) {
			Monitor monitor = (Monitor) iter.next();
			keys.add(monitor.getLabel());
		}

		return keys;
	}

}
