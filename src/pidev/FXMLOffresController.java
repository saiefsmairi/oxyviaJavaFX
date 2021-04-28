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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class FXMLOffresController implements Initializable {

    @FXML
    private ListView<Offres> listviewoffres;
    @FXML
    private Button btnajouter_offres;
    @FXML
    private Button btnmodifier_offres;
    @FXML
    private Button btnsupprimer_offres;
    @FXML
    private Button btnevenements;
    Connection connection=null;
    PreparedStatement preparedstatement=null;
    ResultSet resultSet=null;
    String query=null;
    Offres offre=null;
    ObservableList<Offres> listevoffres=FXCollections.observableArrayList();
    @FXML
    private TextField tfchercher_offre;
    @FXML
    private Button btnchercher_offre;

    private void refreshlist(){
        try {
            listevoffres.clear();
            query="SELECT * FROM `offres`";
            preparedstatement=connection.prepareStatement(query);
            resultSet=preparedstatement.executeQuery();
            while(resultSet.next()){
                listevoffres.add(new Offres(
                        resultSet.getInt("ID"),
                        resultSet.getString("Nom"),
                        resultSet.getDate("Date_De_Debut").toLocalDate(),
                        resultSet.getDate("Date_De_Fin").toLocalDate(),
                        resultSet.getString("Sujet"),
                        resultSet.getDouble("Prix")));
            }
            preparedstatement.close();
            resultSet.close();
            listviewoffres.setItems(listevoffres);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection=DBconnect.getConnect();
        refreshlist();
    }    

    @FXML
    private void ajouter_offres(MouseEvent event) {
        Stage stageclose = (Stage)((Node) event.getSource()).getScene().getWindow();
        stageclose.close();
        try {
            Parent parent=FXMLLoader.load(getClass().getResource("ajoutoffreFXML.fxml"));
            Scene scene=new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLOffresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier_offres() {
        offre = listviewoffres.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("ajoutoffreFXML.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLOffresController.class.getName()).log(Level.SEVERE, null, ex);
        }
                            
        AjoutoffreFXMLController ajoutOffreController=loader.getController();
        ajoutOffreController.setUpdate(true);
        ajoutOffreController.setTextField(offre.getID(), offre.getNom(), 
            offre.getDate_De_Debut(),offre.getDate_De_Fin(),offre.getSujet(), offre.getPrix());
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    private void supprimer_offres() {
        try {
            offre=listviewoffres.getSelectionModel().getSelectedItem();
            query="delete from offres where id="+offre.getID();
            connection=DBconnect.getConnect();
            preparedstatement=connection.prepareStatement(query);
            preparedstatement.execute();
            refreshlist();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gotoevenements(MouseEvent event) {
        Stage stageclose = (Stage)((Node) event.getSource()).getScene().getWindow();
        stageclose.close();
        try {
            Parent parent=FXMLLoader.load(getClass().getResource("FXMLEvenement.fxml"));
            Scene scene=new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLOffresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void chercher_offre(MouseEvent event) {
        String cherchernom=tfchercher_offre.getText();
        
        try {
            listevoffres.clear();
            query="SELECT * FROM `offres` WHERE Nom LIKE '"+cherchernom+"%'";
            preparedstatement=connection.prepareStatement(query);
            resultSet=preparedstatement.executeQuery();
            while(resultSet.next()){
                listevoffres.add(new Offres(
                        resultSet.getInt("ID"),
                        resultSet.getString("Nom"),
                        resultSet.getDate("Date_De_Debut").toLocalDate(),
                        resultSet.getDate("Date_De_Fin").toLocalDate(),
                        resultSet.getString("Sujet"),
                        resultSet.getDouble("Prix")));
            }
            preparedstatement.close();
            resultSet.close();
            listviewoffres.setItems(listevoffres);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}