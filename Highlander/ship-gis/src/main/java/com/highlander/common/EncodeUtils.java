package com.highlander.common;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class EncodeUtils {

    // private static Md5Hash md5hash = new Md5Hash();

    public static String md5(String credentials) {
        String hashAlgorithmName = "md5";
        int hashIterations = 1;
        Object obj = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
        String dest = obj.toString().substring(0, 15).toUpperCase();
        return dest;
    }

    public static void main(String[] args) {
        Md5Hash md5 = new Md5Hash("admin");
        System.out.println(md5.toHex().toUpperCase());
    }
}
