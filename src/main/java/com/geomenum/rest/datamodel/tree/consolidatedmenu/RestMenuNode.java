/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.datamodel.tree.consolidatedmenu;

import com.geomenum.common.datamodel.tree.generic.Node;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;
import java.util.Locale;

/**
 * {@link com.geomenum.rest.domainmodel.menu.RestMenu} related {@link Node} interface.
 */
public interface RestMenuNode extends Node<RestMenuNodeContent> {

    List<RestMenuNode> getChildrenAsRestMenuNode();

    ResourceSupport getResource(Locale locale);
}
