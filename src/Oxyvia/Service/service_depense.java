/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.Service;

import Oxyvia.entities.Depense;
import Oxyvia.utils.BDConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author smp
 */
public class service_depense {
     Connection conx = BDConnector.driverBD();
    /* public Depense recherche_depense(String nom) {
        Depense p = new Depense();
        try {
            PreparedStatement req = conx.prepareStatement("select * from depense where nom=?  ");
            req.setString(1, nom);
            ResultSet rs = req.executeQuery();
            rs.next();
            //p.setId(rs.getInt("id"));
            p.setId_personnel_id(rs.getString("id_personnel_id"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setPicture(rs.getString("picture"));
            p.setOccupation(rs.getString("occupation"));
            p.setSalaire(rs.getString("salaire"));
            p.setHoraire_reguliere(rs.getString("horaire_reguliere"));
            p.setHoraire_sup(rs.getString("horaire_sup"));
            p.setExempte(rs.getString("exempte"));
            p.setDate_depense(rs.getString("date_depense"));
            //p.setEnabled(rs.getBoolean("enabled"));
            //p.setColor(rs.getString("color"));
         
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return p;
    }*/
    public ResultSet tri_depense() {
         
        try {
            PreparedStatement req = conx.prepareStatement("SELECT * FROM depense ORDER BY date_depense");
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
}
    
   /* public ResultSet unpaid_depense() {
         
        try {
            PreparedStatement req = conx.prepareStatement("SELECT d.* FROM depense d where d.date_depense >= CURRENT_DATE()");
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
}*/
  /*  public ResultSet paid_depense() {
         //f.date_paiement >= CURRENT_DATE()
         //ORDER BY date_depense
        try {
            PreparedStatement req = conx.prepareStatement("SELECT d.* FROM depense d where d.date_depense <= CURRENT_DATE()");
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
}*/
    public ResultSet getall_depense() {
         
        try {
            PreparedStatement req = conx.prepareStatement("SELECT * FROM depense");
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
      return null;
    }
}
