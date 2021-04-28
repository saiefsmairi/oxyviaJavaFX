/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author smp
 */
public class DashboardController implements Initializable {

    @FXML
    private Button btn_GHome;
    @FXML
    private Button btn_Home;
    @FXML
    private Button btn_produit;
    @FXML
    private Button btn_sauthentifier;
    @FXML
    private Button btn_Home4;
    @FXML
    private Label btn_depense;
    @FXML
    private Label btn_facture;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GHome(ActionEvent event) throws IOException {
        btn_GHome.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/Dashboard.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
        
    }
    

    @FXML
    private void GProduit(ActionEvent event) {
    }

    @FXML
    private void sauthentifier(ActionEvent event) throws IOException {
        btn_sauthentifier.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/login.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void depense(MouseEvent event) throws IOException {
          btn_depense.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/AfficheDepense.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void facture(MouseEvent event) throws IOException {
        btn_facture.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/AfficheFacture.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();

    }

    @FXML
    private void display(ActionEvent event) throws IOException {
        btn_Home4.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/home.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
 
        
        
    }

   /* @FXML
    private void stats(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/stats.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Stats2(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/Barchar_stats.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    @FXML
    private void stats(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/stats.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Stats2(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/Barchar_stats.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pay(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/payment.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pay2(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/payer2.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
