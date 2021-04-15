/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.gui;

import Oxyvia.entities.Depense;

import Oxyvia.utils.BDConnector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author smp
 */
public class AddDepenseController implements Initializable {

    @FXML
    private TextField FC1;
    @FXML
    private TextField FC3;
    @FXML
    private TextField FC5;
    @FXML
    private TextField FC6;
    @FXML
    private ComboBox FC7;
    @FXML
    private ComboBox FC8;
    @FXML
    private ComboBox FC9;
    @FXML
    private DatePicker FC10;
    @FXML
    private TextField FC2;
    private TextField FC11;
    @FXML
    private RadioButton FC12;
    @FXML
    private RadioButton FC13;
    
      String query=null;
    Connection conx = null;
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    Depense depense = null;
    private boolean update;
    int depenseId ;
    private ToggleGroup Paiement_ToggleGroup;
    @FXML
    private Button image;
    @FXML
    private ImageView imgg;
     ArrayList<String> typee;
    @FXML
    private TextField FC4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //FC1.getItems().addAll("98765");
        FC7.getItems().addAll("Exelente","Acceptable","Insuffisante");
        FC8.getItems().addAll("Bonne","90%","80%","70%","Moyenne","60%","70%","80%","Faible","30%","20%","10%");
        FC9.getItems().addAll("Absenté (Absence personnel)","Absenté (Absence personnelisé)","Absenté (Suite au maladie)");
        Paiement_ToggleGroup = new ToggleGroup();
        this.FC12.setToggleGroup(Paiement_ToggleGroup);
        this.FC13.setToggleGroup(Paiement_ToggleGroup);
        typee =new ArrayList();
        typee.add("*.jpg");
         typee.add("*.png");
        
    }    

    @FXML
    private void save(MouseEvent event) throws FileNotFoundException {
        
        conx = BDConnector.driverBD();
        
        
        //String id_personnel_id= FC1.getValue().toString();
        String id_personnel_id= FC1.getText();
        String nom_prenom= FC2.getText();
        String email= FC3.getText();
        String picture= FC4.getText();
        FileInputStream fis=new FileInputStream(new File(immg));
        String occupation= FC5.getText();
        String salaire= FC6.getText();
      
        String horaire_reguliere= FC7.getValue().toString();
        
        String horaire_sup= FC8.getValue().toString();
        String exempte= FC9.getValue().toString();
        String date_depense= String.valueOf(FC10.getValue());
           
     
       
         if(this.Paiement_ToggleGroup.getSelectedToggle().equals(this.FC12))
         { String enabled= FC12.getText();}
          else
         { String enabled= FC13.getText();}
         
       
        if(nom_prenom.isEmpty()||id_personnel_id.isEmpty()||email.isEmpty()||occupation.isEmpty()||salaire.isEmpty()||date_depense.isEmpty())
          
        { Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else if(!FC5.getText().matches("^[a-zA-Z\\s]*$"))
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
            alert.showAndWait();}
    //Error("invalide description : le champs description ne contient que des lettres");}
        else {
            getQuery();
            insert();
        

        }
        
            
      
    
    
}

    private void getQuery() {
        if (update == false){
        query="INSERT INTO `compte_personnel`(`id_personnel_id`, `nom_prenom`, `email`, `picture`, `occupation`, `salaire`, `horaire_reguliere`, `horaire_sup`, `exempte`, `date_depense`, `enabled`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        } else{  query = "UPDATE `compte_personnel` SET "
                    + "`id_personnel_id`=?,"
                    + "`nom_prenom`=?,"
                    + "`email`=?,"
                    +"`picture`=?,"
                    +"`occupation`=?,"
                    +"`salaire`=?,"
                    +"`horaire_reguliere`=?,"
                    +"`horaire_sup`=?,"
                    +"`exempte`=?,"
                    +"`date_depense`=?,"
                    + "`enabled`= ? WHERE id = '"+depenseId+"'";
        }
    }
    private boolean validateEmail() {
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
        
    }

    private void insert() throws FileNotFoundException {
                if(validateEmail()){
        try {
        preparedStatement = conx.prepareStatement(query);
        preparedStatement.setString(1,FC1.getText());
        //preparedStatement.setString(1,String.valueOf(FC1.getValue()));
        preparedStatement.setString(2,FC2.getText());
        preparedStatement.setString(3,FC3.getText());
        preparedStatement.setString(4,FC4.getText());
        //FileInputStream fis=new FileInputStream(new File(immg));
        preparedStatement.setString(5,FC5.getText());
        preparedStatement.setString(6,FC6.getText());
        preparedStatement.setString(7,FC7.getValue().toString());
        preparedStatement.setString(8,FC8.getValue().toString());
        preparedStatement.setString(9,FC9.getValue().toString());
        preparedStatement.setString(10,String.valueOf(FC10.getValue()));
       
        if(this.Paiement_ToggleGroup.getSelectedToggle().equals(this.FC12)){
        preparedStatement.setString(11,FC12.getText());}
        else{
            
         preparedStatement.setString(11,FC13.getText());}
        
        
        preparedStatement.execute();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Depense added!");
            //alert.showingProperty();
            alert.showAndWait();
     } catch (SQLException ex) {
            Logger.getLogger(AddDepenseController.class.getName()).log(Level.SEVERE, null, ex);
        }   
                }
        
    }

    void setUpdate(boolean b) {
        this.update = b;
        
    }

    

    void setTextField(int id,String id_personnel_id, String nom_prenom, String email, String picture, String occupation, String salaire, String horaire_reguliere, String horaire_sup, String exempte, LocalDate toLocalDate, String enabled) {
        depenseId = id;
        FC1.setText(id_personnel_id);
       // FC1.setValue(id_personnel_id);
        FC2.setText(nom_prenom);
        FC3.setText(email);   //C:/Users/smp/Documents/3Aa/Semestre  2/Java Projet/pi/pi/src
        //imgg.setImage(new Image("file:/C:/Users/smp/Documents/3Aa/Semestre  2/Java Projet/pi/pi/src/"+picture));
        FC4.setText(picture);
        FC5.setText(occupation);
        FC6.setText(salaire);
       //      //imgg.setImage(x.getImage());

        FC7.setValue(horaire_reguliere);
        
        // FC71.setSelected(true);}
        
        
         //FC72.setSelected(true);}
     
        FC8.setValue(horaire_sup);
       
        FC9.setValue(exempte);
       
       
       FC10.setValue(toLocalDate);        
        FC12.setSelected(true);
        //FC13.setV(false);
        FC13.setSelected(false);
    }
    String img;
 String immg="";
    String immmg="";
    @FXML
    private void upload(ActionEvent event) {
        FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpg,png", typee));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
             img=fc.getAbsoluteFile().toURI().toString();
             immg=fc.getPath();
             immmg=fc.getName();
             System.out.println(img);
          // System.out.print(img);
             Image i = new Image(img);
           imgg.setImage(i);


   
    }

}

    @FXML
    private void clean(MouseEvent event) {
    }

    
}