/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.datamodel.tree.consolidatedmenu;

import com.geomenum.common.integration.Mappable;

/**
 * This interface declares the behavior related to the construction of a rest menu (i.e a menu which purpose is
 * to be exported outside of the application).
 */
public interface RestMenuNodeContent extends Mappable {

    boolean getEnabled();
}
