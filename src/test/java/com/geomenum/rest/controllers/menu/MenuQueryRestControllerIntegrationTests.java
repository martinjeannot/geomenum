/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.controllers.menu;

import com.geomenum.core.domainmodel.menu.CoreMenuFixtures;
import com.geomenum.core.domainmodel.restaurant.CoreRestaurantFixtures;
import com.geomenum.rest.api.RestApiUriTestUtil;
import com.geomenum.rest.controllers.AbstractRestControllerIntegrationTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuQueryRestControllerIntegrationTests extends AbstractRestControllerIntegrationTests {

    @BeforeClass
    public void setUpBeforeClass() {
        super.setUpBeforeClass();
    }

    @AfterClass
    public void tearDownAfterClass() {
        clearMenusCollection();
        //clearRestaurantCollection();
    }

    @BeforeMethod
    public void setUpBeforeMethod() {
        clearMenusCollection();
    }

    public void getMenu() throws Exception {
        insertStandardMenu();

        mockMvc.perform(get(RestApiUriTestUtil.getMenuURI(CoreRestaurantFixtures.ID, CoreMenuFixtures.ID)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
