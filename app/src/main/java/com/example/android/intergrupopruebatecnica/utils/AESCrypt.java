package com.example.android.intergrupopruebatecnica.utils;

import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESCrypt {

    private static final String ALGORITHM = "AES";
    private static final byte[] salt =
            new byte[]{'Y', 'q', 'x', 't', 'e', 'q', 'p',
                    'X', 'C', 'u', 'R', 'l', 'P', 'H', 'g', 'l'};

    public static String encrypt(String message) throws Exception {
        byte[] encodedBytes = Base64.encode(message.getBytes(), Base64.DEFAULT);
        SecretKeySpec key = new SecretKeySpec(salt, ALGORITHM);
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(encodedBytes);
        String encrypted= Base64.encodeToString(encVal, Base64.DEFAULT);
        return encrypted;
    }

    public static String decrypt(String message) throws Exception {
        Cipher c = Cipher.getInstance(ALGORITHM);
        SecretKeySpec key = new SecretKeySpec(salt, ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.decode(message.getBytes(), Base64.DEFAULT);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue);
        String decoded=new String(Base64.decode(decryptedValue, Base64.DEFAULT));
        return decoded;
    }
}

