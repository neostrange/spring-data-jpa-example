package com.example.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
		return (locDateTime == null ? null : Timestamp.valueOf(locDateTime));
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
		return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime());
	}
}

// @Converter(autoApply = true)
// public class LocalDateAttributeConverter implements
// AttributeConverter<LocalDateTime, Date> {
//
// @Override
// public Date convertToDatabaseColumn(LocalDateTime locDate) {
// LocalDateTime ldt = locDate;
// Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
// Date res = Date.valueOf(instant.toString());
// return res;
//
// }
//
// @Override
// public LocalDateTime convertToEntityAttribute(Date sqlDate) {
// Instant instant = Instant.ofEpochMilli(sqlDate.getTime());
// LocalDateTime res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
// return res;
// }
// }
