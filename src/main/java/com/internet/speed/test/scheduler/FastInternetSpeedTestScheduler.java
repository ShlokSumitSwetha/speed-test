/*
 * Copyright 2018 the original author or authors.
 *
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
 */
package com.internet.speed.test.scheduler;

import com.internet.speed.test.dao.InternetSpeedDao;
import com.internet.speed.test.domain.InternetSpeedDomain;
import com.internet.speed.test.service.InternetSpeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduler
 * @author Sumith Ksheerasagar
 */
@Component
public class FastInternetSpeedTestScheduler {

	@Autowired
	private InternetSpeedService internetSpeedService;

	@Autowired
	private InternetSpeedDao internetSpeedDao;

	@Scheduled(fixedRate = 180)
	public void runTest(){
		InternetSpeedDomain internetSpeedDomain = internetSpeedService.evaluateInternetSpeed();
		internetSpeedDao.addSpeed(internetSpeedDomain.getId(), internetSpeedDomain.getTimestamp(),internetSpeedDomain.getSpeed(), internetSpeedDomain.getUnit() );
	}
}
