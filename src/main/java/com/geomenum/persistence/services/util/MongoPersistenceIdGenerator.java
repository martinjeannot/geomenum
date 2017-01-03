/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.services.util;

import com.geomenum.persistence.converters.PersistenceIdToStringConverter;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

/**
 * {@link PersistenceIdGenerator} MongoDB implementation.
 */
@Service
public class MongoPersistenceIdGenerator implements PersistenceIdGenerator {

    @Override
    public String generate() {
        return PersistenceIdToStringConverter.convert(new ObjectId());
    }
}
