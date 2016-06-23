package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.model.Feed;


@RepositoryRestResource(collectionResourceRel="feed", path = "feed")
public interface FeedRepository extends JpaRepository<Feed, String> {

	List<Feed> findByType(@Param("type") String type);
	
	List<Feed> findByTypeAndFirstSeen(@Param("type") String type, @Param("firstSeen") String firstSeen);

}
