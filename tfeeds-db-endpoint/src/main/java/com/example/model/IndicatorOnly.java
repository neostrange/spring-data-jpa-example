package com.example.model;

import java.time.LocalDateTime;

import org.springframework.data.rest.core.config.Projection;


@Projection(name="IndicatorOnly", types=Feed.class)
public interface IndicatorOnly {
	
	String getIndicator();
	Double getRiskFactor();
    LocalDateTime getExpiry();
	

}
