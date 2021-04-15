/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Hotel;
import Entities.Voyage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author PC-Saif
 */
public class HotelService {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public ObservableList<Hotel> Afficherhotels() {

        try {

            Statement st = MyConnection.getInstance().getCnx().createStatement();

            rs = st.executeQuery("SELECT * FROM hotel");

            ObservableList<Hotel> liste = FXCollections.observableArrayList();

            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setPays(rs.getString("pays"));
                hotel.setAdresse(rs.getString("adresse"));
                hotel.setNbetoile(rs.getInt("nbetoile"));
                hotel.setNum(rs.getInt("num"));
                hotel.setEmail(rs.getString("email"));
                hotel.setLat(rs.getInt("lat"));
                hotel.setIng(rs.getInt("lng"));

                liste.add(hotel);
            }
            return liste;
        } catch (SQLException e) {
            System.out.println("Erreur :" + e.getMessage());
            return null;
        }

    }

    public List<Hotel> AfficherNomsHotels() {

        try {

            Statement st = MyConnection.getInstance().getCnx().createStatement();

            rs = st.executeQuery("SELECT * FROM hotel");

            List<Hotel> liste = new LinkedList<Hotel>();
            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setName(rs.getString("name"));
                hotel.setId(rs.getInt("id"));

                liste.add(hotel);

            }
            return liste;
        } catch (SQLException e) {
            System.out.println("Erreur :" + e.getMessage());
            return null;
        }

    }
}
