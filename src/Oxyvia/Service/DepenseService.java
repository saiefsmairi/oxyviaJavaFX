/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.Service;

import Oxyvia.entities.Depense;
import Oxyvia.interfaces.IService;
import Oxyvia.utils.BDConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author smp
 */

public class DepenseService implements IService<Depense> {

    Connection conx = BDConnector.driverBD();
    
    @Override
    public void Ajouter(Depense d) {
        try {
            PreparedStatement preparedStmt = conx.prepareStatement("insert into depense (id_personnel_id,picture,occupation,salaire,horaire_reguliere,horaire_sup,exempte,date_depense,nom,prenom,enabled,color) values (?,?,?,?,?,?,?,?,?,?,?,?)");

     
            preparedStmt.setString(1, d.getId_personnel_id());
            preparedStmt.setString(2, d.getPicture());
            preparedStmt.setString(3, d.getOccupation());
            preparedStmt.setString(4, d.getSalaire());
            preparedStmt.setString(5, d.getHoraire_reguliere());
            preparedStmt.setString(6, d.getHoraire_sup());
            preparedStmt.setString(7, d.getExempte());
            preparedStmt.setString(8, d.getDate_depense());
            preparedStmt.setString(9, d.getNom());
            preparedStmt.setString(10, d.getPrenom());
            preparedStmt.setBoolean(11, d.isEnabled());
            preparedStmt.setString(12, d.getColor());
    
            preparedStmt.execute();
            System.out.println("Insertion Avec Succes");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void Supprimer(Depense d) {
        try {
            PreparedStatement preparedStmt = conx.prepareStatement(" delete from depense where id= ?");
            preparedStmt.setInt(1, d.getId());
            preparedStmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(Depense d) {
        try {
            PreparedStatement preparedStmt = conx.prepareStatement("update depense set  id_personnel_id=? ,picture=?,occupation=? ,salaire=? ,horaire_reguliere=? ,horaire_sup=? ,exempte=? ,date_depense=? ,nom=? ,prenom=? ,enabled=? ,color=?   where id=?");
            preparedStmt.setString(1, d.getId_personnel_id());
            preparedStmt.setString(2, d.getPicture());
            preparedStmt.setString(3, d.getOccupation());
            preparedStmt.setString(4, d.getSalaire());
            preparedStmt.setString(5, d.getHoraire_reguliere());
            preparedStmt.setString(6, d.getHoraire_sup());
            preparedStmt.setString(7, d.getExempte());
            preparedStmt.setString(8, d.getDate_depense());
            preparedStmt.setString(9, d.getNom());
            preparedStmt.setString(10, d.getPrenom());
            preparedStmt.setBoolean(11, d.isEnabled());
            preparedStmt.setString(12, d.getColor());
    
            preparedStmt.execute();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    

    @Override
    public ArrayList<Depense> Lister() {
 ArrayList<Depense> res = new ArrayList<Depense>();
        try {
            Statement stmt = conx.createStatement();
            String sql = "SELECT * FROM depense";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String id_personnel_id = rs.getString("id_personnel_id");
                String picture = rs.getString("picture");
                String occupation = rs.getString("occupation");
                String salaire = rs.getString("salaire");
                String horaire_reguliere = rs.getString("horaire_reguliere");
                String horaire_sup = rs.getString("horaire_sup");
                String exempte = rs.getString("exempte");
                String date_depense = rs.getString("date_depense");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                boolean enabled = rs.getBoolean("enabled");
                String color = rs.getString("color");
               
                
//                Hotel h=new Hotel (idh);
//                System.out.println(h.getId());
                Depense D = new Depense (id,id_personnel_id,picture,occupation,salaire,horaire_reguliere,horaire_sup,exempte,date_depense,nom,prenom,enabled,color);
                res.add(D);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;    
    }

    public DepenseService() {
    }
}


