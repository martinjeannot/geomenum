/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel;

import com.geomenum.core.domainmodel.system.CoreUserFixtures;
import com.geomenum.persistence.services.util.PersistenceIdGenerator;
import com.google.common.collect.Lists;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.testng.annotations.Test;

import java.util.Locale;

import static com.geomenum.config.TestingLevels.UNIT;
import static org.mockito.Mockito.when;

@Test(groups = {UNIT})
public abstract class AbstractCoreDomainModelUnitTests {

    protected static final MessageSource MESSAGE_SOURCE;

    protected static final PersistenceIdGenerator PERSISTENCE_ID_GENERATOR;

    static {
        MESSAGE_SOURCE = Mockito.mock(MessageSource.class);
        PERSISTENCE_ID_GENERATOR = Mockito.mock(PersistenceIdGenerator.class);

        for(Locale language : Lists.newArrayList(Locale.ENGLISH, Locale.FRENCH)) {
            when(MESSAGE_SOURCE.getMessage("menuItem.defaultLocalizedName", null, language))
                    .thenReturn("menuItem.defaultLocalizedName");
            when(MESSAGE_SOURCE.getMessage("menuItem.defaultLocalizedDescription", null, language))
                    .thenReturn("menuItem.defaultLocalizedDescription");

            when(MESSAGE_SOURCE.getMessage("submenu.rootName", null, language))
                    .thenReturn("submenu.rootName");
            when(MESSAGE_SOURCE.getMessage("submenu.defaultLocalizedName", null, language))
                    .thenReturn("submenu.defaultLocalizedName");

            when(MESSAGE_SOURCE.getMessage("menu.defaultWelcomeMessage", null, language))
                    .thenReturn("menu.defaultWelcomeMessage");
        }

        when(PERSISTENCE_ID_GENERATOR.generate()).thenReturn(CoreUserFixtures.ID); //  any persistence ID
    }
}
