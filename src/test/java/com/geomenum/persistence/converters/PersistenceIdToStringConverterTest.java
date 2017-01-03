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
public class PersistenceIdToStringConverterTest {

    public void convert() {
        assertEquals(PersistenceIdToStringConverter.convert(new ObjectId("529e809144ae063bcc329d01")), "JhLy5b2L2cckN6R7C4moIS5Z05QyTZrBCaWUm_KLOM4");
    }

    public void convertList() {
        List<String> expected = Lists.newArrayList(
                "JhLy5b2L2cckN6R7C4moIS5Z05QyTZrBCaWUm_KLOM4",
                "ZmwByn39mWJrmMK0WWxpfptjvtg6KiKoYyLLbdjo3FQ",
                "4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY");

        List<String> actual = PersistenceIdToStringConverter.convert(
                Lists.newArrayList(
                        new ObjectId("529e809144ae063bcc329d01"),
                        new ObjectId("5297975b44ae5bcd328da750"),
                        new ObjectId("5297816844aed8b84a11cadf")));

        assertEquals(actual, expected);
    }

    public void toto() {
        System.out.print(PersistenceIdToStringConverter.convert(new ObjectId("507f191e810c19729de860ea")));
    }
}
