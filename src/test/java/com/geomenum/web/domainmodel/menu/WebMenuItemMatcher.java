/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.menu;

import com.geomenum.web.controllers.menu.MenuItemQueryWebController;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class WebMenuItemMatcher extends BaseMatcher<WebMenuItem> {

    private WebMenuItem expectedMenuItem;

    public WebMenuItemMatcher(WebMenuItem expectedMenuItem) {
        this.expectedMenuItem = expectedMenuItem;
    }

    @Override
    public boolean matches(Object o) {
        if(o instanceof WebMenuItem)  {
            WebMenuItem actualMenuItem = (WebMenuItem) o;
            if(expectedMenuItem.getId().equals(actualMenuItem.getId())
                    && expectedMenuItem.getLocalizedNames().equals(actualMenuItem.getLocalizedNames())
                    && expectedMenuItem.getEnabled().equals(actualMenuItem.getEnabled())
                    && expectedMenuItem.getAmount().equals(actualMenuItem.getAmount())
                    && expectedMenuItem.getCurrency().equals(actualMenuItem.getCurrency())
                    && expectedMenuItem.getLocalizedDescriptions().equals(actualMenuItem.getLocalizedDescriptions())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(MenuItemQueryWebController.menuItemBackingBeanName + " : " + expectedMenuItem.toString());
    }
}
