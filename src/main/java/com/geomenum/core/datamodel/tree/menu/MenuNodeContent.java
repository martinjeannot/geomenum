/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.datamodel.tree.menu;

import com.geomenum.common.integration.Mappable;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * This interface must be implemented by any object willing to be incorporated into a {@link MenuNode}.
 */
public interface MenuNodeContent extends Mappable {

    UUID getId();

    Map<Locale, String> getLocalizedNames();

    /**
     * Add support to the given language.
     *
     * @param language the new language to support
     * @param messageSource messageSource
     */
    void addLanguageSupport(Locale language, MessageSource messageSource);

    /**
     * Remove support of the given language.
     *
     * @param language the language to remove the support of
     */
    void removeLanguageSupport(Locale language);
}
