/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.gui;

import Oxyvia.utils.BDConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author smp
 */
public class StatsController implements Initializable {

    @FXML
    private PieChart pieChart;
    
    String query=null;
    Connection conx = null;
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    ObservableList<PieChart.Data> piechartdata;
    ArrayList<String> horaire_reg = new ArrayList<String>();
     ArrayList<Integer> id = new ArrayList<Integer>();
    /**
     * Initializes the controller class.
     */
      public void loadData()
    {   piechartdata=FXCollections.observableArrayList();
        
        try {
        //query="SELECT d.horaire_reg,d.horaire_sup FROM depense d";
         conx = BDConnector.driverBD();
        preparedStatement = conx.prepareStatement("select horaire_reguliere, id from depense");
        result = preparedStatement.executeQuery();
        while(result.next()){
            piechartdata.add(new PieChart.Data(result.getString("horaire_reguliere"),result.getInt("id")));
            horaire_reg.add(result.getString("horaire_reguliere"));
            id.add(result.getInt("id"));
                    }
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
        pieChart.setData(piechartdata);
       
    }  
   
    
}
