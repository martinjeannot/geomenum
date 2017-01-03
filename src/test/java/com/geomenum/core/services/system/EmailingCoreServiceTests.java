/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.system;

import com.geomenum.config.WebSecurityTestConfiguration;
import com.geomenum.core.domainmodel.system.CoreUser;
import com.geomenum.core.domainmodel.system.CoreUserFixtures;
import com.geomenum.core.domainmodel.system.Email;
import com.geomenum.core.services.AbstractCoreServiceIntegrationTests;
import com.geomenum.core.services.system.mail.EmailingCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

@ContextConfiguration(classes = {WebSecurityTestConfiguration.class})
public class EmailingCoreServiceTests extends AbstractCoreServiceIntegrationTests {

    @Autowired
    private EmailingCoreService emailingCoreService;

    public void sendEmail() {
        Map<Object, Object> coreUserDTO = CoreUserFixtures.standardUser().toMap();
        coreUserDTO.put("username", "toto@pwet.com");
        CoreUser mwa = CoreUser.of(coreUserDTO);
        Email testMail = new Email(mwa);
        testMail.setSubject("test subject");
        testMail.setBody("test body");
        emailingCoreService.sendEmail(testMail);
    }
}
