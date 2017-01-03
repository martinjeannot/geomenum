/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.testutil;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.IndexInfo;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * MongoDB Collection helper class for test.
 */
public class CollectionTestHelper {

    private MongoOperations mongoOps;
    private String collection;

    private CollectionTestHelper() {}

    public static CollectionTestHelper collectionTestHelper(MongoOperations mongoOps, String collection) {
        CollectionTestHelper collectionTestHelper = new CollectionTestHelper();
        collectionTestHelper.mongoOps = mongoOps;
        collectionTestHelper.collection = collection;
        return collectionTestHelper;
    }

    // ASSERTIONS

    public void assertHasIndexOn(String field) {
        assertTrue(hasIndexOn(field), "Missing index on " + field);
    }

    public void assertSingleIndexIsGeo(String field) {
        IndexInfo indexInfo = getIndexInfoForField(field);
        assertNotNull(indexInfo);
        assertTrue(indexInfo.getIndexFields().get(0).isGeo(), "Single index on [" + field + "] is not Geo.");
    }

    private boolean hasIndexOn(String... fields) {
        List<IndexInfo> indexes = mongoOps.indexOps(collection).getIndexInfo();
        for(IndexInfo info : indexes) {
            if (info.isIndexForFields(Arrays.asList(fields))) {
                return true;
            }
        }
        return false;
    }

    private IndexInfo getIndexInfoForField(String field) {
        for(IndexInfo indexInfo : mongoOps.indexOps(collection).getIndexInfo()) {
            if(indexInfo.isIndexForFields(Arrays.asList(field))) {
                return indexInfo;
            }
        }
        return null;
    }
}
