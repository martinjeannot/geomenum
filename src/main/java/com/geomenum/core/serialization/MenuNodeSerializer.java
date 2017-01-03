/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.geomenum.core.datamodel.tree.menu.MenuNode;

import java.io.IOException;

public class MenuNodeSerializer extends JsonSerializer<MenuNode> {

    @Override
    public void serialize(MenuNode value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeObject(value.toMap());
    }
}
