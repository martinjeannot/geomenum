/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.converters;

import com.geomenum.persistence.security.IdCipher;
import com.google.common.collect.ImmutableList;
import org.apache.commons.codec.binary.Base64;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Converter for ids used by our persistence domain objects; used each time an id leaves the persistence layer.<br/>
 * This class does NOT register to Spring.
 */
public class PersistenceIdToStringConverter {

    /**
     * Convert a persistence id (currently {@link ObjectId}) into its corresponding String id.
     *
     * @param source the {@link ObjectId} to convert
     * @return a String id
     */
    public static String convert(ObjectId source) {
        return Base64.encodeBase64URLSafeString(IdCipher.encrypt(source.toString().getBytes()));
    }

    /**
     * Convert a list of persistence ids (currently {@link ObjectId}) into its corresponding
     * list of String ids.
     *
     * @param source the {@link List} of {@link ObjectId} to convert
     * @return an {@link ImmutableList} of String
     */
    public static List<String> convert(List<ObjectId> source) {
        ImmutableList.Builder<String> targetBuilder = ImmutableList.builder();
        for(ObjectId objectId : source) {
            targetBuilder.add(convert(objectId));
        }
        return targetBuilder.build();
    }
}
