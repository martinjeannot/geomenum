/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.converters;

import com.google.common.collect.Lists;
import org.bson.types.ObjectId;
import org.testng.annotations.Test;

import java.util.List;

import static com.geomenum.config.TestingLevels.UNIT;
import static org.testng.Assert.assertEquals;

@Test(groups = {UNIT})
public class StringToPersistenceIdConverterTest {

    public void convert() {
        assertEquals(StringToPersistenceIdConverter.convert("JhLy5b2L2cckN6R7C4moIS5Z05QyTZrBCaWUm_KLOM4"), new ObjectId("529e809144ae063bcc329d01"));
    }

    public void convertList() {
        List<ObjectId> expected = Lists.newArrayList(
                new ObjectId("529e809144ae063bcc329d01"),
                new ObjectId("5297975b44ae5bcd328da750"),
                new ObjectId("5297816844aed8b84a11cadf"));

        List<ObjectId> actual = StringToPersistenceIdConverter.convert(
                Lists.newArrayList(
                        "JhLy5b2L2cckN6R7C4moIS5Z05QyTZrBCaWUm_KLOM4",
                        "ZmwByn39mWJrmMK0WWxpfptjvtg6KiKoYyLLbdjo3FQ",
                        "4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY"));

        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void convertWithNull() {
        StringToPersistenceIdConverter.convert((String) null);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void convertWithEmptyString() {
        StringToPersistenceIdConverter.convert("");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void convertWithWrongLength() {
        StringToPersistenceIdConverter.convert("jQDCiDk2lUtyX31UoasrB9GwxOXhD85f04yza64ccL");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void convertWithNoBase64Encoding() {
        StringToPersistenceIdConverter.convert("jQDCiDk2lUtyX31UoasrB9GwxOXhD85f04yza64ccL\0");
    }
}
