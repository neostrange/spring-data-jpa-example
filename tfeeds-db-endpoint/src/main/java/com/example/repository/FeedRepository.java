package com.example.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.model.Feed;

@RepositoryRestResource(collectionResourceRel = "feed", path = "feed")
public interface FeedRepository extends JpaRepository<Feed, String> {

	List<Feed> findByType(@Param("type") String type);

	List<Feed> findByTypeAndFirstSeen(@Param("type") String type, @Param("firstSeen") String firstSeen);
	
	List<Feed> findByThreatType_BruteForce(@Param("isBruteForce") boolean type);
	
	List<Feed> findByThreatType_Malware(@Param("isMalware") boolean type);
	List<Feed> findByThreatType_Web(@Param("isWeb") boolean type);
	List<Feed> findByThreatType_Sip(@Param("isSip") boolean type);
	List<Feed> findByThreatType_PossibleCompromise(@Param("isPossibleCompromise") boolean type);
	List<Feed> findByThreatType_Db(@Param("isDb") boolean type);
	List<Feed> findByThreatType_Recon(@Param("isRecon") boolean type);
	

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
	
	
	
	// riskfactor less than && greater than
	// threat type == 
	// confidence

}
