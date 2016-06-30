package com.example.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.dao.IfeedRepository;
import com.example.model.Feed;
import com.example.repository.FeedRepository;

@Component
public class ScheduledTasks {

	@Autowired
	DataLoader dataLoader;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 1000 * 60 * 40)
	public void reportCurrentTime() {
		System.out.println("Syncing with Feeds Repository... \n"
				+ " Start time:  " + dateFormat.format(new Date()));

		dataLoader.loadFeedsData();

	}
}
