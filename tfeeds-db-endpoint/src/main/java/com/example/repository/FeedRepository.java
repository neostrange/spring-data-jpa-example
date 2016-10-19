package com.example.repository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import org.springframework.data.domain.Page;
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

@RepositoryRestResource(collectionResourceRel = "feed", path = "feed", excerptProjection = IndicatorOnly.class)
public abstract interface FeedRepository
		extends PagingAndSortingRepository<Feed, String>, CrudRepository<Feed, String> {
	public abstract Page<Feed> findByTypeAndIndicatorNotIn(@Param("type") String type,
			@Param("list") HashSet<String> whitelist, Pageable pageable);

	public abstract Page<Feed> findByTypeAndTimestampNotNullAndExpiryNotNullAndIndicatorNotIn(
			@Param("type") String type, @Param("list") HashSet<String> whitelist, Pageable pageable);

	public abstract Long countByTypeAndIndicatorNotIn(@Param("type") String type,
			@Param("list") HashSet<String> whitelist);

	public abstract Long countByTypeAndTimestampNotNullAndExpiryNotNullAndIndicatorNotIn(
			@Param("type") String type, @Param("list") HashSet<String> whitelist);

	@RestResource(path = "allSince", description = @Description("Full feed including details"))
	public abstract List<Feed> findByTypeAndLastSeenAfter(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	public abstract List<Feed> findByTypeAndFirstSeen(@Param("type") String type,
			@Param("firstSeen") String firstSeen);

	@RestResource(path = "malware")
	public abstract List<Feed> findByTypeAndThreatType_MalwareIsTrue(@Param("type") String type);

	@RestResource(path = "web")
	public abstract List<Feed> findByTypeAndThreatType_WebIsTrue(@Param("type") String type);

	@RestResource(path = "bruteForce")
	public abstract List<Feed> findByTypeAndThreatType_BruteForceIsTrue(@Param("type") String type);

	@RestResource(path = "sip")
	public abstract List<Feed> findByTypeAndThreatType_SipIsTrue(@Param("type") String type);

	@RestResource(path = "possibleCompromise")
	public abstract List<Feed> findByTypeAndThreatType_PossibleCompromiseIsTrue(@Param("type") String type);

	@RestResource(path = "db")
	public abstract List<Feed> findByTypeAndThreatType_DbIsTrue(@Param("type") String type);

	@RestResource(path = "recon")
	public abstract List<Feed> findByTypeAndThreatType_ReconIsTrue(@Param("type") String type);

	@RestResource(path = "malwareSince")
	public abstract List<Feed> findByTypeAndThreatType_MalwareIsTrueAndLastSeenAfter(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "webSince")
	public abstract List<Feed> findByTypeAndThreatType_WebIsTrueAndLastSeenAfter(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "bruteForceSince")
	public abstract List<Feed> findByTypeAndThreatType_BruteForceIsTrueAndLastSeenAfter(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "sipSince")
	public abstract List<Feed> findByTypeAndThreatType_SipIsTrueAndLastSeenAfter(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "possibleCompromiseSince")
	public abstract List<Feed> findProjectedByTypeAndThreatType_PossibleCompromiseIsTrueAndLastSeenAfter(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "dbSince")
	public abstract List<Feed> findByTypeAndThreatType_DbIsTrueAndLastSeenAfter(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	@RestResource(path = "reconSince")
	public abstract List<Feed> findByTypeAndThreatType_ReconIsTrueAndLastSeenAfter(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);

	public abstract Page<Feed> findByTypeAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGT, @Param("clt") double confidenceLT, @Param("rgt") double riskGT,
			@Param("rlt") double riskLT, @Param("list") HashSet<String> whitelist, Pageable page);

    public abstract List<Feed> findListByTypeAndThreatType_MalwareIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGT, @Param("clt") double confidenceLT, @Param("rgt") double riskGT,
			@Param("rlt") double riskLT, @Param("list") HashSet<String> whitelist);

	public abstract List<Feed> findListByTypeAndThreatType_BruteForceIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGT, @Param("clt") double confidenceLT, @Param("rgt") double riskGT,
			@Param("rlt") double riskLT, @Param("list") HashSet<String> whitelist);

	public abstract List<Feed> findListByTypeAndThreatType_SipIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGT, @Param("clt") double confidenceLT, @Param("rgt") double riskGT,
			@Param("rlt") double riskLT, @Param("list") HashSet<String> whitelist);

	public abstract List<Feed> findListByTypeAndThreatType_PossibleCompromiseIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGT, @Param("clt") double confidenceLT, @Param("rgt") double riskGT,
			@Param("rlt") double riskLT, @Param("list") HashSet<String> whitelist);

    public abstract List<Feed> findListByTypeAndThreatType_DbIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGT, @Param("clt") double confidenceLT, @Param("rgt") double riskGT,
			@Param("rlt") double riskLT, @Param("list") HashSet<String> whitelist);

    public abstract List<Feed> findListByTypeAndThreatType_WebIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGT, @Param("clt") double confidenceLT, @Param("rgt") double riskGT,
			@Param("rlt") double riskLT, @Param("list") HashSet<String> whitelist);

	public abstract List<Feed> findListByTypeAndThreatType_ReconIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGT, @Param("clt") double confidenceLT, @Param("rgt") double riskGT,
			@Param("rlt") double riskLT, @Param("list") HashSet<String> whitelist);
	
	public abstract Page<Feed> findByTypeAndThreatType_MalwareIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGT, @Param("clt") double confidenceLT, @Param("rgt") double riskGT,
			@Param("rlt") double riskLT, @Param("list") HashSet<String> whitelist, Pageable page);

	public abstract Page<Feed> findByTypeAndThreatType_BruteForceIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGT, @Param("clt") double confidenceLT, @Param("rgt") double riskGT,
			@Param("rlt") double riskLT, @Param("list") HashSet<String> whitelist, Pageable page);

	public abstract Page<Feed> findByTypeAndThreatType_SipIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGT, @Param("clt") double confidenceLT, @Param("rgt") double riskGT,
			@Param("rlt") double riskLT, @Param("list") HashSet<String> whitelist, Pageable page);

	public abstract Page<Feed> findByTypeAndThreatType_PossibleCompromiseIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGT, @Param("clt") double confidenceLT, @Param("rgt") double riskGT,
			@Param("rlt") double riskLT, @Param("list") HashSet<String> whitelist, Pageable page);

    public abstract Page<Feed> findByTypeAndThreatType_DbIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGT, @Param("clt") double confidenceLT, @Param("rgt") double riskGT,
			@Param("rlt") double riskLT, @Param("list") HashSet<String> whitelist, Pageable page);

    public abstract Page<Feed> findByTypeAndThreatType_WebIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGT, @Param("clt") double confidenceLT, @Param("rgt") double riskGT,
			@Param("rlt") double riskLT, @Param("list") HashSet<String> whitelist, Pageable page);

	public abstract Page<Feed> findByTypeAndThreatType_ReconIsTrueAndLastSeenAfterAndConfidenceBetweenAndRiskFactorBetweenAndIndicatorNotIn(
			@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since,
			@Param("cgt") double confidenceGT, @Param("clt") double confidenceLT, @Param("rgt") double riskGT,
			@Param("rlt") double riskLT, @Param("list") HashSet<String> whitelist, Pageable page);

	@RestResource(path = "confidence")
	public abstract List<Feed> findByConfidenceIs(@Param("conf") double conf);

	@RestResource(path = "riskFactor")
	public abstract List<Feed> findByRiskFactorIs(@Param("gt") double gt);

	@RestResource(path = "riskFactorLessThan")
	public abstract List<Feed> findByRiskFactorLessThan(@Param("lt") double lt);

	@RestResource(path = "riskFactorGreaterThan")
	public abstract List<Feed> findByRiskFactorGreaterThan(@Param("gt") double gt);

	@RestResource(path = "condensed")
	public abstract List<IndicatorOnly> findProjectedByTypeAndLastSeenAfter(@Param("type") String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("since") LocalDateTime since);
}
