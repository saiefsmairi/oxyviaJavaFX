/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utulitaire;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author USER
 */
public class Singleton {
     private static String url = "jdbc:mysql://localhost:3306/pidev";
    private static String user = "root";
    private static String passwd = "";
    private static Connection conn;

    static {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, passwd);
                System.out.println("connection etablie");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConn() {
        return conn;
    }
}
