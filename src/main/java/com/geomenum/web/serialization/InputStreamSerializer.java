/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamSerializer extends JsonSerializer<InputStream> {

    @Override
    public void serialize(InputStream value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        byte[] bytes = FileCopyUtils.copyToByteArray(value); // copy input stream to byte array
        jgen.writeBinary(bytes);
    }
}
