package com.wang.demo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WangTest {
    public static void main(String[] args) throws Exception {
//        testMap();
//        findCourseBug();
        System.out.println(System.getProperty("java.io.tmpdir"));
        File temp = File.createTempFile("temp-file-name", ".tmp");

        System.out.println("Temp file : " + temp.getAbsolutePath());
    }

    private static void findCourseBug() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\wang\\Desktop\\info.txt"));
        String line = null;
        while ((line = reader.readLine()) != null) {
            if (line.indexOf("\"course\":") < 0) {
                continue;
            }
            Pattern pattern = Pattern.compile("\"course\":(.+?),");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
//                System.out.println(line);
                String courseStr = matcher.group(1);
//                System.out.println("courseStr:" + courseStr);
                if (!courseStr.matches("\\d+\\.+\\d+")) {
                    System.out.println("not matches:" + courseStr);
                }
                try {
                    BigDecimal bigDecimal = new BigDecimal(courseStr);
                } catch (Exception e) {
                    System.out.println("error:" + courseStr);
                }
            }
//            break;
        }
    }

    private static void testMap() {
        String host = "http://10.0.0.1/helllo";
        int i1 = host.lastIndexOf("//");
        int i2 = host.indexOf("//");
        System.out.println(i1);
        System.out.println(i2);
        HashMap<Object, Object> m = new HashMap<>();
        m.computeIfAbsent("1", k -> "1");
        System.out.println(m);
        m.computeIfAbsent("1", k -> "2");
        System.out.println(m);
    }
}
