/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.geomenum.common.integration.Mappable;
import com.geomenum.core.domainmodel.CoreDomainModelMapper;
import com.google.common.net.MediaType;

import java.util.Map;

/**
 * An image.
 */
public class Image implements Mappable {

    private final String name = null;
    private final String originalFilename = null;
    private final MediaType mediaType;
    private final byte[] bytes = null;
    private long size; // cannot be final due to JVM inlining optimization
    private boolean empty; // cannot be final due to JVM inlining optimization

    // private ctor
    @SuppressWarnings("unused")
    private Image(@JsonProperty("contentType") String contentType) {
        mediaType = MediaType.parse(contentType);
    }

    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param imageDTO a {@link Map} of properties
     * @return an {@link Image}
     */
    public static Image of(Map<Object, Object> imageDTO) {
        return CoreDomainModelMapper.fromMap(imageDTO, Image.class);
    }

    @Override
    public Map<Object, Object> toMap() {
        return CoreDomainModelMapper.toMap(this);
    }

    //~ Getters & Setters ==============================================================================================

    public String getName() {
        return name;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    /* We don't need to propagate the media type to any other domains right now so we're not going bother writing
     * custom serializers/deserializers. But if the need arises someday, remove @JsonIgnore and write it to the
     * "contentType" property (HTTP header name).
     */
    @JsonIgnore
    public MediaType getMediaType() {
        return mediaType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public long getSize() {
        return size;
    }

    public boolean isEmpty() {
        return empty;
    }
}
