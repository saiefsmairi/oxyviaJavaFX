/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import DB.DBconnect;
import Tables.Evenement;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLfrontController implements Initializable {

    Connection connection=null;
    PreparedStatement preparedstatement=null;
    ResultSet resultSet=null;
    String query=null;
    Evenement evenement=null;
    ObservableList<Evenement> listevenements=FXCollections.observableArrayList();
    @FXML
    private Label labelevenement;
    @FXML
    private Button btntrie;

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
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    public String getSpace(int count)
    {
      String space="";
      for(int i=0;i<count;i++)
            space+=" ";
       return space;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection=DBconnect.getConnect();
        refreshlist();
        for(Evenement e:listevenements){
            labelevenement.setText(labelevenement.getText()+"\n"+e.getNom()+":"+"\n"+"Date Début:"+e.getDate_De_Debut()+getSpace(3)+"Date Fin:"+e.getDate_De_Fin()+getSpace(3)+"Prix:"+e.getPrix()+"\n");
        } 
    }

    @FXML
    private void trierlist(MouseEvent event) {
        FXCollections.sort(listevenements);
        labelevenement.setText("");
        for(Evenement e:listevenements){
            labelevenement.setText(labelevenement.getText()+"\n"+e.getNom()+":"+"\n"+"Date Début:"+e.getDate_De_Debut()+getSpace(3)+"Date Fin:"+e.getDate_De_Fin()+getSpace(3)+"Prix:"+e.getPrix()+"\n");
        }
    }
    
}
