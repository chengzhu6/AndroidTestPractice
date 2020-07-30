package com.thoughtworks.androidtestpractice.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class EncryptUtil {
    private static final char[] hexCode = "0123456789ABCDEF".toCharArray();

    public static String getMD5(String originalString) {
        MessageDigest md;
        String result = originalString;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(originalString.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            result = printHexBinary(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result.toLowerCase();
    }

    private static String printHexBinary(byte[] data) {
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }
}
