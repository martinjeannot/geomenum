/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.domainmodel.restaurant;

import org.hibernate.validator.constraints.NotBlank;

public class RestLocation {

    @NotBlank
    private final String formattedAddress = null;

    //~ Object =========================================================================================================

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof RestLocation) {
            RestLocation location = (RestLocation) obj;
            if(this.formattedAddress.equals(location.formattedAddress)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + formattedAddress.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "[" + getClass().getName() + "] "
                + "address : " + formattedAddress;
    }

    //~ Getters & Setters ==============================================================================================

    public String getFormattedAddress() {
        return formattedAddress;
    }
}
