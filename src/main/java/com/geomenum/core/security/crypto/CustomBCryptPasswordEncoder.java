/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.security.crypto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.CharBuffer;
import java.security.SecureRandom;

/**
 * {@link CustomPasswordEncoder} implementation using Spring's {@link BCryptPasswordEncoder}.
 */
public class CustomBCryptPasswordEncoder extends BCryptPasswordEncoder implements CustomPasswordEncoder {

    public CustomBCryptPasswordEncoder() {
    }

    public CustomBCryptPasswordEncoder(int strength) {
        super(strength);
    }

    public CustomBCryptPasswordEncoder(int strength, SecureRandom random) {
        super(strength, random);
    }

    @Override
    public String encode(char[] rawPassword) {
        return super.encode(CharBuffer.wrap(rawPassword));
    }

    @Override
    public boolean matches(char[] rawPassword, String encodedPassword) {
        return super.matches(CharBuffer.wrap(rawPassword), encodedPassword);
    }
}
