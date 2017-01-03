/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.services;

import com.geomenum.common.integration.Mappable;
import com.geomenum.persistence.converters.StringToPersistenceIdConverter;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Map;

/**
 * {@link GenericPersistenceService} abstract MongoDB implementation.
 */
public abstract class MongoGenericPersistenceService<T extends Mappable> implements GenericPersistenceService<T> {

    @Autowired
    protected MongoTemplate mongoTemplate;

    /**
     * Returns the {@link MongoRepository} related to entities of type T.
     *
     * @return a {@link MongoRepository}
     */
    protected abstract MongoRepository<T, ObjectId> getRepository();

    /**
     * Instantiates a persistence domain object of type T from the given DTO.
     *
     * @param dto the dto from which the object will be created
     * @return a new domain object of type T
     */
    protected abstract T getDomainObjectFromDTO(Map<Object, Object> dto);

    @Override
    public T create(Map<Object, Object> dto) {
        ObjectId id = StringToPersistenceIdConverter.convert((String) dto.get("id"));
        if(!getRepository().exists(id)) { // check that the domain object does not already exist
            // todo investigate mongoTemplate.insert and remove the id check since insert would throw an ex in such case
            T createdDomainObject = getRepository().save(getDomainObjectFromDTO(dto));
            return createdDomainObject;
        } else {
            throw new IllegalArgumentException("Cannot insert an existing object into DB (id already found)");
        }
    }

    @Override
    public T findById(ObjectId id) {
        return getRepository().findOne(id);
    }

    @Override
    public T update(Map<Object, Object> dto) {
        ObjectId id = StringToPersistenceIdConverter.convert((String) dto.get("id"));
        if(getRepository().exists(id)) { // check that the domain object already exists
            T updatedDomainObject = getRepository().save(getDomainObjectFromDTO(dto));
            return updatedDomainObject;
        } else {
            throw new IllegalArgumentException("Cannot update a non-existing object into DB (id not found)");
        }
    }

    @Override
    public T delete(ObjectId id) {
        T domainObjectToDelete = getRepository().findOne(id);
        if(domainObjectToDelete != null) { // check that the domain object already exists
            getRepository().delete(domainObjectToDelete);
            return domainObjectToDelete;
        } else {
            throw new IllegalArgumentException("Cannot delete a non-existing object into DB (object not found)");
        }
    }
}
