package com.highlander.controller;

import org.apache.commons.codec.binary.Base64;

import java.util.Calendar;
import java.util.Date;

public class MainTest {
    //    private String name;
    public static void main(String[] args) {
        testLat();

    }

    private static void testLat() {
        String s = "121° 49.402'\t30° 4.240'\n";

        double d = 0.49402 / 60 * 100 + 121;
        System.out.println(d);
    }


}
