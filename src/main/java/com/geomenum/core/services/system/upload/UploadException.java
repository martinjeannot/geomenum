/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.system.upload;

/**
 * Exceptions thrown when something went wrong regarding the upload of some content.<br/>
 * Since it is essentially a wrapper exception, it will typically encapsulate another exception.
 */
public class UploadException extends RuntimeException {

    public UploadException() {
    }

    public UploadException(String message) {
        super(message);
    }

    public UploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public UploadException(Throwable cause) {
        super(cause);
    }
}
