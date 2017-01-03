/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.reservation;

import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebController;
import com.geomenum.web.controllers.WebController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@WebController
@RequestMapping(value = WebURLPath.RESERVATION, method = RequestMethod.GET)
public class ReservationQueryWebController extends AbstractWebController {

    @RequestMapping
    public String viewReservations() {
        return View.RESERVATION;
    }
}
