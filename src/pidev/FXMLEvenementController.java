/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import DB.DBconnect;
import Tables.Evenement;
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
 * @author asus
 */
public class FXMLEvenementController implements Initializable {

    @FXML
    private ListView<Evenement> listviewevenement;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnoffres;
    Connection connection=null;
    PreparedStatement preparedstatement=null;
    ResultSet resultSet=null;
    String query=null;
    Evenement evenement=null;
    ObservableList<Evenement> listevenements=FXCollections.observableArrayList();
    @FXML
    private TextField tfchercher;
    @FXML
    private Button btnchercher;
    private void refreshlist(){
        try {
            listevenements.clear();
            query="SELECT * FROM `evenements`";
            preparedstatement=connection.prepareStatement(query);
            resultSet=preparedstatement.executeQuery();
            while(resultSet.next()){
                listevenements.add(new Evenement(
                        resultSet.getInt("ID"),
                        resultSet.getString("Nom"),
                        resultSet.getDate("Date_De_Debut").toLocalDate(),
                        resultSet.getDate("Date_De_Fin").toLocalDate(),
                        resultSet.getDouble("Prix")));
            }
            preparedstatement.close();
            resultSet.close();
            listviewevenement.setItems(listevenements);
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
    private void ajouter(MouseEvent event) {
        Stage stageclose = (Stage)((Node) event.getSource()).getScene().getWindow();
        stageclose.close();
        try {
            Parent parent=FXMLLoader.load(getClass().getResource("ajoutFXML.fxml"));
            Scene scene=new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(MouseEvent event) {
        evenement = listviewevenement.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("ajoutFXML.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
                            
        AjoutFXMLController ajoutEvenementController=loader.getController();
        ajoutEvenementController.setUpdate(true);
        ajoutEvenementController.setTextField(evenement.getID(), evenement.getNom(), 
            evenement.getDate_De_Debut(),evenement.getDate_De_Fin(), evenement.getPrix());
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    private void supprimer(MouseEvent event) {
        try {
            evenement=listviewevenement.getSelectionModel().getSelectedItem();
            query="delete from evenements where id="+evenement.getID();
            connection=DBconnect.getConnect();
            preparedstatement=connection.prepareStatement(query);
            preparedstatement.execute();
            refreshlist();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void offres(MouseEvent event) {
        Stage stageclose = (Stage)((Node) event.getSource()).getScene().getWindow();
        stageclose.close();
        try {
            Parent parent=FXMLLoader.load(getClass().getResource("FXMLOffres.fxml"));
            Scene scene=new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void chercher(MouseEvent event) {
        String cherchernom=tfchercher.getText();
        
        try {
            listevenements.clear();
            query="SELECT * FROM `evenements` WHERE Nom LIKE '"+cherchernom+"%'";
            preparedstatement=connection.prepareStatement(query);
            resultSet=preparedstatement.executeQuery();
            while(resultSet.next()){
                listevenements.add(new Evenement(
                        resultSet.getInt("ID"),
                        resultSet.getString("Nom"),
                        resultSet.getDate("Date_De_Debut").toLocalDate(),
                        resultSet.getDate("Date_De_Fin").toLocalDate(),
                        resultSet.getDouble("Prix")));
            }
            preparedstatement.close();
            resultSet.close();
            listviewevenement.setItems(listevenements);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
