/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import Entities.Hotel;
import Entities.Voyage;
import Services.HotelService;
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
public class ListeHotelsController implements Initializable {

    @FXML
    private TableView<Hotel> voyagetable;
    @FXML
    private TableColumn<Hotel, String> nomcol;
    @FXML
    private TableColumn<Hotel, String> villecol;

      @FXML
    private TableColumn<Hotel, String> nbetoile;
    ObservableList<Hotel> lstvoyage;
    HotelService vs = new HotelService();
    @FXML
    private TableColumn<?, ?> colhotelid;
    @FXML
    private TableColumn<?, ?> datedebutcol;
    @FXML
    private TableColumn<?, ?> datefincol;
    @FXML
    private TableColumn<?, ?> nbpersonnecol;
  
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();

    }

    public void loadDate() {

        nomcol.setCellValueFactory(new PropertyValueFactory<>("name"));
        villecol.setCellValueFactory(new PropertyValueFactory<>("pays"));
         nbetoile.setCellValueFactory(new PropertyValueFactory<>("nbetoile"));

        
        lstvoyage = vs.Afficherhotels();

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
  controller.initData2(voyagetable.getSelectionModel().getSelectedItem());

  ///button resever works now i have to insert res hotel to db
  stage.show();

 
    }

}
