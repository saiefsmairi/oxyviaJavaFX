/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import DB.DBconnect;
import Tables.Offres;
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
public class FXMLfront_offreController implements Initializable {

    @FXML
    private Button btntrie_offre;
    @FXML
    private Label labeloffre;
    Connection connection=null;
    PreparedStatement preparedstatement=null;
    ResultSet resultSet=null;
    String query=null;
    Offres offre=null;
    ObservableList<Offres> listevoffres=FXCollections.observableArrayList();
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection=DBconnect.getConnect();
        refreshlist();
        for(Offres o:listevoffres){
            labeloffre.setText(labeloffre.getText()+o.getNom()+":"+"\n"+"Date Début: "+o.getDate_De_Debut()+getSpace(3)+"Date Fin: "+o.getDate_De_Fin()+getSpace(3)+"Prix: "+o.getPrix()+"\n"+"Sujet: "+o.getSujet()+"\n");
        }
    }    

    @FXML
    private void trierlist_offre(MouseEvent event) {
        FXCollections.sort(listevoffres);
        labeloffre.setText("");
        for(Offres o:listevoffres){
            labeloffre.setText(labeloffre.getText()+o.getNom()+":"+"\n"+"Date Début: "+o.getDate_De_Debut()+getSpace(3)+"Date Fin: "+o.getDate_De_Fin()+getSpace(3)+"Prix: "+o.getPrix()+"\n"+"Sujet: "+o.getSujet()+"\n");
        }
    }
    
}
