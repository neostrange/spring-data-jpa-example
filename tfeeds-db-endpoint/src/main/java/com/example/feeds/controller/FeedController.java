package com.example.feeds.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping(value = "allFeeds", method = RequestMethod.GET)
	public ResponseEntity<List<?>> allWhitelisted(@RequestParam(value = "type", defaultValue = "ip") String type,
			@RequestParam(value = "threat", required = false) String threat,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(value = "since", defaultValue = "2000-01-01 00:00:00") LocalDateTime since,
			@RequestParam(value = "cgt", defaultValue = "0") int confidenceGT,
			@RequestParam(value = "clt", defaultValue = "100") int confidenceLT,
			@RequestParam(value = "rgt", defaultValue = "0") int riskGT,
			@RequestParam(value = "rlt", defaultValue = "10") int riskLT,
			@RequestParam(value = "projection", defaultValue = "false") boolean projection) {
		ResponseEntity<List<?>> response = null;
		if (threat != null && type.equalsIgnoreCase("ip")) {
			if (threat.equalsIgnoreCase("malware")) {
				if (projection)
					response = new ResponseEntity<List<?>>(repository
							.findProjectedByTypeAndThreatType_MalwareIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);
				else
					response = new ResponseEntity<List<?>>(repository
							.findByTypeAndThreatType_MalwareIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("bruteforce")) {
				if (projection)
					response = new ResponseEntity<List<?>>(repository
							.findProjectedByTypeAndThreatType_BruteForceIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);
				else
					response = new ResponseEntity<List<?>>(repository
							.findByTypeAndThreatType_BruteForceIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("web")) {
				if (projection)
					response = new ResponseEntity<List<?>>(repository
							.findProjectedByTypeAndThreatType_WebIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);
				else
					response = new ResponseEntity<List<?>>(repository
							.findByTypeAndThreatType_WebIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("sip")) {

				if (projection)
					response = new ResponseEntity<List<?>>(repository
							.findProjectedByTypeAndThreatType_SipIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);

				else
					response = new ResponseEntity<List<?>>(repository
							.findByTypeAndThreatType_SipIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("recon")) {
				if (projection)
					response = new ResponseEntity<List<?>>(repository
							.findProjectedByTypeAndThreatType_ReconIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);
				else
					response = new ResponseEntity<List<?>>(repository
							.findByTypeAndThreatType_ReconIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("possibleCompromise")) {
				if (projection)
					response = new ResponseEntity<List<?>>(repository
							.findProjectedByTypeAndThreatType_PossibleCompromiseIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);
				else
					response = new ResponseEntity<List<?>>(repository
							.findByTypeAndThreatType_PossibleCompromiseIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("db")) {
				if (projection)
					response = new ResponseEntity<List<?>>(repository
							.findProjectedByTypeAndThreatType_DbIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);
				else
					response = new ResponseEntity<List<?>>(repository
							.findByTypeAndThreatType_DbIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
									type, since, confidenceGT, confidenceLT, riskGT, riskLT,
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);
			}

		} else {
			if (projection) {
				response = new ResponseEntity<List<?>>(repository
						.findProjectedByTypeAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
								type, since, confidenceGT, confidenceLT, riskGT, riskLT,
								WhitelistLoader.getWhitelist()),
						HttpStatus.OK);
			} else
				response = new ResponseEntity<List<?>>(repository
						.findByTypeAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(type,
								since, confidenceGT, confidenceLT, riskGT, riskLT, WhitelistLoader.getWhitelist()),
						HttpStatus.OK);
		}

		return response;

	}

	@RequestMapping("all")
	public ResponseEntity<?> all(@RequestParam(value = "type", defaultValue = "ip") String type,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "0") int size, 
			@RequestParam(defaultValue = "timestamp,desc") String sort) {
		String sortParams[] = sort.split(",");
		return new ResponseEntity<Page<?>>(repository.findByTypeAndIndicatorNotIn(
				type, WhitelistLoader.getWhitelist(), new PageRequest(page, size, new Sort(new Order(Direction.fromStringOrNull(sortParams[1]), sortParams[0])))), HttpStatus.OK);

	}

	@RequestMapping("allNotNull")
	public ResponseEntity<?> allNotNull(@RequestParam(value = "type", defaultValue = "ip") String type,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "0") int size,
			@RequestParam(defaultValue = "timestamp,desc") String sort) {
		String sortParams[] = sort.split(",");
		return new ResponseEntity<Page<?>>(
				repository.findByTypeAndTimestampNotNullAndExpiryNotNullAndIndicatorNotIn(type,
						WhitelistLoader.getWhitelist(),
						new PageRequest(page, size,
								new Sort(new Order(Direction.fromStringOrNull(sortParams[1]), sortParams[0])))),
				HttpStatus.OK);

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
		resource.add(new Link(lb.toString() + "/search/all{?type, page, size, sort}", "all"));
		resource.add(new Link(lb.toString() + "/search/allNotNull{?type, page, size, sort}", "allNotNull"));
		resource.add(new Link(lb.toString() + "/search/total{?type}", "total"));
		resource.add(new Link(lb.toString() + "/search/totalNotNull{?type}", "totalNotNull"));

		return resource;
	}

}
