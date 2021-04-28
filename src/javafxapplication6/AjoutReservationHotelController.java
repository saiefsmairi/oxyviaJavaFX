/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import Entity.Hotel;
import Entities.Reservation;
import Entities.Voyage;
import Services.ReservationService;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author PC-Saif
 */
public class AjoutReservationHotelController implements Initializable {

    private Label label;
    @FXML
    private TextField nb_adulte;
    @FXML
    private DatePicker datedebutvoyage;
    @FXML
    private DatePicker datefinvoyage;
    @FXML
    private TextField nb_enfants;
    private TextField txtdouble;
    @FXML
    private TextField single;
    @FXML
    private TableView<?> restable;
    @FXML
    private TableColumn<?, ?> nomcol;
    @FXML
    private TableColumn<?, ?> villecol;
    @FXML
    private TableColumn<?, ?> datedebutcol;
    @FXML
    private TableColumn<?, ?> datefincol;
    @FXML
    private TableColumn<?, ?> prixcol;
    @FXML
    private TableColumn<?, ?> nbpersonnecol;
    @FXML
    private TableColumn<?, ?> colhotelid;
    @FXML
    private TableColumn<?, ?> colidvoyage;
    private Label lblnomHotel;
    @FXML
    private Label lblnom;
    Hotel hotel;
    ReservationService reservationService = new ReservationService();
    private TextField txtdoublechambre;
    @FXML
    private TextField chambredoubletxt;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(Hotel v) {
        hotel = v;
        lblnom.setText(v.getName());
       
        

        
    }

    @FXML
    private void ajoutReservation(ActionEvent event) {
        System.out.println(chambredoubletxt.getText()+" single "+single.getText());
        Reservation res = new Reservation(datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(nb_adulte.getText()),
               Integer.parseInt(nb_enfants.getText()), Integer.parseInt(single.getText()), Integer.parseInt(chambredoubletxt.getText()), hotel, null);
        System.out.println(res);
        reservationService.ajouterReservation(res);
    }

    @FXML
    private void supprimerReservation(ActionEvent event) {
    }

    @FXML
    private void clearChamps(ActionEvent event) {
    }

}
