/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import Services.ReservationService;
import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author PC-Saif
 */
public class StatsReservationController implements Initializable {

    ReservationService rs = new ReservationService();
    @FXML
    private PieChart chartres;
    @FXML
    private NumberAxis prix;
    @FXML
    private CategoryAxis mois;
    @FXML
    private BarChart<String, Integer> chartprix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map<String, Integer> hm = rs.MostHotelRes();
        for (Map.Entry<String, Integer> entry : hm.entrySet()) {

            chartres.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));

        }

        
        Map<String, Integer> hm1 = rs.TotalPrixPerMois();
        for (Map.Entry<String, Integer> entry : hm1.entrySet()) {
            System.out.println("Key = " + entry.getKey()
                    + ", Value = " + entry.getValue());
     XYChart.Series<String, Integer> series = new XYChart.Series();
        series.setName("Prix Total du mois "+entry.getKey());
        //populating the series with data
        series.getData().add(new XYChart.Data( entry.getKey(), entry.getValue()));

        chartprix.getData().add(series);
        }

   

    }

}
