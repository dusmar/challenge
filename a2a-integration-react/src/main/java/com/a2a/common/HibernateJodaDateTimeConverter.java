package com.a2a.common;

import java.sql.Timestamp;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.joda.time.DateTime;

@Converter(autoApply = true)
public class HibernateJodaDateTimeConverter implements AttributeConverter<DateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(DateTime dateTime) {
        return dateTime == null ? null : new Timestamp(dateTime.getMillis());
    }

    @Override
    public DateTime convertToEntityAttribute(Timestamp date) {
        return date == null ? null : new DateTime(date.getTime());
    }
}