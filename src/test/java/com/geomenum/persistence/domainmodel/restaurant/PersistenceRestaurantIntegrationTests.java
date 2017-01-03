/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.domainmodel.restaurant;

import com.geomenum.persistence.DBCollection;
import com.geomenum.persistence.domainmodel.AbstractPersistenceDomainModelIntegrationTests;
import com.geomenum.persistence.testutil.CollectionTestHelper;
import com.geomenum.persistence.testutil.DocumentTestHelper;
import org.testng.annotations.BeforeMethod;

import static com.geomenum.persistence.testutil.CollectionTestHelper.collectionTestHelper;
import static org.testng.Assert.assertEquals;

public class PersistenceRestaurantIntegrationTests extends AbstractPersistenceDomainModelIntegrationTests {

    @BeforeMethod
    public void setUp() {
        clearRestaurantCollection();
    }

    // FIXME when Spring Data MongoDB have full 2DSphere index support
    /*@Test(expectedExceptions = {NumberFormatException.class},
            expectedExceptionsMessageRegExp = "For input string: \"2dsphere\"")*/
    public void indexation() {
        insertStandardRestaurant();

        assertEquals(getRestaurantCollectionSize(), 1);
        CollectionTestHelper collectionTestHelper = collectionTestHelper(getMongoOperations(), DBCollection.RESTAURANTS);
        collectionTestHelper.assertHasIndexOn("_id");
        collectionTestHelper.assertHasIndexOn("menuId");
        collectionTestHelper.assertHasIndexOn("geolocation");
        collectionTestHelper.assertSingleIndexIsGeo("geolocation");
    }

    public void mapping() {
        insertStandardRestaurant();

        assertEquals(getRestaurantCollectionSize(), 1);
        DocumentTestHelper documentTestHelper = DocumentTestHelper.documentTestHelper(getMongoOperations().getCollection(DBCollection.RESTAURANTS).findOne());
        int propertyCount = 0;
        documentTestHelper.assertHasField("_id"); propertyCount++;
        documentTestHelper.assertHasField("menuId"); propertyCount++;
        documentTestHelper.assertHasField("geolocation"); propertyCount++;
        documentTestHelper.assertHasField("name"); propertyCount++;
        documentTestHelper.assertHasField("enabled"); propertyCount++;
        documentTestHelper.assertHasField("location"); propertyCount++;
        documentTestHelper.assertHasField("supportedLanguages"); propertyCount++;
        documentTestHelper.assertHasField("currency"); propertyCount++;
        documentTestHelper.assertHasField("localizedDescriptions"); propertyCount++;
        documentTestHelper.assertHasField("cuisine"); propertyCount++;
        documentTestHelper.assertHasField("creationDate"); propertyCount++;

        // + 1 for Spring Data DefaultMongoTypeMapper.DEFAULT_TYPE_KEY
        documentTestHelper.assertNumberOfProperties(++propertyCount);
    }
}
