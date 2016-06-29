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
	private FeedRepository feedRepository;
	
	@Autowired
	IfeedRepository feedRepo;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	
	@Scheduled(fixedRate = 1000 * 60 * 10)
	public void reportCurrentTime() {
		System.out.println("Synhing with Feeds Repository... \n Start time:  " + dateFormat.format(new Date()));

		Map<Object, Object> feeds = feedRepo.findAllFeeds();

		List<Feed> feedList = new ArrayList<Feed>();

		for (Map.Entry<Object, Object> entry : feeds.entrySet()) {

			feedList.add((Feed) entry.getValue());
		}

		feedRepository.save(feedList);


	}
}
