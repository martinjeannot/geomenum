/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.security.crypto;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Service interface for encoding passwords.
 *
 * The preferred implementation is {@link CustomBCryptPasswordEncoder}.
 *
 * This interface was thought to work with char[] instead of String which is believed to be a bit more secure. The
 * underlying implementation may still use Strings so it really exists to promote proper coding practices outside the
 * PasswordEncoder boundaries (the raw password might still be transformed into a String at some point).
 *
 * TODO : Jackson is currently obliterating this effort by serializing any char array into Strings...
 * see http://jackson.codehaus.org/1.9.9/javadoc/org/codehaus/jackson/map/ser/std/StdArraySerializers.CharArraySerializer.html
 */
public interface CustomPasswordEncoder extends PasswordEncoder {

    /**
     * Encode the raw password.
     * Generally, a good encoding algorithm applies a SHA-1 or greater hash combined with an 8-byte or greater randomly
     * generated salt.
     */
    String encode(char[] rawPassword);

    /**
     * Verify the encoded password obtained from storage matches the submitted raw password after it too is encoded.
     * Returns true if the passwords match, false if they do not.
     * The stored password itself is never decoded.
     *
     * @param rawPassword the raw password to encode and match
     * @param encodedPassword the encoded password from storage to compare with
     * @return true if the raw password, after encoding, matches the encoded password from storage
     */
    boolean matches(char[] rawPassword, String encodedPassword);
}
