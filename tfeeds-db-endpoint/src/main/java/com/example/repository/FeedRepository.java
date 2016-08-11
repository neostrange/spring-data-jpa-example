package com.example.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.model.Feed;
import com.example.model.IndicatorOnly;

@RepositoryRestResource(collectionResourceRel = "feed", path = "feed")
public interface FeedRepository extends PagingAndSortingRepository<Feed, String>, CrudRepository<Feed, String> {

	@RestResource(path = "all", description = @Description("Full feed including details"))
	List<Feed> findByType(@Param("type") String type);

	@RestResource(path = "allPaged", description = @Description("Full feed including details"))
	List<Feed> findByType(@Param("type") String type, Pageable p);

	@RestResource(path = "allNotNull", description = @Description("Full feed including details"))
	List<Feed> findByTypeAndTimestampNotNullAndExpiryNotNull(@Param("type") String type, Pageable p);

	@RestResource(path = "total", description = @Description("Count by type"))
	Long countByType(@Param("type") String type);

	@RestResource(path = "totalNotNull", description = @Description("Count by type"))
	Long countByTypeAndTimestampNotNullAndExpiryNotNull(@Param("type") String type);

	@RestResource(path = "allSince", description = @Description("Full feed including details"))
	List<Feed> findByTypeAndLastSeenAfter(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	List<Feed> findByTypeAndFirstSeen(@Param("type") String type, @Param("firstSeen") String firstSeen);

	// Non-whitelisted

	// find by incident type and threat type
	@RestResource(path = "malware")
	List<Feed> findByTypeAndThreatType_MalwareIsTrue(@Param("type") String type);

	// @Query("select f.indicator, f.expiry from Feed f inner join f.ThreatType
	// as threat where threat.malware = 'true' AND f.type = 'ip'")
	// List<IndicatorOnly> findMalwareIPs();
	@RestResource(path = "web")
	List<Feed> findByTypeAndThreatType_WebIsTrue(@Param("type") String type);

	@RestResource(path = "bruteForce")
	List<Feed> findByTypeAndThreatType_BruteForceIsTrue(@Param("type") String type);

	@RestResource(path = "sip")
	List<Feed> findByTypeAndThreatType_SipIsTrue(@Param("type") String type);

	@RestResource(path = "possibleCompromise")
	List<Feed> findByTypeAndThreatType_PossibleCompromiseIsTrue(@Param("type") String type);

	@RestResource(path = "db")
	List<Feed> findByTypeAndThreatType_DbIsTrue(@Param("type") String type);

	@RestResource(path = "recon")
	List<Feed> findByTypeAndThreatType_ReconIsTrue(@Param("type") String type);

	@RestResource(path = "malwareSince")
	List<Feed> findByTypeAndThreatType_MalwareIsTrueAndLastSeenAfter(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "webSince")
	List<Feed> findByTypeAndThreatType_WebIsTrueAndLastSeenAfter(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "bruteForceSince")
	List<Feed> findByTypeAndThreatType_BruteForceIsTrueAndLastSeenAfter(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "sipSince")
	List<Feed> findByTypeAndThreatType_SipIsTrueAndLastSeenAfter(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "possibleCompromiseSince")
	List<Feed> findProjectedByTypeAndThreatType_PossibleCompromiseIsTrueAndLastSeenAfter(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "dbSince")
	List<Feed> findByTypeAndThreatType_DbIsTrueAndLastSeenAfter(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "reconSince")
	List<Feed> findByTypeAndThreatType_ReconIsTrueAndLastSeenAfter(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	// Whitelisted

	List<Feed> findByTypeAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGreaterThan, 
			@Param("clt") double confidenceLessThan, 
			@Param("rgt") double riskGreaterThan, 
			@Param("rlt") double riskLessThan,
			@Param("list") List<String> whitelist);

	List<Feed> findByTypeAndThreatType_MalwareIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGreaterThan, 
			@Param("clt") double confidenceLessThan, 
			@Param("rgt") double riskGreaterThan, 
			@Param("rlt") double riskLessThan,
			@Param("list") List<String> whitelist);

	List<Feed> findByTypeAndThreatType_BruteForceIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGreaterThan, 
			@Param("clt") double confidenceLessThan, 
			@Param("rgt") double riskGreaterThan, 
			@Param("rlt") double riskLessThan,
			@Param("list") List<String> whitelist);

	List<Feed> findByTypeAndThreatType_SipIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGreaterThan, 
			@Param("clt") double confidenceLessThan, 
			@Param("rgt") double riskGreaterThan, 
			@Param("rlt") double riskLessThan,
			@Param("list") List<String> whitelist);

	List<Feed> findByTypeAndThreatType_PossibleCompromiseIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGreaterThan, 
			@Param("clt") double confidenceLessThan, 
			@Param("rgt") double riskGreaterThan, 
			@Param("rlt") double riskLessThan,
			@Param("list") List<String> whitelist);

	List<Feed> findByTypeAndThreatType_DbIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGreaterThan, 
			@Param("clt") double confidenceLessThan, 
			@Param("rgt") double riskGreaterThan, 
			@Param("rlt") double riskLessThan,
			@Param("list") List<String> whitelist);

	List<Feed> findByTypeAndThreatType_WebIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGreaterThan, 
			@Param("clt") double confidenceLessThan, 
			@Param("rgt") double riskGreaterThan, 
			@Param("rlt") double riskLessThan,
			@Param("list") List<String> whitelist);
	
	List<Feed> findByTypeAndThreatType_ReconIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGreaterThan, 
			@Param("clt") double confidenceLessThan, 
			@Param("rgt") double riskGreaterThan, 
			@Param("rlt") double riskLessThan,
			@Param("list") List<String> whitelist);


	
	@RestResource(path = "malwareConfidenceGreater")
	List<Feed> findByTypeAndThreatType_MalwareIsTrueAndConfidenceGreaterThan(@Param("type") String type,
			@Param("gt") double confidence);

	@RestResource(path = "reconGreaterSince")
	List<Feed> findByTypeAndThreatType_ReconIsTrueAndRiskFactorGreaterThanAndLastSeenAfter(@Param("type") String type,
			@Param("gt") double riskFactor,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "reconLesserSince")
	List<Feed> findByTypeAndThreatType_ReconIsTrueAndRiskFactorLessThanAndLastSeenAfter(@Param("type") String type,
			@Param("lt") double riskFactor,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "malwareLesserSince")
	List<Feed> findByTypeAndThreatType_MalwareIsTrueAndRiskFactorLessThanAndLastSeenAfter(@Param("type") String type,
			@Param("lt") double riskFactor,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "malwareGreaterSince")
	List<Feed> findByTypeAndThreatType_MalwareIsTrueAndRiskFactorGreaterThanAndLastSeenAfter(@Param("type") String type,
			@Param("gt") double riskFactor,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "sipLesserSince")
	List<Feed> findByTypeAndThreatType_SipIsTrueAndRiskFactorLessThanAndLastSeenAfter(@Param("type") String type,
			@Param("lt") double riskFactor,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "sipGreaterSince")
	List<Feed> findByTypeAndThreatType_SipIsTrueAndRiskFactorGreaterThanAndLastSeenAfter(@Param("type") String type,
			@Param("gt") double riskFactor,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "webLesserSince")
	List<Feed> findByTypeAndThreatType_WebIsTrueAndRiskFactorLessThanAndLastSeenAfter(@Param("type") String type,
			@Param("lt") double riskFactor,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "webGreaterSince")
	List<Feed> findByTypeAndThreatType_WebIsTrueAndRiskFactorGreaterThanAndLastSeenAfter(@Param("type") String type,
			@Param("gt") double riskFactor,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "dbLesserSince")
	List<Feed> findByTypeAndThreatType_DbIsTrueAndRiskFactorLessThanAndLastSeenAfter(@Param("type") String type,
			@Param("lt") double riskFactor,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "dbGreaterSince")
	List<Feed> findByTypeAndThreatType_DbIsTrueAndRiskFactorGreaterThanAndLastSeenAfter(@Param("type") String type,
			@Param("gt") double riskFactor,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "bruteForceLesserSince")
	List<Feed> findByTypeAndThreatType_BruteForceIsTrueAndRiskFactorLessThanAndLastSeenAfter(@Param("type") String type,
			@Param("lt") double riskFactor,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "bruteForceGreaterSince")
	List<Feed> findByTypeAndThreatType_BruteForceIsTrueAndRiskFactorGreaterThanAndLastSeenAfter(
			@Param("type") String type, @Param("gt") double riskFactor,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "possibleCompromiseLesserSince")
	List<Feed> findByTypeAndThreatType_PossibleCompromiseIsTrueAndRiskFactorLessThanAndLastSeenAfter(
			@Param("type") String type, @Param("lt") double riskFactor,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "possibleCompromiseGreaterSince")
	List<Feed> findByTypeAndThreatType_PossibleCompromiseIsTrueAndRiskFactorGreaterThanAndLastSeenAfter(
			@Param("type") String type, @Param("gt") double riskFactor,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	// RiskBetween
	@RestResource(path = "malwareRiskBtw")
	List<Feed> findByTypeAndThreatType_MalwareIsTrueAndRiskFactorBetween(@Param("type") String type,
			@Param("gt") double gt, @Param("lt") double lt);

	@RestResource(path = "webRiskBtw")
	List<Feed> findByTypeAndThreatType_WebIsTrueAndRiskFactorBetween(@Param("type") String type, @Param("gt") double gt,
			@Param("lt") double lt);

	@RestResource(path = "bruteForceRiskBtw")
	List<Feed> findByTypeAndThreatType_BruteForceIsTrueAndRiskFactorBetween(@Param("type") String type,
			@Param("gt") double gt, @Param("lt") double lt);

	@RestResource(path = "sipRiskBtw")
	List<Feed> findByTypeAndThreatType_SipIsTrueAndRiskFactorBetween(@Param("type") String type, @Param("gt") double gt,
			@Param("lt") double lt);

	@RestResource(path = "possibleCompromiseRiskBtw")
	List<Feed> findProjectedByTypeAndThreatType_PossibleCompromiseIsTrueAndRiskFactorBetween(@Param("type") String type,
			@Param("gt") double gt, @Param("lt") double lt);

	@RestResource(path = "dbRiskBtw")
	List<Feed> findByTypeAndThreatType_DbIsTrueAndRiskFactorBetween(@Param("type") String type, @Param("gt") double gt,
			@Param("lt") double lt);

	@RestResource(path = "reconRiskBtw")
	List<Feed> findByTypeAndThreatType_ReconIsTrueAndRiskFactorBetween(@Param("type") String type,
			@Param("gt") double gt, @Param("lt") double lt);

	// RISK-CONFIDENCE
	@RestResource(path = "malwareRiskConfBtwSince")
	List<Feed> findByTypeAndThreatType_MalwareIsTrueAndLastSeenAfterAndRiskFactorBetweenAndConfidenceBetween(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("rgt") double rgt, @Param("rlt") double rlt, @Param("cgt") double cgt, @Param("clt") double clt);

	@RestResource(path = "webRiskConfBtwSince")
	List<Feed> findByTypeAndThreatType_WebIsTrueAndLastSeenAfterAndRiskFactorBetweenAndConfidenceBetween(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("rgt") double rgt, @Param("rlt") double rlt, @Param("cgt") double cgt, @Param("clt") double clt);

	@RestResource(path = "bruteForceRiskConfBtwSince")
	List<Feed> findByTypeAndThreatType_BruteForceIsTrueAndLastSeenAfterAndRiskFactorBetweenAndConfidenceBetween(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("rgt") double rgt, @Param("rlt") double rlt, @Param("cgt") double cgt, @Param("clt") double clt);

	@RestResource(path = "sipRiskConfBtwSince")
	List<Feed> findByTypeAndThreatType_SipIsTrueAndLastSeenAfterAndRiskFactorBetweenAndConfidenceBetween(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("rgt") double rgt, @Param("rlt") double rlt, @Param("cgt") double cgt, @Param("clt") double clt);

	@RestResource(path = "possibleCompromiseRiskConfBtwSince")
	List<Feed> findProjectedByTypeAndThreatType_PossibleCompromiseIsTrueAndLastSeenAfterAndRiskFactorBetweenAndConfidenceBetween(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("rgt") double rgt, @Param("rlt") double rlt, @Param("cgt") double cgt, @Param("clt") double clt);

	@RestResource(path = "dbRiskConfBtwSince")
	List<Feed> findByTypeAndThreatType_DbIsTrueAndLastSeenAfterAndRiskFactorBetweenAndConfidenceBetween(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("rgt") double rgt, @Param("rlt") double rlt, @Param("cgt") double cgt, @Param("clt") double clt);

	@RestResource(path = "reconRiskConfBtwSince")
	List<Feed> findByTypeAndThreatType_ReconIsTrueAndLastSeenAfterAndRiskFactorBetweenAndConfidenceBetween(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("rgt") double rgt, @Param("rlt") double rlt, @Param("cgt") double cgt, @Param("clt") double clt);

	// greater-lesser

	@RestResource(path = "reconRiskGreater")
	List<Feed> findByTypeAndThreatType_ReconIsTrueAndRiskFactorGreaterThan(@Param("type") String type,
			@Param("gt") double riskFactor);

	@RestResource(path = "reconRiskLesser")
	List<Feed> findByTypeAndThreatType_ReconIsTrueAndRiskFactorLessThan(@Param("type") String type,
			@Param("lt") double riskFactor);

	@RestResource(path = "malwareRiskLesser")
	List<Feed> findByTypeAndThreatType_MalwareIsTrueAndRiskFactorLessThan(@Param("type") String type,
			@Param("lt") double riskFactor);

	@RestResource(path = "malwareRiskGreater")
	List<Feed> findByTypeAndThreatType_MalwareIsTrueAndRiskFactorGreaterThan(@Param("type") String type,
			@Param("gt") double riskFactor);

	@RestResource(path = "sipRiskLesser")
	List<Feed> findByTypeAndThreatType_SipIsTrueAndRiskFactorLessThan(@Param("type") String type,
			@Param("lt") double riskFactor);

	@RestResource(path = "sipRiskGreater")
	List<Feed> findByTypeAndThreatType_SipIsTrueAndRiskFactorGreaterThan(@Param("type") String type,
			@Param("gt") double riskFactor);

	@RestResource(path = "webRiskLesser")
	List<Feed> findByTypeAndThreatType_WebIsTrueAndRiskFactorLessThan(@Param("type") String type,
			@Param("lt") double riskFactor);

	@RestResource(path = "webRiskGreater")
	List<Feed> findByTypeAndThreatType_WebIsTrueAndRiskFactorGreaterThan(@Param("type") String type,
			@Param("gt") double riskFactor);

	@RestResource(path = "dbRiskLesser")
	List<Feed> findByTypeAndThreatType_DbIsTrueAndRiskFactorLessThan(@Param("type") String type,
			@Param("lt") double riskFactor);

	@RestResource(path = "dbRiskGreater")
	List<Feed> findByTypeAndThreatType_DbIsTrueAndRiskFactorGreaterThan(@Param("type") String type,
			@Param("gt") double riskFactor);

	@RestResource(path = "bruteForceRiskLesser")
	List<Feed> findByTypeAndThreatType_BruteForceIsTrueAndRiskFactorLessThan(@Param("type") String type,
			@Param("lt") double riskFactor);

	@RestResource(path = "bruteForceRiskGreater")
	List<Feed> findByTypeAndThreatType_BruteForceIsTrueAndRiskFactorGreaterThan(@Param("type") String type,
			@Param("gt") double riskFactor);

	@RestResource(path = "possibleCompromiseRiskLesser")
	List<Feed> findByTypeAndThreatType_PossibleCompromiseIsTrueAndRiskFactorLessThan(@Param("type") String type,
			@Param("lt") double riskFactor);

	@RestResource(path = "possibleCompromiseRiskGreater")
	List<Feed> findByTypeAndThreatType_PossibleCompromiseIsTrueAndRiskFactorGreaterThan(@Param("type") String type,
			@Param("gt") double riskFactor);

	// @RestResource(path = "firstSeen")
	// List<Feed> findByFirstSeenBefore(
	// @Param("firstSeen") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	// LocalDateTime firstSeen);

	@RestResource(path = "firstSeen")
	List<Feed> findByFirstSeenBetween(
			@Param("begin") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime begin,
			@Param("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end);

	@RestResource(path = "confidenceLessThan")
	List<Feed> findByConfidenceLessThan(@Param("lt") double confidence);

	@RestResource(path = "confidenceGreaterThan")
	List<Feed> findByConfidenceGreaterThan(@Param("gt") double confidence);

	@RestResource(path = "confidence")
	List<Feed> findByConfidenceIs(@Param("conf") double confidence);

	@RestResource(path = "riskFactor")
	List<Feed> findByRiskFactorIs(@Param("gt") double riskFactor);

	@RestResource(path = "riskFactorLessThan")
	List<Feed> findByRiskFactorLessThan(@Param("lt") double riskFactor);

	@RestResource(path = "riskFactorGreaterThan")
	List<Feed> findByRiskFactorGreaterThan(@Param("gt") double riskFactor);

	@RestResource(path = "condensed")
	List<IndicatorOnly> findProjectedByTypeAndLastSeenAfter(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime time);

}
