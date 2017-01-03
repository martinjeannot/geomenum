/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services;

import com.geomenum.common.integration.Mappable;

/**
 * Generic core service.
 *
 * @param <T> the type of the domain object
 */
public interface GenericCoreService<T extends Mappable> {

    /**
     * Creates a new domain object which must not be already present in DB.
     *
     * @param domainObject the domain object to create
     * @return the created domain object
     */
    T create(T domainObject);

    /**
     * Retrieves the domain object with the given id if found, returns {@code null} otherwise.
     *
     * @param id the id of the domain object to find
     * @return the found domain object or {@code null}
     */
    T findById(String id);

    /**
     * Updates an existing domain object.
     *
     * @param domainObject the domain object to update
     * @return the updated domain object
     */
    T update(T domainObject);

    /**
     * Deletes an existing domain object with optional cascade deletion.
     *
     * @param id the id of the domain object to delete
     * @param cascadeDelete {@code true} to cascade the deletion, {@code false} otherwise
     * @return the deleted domain object
     */
    T delete(String id, boolean cascadeDelete);
}
