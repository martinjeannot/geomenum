/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.geomenum.persistence.converters.StringToPersistenceIdConverter;
import org.bson.types.ObjectId;

import java.io.IOException;

public class ObjectIdDeserializer extends JsonDeserializer<ObjectId> {

    @Override
    public ObjectId deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        if(JsonToken.VALUE_STRING.equals(jp.getCurrentToken())) {
            return StringToPersistenceIdConverter.convert(jp.getValueAsString());
        }
        throw ctxt.mappingException("Cannot deserialize given value : " + jp.getValueAsString());
    }
}
