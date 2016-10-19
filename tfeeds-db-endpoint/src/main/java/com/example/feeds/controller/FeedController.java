package com.example.feeds.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.ExpandedFeeds;
import com.example.model.Feed;
import com.example.repository.FeedRepository;
import com.example.util.WhitelistLoader;

@BasePathAwareController
@RequestMapping(value = "/feed/search")
public class FeedController implements ResourceProcessor<RepositorySearchesResource> {

	@Autowired
	private FeedRepository repository;

	@Autowired
	private EntityLinks entityLinks;

	@Autowired
	private ProjectionFactory factory;
	
	@Autowired
	private PagedResourcesAssembler<ExpandedFeeds> assembler;

			
	@RequestMapping(value = "allPagedFeeds", method = RequestMethod.GET)
	public ResponseEntity<?> allPagedWhitelisted(@RequestParam(value = "type", defaultValue = "ip") String type,
			@RequestParam(value = "threat", required = false) String threat,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(value = "since", defaultValue = "2000-01-01 00:00:00") LocalDateTime since,
			@RequestParam(value = "cgt", defaultValue = "0") int confidenceGT,
			@RequestParam(value = "clt", defaultValue = "100") int confidenceLT,
			@RequestParam(value = "rgt", defaultValue = "0") int riskGT,
			@RequestParam(value = "rlt", defaultValue = "10") int riskLT,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "100") int size) {
		ResponseEntity<?> response = null;
		if (threat != null && type.equalsIgnoreCase("ip")) {
			if (threat.equalsIgnoreCase("malware")) {
				return new ResponseEntity<Page<?>>(repository
							.findByTypeAndThreatType_MalwareIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist(), new PageRequest(page, size)),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("bruteforce")) {
				return new ResponseEntity<Page<?>>(repository
							.findByTypeAndThreatType_BruteForceIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist(), new PageRequest(page, size)),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("web")) {
					return new ResponseEntity<Page<?>>(repository
							.findByTypeAndThreatType_WebIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist(), new PageRequest(page, size)),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("sip")) {

				return new ResponseEntity<Page<?>>(repository
							.findByTypeAndThreatType_SipIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist(), new PageRequest(page, size)),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("recon")) {
				return new ResponseEntity<Page<?>>(repository
							.findByTypeAndThreatType_ReconIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist(), new PageRequest(page, size)),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("possibleCompromise")) {
				return new ResponseEntity<Page<?>>(repository
							.findByTypeAndThreatType_PossibleCompromiseIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist(), new PageRequest(page, size)),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("db")) {
				return new ResponseEntity<Page<?>>(repository
							.findByTypeAndThreatType_DbIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist(), new PageRequest(page, size)),
							HttpStatus.OK);
			}

		} else {
			return new ResponseEntity<Page<?>>(repository
						.findByTypeAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(type,
								since, confidenceGT, confidenceLT, riskGT, riskLT, WhitelistLoader.getWhitelist(), new PageRequest(page, size)),
						HttpStatus.OK);
		}

		return response;

	}

	
	@RequestMapping(value = "allFeeds", method = RequestMethod.GET)
	public ResponseEntity<?> allWhitelisted(@RequestParam(value = "type", defaultValue = "ip") String type,
			@RequestParam(value = "threat", required = false) String threat,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(value = "since", defaultValue = "2000-01-01 00:00:00") LocalDateTime since,
			@RequestParam(value = "cgt", defaultValue = "0") int confidenceGT,
			@RequestParam(value = "clt", defaultValue = "100") int confidenceLT,
			@RequestParam(value = "rgt", defaultValue = "0") int riskGT,
			@RequestParam(value = "rlt", defaultValue = "10") int riskLT) {
		ResponseEntity<?> response = null;
		if (threat != null && type.equalsIgnoreCase("ip")) {
			if (threat.equalsIgnoreCase("malware")) {
				return new ResponseEntity<List<?>>(repository
							.findListByTypeAndThreatType_MalwareIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("bruteforce")) {
				return new ResponseEntity<List<?>>(repository
							.findListByTypeAndThreatType_BruteForceIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("web")) {
					return new ResponseEntity<List<?>>(repository
							.findListByTypeAndThreatType_WebIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("sip")) {

				return new ResponseEntity<List<?>>(repository
							.findListByTypeAndThreatType_SipIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("recon")) {
				return new ResponseEntity<List<?>>(repository
							.findListByTypeAndThreatType_ReconIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("possibleCompromise")) {
				return new ResponseEntity<List<?>>(repository
							.findListByTypeAndThreatType_PossibleCompromiseIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("db")) {
				return new ResponseEntity<List<?>>(repository
							.findListByTypeAndThreatType_DbIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);
			}

		} else {
			return new ResponseEntity<List<?>>(repository
						.findListByTypeAndThreatType_PossibleCompromiseIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(type,
								since, confidenceGT, confidenceLT, riskGT, riskLT, WhitelistLoader.getWhitelist()),
						HttpStatus.OK);
		}

		return response;

	}

	@RequestMapping(value="/feeds", method = RequestMethod.GET, produces = "application/hal+json")
	public ResponseEntity<?> getPeople(Pageable pageable) {
	    Page<Feed> feeds = repository.findAll(pageable);
	    Page<ExpandedFeeds> projected = feeds.map(l -> factory.createProjection(ExpandedFeeds.class, l));
	    PagedResources<Resource<ExpandedFeeds>> resources = assembler.toResource(projected);
	    return ResponseEntity.ok(resources);
	}

	@RequestMapping("/all")
	public ResponseEntity<?> all(@RequestParam(value = "type", defaultValue = "ip") String type,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(defaultValue = "timestamp,desc") String sort) {
		Page<Feed> feeds = repository.findByTypeAndIndicatorNotIn(type, WhitelistLoader.getWhitelist(),
				new PageRequest(page, size));
	    Page<ExpandedFeeds> projected = feeds.map(l -> factory.createProjection(ExpandedFeeds.class, l));
	    PagedResources<Resource<ExpandedFeeds>> resources = assembler.toResource(projected);
	    return ResponseEntity.ok(resources);

	}

	@RequestMapping("allNotNull")
	public ResponseEntity<?> allNotNull(@RequestParam(value = "type", defaultValue = "ip") String type,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "0") int size,
			@RequestParam(defaultValue = "timestamp,desc") String sort) {
		Page<Feed> feeds = repository.findByTypeAndTimestampNotNullAndExpiryNotNullAndIndicatorNotIn(type,
						WhitelistLoader.getWhitelist(), new PageRequest(page, size));
		Page<ExpandedFeeds> projected = feeds.map(l -> factory.createProjection(ExpandedFeeds.class, l));
	    PagedResources<Resource<ExpandedFeeds>> resources = assembler.toResource(projected);
	    return ResponseEntity.ok(resources);

	}

	@RequestMapping("total")
	public ResponseEntity<?> total(@RequestParam(value = "type", defaultValue = "ip") String type) {
		return new ResponseEntity<Long>(repository.countByTypeAndIndicatorNotIn(type, WhitelistLoader.getWhitelist()),
				HttpStatus.OK);
	}

	@RequestMapping("totalNotNull")
	public ResponseEntity<?> totalNotNull(@RequestParam(value = "type", defaultValue = "ip") String type) {
		return new ResponseEntity<Long>(repository.countByTypeAndTimestampNotNullAndExpiryNotNullAndIndicatorNotIn(type,
				WhitelistLoader.getWhitelist()), HttpStatus.OK);
	}

	@Override
	public RepositorySearchesResource process(RepositorySearchesResource resource) {

		LinkBuilder lb = entityLinks.linkFor(Feed.class);
		resource.add(
				new Link(lb.toString() + "/search/allFeeds{?type, threat, since, cgt, clt, rgt, rlt}", "allFeeds"));
		resource.add(
				new Link(lb.toString() + "/search/allPagedFeeds{?type, threat, since, cgt, clt, rgt, rlt, page, size}", "allFeeds"));
		resource.add(new Link(lb.toString() + "/search/all{?type, page, size}", "all"));
		resource.add(new Link(lb.toString() + "/search/allNotNull{?type, page, size}", "allNotNull"));
		resource.add(new Link(lb.toString() + "/search/total{?type}", "total"));
		resource.add(new Link(lb.toString() + "/search/totalNotNull{?type}", "totalNotNull"));

		return resource;
	}

}
