/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.system.upload;

import com.geomenum.common.domainmodel.system.ImageMediaType;
import com.geomenum.core.domainmodel.system.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

abstract class AbstractUploadCoreService implements UploadCoreService {

    /**
     * Parses the image media type of the given image.
     *
     * @param image an {@link Image}
     * @return the {@link ImageMediaType} of the given image
     * @throws UploadException if no image media type could be parsed
     */
    protected ImageMediaType getImageMediaType(Image image) {
        try {
            return ImageMediaType.parse(image.getMediaType().toString());
        } catch (IllegalArgumentException e) {
            throw new UploadException("Unsupported image media type : " + image.getMediaType(), e);
        }
    }

    /**
     * Computes the content of the given image as an array of bytes and returns it. As the resulting array is meant
     * to be saved as a JPEG file, this method will also take care of any needed conversions.
     *
     * @param image the image to get the content from
     * @return a byte array
     * @throws UploadException if anything went wrong during the conversion process for example
     */
    protected byte[] getImageContentConvertedToJPEG(Image image) {
        ImageMediaType imageMediaType = getImageMediaType(image);
        switch (imageMediaType) {
            case JPEG:
                return image.getBytes();
            case BMP:
            case PNG:
            case GIF:
                try (
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        ByteArrayInputStream inputStream = new ByteArrayInputStream(image.getBytes())
                ) {
                    BufferedImage bufferedImage = ImageIO.read(inputStream);
                    if (bufferedImage.getTransparency() == Transparency.OPAQUE) {
                        ImageIO.write(bufferedImage, "jpeg", outputStream);
                    } else {
                        BufferedImage bufferedImageRGB = new BufferedImage(
                                bufferedImage.getWidth(),
                                bufferedImage.getHeight(),
                                BufferedImage.TYPE_INT_RGB);
                        bufferedImageRGB
                                .createGraphics()
                                .drawImage(bufferedImage, 0, 0, bufferedImageRGB.getWidth(), bufferedImageRGB.getHeight(), Color.WHITE, null);
                        ImageIO.write(bufferedImageRGB, "jpeg", outputStream);
                    }
                    return outputStream.toByteArray();
                } catch (IOException e) {
                    throw new UploadException(e);
                }
            default:
                throw new UploadException("Couldn't match given image media type to any conversion algorithm : " + imageMediaType);
        }
    }
}
