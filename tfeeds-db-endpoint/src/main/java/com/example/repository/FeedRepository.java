package com.example.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.model.Feed;

@RepositoryRestResource(collectionResourceRel = "feed", path = "feed")
public interface FeedRepository extends JpaRepository<Feed, String> {

	List<Feed> findByType(@Param("type") String type);

	List<Feed> findByTypeAndFirstSeen(@Param("type") String type, @Param("firstSeen") String firstSeen);

	List<Feed> findByFirstSeenBefore(@Param("firstSeen") 
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime firstSeen);
	
	List<Feed> findByFirstSeenBetween(@Param("begin")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime begin,
	@Param("end")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime end );

}
