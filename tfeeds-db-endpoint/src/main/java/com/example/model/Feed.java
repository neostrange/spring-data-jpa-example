package com.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.util.LocalDateTimeAttributeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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

//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//	@JsonSerialize(using = LocalDateTimeSerializer.class)
//	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime firstSeen;

//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//	@JsonSerialize(using = LocalDateTimeSerializer.class)
//	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime lastSeen;

//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//	@JsonSerialize(using = LocalDateTimeSerializer.class)
//	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime timestamp;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime expiry;

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

	@JsonIgnore	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonIgnore
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonIgnore
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getFirstSeen() {
		return firstSeen;
	}

	public void setFirstSeen(LocalDateTime firstSeen) {
		this.firstSeen = firstSeen;
	}

	@JsonIgnore
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(LocalDateTime lastSeen) {
		this.lastSeen = lastSeen;
	}

	@JsonIgnore
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getExpiry() {
		return expiry;
	}

	public void setExpiry(LocalDateTime expiry) {
		this.expiry = expiry;
	}

	public double getRiskFactor() {
		return riskFactor;
	}

	public void setRiskFactor(double riskFactor) {
		this.riskFactor = riskFactor;
	}

	@JsonIgnore
	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	@JsonIgnore
	public IncidentStats getIncidentStats() {
		return incidentStats;
	}

	public void setIncidentStats(IncidentStats incidentStats) {
		this.incidentStats = incidentStats;
	}

	@JsonIgnore
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@JsonIgnore
	public String getTlpLevel() {
		return tlpLevel;
	}

	public void setTlpLevel(String tlpLevel) {
		this.tlpLevel = tlpLevel;
	}

	@JsonIgnore
	public TTPType getTtpType() {
		return ttpType;
	}

	public void setTtpType(TTPType ttpType) {
		this.ttpType = ttpType;
	}
	
	@JsonIgnore
	public ThreatType getThreatType() {
		return threatType;
	}

	public void setThreatType(ThreatType threatType) {
		this.threatType = threatType;
	}

	@JsonIgnore
	public SuggestedCOA getSuggestedCOA() {
		return suggestedCOA;
	}

	public void setSuggestedCOA(SuggestedCOA suggestedCOA) {
		this.suggestedCOA = suggestedCOA;
	}



}
