/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.gui;

import Oxyvia.entities.Facture;
import Oxyvia.utils.BDConnector;
import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private RadioButton FC7;
    @FXML
    private TextField FC12;
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
    private RadioButton FC71;
    private RadioButton FC72;
    private RadioButton FC131;
    @FXML
    private TextField FC21;
    @FXML
    private GridPane pannel;
    @FXML
    private Button pdf;
    @FXML
    private Button pdf1;
   
    
   
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FC4.getItems().addAll("Dinar Tunisien TND","Dinar Algérien DA","Dirham Marocain","Dollar Américain $","Dollar Canadien CA","Euro £");
        FC5.getItems().addAll("Paiement On ligne ","Paysafecard Option","visa","par CB");
        FC6.getItems().addAll("Comptant","par Facilité");
        //FC11.getItems().addAll("Lac1(les berges du lac)","Sousse(proche du Mall of sousse)","Bizerte(centre)");
        
       /* CB_ToggleGroup = new ToggleGroup();
        this.FC7.setToggleGroup(CB_ToggleGroup);
        this.FC71.setToggleGroup(CB_ToggleGroup);
        this.FC72.setToggleGroup(CB_ToggleGroup);
        Paiement_ToggleGroup = new ToggleGroup();
        this.FC13.setToggleGroup(Paiement_ToggleGroup);
        this.FC131.setToggleGroup(Paiement_ToggleGroup);*/
        
        
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
       /* if(this.CB_ToggleGroup.getSelectedToggle().equals(this.FC7))
        {String type_cb= FC7.getText();}
        else if (this.CB_ToggleGroup.getSelectedToggle().equals(this.FC71))
        {String type_cb= FC71.getText();}
        else 
        {String type_cb= FC72.getText();}*/
        /*String ncb= FC8.getText();
        String code_securite= FC9.getText();*/
       // String date_expiration= String.valueOf(FC10.getValue());
        //String location= FC11.getValue().toString();
        String pays= FC12.getText();
        /* if(this.Paiement_ToggleGroup.getSelectedToggle().equals(this.FC13))
         { String enabled= FC13.getText();}
          else
         { String enabled= FC131.getText();}*/
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
else if(!FC21.getText().matches("^[a-zA-Z\\s]*$"))
{   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("\"invalide nom : le champs nom et prénom ne contient que des lettres\"");
            alert.showAndWait();}
        else {
            getQuery();
            insert();
        

        }
        
            
      
    
    
}

    private void getQuery() {
        if (update == false){
        query="INSERT INTO `facture`(`identifiant`, `nom_prenom`, `montant`, `date_paiement`, `devise`, `moyen_paiement`, `mode_paiement`, `pays`, `color`) VALUES (?,?,?,?,?,?,?,?,?)";
        } else{  query = "UPDATE `facture` SET "
                    + "`identifiant`=?,"
                    +"`nom_prenom`=?,"
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
        preparedStatement.setString(1,(FC1.getText()));
        preparedStatement.setString(2,FC21.getText());
        preparedStatement.setString(3,FC2.getText());
        preparedStatement.setString(4,String.valueOf(FC3.getValue()));
        preparedStatement.setString(5,FC4.getValue().toString());
        preparedStatement.setString(6,FC5.getValue().toString());
        preparedStatement.setString(7,FC6.getValue().toString());
        /* if(this.CB_ToggleGroup.getSelectedToggle().equals(this.FC7))
         {
        preparedStatement.setString(8,FC7.getText());}
         else if(this.CB_ToggleGroup.getSelectedToggle().equals(this.FC71))
         {
        preparedStatement.setString(8,FC71.getText());}
        else
         {
        preparedStatement.setString(8,FC72.getText());}*/
//        preparedStatement.setString(9,FC8.getText());
//        preparedStatement.setString(10,FC9.getText());
//        preparedStatement.setString(11,String.valueOf(FC10.getValue()));
       // preparedStatement.setString(8,FC11.getValue().toString());
        preparedStatement.setString(8,FC12.getText());
        /*if(this.Paiement_ToggleGroup.getSelectedToggle().equals(this.FC13)){
        preparedStatement.setString(14,FC13.getText());}
        else{
            
         preparedStatement.setString(14,FC131.getText());}*/
        preparedStatement.setString(9,FC14.getText());
        
        preparedStatement.execute();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Your payment 's Well done You w'll receive SMS Notification later!  ");
            //alert.showingProperty();
            alert.showAndWait();
     } catch (SQLException ex) {
            Logger.getLogger(AddFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        
    }

    void setUpdate(boolean b) {
        this.update = b;
        
    }

    void setTextField(int id,String identifiant, String nom_prenom, String montant, LocalDate toLocalDate, String devise, String moyen_paiement, String mode_paiement, String pays, String color) {
       factureId = id;
        FC1.setText(identifiant);
       
        FC21.setText(nom_prenom);
        FC2.setText(montant);
        FC3.setValue(toLocalDate);
        FC4.setValue(devise);
        FC5.setValue(moyen_paiement);
        FC6.setValue(mode_paiement);
       
      /*  {FC7.setSelected(true);
        FC71.setSelected(false);
        // FC71.setSelected(true);}
        
        
         //FC72.setSelected(true);}
        FC72.setSelected(false);}*/
        /*FC8.setVisible(false);
        FC9.setVisible(false);
        FC10.setVisible(false);*/
        //FC11.setValue(location);
        FC12.setText(pays);
        /*FC13.setSelected(enabled);
        FC131.setSelected(enabled);*/
        FC14.setVisible(false);
        //FC14.setText(color);  
    }

    @FXML
    private void print(ActionEvent event) {
        
        
         Printer printer = Printer.getDefaultPrinter();

        // Print page layout object.
        // Set "LANDSCAPE" as the page orientation for the convenience for the test.
        //   If the output pdf has the text information, the output file is shown in a PORTRAIT mode.
        //   If not, it will be shown in a LANDSCAPE mode.
        PageLayout layout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);

        // Create a printer job.
        PrinterJob  job = PrinterJob.createPrinterJob();

        if (job != null) {
            // Set the job name.
            job.getJobSettings().setJobName("SOLUTION");

            if (job.showPrintDialog(this.pannel.getScene().getWindow())) {
                // Print out the specified pane.
                job.printPage(layout,this.pannel);
                
                
            }
            else {
                System.out.println("Print canceled.");
            }

            // Finish the print job.
            job.endJob();
        }
    }

    @FXML
    private void payer(ActionEvent event) {
        
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
    
    
    
}