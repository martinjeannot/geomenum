/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.converters;

import com.mongodb.DBObject;
import org.joda.time.LocalTime;
import org.springframework.core.convert.converter.Converter;

public class LocalTimeReadConverter implements Converter<DBObject, LocalTime> {

    @Override
    public LocalTime convert(DBObject source) {
        return LocalTime.fromMillisOfDay((Long) source.get("millisOfDay"));
    }
}
