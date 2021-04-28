/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.Service;

import Oxyvia.entities.Depense;
import Oxyvia.entities.Facture;
import Oxyvia.utils.BDConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author smp
 */
public class service_facture {
     Connection conx = BDConnector.driverBD();
   /*  public Facture recherche_facture(String identifiant) {
        Facture p = new Facture();
        try {
            PreparedStatement req = conx.prepareStatement("select * from facture where identifiant=?  ");
            req.setString(1, identifiant);
            ResultSet rs = req.executeQuery();
            rs.next();
            //p.setId(rs.getInt("id"));
            p.setIdentifiant(rs.getString("identifiant"));
            p.setMontant(rs.getString("montant"));
            p.setDevise(rs.getString("devise"));
            p.setDate_paiement(rs.getDate("date_paiement"));
            p.setMoyen_paiement(rs.getString("moyen_paiement"));
            p.setMode_paiement(rs.getString("mode_paiement"));
           // p.setType_cb(rs.getString("type_cb"));
           // p.setNcb(rs.getString("ncb"));
           // p.setCode_securite(rs.getString("code_securite"));
           // p.setDate_expiration(rs.getDate("date_expiration"));
           // p.setLocation(rs.getString("location"));
            p.setPays(rs.getString("pays"));
           // p.setEnabled(rs.getBoolean("enabled"));
            //p.setColor(rs.getString("color"));
         
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return p;
    }*/
    public ResultSet tri_facture() {
         
        try {
            PreparedStatement req = conx.prepareStatement("SELECT * FROM facture ORDER BY date_paiement");
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
}

    
    
     public ResultSet getall_facture() {
         
        try {
            PreparedStatement req = conx.prepareStatement("SELECT * FROM facture");
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
      return null;
    }


}
