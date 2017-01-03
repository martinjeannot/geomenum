/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.domainmodel;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.geomenum.common.integration.Mappable;
import com.geomenum.config.rootcontext.SpringApplicationContext;
import com.geomenum.config.rootcontext.StaticResourcesResolver;
import com.geomenum.rest.datamodel.tree.consolidatedmenu.RestMenuNode;
import com.geomenum.rest.serialization.RestMenuNodeDeserializer;
import com.geomenum.rest.serialization.URIDeserializer;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.net.URI;
import java.util.Map;
import java.util.Set;

public class RestDomainModelMapper {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    static {
        /*
         * Applying the robustness principle also known as Postel's law :
         * "Be conservative in what you send, be liberal in what you accept".
         */
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        SimpleModule restModule = new SimpleModule();

        restModule.addDeserializer(RestMenuNode.class, new RestMenuNodeDeserializer());

        restModule.addDeserializer(URI.class, new URIDeserializer(SpringApplicationContext.getBean(StaticResourcesResolver.class)));

        OBJECT_MAPPER.registerModule(restModule);
        OBJECT_MAPPER.registerModule(new JodaModule());
    }

    //==================================================================================================================

    /**
     * Given an appropriate {@link Map} of properties (i.e corresponding to the "mapped" output of the given type),
     * this static factory will return an instance of the given type holding the same values.<br/>
     * This factory will also validate the created object and throw an {@link IllegalArgumentException} in case
     * of an invalid object creation attempt.
     *
     * @param dto a {@link Map} of properties
     * @param clazz the class of the instance to create
     * @param <T> the type of the instance to create
     * @return a new instance of type T
     * @see #toMap(com.geomenum.common.integration.Mappable)
     */
    public static <T extends Mappable> T fromMap(Map<Object, Object> dto, Class<T> clazz) {
        T domainObject = OBJECT_MAPPER.convertValue(dto, clazz);
        Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validate(domainObject);
        if(constraintViolations.isEmpty()) {
            return domainObject;
        } else {
            StringBuilder message = new StringBuilder("[" + clazz.getName() + "] Validation failed :\n");
            for(ConstraintViolation<T> constraintViolation : constraintViolations) {
                message.append(constraintViolation.getPropertyPath().toString())
                        .append(" : ").append(constraintViolation.getMessage()).append("\n");
            }
            throw new ValidationException(message.toString());
        }
    }

    /**
     * Returns a {@link Map} of the given object's properties.
     *
     * @param object the object to map
     * @return a {@link Map}
     * @see #fromMap(java.util.Map, Class)
     */
    @SuppressWarnings("unchecked")
    public static Map<Object, Object> toMap(Mappable object) {
        return OBJECT_MAPPER.convertValue(object, Map.class);
    }
}
