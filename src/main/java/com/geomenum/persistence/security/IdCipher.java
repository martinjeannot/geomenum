/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.security;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * This class contains the encryption/decryption logic of any id willing to exit the persistence layer.
 */
public class IdCipher {

    public static byte[] encrypt(byte[] bytes) {
        try {
            return cipher.doFinal(bytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] decrypt(byte[] bytes) {
        try {
            return decipher.doFinal(bytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    // If you touch anything below this point, you better know what you are doing...

    private static Cipher cipher;
    private static Cipher decipher;

    static {
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            decipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            Key key = generateKey();
            cipher.init(Cipher.ENCRYPT_MODE, key);
            decipher.init(Cipher.DECRYPT_MODE, key);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidKeySpecException e) {
            throw new Error("[FATAL ERROR] IdCipher INITIALIZATION FAILED !", e); // abort mission !
        }
    }

    private static Key generateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        List<String> keys;
        try {
            keys = Files.readAllLines(
                    Paths.get(IdCipher.class.getClassLoader().getResource("security/persistenceIdKeys.txt").getPath()),
                    Charset.defaultCharset());
        } catch (IOException e) {
            throw new Error("[FATAL ERROR] IdCipher INITIALIZATION FAILED !", e); // abort mission !
        }
        char[] password = keys.get(0).toCharArray();
        byte[] salt = keys.get(1).getBytes();
        int iterations = 10000;
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey tmp = factory.generateSecret(new PBEKeySpec(password, salt, iterations, 128));
        return new SecretKeySpec(tmp.getEncoded(), "AES");
    }
}
