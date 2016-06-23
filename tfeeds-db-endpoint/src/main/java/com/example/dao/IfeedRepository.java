package com.example.dao;

import java.util.Map;

import com.example.model.Feed;

public interface IfeedRepository {

	Feed findFeed(String id);
	void updateFeed(Feed feed);
	void saveFeed(Feed feed);
	Map<Object,Object> findAllFeeds();

}