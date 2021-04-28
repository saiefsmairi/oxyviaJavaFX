/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.gui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.BankAccount;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.ExternalAccount;
import com.stripe.model.Token;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author smp
 */
public class Payer2Controller implements Initializable {

    @FXML
    private Button payer_commande;
    @FXML
    private TextField montant;
    @FXML
    private TextField devise;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Stripe.apiKey="sk_test_51IjNj7KWu4PpaPAOpRYkvkQcw80G0v6rdLPbUnFJWSUqykKoDnEbazZZxv4LL3QoGORielwKb7e18dK3IbkxMNRn00y6kMCk50";
    }    

    @FXML
    private void Payer_commande(ActionEvent event) throws StripeException, TwilioRestException {
     
       /*Customer customer = Customer.retrieve("cus_JMq7rJ8XF5zPcj");
      Map <String,Object> Param = new HashMap<String,Object>();
      Param.put("default_source", "ba_1Ik7mDKWu4PpaPAO6LzPo6yg");
      customer.update(Param);*/
      
   
        
        
        
        
        Map <String,Object> Param = new HashMap<String,Object>();
        Param.put("amount", montant.getText());
        Param.put("currency", devise.getText());
        Param.put("customer", "cus_JMq7rJ8XF5zPcj");
        Charge charge= Charge.create(Param);
         
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes !");
            alert.setHeaderText(null);
            alert.setContentText("la paiement relative au dépense est : "+montant.getText()+" USD est effectuée avec success et une message de confirmation a été envoyer au solarié! ");
            alert.showAndWait(); 
             
       TwilioRestClient client = new TwilioRestClient("AC9567a7216e930328b653d592b34f29b5","d2be7d8a195ea0e8b0abca051ea5a99c");
        com.twilio.sdk.resource.instance.Account account;
        account = client.getAccount();
        SmsFactory factory = account.getSmsFactory();
        HashMap<String, String> message = new HashMap<>();
        
        message.put("To","+21624335677");
        message.put("From","+19893037850");
        message.put("Body","Notification de paiement!");
        factory.create(message);
        
    
}
}