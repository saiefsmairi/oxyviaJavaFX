/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mortadha
 */
public class DBconnect {
    private static Connection connection;
    public static Connection getConnect(){
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3308/dbpetit","root","");
        } catch (SQLException ex) {
            Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
}
