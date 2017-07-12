package org.wang.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DatabaseUtil {


    private static String url = "";
    private static String user = "";
    private static String password = "";
    private static String driverClass = "";

    static {
        init();
    }
	public static ResultSet executeQuery(String sql) {
        try  {
            Class.forName(driverClass);
            Connection con = DriverManager.getConnection(url, user, password);
			// Thread.currentThread().sleep(1000);
            // Connection con = DriverManager.getConnection(url, user,
            // password);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			return rs;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

    private static void init() {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("jdbc.properties");
            Properties p = new Properties();
            p.load(inputStream);

            url = p.getProperty("jdbc.url");
            user = p.getProperty("jdbc.username");
            password = p.getProperty("jdbc.password");
            driverClass = p.getProperty("jdbc.driverClassName");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(url);
    }
}
