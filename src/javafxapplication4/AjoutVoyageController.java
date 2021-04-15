/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import Entities.Hotel;
import Entities.Place;
import Entities.Voyage;
import Services.VoyageService;
import Services.HotelService;
import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author PC-Saif
 */
public class AjoutVoyageController implements Initializable {

    @FXML
    private TextField nomvoyage;
    @FXML
    private DatePicker datedebutvoyage;
    @FXML
    private DatePicker datefinvoyage;
    @FXML
    private TextField villevoyage;
    @FXML
    private TextArea descvoyage;
    @FXML
    private TextField nbpersonnevoyage;
    @FXML
    private TextField prixpersonnevoyage;
    VoyageService voyageService = new VoyageService();
    HotelService hotelService = new HotelService();
    @FXML
    private ComboBox<Hotel> comboHotel;
    private boolean EDIT = false, ADD = true;
    private int ID;

    private Hotel idSelectedHotel;
    @FXML
    private TextField txtaltitude;
    @FXML
    private TextField txtlong;
    @FXML
    private TextField txtplace1;

    @FXML
    private TableView<Voyage> voyagetable;
    @FXML
    private TableColumn<Voyage, String> nomcol;
    @FXML
    private TableColumn<Voyage, String> villecol;
    @FXML
    private TableColumn<Voyage, String> datedebutcol;
    @FXML
    private TableColumn<Voyage, String> datefincol;
    @FXML
    private TableColumn<Voyage, String> prixcol;
    @FXML
    private TableColumn<Voyage, String> nbpersonnecol;
    @FXML
    private TableColumn<Voyage, String> colhotelid;
    @FXML
    private TableColumn<Voyage, String> colidvoyage;
    private int indexSelectHotel;
    ObservableList<Voyage> lstvoyage;
    Voyage v = null;
    @FXML
    private Button saveVoyageForupdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDate();

        List<Hotel> liste = hotelService.AfficherNomsHotels();

        comboHotel.setConverter(new StringConverter<Hotel>() {
            @Override
            public String toString(Hotel object) {
                return object.getName();
            }

            @Override
            public Hotel fromString(String string) {
                return null;
            }
        });

        comboHotel.setItems(FXCollections.observableArrayList(liste));

        comboHotel.valueProperty().addListener((obs, oldVal, newVal) -> {
            idSelectedHotel = newVal;
            indexSelectHotel = comboHotel.getSelectionModel().getSelectedIndex();

        });

    }
    
   

    public void loadDate() {

        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        villecol.setCellValueFactory(new PropertyValueFactory<>("ville"));
        datedebutcol.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        datefincol.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        prixcol.setCellValueFactory(new PropertyValueFactory<>("prix_personne"));
        nbpersonnecol.setCellValueFactory(new PropertyValueFactory<>("nb_personne"));
        colhotelid.setCellValueFactory(new PropertyValueFactory<>("idHotel"));
        colidvoyage.setCellValueFactory(new PropertyValueFactory<>("id"));

        lstvoyage = voyageService.AfficherVoyages();

        voyagetable.setItems(lstvoyage);
    }

    private void AjoutVoyage(ActionEvent event) {

        Voyage v = new Voyage(nomvoyage.getText(), villevoyage.getText(), descvoyage.getText(), datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(prixpersonnevoyage.getText()), Integer.parseInt(nbpersonnevoyage.getText()), null, idSelectedHotel);

        Place p = new Place(txtlong.getText(), txtaltitude.getText(), txtplace1.getText());
        v.setHotel(idSelectedHotel);
        voyageService.ajouterVoyage(v, p);

        loadDate();

    }

    @FXML
    private void updateVoyage(ActionEvent event) {
        ADD = false;
        EDIT = true;
        v = voyagetable.getSelectionModel().getSelectedItem();
        ID = v.getId();
        nomvoyage.setText(v.getNom());
        datedebutvoyage.setValue(LocalDate.parse(v.getDate_debut()));
        datefinvoyage.setValue(LocalDate.parse(v.getDate_debut()));
        nbpersonnevoyage.setText(v.getNb_personne() + "");
        prixpersonnevoyage.setText(v.getPrix_personne() + "");
        villevoyage.setText(v.getVille());
        descvoyage.setText(v.getDescription());

        comboHotel.setValue(voyageService.findHotelByID(v.getIdHotel())); 

    }

    @FXML
    private void supprimerVoyage(ActionEvent event) {
        v = voyagetable.getSelectionModel().getSelectedItem();
        System.out.println(v);
        voyageService.supprimerVoyage(v.getId());
        loadDate();

    }

    @FXML
    private void saveVoyageForupdate(ActionEvent event) {
        System.out.println(idSelectedHotel);

        if (ADD == false && EDIT) {

            Voyage voy = new Voyage(ID, nomvoyage.getText(), villevoyage.getText(), descvoyage.getText(), datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(prixpersonnevoyage.getText()), Integer.parseInt(nbpersonnevoyage.getText()), null, comboHotel.getSelectionModel().getSelectedItem());
            Place p = new Place(txtlong.getText(), txtaltitude.getText(), txtplace1.getText());

            voyageService.modifierVoyage(voy, ID, p);
            loadDate();
        } else {

            Voyage v = new Voyage(nomvoyage.getText(), villevoyage.getText(), descvoyage.getText(), datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(prixpersonnevoyage.getText()), Integer.parseInt(nbpersonnevoyage.getText()), null, idSelectedHotel);

            Place p = new Place(txtlong.getText(), txtaltitude.getText(), txtplace1.getText());
            v.setHotel(idSelectedHotel);
            voyageService.ajouterVoyage(v, p);

            loadDate();

        }
    }

    @FXML
    private void viderChamps(ActionEvent event) {
        nomvoyage.clear();
        datedebutvoyage.setValue(null);
        datefinvoyage.setValue(null);
        nbpersonnevoyage.clear();
        prixpersonnevoyage.clear();
        villevoyage.clear();
        descvoyage.clear();
        txtaltitude.clear();
        txtlong.clear();
        txtplace1.clear();
        comboHotel.getSelectionModel().select(null);

    }


}
