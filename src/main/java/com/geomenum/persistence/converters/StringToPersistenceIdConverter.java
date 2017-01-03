/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.converters;

import com.geomenum.persistence.security.IdCipher;
import com.google.common.collect.Lists;
import org.apache.commons.codec.binary.Base64;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Converter for ids used by our persistence domain objects; used each time an id comes in the persistence layer.<br/>
 * This class does NOT register to Spring.
 */
public class StringToPersistenceIdConverter {

    /**
     * Convert an id (as a String) into its corresponding persistence id (currently {@link ObjectId}).
     *
     * @param source the id to convert
     * @return an {@link ObjectId}
     */
    public static ObjectId convert(String source) {
        return new ObjectId(new String(IdCipher.decrypt(Base64.decodeBase64(source))));
    }

    /**
     * Convert a {@link List} of ids (as Strings) into its corresponding {@link List} of persistence ids
     * (currently {@link ObjectId}).
     *
     * @param source the {@link List} of ids to convert
     * @return a {@link List} of {@link ObjectId}
     */
    public static List<ObjectId> convert(List<String> source) {
        List<ObjectId> target = Lists.newArrayListWithCapacity(source.size());
        for(String id : source) {
            target.add(convert(id));
        }
        return target;
    }
}
