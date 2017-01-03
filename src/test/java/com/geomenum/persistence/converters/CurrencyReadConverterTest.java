/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.converters;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Currency;

import static com.geomenum.config.TestingLevels.UNIT;
import static org.testng.Assert.assertEquals;

@Test(groups = {UNIT})
public class CurrencyReadConverterTest {

    private CurrencyReadConverter converter;

    @BeforeClass
    public void setUp() {
        converter = new CurrencyReadConverter();
    }

    public void convert() {
        DBObject object = new BasicDBObject("currencyCode", "USD");
        Currency convertedCurrency = converter.convert(object);

        assertEquals(convertedCurrency, Currency.getInstance("USD"));
        assertEquals(convertedCurrency.hashCode(), Currency.getInstance("USD").hashCode());
    }
}
