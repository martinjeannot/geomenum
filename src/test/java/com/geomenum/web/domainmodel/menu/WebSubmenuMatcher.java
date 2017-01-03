/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.menu;

import com.geomenum.web.controllers.menu.SubmenuQueryWebController;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class WebSubmenuMatcher extends BaseMatcher<WebSubmenu> {

    private WebSubmenu expectedSubmenu;

    public WebSubmenuMatcher(WebSubmenu expectedSubmenu) {
        this.expectedSubmenu = expectedSubmenu;
    }

    @Override
    public boolean matches(Object o) {
        if(o instanceof WebSubmenu) {
            WebSubmenu actualSubmenu = (WebSubmenu) o;
            if(expectedSubmenu.getId().equals(actualSubmenu.getId())
                    && expectedSubmenu.getLocalizedNames().equals(actualSubmenu.getLocalizedNames())
                    && expectedSubmenu.getEnabled().equals(actualSubmenu.getEnabled())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(SubmenuQueryWebController.submenuBackingBeanName + " : " + expectedSubmenu.toString());
    }
}
