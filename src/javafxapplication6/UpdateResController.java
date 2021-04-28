/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import Entities.Reservation;
import Entities.Voyage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Services.ReservationService;
import Services.VoyageService;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC-Saif
 */
public class UpdateResController implements Initializable {

    @FXML
    private Label lblchambresingle;
    @FXML
    private Label lblchambredouble;
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
    private Label lblreservationpour;
    Reservation t;
    VoyageService resservice = new VoyageService();
    @FXML
    private Button but;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(Reservation v) {
        t = v;

        datedebutvoyage.setValue(LocalDate.parse(v.getDate_debut()));
        datefinvoyage.setValue(LocalDate.parse(v.getDate_fin()));
        txtdouble.setText(v.getNbChambreDoubleReserve() + "");
        single.setText(v.getNbChambreSingleReserve() + "");
        nb_adulte.setText(v.getNb_adulte() + "");
        nb_enfants.setText(v.getNb_enfants() + "");

        if (v.getType().equals("reservation voyage")) {
            lblchambredouble.setVisible(false);
            lblchambresingle.setVisible(false);
            datedebutvoyage.setEditable(false);
            datefinvoyage.setEditable(false);
            single.setVisible(false);

            txtdouble.setVisible(false);
        }

        // loadDate();
    }

    @FXML
    private void update(ActionEvent event) {
        Reservation res = new Reservation(t.getId(), datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(nb_adulte.getText()),
                Integer.parseInt(nb_enfants.getText()), Integer.parseInt(single.getText()), Integer.parseInt(txtdouble.getText()), null, t.getType());

        resservice.modifierRes(res, t.getId());
        Stage stage = (Stage) but.getScene().getWindow();
        stage.close();

    }

}
