/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.geomenum.core.datamodel.tree.menu.BranchMenuNode;
import com.geomenum.core.datamodel.tree.menu.LeafMenuNode;
import com.geomenum.core.datamodel.tree.menu.MenuNode;

import java.io.IOException;
import java.util.Map;

public class MenuNodeDeserializer extends JsonDeserializer<MenuNode> {

    @Override
    public MenuNode deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        Map<Object, Object> menuNodeDTO = jp.getCodec().readValue(jp, Map.class);
        if(menuNodeDTO.containsKey("children")) {
            return new BranchMenuNode(menuNodeDTO);
        } else {
            return new LeafMenuNode(menuNodeDTO);
        }
    }
}
