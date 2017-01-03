/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.converters;

import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;

import java.util.Currency;

public class CurrencyReadConverter implements Converter<DBObject, Currency> {

    @Override
    public Currency convert(DBObject source) {
        return Currency.getInstance((String) source.get("currencyCode"));
    }
}
