package com.example.model;

import java.time.LocalDateTime;

import org.springframework.data.rest.core.config.Projection;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.model.Feed.SuggestedCOA;
import com.example.model.Feed.TTPType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;


@Projection(name="expand", types=Feed.class)
public interface ExpandedFeeds {
	
	String getIndicator();
	String getType();
	String getTitle();
	public String getDescription();
	Double getRiskFactor();
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime getExpiry();

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonSerialize(using = LocalDateTimeSerializer.class)	
    LocalDateTime getFirstSeen();

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getLastSeen();

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	LocalDateTime getTimestamp();

	double getConfidence();

	IncidentStats getIncidentStats();

	String getSource();

    String getTlpLevel();

    TTPType getTtpType();
	
	ThreatType getThreatType();

	SuggestedCOA getSuggestedCOA();
}
