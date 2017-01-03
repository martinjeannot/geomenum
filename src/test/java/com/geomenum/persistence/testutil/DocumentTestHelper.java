/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.testutil;

import com.mongodb.DBObject;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * MongoDB Document helper class for test.
 */
public class DocumentTestHelper {

    private DBObject document;

    private DocumentTestHelper() {}

    public static DocumentTestHelper documentTestHelper(DBObject document) {
        DocumentTestHelper documentTestHelper = new DocumentTestHelper();
        documentTestHelper.document = document;
        return documentTestHelper;
    }

    // ASSERTIONS

    public void assertHasField(String field) {
        assertTrue(document.containsField(field), "Missing field " + field);
    }

    public void assertNumberOfProperties(int num) {
        assertEquals(document.keySet().size(), num);
    }
}
