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
import com.stripe.model.Account;
import com.stripe.model.BankAccount;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.ExternalAccount;
import com.stripe.model.Sku;
import com.stripe.model.Token;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author smp
 */
public class PaymentController implements Initializable {

    @FXML
    private TextField num_carte;
    @FXML
    private TextField mois_expiration;
    @FXML
    private TextField annee_expiration;
    @FXML
    private TextField cvc;
  
    @FXML
    private TextField description;
    @FXML
    private TextField email;
    @FXML
    private TextField montant;
    @FXML
    private TextField devise;
    @FXML
    private Button payer_commande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Stripe.apiKey="sk_test_51IjNj7KWu4PpaPAOpRYkvkQcw80G0v6rdLPbUnFJWSUqykKoDnEbazZZxv4LL3QoGORielwKb7e18dK3IbkxMNRn00y6kMCk50";
    }    

    @FXML
    private void Payer_commande(ActionEvent event) throws StripeException, IOException, TwilioRestException{
        Map<String, Object> customerParameter = new HashMap< String, Object>();
        customerParameter.put("email",email.getText());
        customerParameter.put("description",description.getText());
        Customer newCustomer =Customer.create(customerParameter);
        System.out.println("customer ajouté");
       String idcas=newCustomer.getId();
        Customer a=Customer.retrieve(idcas);
           Map<String, Object> Cardparam = new HashMap< >();
            Cardparam.put("number",num_carte.getText());
            Cardparam.put("exp_month", mois_expiration.getText());
            Cardparam.put("exp_year", annee_expiration.getText());
            Cardparam.put("cvc", cvc.getText());
            Map <String,Object> tokenParam = new HashMap<String,Object>();
            tokenParam.put("card",Cardparam);
            Token token=Token.create(tokenParam);
            
            Map <String,Object> source = new HashMap<>();
            source.put("source",token.getId());
       Map<String, Object> retrieveParams =
  new HashMap<>();
List<String> expandList = new ArrayList<>();
expandList.add("sources");
retrieveParams.put("expand", expandList);
Customer customer =
  Customer.retrieve(
    idcas,
    retrieveParams,
    null
  );

Map<String, Object> params = new HashMap<>();
params.put("source", "tok_visa");

Card card =
  (Card) customer.getSources().create(params);
Map <String,Object> chargeParam = new HashMap<String,Object>(); 
          chargeParam.put("amount", montant.getText());
          chargeParam.put("currency", devise.getText());
          chargeParam.put("customer", idcas);
          Charge.create(chargeParam);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Bonjour l'équipe de Oxyvia Tours vous informe que le payement de votre facture est : "+montant.getText()+" USD est effectué avec success et une message de confirmation a été envoyée sur votre téléphone ! ");
            
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
        
        
      /*  if ((event.getSource() == payer_commande) ) {
            
             Parent parent = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/addFacture.fxml"));
            Scene sceneview = new Scene(parent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();

    }*/
        
        
        
        
        
    }
    
}
