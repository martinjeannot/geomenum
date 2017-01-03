/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.system.upload;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.geomenum.config.profiles.Production;
import com.geomenum.core.domainmodel.system.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * {@link UploadCoreService} implementation using AWS S3.
 */
@Service
@Production
public class AmazonS3UploadCoreService extends AbstractUploadCoreService {

    private static final Logger logger = LoggerFactory.getLogger(AmazonS3UploadCoreService.class);

    private static final String BUCKET_NAME = "geomenum-static";
    private static final AmazonS3 s3Client;

    static {
        List<String> AWSSystemUserCredentials;
        try {
            AWSSystemUserCredentials = Files.readAllLines(
                    Paths.get(AmazonS3UploadCoreService.class.getClassLoader().getResource("security/AWSSystemUserCredentials.txt").getPath()),
                    Charset.defaultCharset());
        } catch (IOException e) {
            throw new Error("[FATAL ERROR] AmazonS3UploadCoreService INITIALIZATION FAILED !", e); // abort mission !
        }
        s3Client = new AmazonS3Client(new BasicAWSCredentials(AWSSystemUserCredentials.get(0), AWSSystemUserCredentials.get(1)));
    }

    @Override
    public void upload(Image image, URI destination) throws UploadException {
        byte[] imageContent = getImageContentConvertedToJPEG(image);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(imageContent.length);
        metadata.setContentType(getImageMediaType(image).toString());

        try (
                InputStream inputStream = new ByteArrayInputStream(imageContent)
        ) {
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, destination.toString(), inputStream, metadata);
            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
            s3Client.putObject(putObjectRequest);
        } catch (IOException | AmazonClientException e) {
            throw new UploadException(e);
        }
    }

    @Override
    public void delete(URI targetURI) {
        try {
            s3Client.deleteObject(BUCKET_NAME, targetURI.toString());
        } catch (AmazonClientException e) {
            throw new UploadException(e);
        }
    }
}
