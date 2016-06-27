package com.example.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.model.Feed;
import com.example.model.IndicatorOnly;


@RepositoryRestResource(collectionResourceRel = "feed", path = "feed")


public interface FeedRepository extends CrudRepository<Feed, String> {

	List<Feed> findByType(@Param("type") String type);

	List<Feed> findByTypeAndFirstSeen(@Param("type") String type, @Param("firstSeen") String firstSeen);

	// find by incident type and threat type
	List<Feed> findByTypeAndThreatType_Malware(@Param("type") String type, @Param("isMalware") boolean value);
	List<Feed> findByTypeAndThreatType_Web(@Param("type") String type, @Param("isWeb") boolean value);
	List<Feed> findByTypeAndThreatType_BruteForce(@Param("type") String type, @Param("isBruteForce") boolean value);
	List<Feed> findByTypeAndThreatType_Sip(@Param("type") String type, @Param("isSip") boolean value);
	List<Feed> findByTypeAndThreatType_PossibleCompromise(@Param("type") String type, @Param("isPossibleCompromise") boolean value);
	List<Feed> findByTypeAndThreatType_Db(@Param("type") String type, @Param("isDb") boolean value);
	List<Feed> findByTypeAndThreatType_Recon(@Param("type") String type, @Param("isRecon") boolean value);
	
	
	List<Feed> findByThreatType_BruteForce(@Param("isBruteForce") boolean type);
	
	List<Feed> findByThreatType_Malware(@Param("isMalware") boolean type);
	List<Feed> findByThreatType_Web(@Param("isWeb") boolean type);
	List<Feed> findByThreatType_Sip(@Param("isSip") boolean type);
	List<Feed> findByThreatType_PossibleCompromise(@Param("isPossibleCompromise") boolean type);
	List<Feed> findByThreatType_Db(@Param("isDb") boolean type);
	List<Feed> findByThreatType_Recon(@Param("isRecon") boolean type);
	
	
	
	List<Feed> findByTypeAndThreatType_MalwareAndConfidenceGreaterThan(@Param("type") String type, 
			@Param("isMalware") boolean value,
			@Param("confidenceLessThan") double confidence);

	List<Feed> findByFirstSeenBefore(@Param("firstSeen") 
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime firstSeen);
	
	List<Feed> findByFirstSeenBetween(@Param("begin")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime begin,
	@Param("end")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime end );
	
	
	List<Feed> findByConfidenceLessThan(@Param("confidenceLessThan") double confidence);
	List<Feed> findByConfidenceGreaterThan(@Param("confidenceGreaterThan") double confidence);
	List<Feed> findByConfidenceIs(@Param("confidenceIs") double confidence);
	
	
	
	
	@RestResource(path = "riskFactor")
	List<Feed> findByRiskFactorIs(@Param("riskFactorIs") double riskFactor);
	//@RestResource(path = "riskFactor")
	List<Feed> findByRiskFactorLessThan(@Param("riskFactorLessThan") double riskFactor);
	//@RestResource(path = "riskFactor")
	List<Feed> findByRiskFactorGreaterThan(@Param("riskFactorGreaterThan") double riskFactor);
	
	List<IndicatorOnly> findProjectedByType(@Param("type") String type);
	
	// riskfactor less than && greater than
	// threat type == 
	// confidence

}
