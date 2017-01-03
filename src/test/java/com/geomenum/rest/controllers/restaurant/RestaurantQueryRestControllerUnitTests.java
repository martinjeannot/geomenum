/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.controllers.restaurant;

import com.geomenum.rest.controllers.AbstractRestControllerUnitTests;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class RestaurantQueryRestControllerUnitTests extends AbstractRestControllerUnitTests {

    private MockMvc mockMvc;

    @InjectMocks
    private RestaurantsQueryRestController restaurantsQueryRestController;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantsQueryRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    public void getRestaurantWithExactMatch() throws Exception {
        /*Mockito.<Map<?, ?>>when(syncMessaging.send(MessageBuilder.message("lat", "2", "lng", "38")))
                .thenReturn(Response.success((Mappable) null));*/

        mockMvc.perform(get("/menum/restaurants")
                    .param("lat", "2")
                    .param("lng", "38")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.toto").value(1));
    }
}
