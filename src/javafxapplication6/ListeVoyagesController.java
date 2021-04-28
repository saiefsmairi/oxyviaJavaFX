/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import Entities.Voyage;
import Services.VoyageService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author PC-Saif
 */
public class ListeVoyagesController implements Initializable {

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
    ObservableList<Voyage> lstvoyage;
    VoyageService vs = new VoyageService();
    @FXML
    private TableColumn<?, ?> colhotelid;
    @FXML
    private TableColumn<?, ?> colidvoyage;
  
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();

    }

    public void loadDate() {

        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        villecol.setCellValueFactory(new PropertyValueFactory<>("ville"));
        datedebutcol.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        datefincol.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        prixcol.setCellValueFactory(new PropertyValueFactory<>("prix_personne"));
        nbpersonnecol.setCellValueFactory(new PropertyValueFactory<>("nb_personne"));
        lstvoyage = vs.AfficherVoyages();

        voyagetable.setItems(lstvoyage);
    }

    private void openAjoutVoyageAction(ActionEvent event) throws IOException {

        
    }

    @FXML
    private void reserverVoyageBtn(ActionEvent event) throws IOException {
              FXMLLoader loader = new FXMLLoader(
    getClass().getResource(
      "AjoutReservation.fxml"
    )
  );

  Stage stage = new Stage(StageStyle.DECORATED);
  stage.setScene(
    new Scene(loader.load())
  );

  AjoutReservationController controller = loader.getController();
  controller.initData(voyagetable.getSelectionModel().getSelectedItem());

  
  stage.show();

 
    }

}
