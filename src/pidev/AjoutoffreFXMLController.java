/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;


import DB.DBconnect;
import Tables.Offres;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class AjoutoffreFXMLController implements Initializable {

    @FXML
    private TextField tfnomoffre;
    @FXML
    private DatePicker dpdddoffre;
    @FXML
    private DatePicker dpddfoffre;
    @FXML
    private TextField tfsujetoffre;
    @FXML
    private TextField tfprixoffre;

    /**
     * Initializes the controller class.
     */
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Offres offre = null;
    private boolean update;
    int offreId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public static boolean isDouble(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }

    @FXML
    private void save_offre(MouseEvent event) throws ParseException  {
        
        connection = DBconnect.getConnect();
        String Nom=tfnomoffre.getText();
        String Date_De_Debut=String.valueOf(dpdddoffre.getValue());
        String Date_De_Fin=String.valueOf(dpddfoffre.getValue());
        String Sujet=tfsujetoffre.getText();
        String Prix=tfprixoffre.getText();
        if (Nom.isEmpty() || Date_De_Debut=="null" || Date_De_Fin=="null"||Prix.isEmpty()||Sujet.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        else if (!isDouble(Prix)){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please INSERT number in Prix");
            alert.showAndWait();
            
        }
        else {
            getQuery();
            insert();
            clear_offre();

        
            Stage stageclose = (Stage)((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("FXMLOffres.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(AjoutFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }
        
    }

    @FXML
    private void clear_offre() {
        tfnomoffre.setText(null);
        dpdddoffre.setValue(null);
        dpddfoffre.setValue(null);
        tfsujetoffre.setText(null);
        tfprixoffre.setText(null);
    }
    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `offres`(`Nom`, `Date_De_Debut`, `Date_De_Fin`, `Sujet`, `Prix`) VALUES (?,?,?,?,?)";

        }else{
            query = "UPDATE `offres` SET"
                    +"`Nom`=?,"
                    +"`Date_De_Debut`=?,"
                    +"`Date_De_Fin`=?,"
                    +"`Sujet`=?,"
                    +"`Prix`=? "
                    +"WHERE id = '"+offreId+"'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tfnomoffre.getText());
            preparedStatement.setString(2, String.valueOf(dpdddoffre.getValue()));
            preparedStatement.setString(3, String.valueOf(dpddfoffre.getValue()));
            preparedStatement.setString(4, tfsujetoffre.getText());
            preparedStatement.setString(5, tfprixoffre.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    void setTextField(int ID, String Nom, LocalDate Date_De_Debut, LocalDate Date_De_Fin,String Sujet, double Prix) {
        offreId=ID;
        tfnomoffre.setText(Nom);
        dpdddoffre.setValue(Date_De_Debut);
        dpddfoffre.setValue(Date_De_Fin);
        tfsujetoffre.setText(Sujet);
        tfprixoffre.setText(Prix+"");
    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    
}