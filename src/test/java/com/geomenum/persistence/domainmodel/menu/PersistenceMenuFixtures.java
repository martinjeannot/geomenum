/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.domainmodel.menu;

import com.geomenum.core.domainmodel.menu.CoreMenuFixtures;
import com.geomenum.persistence.converters.StringToPersistenceIdConverter;
import org.bson.types.ObjectId;

import java.util.Map;

import static com.geomenum.common.Fixtures.localDateTime;

/**
 * Fixtures for {@link PersistenceMenu}.
 */
public class PersistenceMenuFixtures {

    public static ObjectId ID = StringToPersistenceIdConverter.convert(CoreMenuFixtures.ID);

    public static PersistenceMenu standardMenu() {
        return PersistenceMenu.of(newDto());
    }

    public static Map<Object, Object> newDto() {
        Map<Object, Object> menuDto = CoreMenuFixtures.newDto();
        menuDto.put("creationDate", localDateTime);
        return menuDto;
    }

    public static Map<Object, Object> invalidDto() {
        Map<Object, Object> menuDto = newDto();
        menuDto.remove("enabled");
        return menuDto;
    }

    public static Map<Object, Object> updatedDto() {
        Map<Object, Object> menuDto = CoreMenuFixtures.updatedDto();
        menuDto.put("lastUpdateDate", localDateTime);
        return menuDto;
    }
}
