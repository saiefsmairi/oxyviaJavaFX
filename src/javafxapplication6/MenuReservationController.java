/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author PC-Saif
 */
public class MenuReservationController implements Initializable {

    @FXML
    private AnchorPane espacepartenaraieadminpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void OpenStats(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("StatsReservation.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    private void openGestRes(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("ListeHotels.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.setX(200);
        stage.setY(200);
        stage.show();

        Parent parent1 = FXMLLoader.load(getClass().getResource("ListeVoyages.fxml"));
        Scene scene1 = new Scene(parent1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.initStyle(StageStyle.UTILITY);
        stage1.show();
    }

}
