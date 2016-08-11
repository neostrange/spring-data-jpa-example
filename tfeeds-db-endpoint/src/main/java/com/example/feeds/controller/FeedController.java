package com.example.feeds.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
	public ResponseEntity<List<Feed>> allWhitelisted(@RequestParam(value = "type", defaultValue = "ip") String type,
			@RequestParam(value = "threat", required = false) String threat,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(value = "since", defaultValue = "2000-01-01 00:00:00") LocalDateTime since,
			@RequestParam(value = "cgt", defaultValue = "0") int confidenceGT, 
			@RequestParam(value = "clt", defaultValue = "100") int confidenceLT,
			@RequestParam(value = "rgt", defaultValue = "0") int riskGT, 
			@RequestParam(value = "rlt", defaultValue = "10") int riskLT
			) {
		ResponseEntity<List<Feed>> response = null;
		if (threat != null) {
			if (threat.equalsIgnoreCase("malware")) {
					response = new ResponseEntity<List<Feed>>(
							repository.findByTypeAndThreatType_MalwareIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(type, since, confidenceGT, confidenceLT, riskGT, riskLT, 
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("bruteforce")) {
				if(since == null)
					response = new ResponseEntity<List<Feed>>(
							repository.findByTypeAndThreatType_BruteForceIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(type, since, confidenceGT, confidenceLT, riskGT, riskLT, 
									WhitelistLoader.getWhitelist()),
							HttpStatus.OK);


			} else if (threat.equalsIgnoreCase("web")) {
				response = new ResponseEntity<List<Feed>>(
						repository.findByTypeAndThreatType_WebIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(type, since, confidenceGT, confidenceLT, riskGT, riskLT, 
								WhitelistLoader.getWhitelist()),
						HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("sip")) {
				response = new ResponseEntity<List<Feed>>(
						repository.findByTypeAndThreatType_SipIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(type, since, confidenceGT, confidenceLT, riskGT, riskLT, 
								WhitelistLoader.getWhitelist()),
						HttpStatus.OK);


			} else if (threat.equalsIgnoreCase("recon")) {
				response = new ResponseEntity<List<Feed>>(
						repository.findByTypeAndThreatType_ReconIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(type, since, confidenceGT, confidenceLT, riskGT, riskLT, 
								WhitelistLoader.getWhitelist()),
						HttpStatus.OK);

			} else if (threat.equalsIgnoreCase("possibleCompromise")) {
				response = new ResponseEntity<List<Feed>>(
						repository.findByTypeAndThreatType_PossibleCompromiseIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(type, since, confidenceGT, confidenceLT, riskGT, riskLT, 
								WhitelistLoader.getWhitelist()),
						HttpStatus.OK);
				
			} else if (threat.equalsIgnoreCase("db")) {
				response = new ResponseEntity<List<Feed>>(
						repository.findByTypeAndThreatType_DbIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(type, since, confidenceGT, confidenceLT, riskGT, riskLT, 
								WhitelistLoader.getWhitelist()),
						HttpStatus.OK);
			}

		} else
			response = new ResponseEntity<List<Feed>>(
					repository.findByTypeAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(type, since, confidenceGT, confidenceLT, riskGT, riskLT, 
							WhitelistLoader.getWhitelist()),
					HttpStatus.OK);
		
		return response;

	}

	@Override
	public RepositorySearchesResource process(RepositorySearchesResource resource) {

		LinkBuilder lb = entityLinks.linkFor(Feed.class);
		resource.add(new Link(lb.toString() + "/search/allFeeds{?type, threat, since, cgt, clt, rgt, rlt}", "allFeeds"));
		return resource;
	}

}
