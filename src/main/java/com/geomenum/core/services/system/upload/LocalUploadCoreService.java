/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.system.upload;

import com.geomenum.config.profiles.Development;
import com.geomenum.config.rootcontext.LocalStaticResourcesConfiguration;
import com.geomenum.core.domainmodel.system.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * {@link UploadCoreService} implementation using the local FS.
 */
@Service
@Development
public class LocalUploadCoreService extends AbstractUploadCoreService {

    private static final Logger logger = LoggerFactory.getLogger(LocalUploadCoreService.class);

    private static final Path uploadRootPath = Paths.get(LocalStaticResourcesConfiguration.FILE_UPLOAD_ROOT);

    @Override
    public void upload(Image image, URI destination) throws UploadException {
        Path absoluteDestination = uploadRootPath.resolve(destination.toString());

        // ensure directories exist
        try {
            Files.createDirectories(absoluteDestination.getParent());
        } catch (IOException e) {
            throw new UploadException(e);
        }

        // save the JPEG converted content to the local fs
        try (
                OutputStream outputStream = new FileOutputStream(absoluteDestination.toFile())
        ) {
            outputStream.write(getImageContentConvertedToJPEG(image));
            outputStream.flush();
        } catch (IOException e) {
            throw new UploadException(e);
        }
    }

    @Override
    public void delete(URI targetURI) {
        Path absoluteTargetPath = uploadRootPath.resolve(targetURI.toString());
        try {
            Files.deleteIfExists(absoluteTargetPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
