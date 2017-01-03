/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config.rootcontext;

import com.geomenum.config.profiles.Production;
import org.springframework.context.annotation.Configuration;

@Configuration
@Production
public class AWSStaticResourcesConfiguration extends AbstractStaticResourcesConfiguration {

    private static final String READ_ROOT = "https://geomenum-static.s3.amazonaws.com/"; // trailing slash is mandatory

    @Override
    protected String getReadRoot() {
        return READ_ROOT;
    }
}
