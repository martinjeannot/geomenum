/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.advices;

import com.geomenum.r2d2.common.RequestDispatcherFactory;
import com.geomenum.web.controllers.AbstractWebController;
import com.geomenum.web.requestsresponses.ui.GetHeaderRequest;
import com.geomenum.web.requestsresponses.ui.GetHeaderResponse;
import com.geomenum.web.util.UIUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@ControllerAdvice(assignableTypes = {AbstractWebController.class})
public class AbstractWebControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(AbstractWebControllerAdvice.class);

    @Autowired
    private RequestDispatcherFactory requestDispatcherFactory;

    // todo : would be nice not to expose the servlet API here and just deal with the model or something
    @ModelAttribute(AbstractWebController.headerBeanName)
    private Map<String, List<Map<String, String>>> populateHeader(HttpSession session) {
        if(session.getAttribute(AbstractWebController.headerBeanName) == null) {
            GetHeaderResponse response = requestDispatcherFactory.createRequestDispatcher().getResponse(
                    new GetHeaderRequest(), GetHeaderResponse.class);
            if(!response.hasExceptionOccurred()) {
                session.setAttribute(AbstractWebController.headerBeanName, UIUtil.buildHeader(response));
            }
        }
        return (Map<String, List<Map<String, String>>>) session.getAttribute(AbstractWebController.headerBeanName);
    }
}
