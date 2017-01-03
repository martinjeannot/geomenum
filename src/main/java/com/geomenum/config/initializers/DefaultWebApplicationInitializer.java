/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config.initializers;

import com.geomenum.config.profiles.Production;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * Default {@link org.springframework.web.WebApplicationInitializer}
 */
@Order(2)
public class DefaultWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DefaultWebApplicationInitializer.class);

    private static final String uploadLocation = "/tmp";
    private static final int maxFileSize = 1024 * 1024 * 5; // 5 MB
    private static final int maxRequestSize = 1024 * 1024 * 5 * 5; // 25 MB
    private static final int fileSizeThreshold = 1024 * 1024; // 1 MB

    static {
        // production profile set as the default one
        System.setProperty("spring.profiles.default", Production.PROFILE_NAME);
    }

    /*
     * TODO this function should not exist, it is convenient right now but must be removed in the future.
     * Spring "profile" feature (+ annotation scanning) should be enough for our needs.
     */
    public static String getCurrentProfile() {
        String currentProfile = Production.PROFILE_NAME; // production profile is default
        if (System.getProperties().containsKey("spring.profiles.active")) {
            currentProfile = System.getProperty("spring.profiles.active");
        } else if(System.getProperties().containsKey("spring.profiles.default")) {
            currentProfile = System.getProperty("spring.profiles.default");
        }
        return currentProfile;
    }

    private void logProfiles() {
        logger.info("Spring default profiles : " + System.getProperty("spring.profiles.default"));
        logger.info("Spring active profiles : " + System.getProperty("spring.profiles.active"));
    }

    /*
     * Since the AnnotationConfigWebApplicationContext#scan(String...) method cannot be reached by any means from an
     * AbstractAnnotationConfigDispatcherServletInitializer, the only way to register configuration classes through
     * package scanning is to override this AbstractAnnotationConfigDispatcherServletInitializer#createRootApplicationContext()
     * method and to create the WebApplicationContext instance ourselves.
     */
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
        rootAppContext.scan("com.geomenum.config.rootcontext");

        // could be somewhere else
        logProfiles();

        return rootAppContext;
    }

    /*
     * Root configuration classes are registered through package scanning.
     * See DefaultWebApplicationInitializer#createRootApplicationContext()
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /*
     * Since the AnnotationConfigWebApplicationContext#scan(String...) method cannot be reached by any means from an
     * AbstractAnnotationConfigDispatcherServletInitializer, the only way to register configuration classes through
     * package scanning is to override this AbstractAnnotationConfigDispatcherServletInitializer#createServletApplicationContext()
     * method and to create the WebApplicationContext instance ourselves.
     */
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
        servletAppContext.scan("com.geomenum.config.servletcontext");
        return servletAppContext;
    }

    /*
     * Servlet configuration classes are registered through package scanning.
     * See DefaultWebApplicationInitializer#createServletApplicationContext()
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        this.createRootApplicationContext();
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                uploadLocation, maxFileSize, maxRequestSize, fileSizeThreshold);
        registration.setMultipartConfig(multipartConfigElement);
    }

    /*
     * This has been moved to the SecurityWebApplicationInitializer because the character encoding filter
     * must be first on the filter chain (otherwise the default Spring encoding, ISO-8859-1, is used).
     * If you have to disable security, uncomment the following line to keep UTF-8 encoding.
     */
    /*@Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] { characterEncodingFilter };
    }*/
}
