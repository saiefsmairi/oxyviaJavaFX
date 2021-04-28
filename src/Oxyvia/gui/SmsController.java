/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.gui;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author smp
 */
public class SmsController implements Initializable {

    @FXML
    private TextField numT;
    @FXML
    private Label erreurTitre;
    @FXML
    private TextField textF;
    @FXML
    private ImageView imgg;
    @FXML
    private Button sms;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void sms(ActionEvent event) throws TwilioRestException {
        TwilioRestClient client = new TwilioRestClient("AC9567a7216e930328b653d592b34f29b5","d2be7d8a195ea0e8b0abca051ea5a99c");
        com.twilio.sdk.resource.instance.Account account;
        account = client.getAccount();
        SmsFactory factory = account.getSmsFactory();
        HashMap<String, String> message = new HashMap<>();
       // "+21624335677"
        message.put("To",numT.getText());
        message.put("From","+19893037850");
        message.put("Body",textF.getText());
        factory.create(message);
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Your SMS Message well sent!  ");
            //alert.showingProperty();
            alert.showAndWait();
        
    }
    
}
