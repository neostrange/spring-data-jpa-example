package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.model.Feed;


@RepositoryRestResource(collectionResourceRel="feed", path = "feed")
public interface FeedRepository extends CrudRepository<Feed, String> {

}
