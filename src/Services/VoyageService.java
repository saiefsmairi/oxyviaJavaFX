/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Hotel;
import Entities.Place;
import Entities.Voyage;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author PC-Saif
 */
public class VoyageService {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public ObservableList<Voyage> AfficherVoyages() {

        try {

            Statement st = MyConnection.getInstance().getCnx().createStatement();

            rs = st.executeQuery("SELECT * FROM voyage");

            ObservableList<Voyage> liste = FXCollections.observableArrayList();

            while (rs.next()) {
                Voyage voyage = new Voyage();
                voyage.setId(rs.getInt("id"));
                voyage.setNom(rs.getString("nom"));
                voyage.setDescription(rs.getString("Description"));
                voyage.setVille(rs.getString("ville"));
                voyage.setDate_debut(rs.getString("date_debut"));
                voyage.setDate_fin(rs.getString("date_fin"));
                voyage.setNb_personne(rs.getInt("nb_personne"));
                voyage.setPrix_personne(rs.getInt("prix_personne"));

                voyage.setIdHotel(rs.getInt("hotel_id"));

                liste.add(voyage);
            }
            return liste;
        } catch (SQLException e) {
            System.out.println("Erreur :" + e.getMessage());
            return null;
        }

    }

    public void ajouterVoyage(Voyage c, Place p) {

        try {

            String requete2 = "INSERT INTO place(nom,longitude,altitude) VALUES(?,?,?)";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2, Statement.RETURN_GENERATED_KEYS);
            pst2.setString(1, p.getNom());
            pst2.setString(2, p.getLongitude());
            pst2.setString(3, p.getAltitude());
            pst2.execute();

            ResultSet rs = pst2.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

            System.out.println("Inserted record's ID: " + generatedKey);

            String requete = "INSERT INTO voyage(hotel_id,nom,ville,description,date_debut,date_fin,prix_personne,nb_personne,image) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, c.getHotel().getId());
            pst.setString(2, c.getNom());
            pst.setString(3, c.getVille());
            pst.setString(4, c.getDescription());
            pst.setString(5, c.getDate_debut());
            pst.setString(6, c.getDate_fin());
            pst.setInt(7, c.getPrix_personne());
            pst.setInt(8, c.getNb_personne());
            pst.setString(9, "imagestillNULL");
            pst.execute();

            ResultSet rs1 = pst.getGeneratedKeys();
            int generatedKey2 = 0;
            if (rs1.next()) {
                generatedKey2 = rs1.getInt(1);
            }

            System.out.println("Inserted voyage record's ID: " + generatedKey2);

            String requete3 = "INSERT INTO voyage_place VALUES(?,?)";
            PreparedStatement pst3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            pst3.setInt(1, generatedKey);
            pst3.setInt(2, generatedKey2);

            pst3.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierVoyage(Voyage c, int id,Place p1) {
        Place p = new Place();
        int idplace = 0;

        try {
            String requete = "UPDATE voyage set nom=?,ville=?,description=?,date_debut=?,date_fin=?,prix_personne=?,nb_personne=?,hotel_id=? WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getVille());
            pst.setString(3, c.getDescription());
            pst.setString(4, c.getDate_debut());
            pst.setString(5, c.getDate_fin());
            pst.setInt(6, c.getPrix_personne());
            pst.setInt(7, c.getNb_personne());
            pst.setInt(8, c.getHotel().getId());

            pst.setInt(9, id);
            pst.executeUpdate();

            System.out.println("voy Modifiée" + c);

            String requete2 = "SELECT * from voyage_place where voyage_id=?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2);
            pst2.setInt(1, c.getId());
            pst2.executeQuery();

            ResultSet rs5 = pst2.executeQuery();
            while (rs5.next()) {
                idplace = rs5.getInt("place_id");
            }

            String requete3 = "SELECT * from place where id=?";
            PreparedStatement pst3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            pst3.setInt(1, idplace);
            pst3.executeQuery();

            ResultSet rs6 = pst3.executeQuery();
            while (rs6.next()) {
                p.setId(rs6.getInt("id"));
                p.setNom(rs6.getString("nom"));
                p.setAltitude(rs6.getString("altitude"));
                p.setLongitude(rs6.getString("longitude"));
            }

            System.out.println(" PLACE" + p);

            
            if(p1.getNom()!=null&&p1.getLongitude()!=null&&p1.getAltitude()!=null){
            String requete8 = "UPDATE place set nom=?,longitude=?,altitude=? WHERE id=?";
            PreparedStatement pst8 = MyConnection.getInstance().getCnx().prepareStatement(requete8);

            pst8.setString(1, p1.getNom());
            pst8.setString(2, p1.getLongitude());
            pst8.setString(3, p1.getAltitude());
            pst8.setInt(4, p.getId());
            pst8.executeUpdate();
}
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void supprimerVoyage(Integer r) {
        try {
            String requete = "delete from `voyage` where id=?";
            PreparedStatement ps;
            ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, r);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Hotel findHotelByID(int id) {
        Hotel c = new Hotel();

        try {
            String requete = "SELECT * from hotel WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeQuery();

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));

            }

            System.out.println("affichage hotel by id");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return c;
    }
}
