/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.domainmodel.menu;

import com.geomenum.persistence.DBCollection;
import com.geomenum.persistence.domainmodel.AbstractPersistenceDomainModelIntegrationTests;
import com.geomenum.persistence.testutil.CollectionTestHelper;
import com.geomenum.persistence.testutil.DocumentTestHelper;
import org.testng.annotations.BeforeMethod;

import static com.geomenum.persistence.testutil.CollectionTestHelper.collectionTestHelper;
import static org.testng.Assert.assertEquals;

public class PersistenceMenuIntegrationTests extends AbstractPersistenceDomainModelIntegrationTests {

    @BeforeMethod
    public void setUp() {
        clearMenuCollection();
    }

    public void indexation() {
        insertStandardMenu();

        assertEquals(getMenuCollectionSize(), 1);
        CollectionTestHelper collectionTestHelper = collectionTestHelper(getMongoOperations(), DBCollection.MENUS);
        collectionTestHelper.assertHasIndexOn("_id");
        collectionTestHelper.assertHasIndexOn("restaurantId");
    }

    public void mapping() {
        insertStandardMenu();

        assertEquals(getMenuCollectionSize(), 1);
        DocumentTestHelper documentTestHelper = DocumentTestHelper.documentTestHelper(getMongoOperations().getCollection(DBCollection.MENUS).findOne());
        int propertyCount = 0;
        documentTestHelper.assertHasField("_id"); propertyCount++;
        documentTestHelper.assertHasField("restaurantId"); propertyCount++;
        documentTestHelper.assertHasField("menuTree"); propertyCount++;
        documentTestHelper.assertHasField("enabled"); propertyCount++;
        documentTestHelper.assertHasField("supportedLanguages"); propertyCount++;
        documentTestHelper.assertHasField("currency"); propertyCount++;
        documentTestHelper.assertHasField("creationDate"); propertyCount++;

        // + 1 for Spring Data DefaultMongoTypeMapper.DEFAULT_TYPE_KEY
        documentTestHelper.assertNumberOfProperties(++propertyCount);
    }
}
