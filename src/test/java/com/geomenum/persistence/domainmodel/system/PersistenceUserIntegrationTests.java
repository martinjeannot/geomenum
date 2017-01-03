/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.domainmodel.system;

import com.geomenum.persistence.DBCollection;
import com.geomenum.persistence.domainmodel.AbstractPersistenceDomainModelIntegrationTests;
import com.geomenum.persistence.testutil.CollectionTestHelper;
import com.geomenum.persistence.testutil.DocumentTestHelper;
import org.testng.annotations.BeforeMethod;

import static com.geomenum.persistence.testutil.CollectionTestHelper.collectionTestHelper;
import static org.testng.Assert.assertEquals;

public class PersistenceUserIntegrationTests extends AbstractPersistenceDomainModelIntegrationTests {

    @BeforeMethod
    public void setUp() {
        clearUserCollection();
    }

    public void indexation() {
        insertStandardUser();

        assertEquals(getUserCollectionSize(), 1);
        CollectionTestHelper collectionTestHelper = collectionTestHelper(getMongoOperations(), DBCollection.USERS);
        collectionTestHelper.assertHasIndexOn("_id");
        collectionTestHelper.assertHasIndexOn("restaurantId");
    }

    public void mapping() {
        insertStandardUser();

        assertEquals(getUserCollectionSize(), 1);
        DocumentTestHelper documentTestHelper = DocumentTestHelper.documentTestHelper(getMongoOperations().getCollection(DBCollection.USERS).findOne());
        int propertyCount = 0;
        documentTestHelper.assertHasField("_id"); propertyCount++;
        documentTestHelper.assertHasField("restaurantId"); propertyCount++;
        documentTestHelper.assertHasField("username"); propertyCount++;
        documentTestHelper.assertHasField("password"); propertyCount++;
        documentTestHelper.assertHasField("authorities"); propertyCount++;
        documentTestHelper.assertHasField("accountNonExpired"); propertyCount++;
        documentTestHelper.assertHasField("accountNonLocked"); propertyCount++;
        documentTestHelper.assertHasField("credentialsNonExpired"); propertyCount++;
        documentTestHelper.assertHasField("enabled"); propertyCount++;
        documentTestHelper.assertHasField("firstName"); propertyCount++;
        documentTestHelper.assertHasField("lastName"); propertyCount++;
        documentTestHelper.assertHasField("language"); propertyCount++;
        documentTestHelper.assertHasField("creationDate"); propertyCount++;

        // + 1 for Spring Data DefaultMongoTypeMapper.DEFAULT_TYPE_KEY
        documentTestHelper.assertNumberOfProperties(++propertyCount);
    }
}
