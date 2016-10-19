package com.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	@Autowired
	DataLoader dataLoader;
	
	@Autowired
	WhitelistLoader whiteLoader;
	


	private Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	/**
	 * Sync feeds
	 */
	@Scheduled(fixedRate = 1000 * 60 * 40)
	public void reportCurrentTime() {
		log.info("Syncing with Feeds Repository...");
		dataLoader.loadFeedsData();
	}
	
	
}
