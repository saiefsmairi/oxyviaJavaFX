/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entity.Chambre;
import Entity.Hotel;
import Entity.chambrestatic;
import Service.ChambreService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class HomechambreController implements Initializable {

    @FXML
    private Button btn_Home;
    @FXML
    private Button btn_produit;
    @FXML
    private Button btn_Home3;
    @FXML
    private Button btn_Home4;
    @FXML
    private Button btn_sauthentifier;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private ImageView retour;
                   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ChambreService s=  new ChambreService();
         List <Chambre> listchambres=s.Lister(chambrestatic.id);
           scroll.setVisible(true);
        grid.getChildren().clear();
    
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < listchambres.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemchambre.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemChambreController itemController = fxmlLoader.getController();
                itemController.setData(listchambres.get(i));

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

              //  GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
                        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);

            
        }
        // TODO
    }    

    @FXML
    private void GHome(ActionEvent event) {
    }

    @FXML
    private void payment(MouseEvent event) {
    }

    @FXML
    private void GProduit(ActionEvent event) {
    }

    @FXML
    private void sauthentifier(ActionEvent event) {
    }

    @FXML
    private void goretour(MouseEvent event) throws IOException {
        
        
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btn_Home.getScene().setRoot(root);
    }
    
}
