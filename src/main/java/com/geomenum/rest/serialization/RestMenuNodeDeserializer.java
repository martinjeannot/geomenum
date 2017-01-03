/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.geomenum.rest.datamodel.tree.consolidatedmenu.BranchRestMenuNode;
import com.geomenum.rest.datamodel.tree.consolidatedmenu.LeafRestMenuNode;
import com.geomenum.rest.datamodel.tree.consolidatedmenu.RestMenuNode;

import java.io.IOException;
import java.util.Map;

public class RestMenuNodeDeserializer extends JsonDeserializer<RestMenuNode> {

    @Override
    public RestMenuNode deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Map<Object, Object> restMenuNodeDTO = jp.getCodec().readValue(jp, Map.class);
        if(restMenuNodeDTO.containsKey("children")) {
            return new BranchRestMenuNode(restMenuNodeDTO);
        } else {
            return new LeafRestMenuNode(restMenuNodeDTO);
        }
    }
}
