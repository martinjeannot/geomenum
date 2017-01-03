/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services;

import com.geomenum.common.integration.Mappable;
import com.geomenum.common.validation.constraintvalidators.PersistenceIdValidator;
import com.geomenum.core.domainmodel.bi.TimeTrackable;
import com.geomenum.persistence.servicefacades.GenericPersistenceServiceFacade;

import java.util.Objects;

/**
 * {@link GenericCoreService} abstract default implementation.
 */
public abstract class DefaultGenericCoreService<T extends Mappable> implements GenericCoreService<T> {

    /**
     * Returns the {@link GenericPersistenceServiceFacade} related to domain objects of type T.
     *
     * @return a {@link GenericPersistenceServiceFacade}
     */
    protected abstract GenericPersistenceServiceFacade<T> getPersistenceService();

    protected static final PersistenceIdValidator PERSISTENCE_ID_VALIDATOR = new PersistenceIdValidator();

    @Override
    public T create(T domainObject) {
        Objects.requireNonNull(domainObject, "Cannot create a null object");
        if (domainObject instanceof TimeTrackable) { // set the creation date
            ((TimeTrackable) domainObject).setCreationDateToNow();
        }
        return getPersistenceService().create(domainObject);
    }

    @Override
    public T findById(String id) {
        if (!PERSISTENCE_ID_VALIDATOR.isValid(id, null)) {
            throw new IllegalArgumentException("Invalid id : " + id);
        }
        return getPersistenceService().findById(id);
    }

    @Override
    public T update(T domainObject) {
        Objects.requireNonNull(domainObject, "Cannot update a null object");
        if (domainObject instanceof TimeTrackable) { // reset the last update date
            ((TimeTrackable) domainObject).setLastUpdateDateToNow();
        }
        return getPersistenceService().update(domainObject);
    }

    @Override
    public T delete(String id, boolean cascadeDelete) {
        if (cascadeDelete) {
            return this.cascadeDelete(id);
        } else {
            return delete(id);
        }
    }

    /**
     * Deletes the domain object with the given id.
     *
     * @param id the id of the domain object to delete
     * @return the deleted domain object
     */
    protected T delete(String id) {
        if(!PERSISTENCE_ID_VALIDATOR.isValid(id, null)) {
            throw new IllegalArgumentException("Invalid id : " + id);
        }
        T domainObject = getPersistenceService().delete(id);
        if(domainObject instanceof TimeTrackable) { // set the deletion date
            ((TimeTrackable) domainObject).setDeletionDateToNow();
        }
        return domainObject;
    }

    /**
     * Deleted the domain object with the given id and cascade the deletion.
     *
     * @param id the id of the domain object to delete
     * @return the deleted domain object
     */
    protected T cascadeDelete(String id) {
        throw new UnsupportedOperationException("Cascade deletion is not supported by this domain object.");
    }
}
