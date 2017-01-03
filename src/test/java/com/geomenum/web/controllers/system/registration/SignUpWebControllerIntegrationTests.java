/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.system.registration;

import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebControllerIntegrationTests;
import org.testng.annotations.BeforeClass;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SignUpWebControllerIntegrationTests extends AbstractWebControllerIntegrationTests {

    @BeforeClass
    public void setUpBeforeClass() {
        super.setUpBeforeClass();
        clearMenusCollection();
        clearRestaurantsCollection();
        clearUsersCollection();
    }

    public void showSignUpForm() throws Exception {
        mockMvc.perform(get(WebURLPath.SIGN_UP))
                //.andDo(print())
                .andExpect(status().isAlreadyReported()); // TODO finish this test
    }

    public void registerNewUser() throws Exception{
        mockMvc.perform(post(WebURLPath.SIGN_UP)
                .param("firstName", "Morgan")
                .param("lastName", "Sullivan")
                .param("username", "morgan.sullivan@anyvaliddea.com")
                .param("password", "passw0rd")
                .param("restaurantName", "Chez Sullivan")
                .param("restaurantCountryCode", "FR")
                .param("restaurantAddress", "29 rue du 8 mai 1945")
                .param("restaurantPostalCode", "92370")
                .param("restaurantCity", "Chaville")
                .param("latitude", "48.8064362")
                .param("longitude", "2.1890269000000444")
                .param("formattedAddress", "29 Rue du 8 Mai 1945, 92370 Chaville, France"))
                //.andDo(print())
                .andExpect(status().isFound());
    }
}
