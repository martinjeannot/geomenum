/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.controllers.menu;

import com.geomenum.rest.RestApiUri;
import com.geomenum.rest.controllers.AbstractRestController;
import com.geomenum.rest.domainmodel.menu.MenuResource;
import com.geomenum.rest.requestsresponses.menu.GetMenuRequest;
import com.geomenum.rest.requestsresponses.menu.GetMenuResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping(value = RestApiUri.MENU, method = RequestMethod.GET)
public class MenuQueryRestController extends AbstractRestController {

    private static final Logger logger = LoggerFactory.getLogger(MenuQueryRestController.class);

    @RequestMapping
    public ResponseEntity<?> getMenu(@PathVariable("restaurantId") String restaurantId,
                                     @PathVariable("menuId") String menuId,
                                     WebRequest webRequest) {
        GetMenuResponse response = createRequestDispatcher().getResponse(
                new GetMenuRequest(menuId),
                GetMenuResponse.class);

        if(response.hasExceptionOccurred()) {
            return getInternalServerErrorResponse();
        }

        if(!response.wasMenuFound()) {
            return getNotFoundResponse();
        }

        return new ResponseEntity<>(new MenuResource(response.getMenu(), getLocale(webRequest)), HttpStatus.OK);
    }
}
