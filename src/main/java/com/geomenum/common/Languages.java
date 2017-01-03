/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * List all the languages our application can deal with.
 */
public final class Languages {

    public static final Set<Locale> LANGUAGES;

    public static final Set<Locale> SUPPORTED_LANGUAGES = ImmutableSet.of(
            Locale.ENGLISH,
            Locale.FRENCH);

    public static Collection<Locale> getLanguagesSortedByName(Locale lang) {
        if(lang == null) {
            return LANGUAGES;
        }

        List<Locale> languagesSortedByName = Lists.newArrayList(LANGUAGES);
        sortByName(languagesSortedByName, lang);
        return languagesSortedByName;
    }

    public static Collection<Locale> getSupportedLanguagesSortedByName(Locale lang) {
        if(lang == null) {
            return SUPPORTED_LANGUAGES;
        }

        List<Locale> supportedLanguagesSortedByName = Lists.newArrayList(SUPPORTED_LANGUAGES);
        sortByName(supportedLanguagesSortedByName, lang);
        return supportedLanguagesSortedByName;
    }

    private static void sortByName(List<Locale> languages, final Locale lang) {
        Collections.sort(languages, new Comparator<Locale>() {
            @Override
            public int compare(Locale o1, Locale o2) {
                return o1.getDisplayLanguage(lang).compareTo(o2.getDisplayLanguage(lang));
            }
        });
    }

    //~ Languages Initialization =======================================================================================

    static {
        ImmutableSet.Builder<Locale> languageSetBuilder = ImmutableSet.builder();

        for(String languageCode : Locale.getISOLanguages()) {
            languageSetBuilder.add(Locale.forLanguageTag(languageCode));
        }

        LANGUAGES = languageSetBuilder.build();
    }
}
