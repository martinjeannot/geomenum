/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.system.upload;

import com.geomenum.core.domainmodel.system.Image;

import java.net.URI;

/**
 * Service used to upload files.
 */
public interface UploadCoreService {

    /**
     * Upload the given image to the given relative URI.
     *
     * @param image the image to upload
     * @param destination the relative URI to upload the image to
     * @throws UploadException in case something went wrong during the upload.
     *                         May also serves as a wrapper for other exceptions thrown during the upload process.
     */
    void upload(Image image, URI destination) throws UploadException;

    /**
     * TODO : find a better place to suit this function
     *
     * Delete the resources targeted by the given relative URI.
     *
     * @param targetURI the target relative URI
     */
    void delete(URI targetURI);
}
