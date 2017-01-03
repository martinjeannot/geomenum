/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.domainmodel.system;

import com.geomenum.core.domainmodel.system.CoreUserFixtures;
import com.geomenum.persistence.converters.StringToPersistenceIdConverter;
import org.bson.types.ObjectId;

import java.util.Map;

import static com.geomenum.common.Fixtures.localDateTime;

/**
 * Fixtures for {@link PersistenceUser}.
 */
public class PersistenceUserFixtures {

    public static ObjectId ID = StringToPersistenceIdConverter.convert(CoreUserFixtures.ID);

    public static PersistenceUser standardUser() {
        return PersistenceUser.of(newDto());
    }

    public static Map<Object, Object> newDto() {
        Map<Object, Object> userDto = CoreUserFixtures.newDTO();
        userDto.put("creationDate", localDateTime);
        return userDto;
    }

    public static Map<Object, Object> invalidDto() {
        Map<Object, Object> userDto = newDto();
        userDto.remove("enabled");
        return userDto;
    }

    public static Map<Object, Object> updatedDto() {
        Map<Object, Object> userDto = CoreUserFixtures.updatedDTO();
        userDto.put("lastUpdateDate", localDateTime);
        return userDto;
    }
}
