package com.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Period;

import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.util.LocalDateTimeAttributeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Feed implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7996275520964690026L;

	public enum TTPType {
		C2, MALWARE, SCANNING, WEB, SSH
	};

	public enum SuggestedCOA {
		BLOCK, MONITOR
	};

	public enum Type {
		IP, MD5, URL
	};

	@Id
	private String indicator;

	private String type;

	private String title;
	
	private String description;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime firstSeen;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime lastSeen;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime timestamp;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime expiry;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getExpiry() {
		return expiry;
	}

	public void setExpiry(LocalDateTime expiry) {
		this.expiry = expiry;
	}

	private double riskFactor;

	private double confidence;

	@Transient
	private IncidentStats incidentStats;

	private String source;

	private String tlpLevel;

	private TTPType ttpType;
	
	@Embedded
	private ThreatType threatType;

	private SuggestedCOA suggestedCOA;

	public Feed() {
	}
	
	public Feed(String indicator, String type) {
		this.indicator = indicator;
		this.type = type;
	}

	public Feed(String indicator, String type, LocalDateTime firstSeen, LocalDateTime lastSeen, LocalDateTime timestamp) {
		this.indicator = indicator;
		this.type = type;
		this.firstSeen = firstSeen;
		this.lastSeen = lastSeen;
		this.timestamp = timestamp;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getFirstSeen() {
		return firstSeen;
	}

	public void setFirstSeen(LocalDateTime firstSeen) {
		this.firstSeen = firstSeen;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(LocalDateTime lastSeen) {
		this.lastSeen = lastSeen;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public double getRiskFactor() {
		return riskFactor;
	}

	public void setRiskFactor(double riskFactor) {
		this.riskFactor = riskFactor;
	}

	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	public IncidentStats getIncidentStats() {
		return incidentStats;
	}

	public void setIncidentStats(IncidentStats incidentStats) {
		this.incidentStats = incidentStats;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTlpLevel() {
		return tlpLevel;
	}

	public void setTlpLevel(String tlpLevel) {
		this.tlpLevel = tlpLevel;
	}

	public TTPType getTtpType() {
		return ttpType;
	}

	public void setTtpType(TTPType ttpType) {
		this.ttpType = ttpType;
	}
	
	public ThreatType getThreatType() {
		return threatType;
	}

	public void setThreatType(ThreatType threatType) {
		this.threatType = threatType;
	}

	public SuggestedCOA getSuggestedCOA() {
		return suggestedCOA;
	}

	public void setSuggestedCOA(SuggestedCOA suggestedCOA) {
		this.suggestedCOA = suggestedCOA;
	}



}
