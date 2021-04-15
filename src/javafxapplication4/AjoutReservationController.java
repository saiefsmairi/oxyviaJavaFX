/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import Entities.Hotel;
import Entities.Reservation;
import Entities.Voyage;
import Services.ReservationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author PC-Saif
 */
public class AjoutReservationController implements Initializable {

    private Label label;
    @FXML
    private TextField nb_adulte;
    @FXML
    private DatePicker datedebutvoyage;
    @FXML
    private DatePicker datefinvoyage;
    @FXML
    private TextField nb_enfants;
    @FXML
    private TextField txtdouble;
    @FXML
    private TextField single;
    @FXML
    private TableView<Reservation> restable;
    @FXML
    private TableColumn<?, ?> datefincol;
    @FXML
    private TableColumn<?, ?> colhotelid;
    @FXML
    private TableColumn<?, ?> colidvoyage;
    private Label lblnomHotel;
    @FXML
    private Label lblnom;
    Voyage voyage;
    private boolean EDIT = false, ADD = true;
    Reservation resu;
    Hotel h;
    ReservationService reservationService = new ReservationService();
    @FXML
    private Label lblreservationpour;
    @FXML
    private TableColumn<Reservation, String> datedebcol;
    @FXML
    private TableColumn<Reservation, String> nbadultecol;
    @FXML
    private TableColumn<Reservation, String> nbenfantscol;
    @FXML
    private TableColumn<Reservation, String> hotelnamecol;
    ObservableList<Reservation> lstres;
    ObservableList<Reservation> lstreshotels;

    Reservation res;
    ReservationService resservice = new ReservationService();
    @FXML
    private TableColumn<?, ?> singleroomcol;
    @FXML
    private TableColumn<?, ?> doubleroomcol;
    private int ID;
    private Voyage voyVerifUpdate;
    @FXML
    private Label lblchambresingle;
    @FXML
    private Label lblchambredouble;
    Alert a = new Alert(AlertType.CONFIRMATION);
    Alert alertAjoutRes = new Alert(AlertType.ERROR);

    @FXML
    private TableColumn<Reservation, String> prixcol;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void loadDate() {

        datedebcol.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        datefincol.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        nbadultecol.setCellValueFactory(new PropertyValueFactory<>("nb_adulte"));
        nbenfantscol.setCellValueFactory(new PropertyValueFactory<>("nb_enfants"));
        hotelnamecol.setCellValueFactory(new PropertyValueFactory<>("nomHotel"));
        doubleroomcol.setCellValueFactory(new PropertyValueFactory<>("nbChambreDoubleReserve"));
        singleroomcol.setCellValueFactory(new PropertyValueFactory<>("NbChambreSingleReserve"));
        prixcol.setCellValueFactory(new PropertyValueFactory<>("prix"));

        lstres = resservice.AfficherReservation();
        lstreshotels = resservice.AfficherReservationHotels();
        if (voyage != null) {
            restable.setItems(lstres);

        } else if (h != null) {
            restable.setItems(lstreshotels);

        }
    }

    public void initData(Voyage v) {
        voyage = v;
        lblnom.setText(v.getNom());
        lblreservationpour.setText("Reservation pour le voyage :");
        datedebutvoyage.setValue(LocalDate.parse(v.getDate_debut()));
        datefinvoyage.setValue(LocalDate.parse(v.getDate_fin()));
        System.out.println("val h" + voyage);
        txtdouble.setVisible(false);
        single.setVisible(false);
        lblchambredouble.setVisible(false);
        lblchambresingle.setVisible(false);

        datedebutvoyage.setEditable(false);
        datefinvoyage.setEditable(false);
        loadDate();

    }

    public void initData2(Hotel v) {
        h = v;
        lblreservationpour.setText("Reservation pour l'Hotel :");
        lblnom.setText(v.getName());
        loadDate();
        System.out.println("val h" + h);
    }

    private void ajoutReservation(ActionEvent event) {

        if (voyage != null) {
            Reservation res = new Reservation(datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(nb_adulte.getText()),
                    Integer.parseInt(nb_enfants.getText()), null, voyage);

            reservationService.ajouterReservation(res);
        }

        if (h != null) {
            Reservation res = new Reservation(datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(nb_adulte.getText()),
                    Integer.parseInt(nb_enfants.getText()), Integer.parseInt(single.getText()), Integer.parseInt(txtdouble.getText()), h, null);

            reservationService.ajouterReservation(res);
        }

    }

    @FXML
    private void supprimerReservation(ActionEvent event) {
        res = restable.getSelectionModel().getSelectedItem();
        System.out.println(res);
        resservice.supprimerRes(res.getId());
        loadDate();

    }

    @FXML
    private void clearChamps(ActionEvent event) {

        nb_adulte.clear();
        nb_enfants.clear();
        single.clear();
        txtdouble.clear();
        if (h != null) {

            datedebutvoyage.setValue(null);
            datefinvoyage.setValue(null);
        }
    }
    ///mezelt update and we done 80%

    @FXML
    private void saveres(ActionEvent event) {
        if (ADD == false && EDIT) {
            Reservation res = new Reservation(ID, datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(nb_adulte.getText()),
                    Integer.parseInt(nb_enfants.getText()), Integer.parseInt(single.getText()), Integer.parseInt(txtdouble.getText()), voyVerifUpdate);
            resservice.modifierRes(res, ID);
            loadDate();
            ADD = true;
            EDIT = false;
        } else {
            if (voyage != null) {
                if (nb_adulte.getText().length() != 0 && nb_enfants.getText().length() != 0) {
                    Reservation res = new Reservation(datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(nb_adulte.getText()),
                            Integer.parseInt(nb_enfants.getText()), null, voyage);

                    reservationService.ajouterReservation(res);
                    loadDate();
                    nb_adulte.clear();
                    nb_enfants.clear();
                    single.clear();
                    txtdouble.clear();

                } else {
                    alertAjoutRes.setContentText("Veuillez remplir les champs");
                    alertAjoutRes.show();

                }

            }

            if (h != null) {
                if (txtdouble.getText().length() != 0 && single.getText().length() != 0 && nb_adulte.getText().length() != 0 && nb_enfants.getText().length() != 0 && datefinvoyage.getValue().toString().length() != 0 && datedebutvoyage.getValue().toString().length() != 0) {
                    Reservation res = new Reservation(datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(nb_adulte.getText()),
                            Integer.parseInt(nb_enfants.getText()), Integer.parseInt(single.getText()), Integer.parseInt(txtdouble.getText()), h, null);

                    a.setContentText("Confirmation de la réservation");

                    Optional<ButtonType> result = a.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        reservationService.ajouterReservation(res);

                    } else if (result.get() == ButtonType.CANCEL) {
                        System.out.println("no press");

                    }

                    loadDate();
                    nb_adulte.clear();
                    nb_enfants.clear();
                    single.clear();
                    txtdouble.clear();

                    datedebutvoyage.setValue(null);
                    datefinvoyage.setValue(null);
                }
                else{
                        alertAjoutRes.setContentText("Veuillez remplir les champs");
                    alertAjoutRes.show();
                }
            }
        }
    }

    @FXML
    private void updateRes(ActionEvent event) {
        ADD = false;
        EDIT = true;
        resu = restable.getSelectionModel().getSelectedItem();
        ID = resu.getId();
        nb_adulte.setText(resu.getNb_adulte() + "");
        nb_enfants.setText(resu.getNb_enfants() + "");
        single.setText(resu.getNbChambreSingleReserve() + "");
        txtdouble.setText(resu.getNbChambreDoubleReserve() + "");
        voyVerifUpdate = resu.getVoyage();
        datedebutvoyage.setValue(LocalDate.parse(resu.getDate_debut()));
        datefinvoyage.setValue(LocalDate.parse(resu.getDate_fin()));

    }

}
