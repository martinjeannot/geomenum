/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.domainmodel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ResourceSupport;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Base class of all our REST resource classes.
 */
public class AbstractResource extends ResourceSupport {

    private static final Logger logger = LoggerFactory.getLogger(AbstractResource.class);

    /**
     * Has been replaced by the "hasImage" boolean.
     *
     * @param imageURL
     * @return
     */
    @Deprecated
    protected boolean checkImageExistence(URL imageURL) {
        // try to reach the image to see if it exists
        try {
            HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
            connection.connect(); // just in case
            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            logger.error("Couldn't open image connection : ", e);
            return false;
        }
    }
}
