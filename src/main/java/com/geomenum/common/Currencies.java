/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * List all the currencies our application can deal with.
 */
public final class Currencies {

    public static final Set<Currency> CURRENCIES = Currency.getAvailableCurrencies();

    public static Collection<Currency> getCurrenciesSortedByName(final Locale lang) {
        if(lang == null) {
            return CURRENCIES;
        }

        List<Currency> currenciesSortedByName = Lists.newArrayList(CURRENCIES);
        Collections.sort(currenciesSortedByName, new Comparator<Currency>() {
            @Override
            public int compare(Currency o1, Currency o2) {
                return o1.getDisplayName(lang).compareTo(o2.getDisplayName(lang));
            }
        });

        return currenciesSortedByName;
    }
}
