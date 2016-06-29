package com.example.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dao.IfeedRepository;
import com.example.model.Feed;
import com.example.repository.FeedRepository;

/*
 * Loads data from Redis Instance into embedded H2 database.
 */

@Component
public class DataLoader {

	@Autowired
	private FeedRepository feedRepository;

	@Autowired
	IfeedRepository feedRepo;

	@PostConstruct
	private void InitializeFeedsData() {
		loadFeedsData();
	}

	public void loadFeedsData() {
		Map<Object, Object> feeds = feedRepo.findAllFeeds();

		List<Feed> feedList = new ArrayList<Feed>();

		for (Map.Entry<Object, Object> entry : feeds.entrySet()) {

			feedList.add((Feed) entry.getValue());
		}

		feedRepository.save(feedList);
	}

}
