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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author smp
 */
public class UpdateFactureController implements Initializable {

    @FXML
    private TextField FC1;
    @FXML
    private DatePicker FC3;
    @FXML
    private TextField FC2;
    @FXML
    private TextField FC12;
    private RadioButton FC13;
    @FXML
    private TextField FC14;
    @FXML
    private ComboBox FC4;
    @FXML
    private ComboBox FC5;
    @FXML
    private ComboBox FC6;
    private RadioButton FC7;
    private RadioButton FC71;
    private RadioButton FC72;
    
    
     String query=null;
    Connection conx = null;
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    Facture facture = null;
    private boolean update;
    int factureId ;
    private ToggleGroup CB_ToggleGroup;
    private ToggleGroup Paiement_ToggleGroup;
    private RadioButton FC131;
    @FXML
    private Button image;
    @FXML
    private TextField FC21;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FC4.getItems().addAll("Dinar Tunisien TND","Dinar Algérien DA","Dirham Marocain","Dollar Américain $","Dollar Canadien CA","Euro £");
        FC5.getItems().addAll("Paiement On ligne ","Paysafecard Option","Paypal","par CB");
        FC6.getItems().addAll("Comptant","par Facilité");
       // FC11.getItems().addAll("Lac1(les berges du lac)","Sousse(proche du Mall of sousse)","Bizerte(centre)");
        
       /* CB_ToggleGroup = new ToggleGroup();
        this.FC7.setToggleGroup(CB_ToggleGroup);
        this.FC71.setToggleGroup(CB_ToggleGroup);
        this.FC72.setToggleGroup(CB_ToggleGroup);
        Paiement_ToggleGroup = new ToggleGroup();
        this.FC13.setToggleGroup(Paiement_ToggleGroup);
        this.FC131.setToggleGroup(Paiement_ToggleGroup);*/
        // TODO
    }    

    @FXML
    private void save(MouseEvent event) {
   conx = BDConnector.driverBD();
        
        
        String identifiant= FC1.getText();
        String nom_prenom = FC21.getText();
        String montant= FC2.getText();
        String date_paiement= String.valueOf(FC3.getValue());
        String devise= FC4.getValue().toString();
        String moyen_paiement= FC5.getValue().toString();
        String mode_paiement= FC6.getValue().toString();
       
       
//        String location= FC11.getValue().toString();
        String pays= FC12.getText();
        
        String color= FC14.getText();
        if(mode_paiement.isEmpty()||identifiant.isEmpty()||montant.isEmpty()||date_paiement.isEmpty()||devise.isEmpty()||moyen_paiement.isEmpty())
          
        { Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        }else if(!FC2.getText().matches("^[0-9\\s]*$"))
{   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("\"invalide nom : le champs montant ne contient que des chiffres\"");
            alert.showAndWait();}
else if(!FC12.getText().matches("^[a-zA-Z\\s]*$"))
{   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("\"invalide nom : le champs pays ne contient que des lettres\"");
            alert.showAndWait();} else {
            getQuery();
            insert();
        

        }
        
            
      
    
    
}

    private void getQuery() {
        if (update == false){
        query="INSERT INTO `facture`(`identifiant`, `nom_prenom`, `montant`, `date_paiement`, `devise`, `moyen_paiement`, `mode_paiement`, `type_cb`, `ncb`, `code_securite`, `date_expiration`, `location`, `pays`, `enabled`, `color`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        } else{  query = "UPDATE `facture` SET "
                    + "`identifiant`=?,"
                    + "`nom_prenom`=?,"
                    + "`montant`=?,"
                    + "`date_paiement`=?,"
                    +"`devise`=?,"
                    +"`moyen_paiement`=?,"
                    +"`mode_paiement`=?,"
                    +"`pays`=?,"
                    + "`color`= ? WHERE id = '"+factureId+"'";
        }
    }

    private void insert() {
        try {
        preparedStatement = conx.prepareStatement(query);
        preparedStatement.setString(1,FC1.getText());
        preparedStatement.setString(2,FC2.getText());
        preparedStatement.setString(3,FC21.getText());
        preparedStatement.setString(4,String.valueOf(FC3.getValue()));
        preparedStatement.setString(5,FC4.getValue().toString());
        preparedStatement.setString(6,FC5.getValue().toString());
        preparedStatement.setString(7,FC6.getValue().toString());
        
      
        //preparedStatement.setString(8,FC11.getValue().toString());
        preparedStatement.setString(8,FC12.getText());
        preparedStatement.setString(9,FC14.getText());
        preparedStatement.execute();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Facture Updated !");
            //alert.showingProperty();
            alert.showAndWait();
     } catch (SQLException ex) {
            Logger.getLogger(AddFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        
    }

    void setUpdate(boolean b) {
        this.update = b;
        
    }

    void setTextField(int id,String identifiant,String nom_prenom, String montant, LocalDate toLocalDate, String devise, String moyen_paiement, String mode_paiement, String pays, String color) {
       factureId = id;
        FC1.setText(identifiant);
        FC21.setText(nom_prenom);
        FC2.setText(montant);
        FC3.setValue(toLocalDate);
        FC4.setValue(devise);
        FC5.setValue(moyen_paiement);
        FC6.setValue(mode_paiement);
       
        
        
    
       
        FC12.setText(pays);
        
        FC14.setVisible(false);  
    }

    @FXML
    private void upload(ActionEvent event) {
    }

    

   


    
}
