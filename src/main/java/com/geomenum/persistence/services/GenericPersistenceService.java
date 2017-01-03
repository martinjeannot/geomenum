/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.services;

import com.geomenum.common.integration.Mappable;
import org.bson.types.ObjectId;

import java.util.Map;

/**
 * Generic persistence service.
 *
 * @param <T> the type of the domain object
 */
public interface GenericPersistenceService<T extends Mappable> {

    /**
     * Inserts a new domain object which must not be already present in DB.
     *
     * @param dto the dto of the domain object to insert
     * @return the created domain object
     * @throws IllegalArgumentException if the domain object already exists or is invalid
     */
    T create(Map<Object, Object> dto);

    /**
     * Retrieves the domain object with the given id if found, returns {@code null} otherwise.
     *
     * @param id the id of the domain object to find
     * @return the found domain object or {@code null}
     */
    T findById(ObjectId id);

    /**
     * Updates an existing domain object.
     *
     * @param dto the dto of the domain object to update
     * @return the updated domain object
     * @throws IllegalArgumentException if the domain object does not already exist or is invalid
     */
    T update(Map<Object, Object> dto);


    /**
     * Deletes an existing domain object.
     *
     * @param id the id of the domain object delete
     * @return the deleted domain object
     * @throws IllegalArgumentException if the domain object does not already exist
     */
    T delete(ObjectId id);
}
