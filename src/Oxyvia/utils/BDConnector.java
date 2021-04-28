/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.utils;
import Oxyvia.entities.Depense;
import Oxyvia.entities.Facture;
  import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author smp
 */
public class BDConnector {
   
	static Connection conx = null;
	static ResultSet rs = null;
	static Statement stmt = null;

	public static void main(String[] args) {
		conx = driverBD();
	}

	public static Connection driverBD() {
		try {

			Class.forName("com.mysql.jdbc.Driver");   //protocole appel le packetge du driver du jdbc mysql
			String url = "jdbc:mysql://localhost:3306/pidev";  //nom base.port.jdbc driver
			Connection conx = DriverManager.getConnection(url, "root", "");
			return conx;
		} catch (Exception e) {
			System.out.println("Erreur lors du chargement du pilote :" + e.getMessage());
			return null;

		}
	}
        
        
        
        
        
        //pour recherche    
    public static ObservableList<Depense> getDataDepense(){
        Connection conx = driverBD();
        ObservableList<Depense> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conx.prepareStatement("select * from depense");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Depense((rs.getInt("id")),rs.getString("email"), rs.getString("picture"), rs.getString("occupation"), rs.getString("salaire"), rs.getString("horaire_reguliere"), rs.getString("horaire_sup"), rs.getString("exempte"), rs.getString("date_depense"), rs.getString("nom"), rs.getString("prenom"), rs.getBoolean("enabled"), rs.getString("color")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public static ObservableList<Facture> getDataFacture(){
        Connection conx = driverBD();
        ObservableList<Facture> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conx.prepareStatement("select * from facture");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Facture((rs.getInt("id")), rs.getString("identifiant"), rs.getString("nom_prenom"), rs.getString("montant"), rs.getDate("date_paiement"), rs.getString("devise"), rs.getString("moyen_paiement"), rs.getString("mode_paiement"), rs.getString("location"), rs.getString("pays"), rs.getBoolean("enabled"), rs.getString("color")));               
            }
        } catch (Exception e) {
        }
        return list;
    }

        
        
        
        
        
        
        
        
        
        
        
        
        
        
}

