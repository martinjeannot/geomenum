/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.servicefacades;

import com.geomenum.common.integration.Mappable;

/**
 * Generic persistence service facade.<br/>
 * These facades make the bridge between the core domain and the persistence domain.
 *
 * @param <T> the type of the CORE domain object
 */
public interface GenericPersistenceServiceFacade<T extends Mappable> {

    T create(T entity);

    T findById(String id);

    T update(T entity);

    T delete(String id);
}
