/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Hotel;
import Entities.Place;
import Entities.Reservation;
import Entities.Voyage;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;
import static java.util.concurrent.TimeUnit.DAYS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author PC-Saif
 */
public class ReservationService {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public void supprimerRes(Integer r) {
        try {
            String requete = "delete from `reservation` where id=?";
            PreparedStatement ps;
            ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, r);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void ajouterReservation(Reservation r) {
        int prixchambresingle = 0;
        int prixchambredouble = 0;

        System.out.println("res" + r);

        try {

            if (r.getVoyage() != null) {
                String requete1 = "SELECT * from chambre WHERE idhotel_id=? and type='single room' and occupe='non occupe'";
                PreparedStatement pst1 = MyConnection.getInstance().getCnx().prepareStatement(requete1);
                pst1.setInt(1, r.getVoyage().getIdHotel());

                pst1.executeQuery();

                ResultSet rs2 = pst1.executeQuery();
                while (rs2.next()) {
                    prixchambresingle = rs2.getInt("prix");

                }
                System.out.println(prixchambresingle);

                ///update room to to occupe
                String requete = "INSERT INTO reservation(client_id,hotel_id,voyage_id,date_debut,date_fin,prix,nb_adulte,type,nb_enfants,nb_chambre_single_reserve,nb_chambre_double_reserve,confirme,token)"
                        + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
                pst.setInt(1, 1);
                pst.setInt(2, r.getVoyage().getIdHotel());
                pst.setInt(3, r.getVoyage().getId());
                pst.setString(4, r.getDate_debut());
                pst.setString(5, r.getDate_fin());
                long noOfDaysBetween = ChronoUnit.DAYS.between(LocalDate.parse(r.getDate_debut()), LocalDate.parse(r.getDate_fin()));
                pst.setLong(6, noOfDaysBetween * r.getNb_adulte() * prixchambresingle + r.getVoyage().getPrix_personne());

                pst.setInt(7, r.getNb_adulte());
                pst.setString(8, "reservation voyage");
                pst.setInt(9, r.getNb_enfants());
                pst.setInt(10, r.getNb_adulte());//chaque pers a une single room
                pst.setInt(11, 0);
                pst.setString(12, "confirme");
                pst.setString(13, "");
                pst.execute();

                ResultSet rs1 = pst.getGeneratedKeys();
                int generatedKey2 = 0;
                if (rs1.next()) {
                    generatedKey2 = rs1.getInt(1);
                }

                System.out.println("Inserted res record's ID: " + generatedKey2);
            } else if (r.getHotel() != null) {

                String requete70 = "SELECT * from chambre WHERE idhotel_id=? and type='single room' and occupe='non occupe'";
                PreparedStatement pst70 = MyConnection.getInstance().getCnx().prepareStatement(requete70);
                pst70.setInt(1, r.getHotel().getId());

                pst70.executeQuery();

                ResultSet rs70 = pst70.executeQuery();
                while (rs70.next()) {
                    prixchambresingle = rs70.getInt("prix");

                }

                String requete71 = "SELECT * from chambre WHERE idhotel_id=? and type='double room' and occupe='non occupe'";
                PreparedStatement pst71 = MyConnection.getInstance().getCnx().prepareStatement(requete71);
                pst71.setInt(1, r.getHotel().getId());

                pst71.executeQuery();

                ResultSet rs71 = pst71.executeQuery();
                while (rs71.next()) {
                    prixchambredouble = rs71.getInt("prix");

                }

                String requete1 = "SELECT * from chambre WHERE idhotel_id=? and type='single room' and occupe='non occupe'";
                PreparedStatement pst1 = MyConnection.getInstance().getCnx().prepareStatement(requete1);
                pst1.setInt(1, r.getHotel().getId());

                pst1.executeQuery();

                ResultSet rs2 = pst1.executeQuery();
                while (rs2.next()) {
                    prixchambresingle = rs2.getInt("prix");

                }
                System.out.println(prixchambresingle);

                ///update room to to occupe
                String requete = "INSERT INTO reservation(client_id,hotel_id,date_debut,date_fin,prix,nb_adulte,type,nb_enfants,nb_chambre_single_reserve,nb_chambre_double_reserve,confirme,token)"
                        + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
                pst.setInt(1, 1);
                pst.setInt(2, r.getHotel().getId());
                pst.setString(3, r.getDate_debut());
                pst.setString(4, r.getDate_fin());

                long noOfDaysBetween = ChronoUnit.DAYS.between(LocalDate.parse(r.getDate_debut()), LocalDate.parse(r.getDate_fin()));
                pst.setLong(5, noOfDaysBetween * r.getNbChambreSingleReserve() * prixchambresingle + noOfDaysBetween * r.getNbChambreDoubleReserve() * prixchambredouble);
                pst.setInt(6, r.getNb_adulte());
                pst.setString(7, "reservation hotel");
                pst.setInt(8, r.getNb_enfants());
                pst.setInt(9, r.getNbChambreSingleReserve());
                pst.setInt(10, r.getNbChambreDoubleReserve());
                pst.setString(11, "confirme");
                pst.setString(12, "");
                pst.execute();

                ResultSet rs1 = pst.getGeneratedKeys();
                int generatedKey2 = 0;
                if (rs1.next()) {
                    generatedKey2 = rs1.getInt(1);
                }

                System.out.println("Inserted res record's ID: " + generatedKey2);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
//affich res pour voyage

    public ObservableList<Reservation> AfficherReservation() {

        try {

            Statement st = MyConnection.getInstance().getCnx().createStatement();

            rs = st.executeQuery("SELECT r.id,r.date_debut,r.date_fin,r.nb_adulte,r.nb_enfants,r.nb_chambre_single_reserve,r.nb_chambre_double_reserve,r.prix,h.name FROM reservation r,hotel h where r.hotel_id=h.id and r.voyage_id is not null");

            ObservableList<Reservation> liste = FXCollections.observableArrayList();

            while (rs.next()) {
                Reservation r = new Reservation();
                r.setId(rs.getInt("id"));
                r.setDate_debut(rs.getString("date_debut"));
                r.setDate_fin(rs.getString("date_fin"));
                r.setNb_adulte(rs.getInt("nb_adulte"));
                r.setNb_enfants(rs.getInt("nb_enfants"));
                r.setNomHotel(rs.getString("name"));
                r.setPrix(rs.getInt("prix"));
                r.setNbChambreSingleReserve(rs.getInt("nb_chambre_single_reserve"));
                r.setNbChambreDoubleReserve(rs.getInt("nb_chambre_double_reserve"));
                liste.add(r);
            }
            return liste;
        } catch (SQLException e) {
            System.out.println("Erreur :" + e.getMessage());
            return null;
        }

    }

    public ObservableList<Reservation> AfficherReservationHotels() {

        try {

            Statement st = MyConnection.getInstance().getCnx().createStatement();

            rs = st.executeQuery("SELECT r.id,r.date_debut,r.date_fin,r.nb_adulte,r.nb_enfants,r.nb_chambre_single_reserve,r.nb_chambre_double_reserve,r.prix,h.name FROM reservation r,hotel h where r.hotel_id=h.id and r.voyage_id is null");

            ObservableList<Reservation> liste = FXCollections.observableArrayList();

            while (rs.next()) {
                Reservation r = new Reservation();
                r.setId(rs.getInt("id"));
                r.setDate_debut(rs.getString("date_debut"));
                r.setDate_fin(rs.getString("date_fin"));
                r.setNb_adulte(rs.getInt("nb_adulte"));
                r.setNb_enfants(rs.getInt("nb_enfants"));
                r.setNomHotel(rs.getString("name"));
                r.setPrix(rs.getInt("prix"));

                r.setNbChambreSingleReserve(rs.getInt("nb_chambre_single_reserve"));
                r.setNbChambreDoubleReserve(rs.getInt("nb_chambre_double_reserve"));
                liste.add(r);
            }
            return liste;
        } catch (SQLException e) {
            System.out.println("Erreur :" + e.getMessage());
            return null;
        }

    }

    public void modifierRes(Reservation c, int id) {
        Reservation p = new Reservation();
        int idplace = 0;

        try {
            if (c.getVoyage() != null) {
                System.out.println("here" + c);
                String requete = "UPDATE reservation set nb_adulte=?,nb_enfants=?,nb_chambre_single_reserve=? WHERE id=?";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

                pst.setInt(1, c.getNb_adulte());
                pst.setInt(2, c.getNb_enfants());
                pst.setInt(3, c.getNb_adulte());//update single rooom with nb adulte its not working cuz res c construct de5elt fi hit ;)
                pst.setInt(4, id);

                pst.executeUpdate();
            } else {
                String requete = "UPDATE reservation set date_debut=?,date_fin=?,nb_adulte=?,nb_enfants=? ,nb_chambre_single_reserve=?,nb_chambre_double_reserve=? WHERE id=?";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setString(1, c.getDate_debut());
                pst.setString(2, c.getDate_fin());
                pst.setInt(3, c.getNb_adulte());
                pst.setInt(4, c.getNb_enfants());
                pst.setInt(5, c.getNbChambreSingleReserve());
                pst.setInt(6, c.getNbChambreDoubleReserve());
                pst.setInt(7, id);

                pst.executeUpdate();
            }
            System.out.println("res Modifiée" + c);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
}
