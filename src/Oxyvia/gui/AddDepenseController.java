/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.gui;

import Oxyvia.Service.comptePersonnel_service;
import Oxyvia.entities.ComptePersonnel;
import Oxyvia.entities.Depense;

import Oxyvia.utils.BDConnector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author smp
 */
public class AddDepenseController implements Initializable {

    @FXML
    private ComboBox FC1;
    @FXML
    private TextField FC3;
    @FXML
    private ComboBox FC5;
    @FXML
    private ComboBox FC6;
    @FXML
    private ComboBox FC7;
    @FXML
    private DatePicker FC8;
    @FXML
    private TextField FC9;
    @FXML
    private TextField FC10;
    @FXML
    private TextField FC2;
    private RadioButton FC11;
    private RadioButton FC12;
    @FXML
    private TextField FC13;
    
      String query=null;
    Connection conx = null;
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    Depense depense = null;
    private boolean update;
    int depenseId ;
    private ToggleGroup Paiement_ToggleGroup;
    @FXML
    private ImageView imgg;
     ArrayList<String> typee;
    @FXML
    private TextField FC4;
    @FXML
    private AnchorPane pane_login;
    @FXML
    private Button start;
    @FXML
    private Button pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // Depense dep = new Depense() ;
       // ComptePersonnel cmp =new ComptePersonnel();
        /*ObservableList<String> list;
        list = FXCollections.observableArrayList(this.ListerNom());
        FC1.setItems(list);*/
        
        //FC1.getItems().addAll("56","54","48","47");
        FC5.getItems().addAll("Exelente","Acceptable","Insuffisante");
        FC6.getItems().addAll("Bonne","90%","80%","70%","Moyenne","60%","70%","80%","Faible","30%","20%","10%");
        FC7.getItems().addAll("présent et pnctuelle ","Absenté (Absence personnel)","Absenté (Absence personnelisé)","Absenté (Suite au maladie)");
        Paiement_ToggleGroup = new ToggleGroup();
        /*this.FC11.setToggleGroup(Paiement_ToggleGroup);
        this.FC12.setToggleGroup(Paiement_ToggleGroup);*/
        typee =new ArrayList();
        typee.add("*.jpg");
         typee.add("*.png");
         comptePersonnel_service cmp =new comptePersonnel_service ();
         ObservableList<String> list;
        list = FXCollections.observableArrayList(cmp.ListerID());
        FC1.setItems(list);
         
       
      
        
        
    }    
    
   
    /*public int getIdNom(int idd){
      ComptePersonnel h=new ComptePersonnel();
      int id =0;
       try {
            Statement stmt = conx.createStatement();
            String sql = "SELECT * FROM compte_personnel where id='"+idd+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
             
                  id = rs.getInt("id");
                  h=new ComptePersonnel(id);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
       return id;
  }
*/
    
      public ArrayList<String> ListerNom() {
 ArrayList<String> res = new ArrayList<String>();
        try {
            Statement stmt = conx.createStatement();
            String sql = "SELECT * FROM compte_personnel";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                
                String identifiant_id = rs.getString("identifiant_id");
                
                
                res.add(identifiant_id);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;    
    }
    
    
    
    
    
    
    
    
    @FXML
    private void save(MouseEvent event) throws FileNotFoundException {
        
        conx = BDConnector.driverBD();
         
       // int id_personnel_id = this.getIdPersonnall((String) FC1.getValue());
       String email = FC1.getValue().toString();
               
       // String id_personnel_id=  cmp.getId_personnel(FC1.getText());
       // hots.getIdNom(hoteltext.getValue())
        String picture= FC2.getText();
        
        
        String imggg = FC2.getText();
        String ch="/Oxyvia/images/";
        String imgF= ch+imggg;
        
//        Image imageF = new Image(getClass().getResourceAsStream(imgF));
  //      imgg.setImage(imageF);
        
        
        
        String occupation= FC3.getText();
        String salaire= FC4.getText();
      
        String horaire_reguliere= FC5.getValue().toString();
        
        String horaire_sup= FC6.getValue().toString();
        String exempte= FC7.getValue().toString();
     
         String date_depense= FC8.getValue().toString();
         String nom= FC9.getText(); 
         String prenom = FC10.getText();
       
         /*if(this.Paiement_ToggleGroup.getSelectedToggle().equals(this.FC11))
         { String enabled= FC11.getText();}
          else
         { String enabled= FC12.getText();}
         */
         String color = FC13.getText();
        if(occupation.isEmpty()||salaire.isEmpty()||prenom.isEmpty()||nom.isEmpty()||date_depense.isEmpty())
          
        { Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } /*else if(!FC5.getText().matches("^[a-zA-Z\\s]*$"))
{    Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("\"invalide nom : le champs occupation ne contient que des lettres\"");
            alert.showAndWait();
   }
else if(!FC2.getText().matches("^[a-zA-Z\\s]*$"))
{   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("\"invalide nom : le champs nom et prénom ne contient que des lettres\"");
            alert.showAndWait();}
else if(!FC6.getText().matches("^[0-9\\s]*$"))
{   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("\"invalide nom : le champs salaire ne contient que des chiffres\"");
            alert.showAndWait();} */
    //Error("invalide description : le champs description ne contient que des lettres");}
        else {
            getQuery();
            insert();
        

        }
        
            
      
    
    
}

    private void getQuery() {
        if (update == false){
        query="INSERT INTO `depense`(`email`,`picture`, `occupation`, `salaire`, `horaire_reguliere`, `horaire_sup`, `exempte`, `date_depense`, `nom`, `prenom`, `color`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        } else{  query = "UPDATE `depense` SET "
                    + "`email`=?,"
                    + "`picture`=?,"
                    + "`occupation`=?,"
                    +"`salaire`=?,"
                    +"`horaire_reguliere`=?,"
                    +"`horaire_sup`=?,"
                    +"`exempte`=?,"
                    +"`date_depense`=?,"
                    + "`nom`= ?,"
                    + "`prenom`= ?,"
                    + "`color`= ? WHERE id = '"+depenseId+"'";
                   
        }
    }
   /* private boolean validateEmail() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(FC3.getText());
        if(m.find() && m.group().equals(FC3.getText())){
            return true;
        }else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("please Enter Valid Email");
            alert.showAndWait();
            
            return false;
        }
        
    }*/

    private void insert() throws FileNotFoundException {
               //if(validateEmail()){
               ComptePersonnel cmp=new ComptePersonnel();
        try {
        preparedStatement = conx.prepareStatement(query);
        preparedStatement.setString(1,FC1.getValue().toString());
       // preparedStatement.setString(1,String.valueOf(FC1.getText()));
        preparedStatement.setString(2,FC2.getText());
        preparedStatement.setString(3,FC3.getText());
        preparedStatement.setString(4,FC4.getText());
        preparedStatement.setString(5,FC5.getValue().toString());
        preparedStatement.setString(6,FC6.getValue().toString());
        preparedStatement.setString(7,FC7.getValue().toString());
        preparedStatement.setString(8,FC8.getValue().toString());
        preparedStatement.setString(9,FC9.getText());
        preparedStatement.setString(10,FC10.getText());
       // preparedStatement.setString(10,String.valueOf(FC10.getValue()));
       
        /*if(this.Paiement_ToggleGroup.getSelectedToggle().equals(this.FC11)){
        preparedStatement.setString(11,FC11.getText());}
        else{
            
         preparedStatement.setString(11,FC12.getText());}*/
         preparedStatement.setString(11,FC13.getText());
        
        preparedStatement.execute();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Depense added!");
            //alert.showingProperty();
            alert.showAndWait();
     } catch (SQLException ex) {
            Logger.getLogger(AddDepenseController.class.getName()).log(Level.SEVERE, null, ex);
        }   
                //}
        
    }

    void setUpdate(boolean b) {
        this.update = b;
        
    }

    

   
    String img;
 String immg="";
    String immmg="";

  

    void setTextField(int id, String email, String picture, String occupation, String salaire, String horaire_reguliere, String horaire_sup, String exempte, String date_depense, String nom, String prenom, String color) {
          depenseId = id;
        FC1.setValue(email);
        FC2.setText(picture);
        FC3.setText(occupation);   
        FC4.setText(salaire);
        FC5.setValue(horaire_reguliere);
        FC6.setValue(horaire_sup);
       //      

        FC7.setValue(exempte);
        
       
     
        //FC8.setValue(date_depense);
       
        FC9.setText(nom);
        FC10.setText(prenom);
       
          
        /*FC11.setSelected(true);
        //FC13.setV(false);
        FC12.setSelected(false);*/
        FC13.setText(color);
          /* String imggg =  picture ;
        String ch="/Oxyvia/image/";
        String imgF= ch+imggg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        imgg.setImage(imageF);*/
    }

   

   

   /* void setTextField(String nom, String prenom, String tel, String occupation, String salaire_annuel) {
        //depenseId = id;
        //String nom, String prenom, String tel, String occupation, String salaire_annuel
        FC2.setText(tel);
        FC3.setText(occupation);   
        FC4.setText(salaire_annuel);
        FC9.setText(nom);
        FC10.setText(prenom);
       
          
        
    }*/

    

    void setTextField(int id,String email,String nom, String prenom, String tel, String occupation, String salaire_annuel) {
        //depenseId = id;
       FC1.setValue(email);
        FC2.setText(tel);
        FC3.setText(occupation);   
        FC4.setText(salaire_annuel);
       
        
       
     
        //FC8.setValue(date_depense);
       
        FC9.setText(nom);
        FC10.setText(prenom);
       
          
        /*FC11.setSelected(true);
        //FC13.setV(false);
        FC12.setSelected(false);*/
       
    }

    @FXML
    private void payer2(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/payer2.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

            if (job.showPrintDialog(this.pane_login.getScene().getWindow())) {
                // Print out the specified pane.
                job.printPage(layout,this.pane_login);
                
                
            }
            else {
                System.out.println("Print canceled.");
            }

            // Finish the print job.
            job.endJob();
        }
    }

    

    

    

    

    

   

  

    
}