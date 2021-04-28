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

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

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
    ObservableList<Reservation> lstres;
    ObservableList<Reservation> lstreshotels;

    Reservation res;
    ReservationService resservice = new ReservationService();
    private int ID;
    private Voyage voyVerifUpdate;
    @FXML
    private Label lblchambresingle;
    @FXML
    private Label lblchambredouble;
    Alert a = new Alert(AlertType.CONFIRMATION);
    Alert alertAjoutRes = new Alert(AlertType.ERROR);

    private ListView<Reservation> lstviewRes;
    @FXML
    private ImageView gotoimg;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initData(Voyage v) {
        voyage = v;
        System.out.println("val voyyy" + voyage);

        lblnom.setText(v.getNom());
        lblreservationpour.setText("Reservation pour le voyage :");
        datedebutvoyage.setValue(LocalDate.parse(v.getDate_debut()));
        datefinvoyage.setValue(LocalDate.parse(v.getDate_fin()));
        txtdouble.setVisible(false);
        single.setVisible(false);
        lblchambredouble.setVisible(false);
        lblchambresingle.setVisible(false);

        datedebutvoyage.setEditable(false);
        datefinvoyage.setEditable(false);
        // loadDate();

    }

    public void initData2(Hotel v) {
        h = v;
        lblreservationpour.setText("Reservation pour l'Hotel :");
        lblnom.setText(v.getName());
        //  loadDate();
        System.out.println("val hotel" + h);
    }

    private void ajoutReservation(ActionEvent event) {
        Reservation res = new Reservation(datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(nb_adulte.getText()),
                Integer.parseInt(nb_enfants.getText()), Integer.parseInt(single.getText()), Integer.parseInt(txtdouble.getText()), h, null);
        System.out.println("fnn hotel");
        reservationService.ajouterReservation(res);

    }

    private void supprimerReservation(ActionEvent event) {
        res = lstviewRes.getSelectionModel().getSelectedItem();
        System.out.println(res);
        resservice.supprimerRes(res.getId());
        lstviewRes.getItems().clear();

        //  loadDate();
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
                    Integer.parseInt(nb_enfants.getText()), Integer.parseInt(single.getText()), Integer.parseInt(txtdouble.getText()), voyVerifUpdate, null);
            resservice.modifierRes(res, ID);
            //  loadDate();
            ADD = true;
            EDIT = false;
        } else {
            if (voyage != null) {
                if (nb_adulte.getText().length() != 0 && nb_enfants.getText().length() != 0) {
                    Reservation res = new Reservation(datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(nb_adulte.getText()),
                            Integer.parseInt(nb_enfants.getText()), null, voyage);

                    reservationService.ajouterReservation(res);
                    //  lstviewRes.getItems().clear();

                    //   loadDate();
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

                        VonageClient client = VonageClient.builder().apiKey("8c23c1c3").apiSecret("XFHt3mkHjwR15n4E").build();
                        TextMessage message = new TextMessage("Vonage APIs",
                                "21629162035",
                                "votre réservation à été prise en compte ! Oxyvia Tours :) "
                        );

                        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

                        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
                            System.out.println("Message sent successfully.");
                        } else {
                            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
                        }
//                        lstviewRes.getItems().clear();

                        // loadDate();
                    } else if (result.get() == ButtonType.CANCEL) {
                        System.out.println("no press");

                    }

                    nb_adulte.clear();
                    nb_enfants.clear();
                    single.clear();
                    txtdouble.clear();

                    datedebutvoyage.setValue(null);
                    datefinvoyage.setValue(null);
                } else {
                    alertAjoutRes.setContentText("Veuillez remplir les champs");
                    alertAjoutRes.show();
                }
            }
        }
    }

    private void updateRes(ActionEvent event) {

        System.out.println("upppppppXXXX " + resu);
        ADD = false;
        EDIT = true;
        resu = lstviewRes.getSelectionModel().getSelectedItem();

        ID = resu.getId();
        nb_adulte.setText(resu.getNb_adulte() + "");
        nb_enfants.setText(resu.getNb_enfants() + "");
        single.setText(resu.getNbChambreSingleReserve() + "");
        txtdouble.setText(resu.getNbChambreDoubleReserve() + "");
        voyVerifUpdate = resu.getVoyage();
        datedebutvoyage.setValue(LocalDate.parse(resu.getDate_debut()));
        datefinvoyage.setValue(LocalDate.parse(resu.getDate_fin()));

    }

    @FXML
    private void gotoGestionRes(MouseEvent event) {
        Stage stage1 = (Stage) gotoimg.getScene().getWindow();
        stage1.close();
     FXMLLoader loader = new FXMLLoader(
                            getClass().getResource(
                                    "GestionRes.fxml"
                            )
                    );
                    Stage stage = new Stage(StageStyle.DECORATED);
                    try {
                        stage.setScene(
                                new Scene(loader.load())
                        );
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                  

                    ///button resever works now i have to insert res hotel to db
                    stage.show();
        
    }

}
