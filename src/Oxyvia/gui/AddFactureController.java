/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.gui;

import Oxyvia.entities.Facture;
import Oxyvia.utils.BDConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author smp
 */
public class AddFactureController implements Initializable {

    @FXML
    private TextField FC1;
    @FXML
    private DatePicker FC3;
    @FXML
    private TextField FC2;
    @FXML
    private ComboBox FC4;
    @FXML
    private ComboBox FC5;
    @FXML
    private ComboBox FC6;
    @FXML
    private RadioButton FC7;
    @FXML
    private TextField FC8;
    @FXML
    private TextField FC9;
    @FXML
    private DatePicker FC10;
    @FXML
    private ComboBox FC11;
    @FXML
    private TextField FC12;
    @FXML
    private RadioButton FC13;
    @FXML
    private TextField FC14;
 
    
      String query=null;
    Connection conx = null;
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    Facture facture = null;
    private boolean update;
    int factureId ;
    private ToggleGroup CB_ToggleGroup;
    private ToggleGroup Paiement_ToggleGroup;
    @FXML
    private RadioButton FC71;
    @FXML
    private RadioButton FC72;
    @FXML
    private RadioButton FC131;
   
    
   
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FC4.getItems().addAll("Dinar Tunisien TND","Dinar Algérien DA","Dirham Marocain","Dollar Américain $","Dollar Canadien CA","Euro £");
        FC5.getItems().addAll("Paiement hors Ligne","par chéque","par espéce","Paiement On ligne ","Paysafecard Option","Paypal","par CB");
        FC6.getItems().addAll("Comptant","par Facilité");
        FC11.getItems().addAll("Lac1(les berges du lac)","Sousse(proche du Mall of sousse)","Bizerte(centre)");
        
        CB_ToggleGroup = new ToggleGroup();
        this.FC7.setToggleGroup(CB_ToggleGroup);
        this.FC71.setToggleGroup(CB_ToggleGroup);
        this.FC72.setToggleGroup(CB_ToggleGroup);
        Paiement_ToggleGroup = new ToggleGroup();
        this.FC13.setToggleGroup(Paiement_ToggleGroup);
        this.FC131.setToggleGroup(Paiement_ToggleGroup);
        
        
    }    

    @FXML
    private void save(MouseEvent event) {
        
        conx = BDConnector.driverBD();
        
        
        String identifiant= FC1.getText();
        String montant= FC2.getText();
        String date_paiement= String.valueOf(FC3.getValue());
        String devise= FC4.getValue().toString();
        String moyen_paiement= FC5.getValue().toString();
        String mode_paiement= FC6.getValue().toString();
        if(this.CB_ToggleGroup.getSelectedToggle().equals(this.FC7))
        {String type_cb= FC7.getText();}
        else if (this.CB_ToggleGroup.getSelectedToggle().equals(this.FC71))
        {String type_cb= FC71.getText();}
        else 
        {String type_cb= FC72.getText();}
        String ncb= FC8.getText();
        String code_securite= FC9.getText();
        String date_expiration= String.valueOf(FC10.getValue());
        String location= FC11.getValue().toString();
        String pays= FC12.getText();
         if(this.Paiement_ToggleGroup.getSelectedToggle().equals(this.FC13))
         { String enabled= FC13.getText();}
          else
         { String enabled= FC131.getText();}
        String color= FC14.getText();
        if(mode_paiement.isEmpty()||identifiant.isEmpty()||montant.isEmpty()||date_paiement.isEmpty()||devise.isEmpty()||moyen_paiement.isEmpty())
          
        { Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else if(!FC2.getText().matches("^[0-9\\s]*$"))
{   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("\"invalide nom : le champs montant ne contient que des chiffres\"");
            alert.showAndWait();}
else if(!FC12.getText().matches("^[a-zA-Z\\s]*$"))
{   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("\"invalide nom : le champs pays ne contient que des lettres\"");
            alert.showAndWait();}
        else {
            getQuery();
            insert();
        

        }
        
            
      
    
    
}

    private void getQuery() {
        if (update == false){
        query="INSERT INTO `facture`(`identifiant`, `montant`, `date_paiement`, `devise`, `moyen_paiement`, `mode_paiement`, `type_cb`, `ncb`, `code_securite`, `date_expiration`, `location`, `pays`, `enabled`, `color`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        } else{  query = "UPDATE `facture` SET "
                    + "`identifiant`=?,"
                    + "`montant`=?,"
                    + "`date_paiement`=?,"
                    +"`devise`=?,"
                    +"`moyen_paiement`=?,"
                    +"`mode_paiement`=?,"
                    +"`type_cb`=?,"
                    +"`ncb`=?,"
                    +"`code_securite`=?,"
                    +"`date_expiration`=?,"
                    +"`location`=?,"
                    +"`pays`=?,"
                    +"`enabled`=?,"
                    + "`color`= ? WHERE id = '"+factureId+"'";
        }
    }
 
    private void insert() {
        try {
        preparedStatement = conx.prepareStatement(query);
        preparedStatement.setString(1,FC1.getText());
        preparedStatement.setString(2,FC2.getText());
        preparedStatement.setString(3,String.valueOf(FC3.getValue()));
        preparedStatement.setString(4,FC4.getValue().toString());
        preparedStatement.setString(5,FC5.getValue().toString());
        preparedStatement.setString(6,FC6.getValue().toString());
         if(this.CB_ToggleGroup.getSelectedToggle().equals(this.FC7))
         {
        preparedStatement.setString(7,FC7.getText());}
         else if(this.CB_ToggleGroup.getSelectedToggle().equals(this.FC71))
         {
        preparedStatement.setString(7,FC71.getText());}
        else
         {
        preparedStatement.setString(7,FC72.getText());}
        preparedStatement.setString(8,FC8.getText());
        preparedStatement.setString(9,FC9.getText());
        preparedStatement.setString(10,String.valueOf(FC10.getValue()));
        preparedStatement.setString(11,FC11.getValue().toString());
        preparedStatement.setString(12,FC12.getText());
        if(this.Paiement_ToggleGroup.getSelectedToggle().equals(this.FC13)){
        preparedStatement.setString(13,FC13.getText());}
        else{
            
         preparedStatement.setString(13,FC131.getText());}
        preparedStatement.setString(14,FC14.getText());
        
        preparedStatement.execute();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Your payment 's Well done You w'll receive mail later!  ");
            //alert.showingProperty();
            alert.showAndWait();
     } catch (SQLException ex) {
            Logger.getLogger(AddFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        
    }

    void setUpdate(boolean b) {
        this.update = b;
        
    }

    void setTextField(int id,String identifiant, String montant, LocalDate toLocalDate, String devise, String moyen_paiement, String mode_paiement, String type_cb, String ncb, String code_securite, LocalDate toLocalDate0, String location, String pays, boolean enabled, String color) {
       factureId = id;
        FC1.setText(identifiant);
        FC2.setText(montant);
        FC3.setValue(toLocalDate);
        FC4.setValue(devise);
        FC5.setValue(moyen_paiement);
        FC6.setValue(mode_paiement);
       
        {FC7.setSelected(true);
        FC71.setSelected(false);
        // FC71.setSelected(true);}
        
        
         //FC72.setSelected(true);}
        FC72.setSelected(false);}
        FC8.setText(ncb);
        FC9.setText(code_securite);
        FC10.setValue(toLocalDate0);
        FC11.setValue(location);
        FC12.setText(pays);
        FC13.setSelected(enabled);
        FC131.setSelected(enabled);
        FC14.setVisible(true);
        //FC14.setText(color);  
    }
    
    
    
}