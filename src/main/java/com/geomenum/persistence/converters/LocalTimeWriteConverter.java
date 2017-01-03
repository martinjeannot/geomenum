/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.converters;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.joda.time.LocalTime;
import org.springframework.core.convert.converter.Converter;

public class LocalTimeWriteConverter implements Converter<LocalTime, DBObject> {

    @Override
    public DBObject convert(LocalTime source) {
        DBObject dbo = new BasicDBObject();
        dbo.put("millisOfDay", (long) source.getMillisOfDay());
        return dbo;
    }
}
