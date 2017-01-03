/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config.rootcontext;

import com.geomenum.persistence.converters.CurrencyReadConverter;
import com.geomenum.persistence.converters.LocalTimeReadConverter;
import com.geomenum.persistence.converters.LocalTimeWriteConverter;
import com.google.common.collect.Lists;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

/**
 * Spring Data MongoDB configuration.
 */
@Configuration
@EnableMongoRepositories(basePackages = {"com.geomenum.persistence.repositories"})
public class MongoConfiguration extends AbstractMongoConfiguration {

    private static final String DATABASE_NAME = "geomenum";
    private static final String MAPPING_BASE_PACKAGE = "com.geomenum.persistence.domain";

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("localhost");
    }

    @Override
    protected String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public String getMappingBasePackage() {
        return MAPPING_BASE_PACKAGE;
    }

    @Override
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converters = Lists.newArrayList();
        converters.add(new CurrencyReadConverter());
        converters.add(new LocalTimeReadConverter());
        converters.add(new LocalTimeWriteConverter());
        return new CustomConversions(converters);
    }
}
