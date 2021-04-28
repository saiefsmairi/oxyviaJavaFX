/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.gui;

import Oxyvia.utils.BDConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author smp
 */
public class Barchar_statsController implements Initializable {

    @FXML
    private BarChart<String,Double>barChart;
    @FXML
    private Button loadChart;

    /**
     * Initializes the controller class.
     */
     String query=null;
    Connection conx = null;
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    
    private void loadChart(ActionEvent event) {
        try {
            conx = BDConnector.driverBD();
            query="select count(*), SUBSTRING(date_paiement, 1, 10) as date_annonce FROM facture GROUP BY date_annonce ";
            XYChart.Series<String,Double> series = new XYChart.Series<>();
            preparedStatement = conx.prepareStatement(query);
            result = preparedStatement.executeQuery();
            while(result.next()){
                series.getData().add(new XYChart.Data<>(result.getString(2),result.getDouble(1)));}
            barChart.getData().add(series);
        } catch (SQLException ex) {
            Logger.getLogger(Barchar_statsController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
}
