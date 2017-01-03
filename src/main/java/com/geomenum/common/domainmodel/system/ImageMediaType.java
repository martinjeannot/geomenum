/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.domainmodel.system;

/**
 * Represents an Internet Media Type (also known as a MIME Type or Content Type).<br/>
 *
 * Note that this class exists solely for the purpose of being an {@link Enum}. Please use
 * the Guava alternative ({@link com.google.common.net.MediaType MediaType}) when you can.
 *
 * @see com.google.common.net.MediaType
 */
public enum ImageMediaType {

    BMP("image/bmp"),
    CRW("image/x-canon-crw"),
    GIF("image/gif"),
    ICO("image/vnd.microsoft.icon"),
    JPEG("image/jpeg"),
    PNG("image/png"),
    PSD("image/vnd.adobe.photoshop"),
    SVG_UTF_8("image/svg+xml; charset=utf-8"),
    TIFF("image/tiff"),
    WEBP("image/webp");

    private final String mediaType;

    ImageMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }

    public static ImageMediaType parse(String mediaType) {
        for (ImageMediaType imageMediaType : ImageMediaType.values()) {
            if (imageMediaType.getMediaType().equals(mediaType)) {
                return imageMediaType;
            }
        }
        throw new IllegalArgumentException("No enum constant matches image media type " + mediaType);
    }

    @Override
    public String   toString() {
        return mediaType;
    }
}
