/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.Service;

import Oxyvia.utils.BDConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author smp
 */
public class comptePersonnel_service {
      Connection conx = BDConnector.driverBD();
       public ArrayList<String> ListerID() {
 ArrayList<String> res = new ArrayList<String>();
        try {
            Statement stmt = conx.createStatement();
            String sql = "SELECT * FROM compte_personnel";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                
                String name = rs.getString("email");
                
                
                res.add(name);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;    
    }
}
