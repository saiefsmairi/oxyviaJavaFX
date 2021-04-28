/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.gui;

import Oxyvia.utils.BDConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author smp
 */
public class LoginController implements Initializable {

    private TextField txt_username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button btn_login;
    @FXML
    private AnchorPane pane_login;
    @FXML
    private AnchorPane pane_signup;
    @FXML
    private TextField txt_username_up;
    @FXML
    private TextField txt_password_up;
    private ComboBox type_up;
     Connection conx = null;
     ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private TextField txt_email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              // type_up.getItems().addAll("comptable","responsable touristique","responsable reservation");
        // TODO
    }    

    @FXML
    private void LoginpaneShow(ActionEvent event) {
        pane_login.setVisible(true);
        pane_signup.setVisible(false);
    }

    @FXML
    private void SignuppaneShow(ActionEvent event) {
         pane_login.setVisible(false);
        pane_signup.setVisible(true);
    }

    @FXML
    private void Login(ActionEvent event) {
        conx = BDConnector.driverBD();
        String sql = "Select * from administrator where email = ? and password = ? ";
        try {
            pst = conx.prepareStatement(sql);
            pst.setString(1, txt_email.getText());
            pst.setString(2, txt_password.getText());
            
            rs = pst.executeQuery();
            
            if(rs.next()){
                
                JOptionPane.showMessageDialog(null, "E-mail And Password are Correct");
                
                btn_login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/Dashboard.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
               
                
            }else
                JOptionPane.showMessageDialog(null, "Invalide E-mail Or Password");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void jLabel3MousePressed(ActionEvent event) {
          Sendcode sc = new Sendcode();

sc.setVisible(true);
    }

    @FXML
    private void add_users(ActionEvent event) {
        
          conx = BDConnector.driverBD();
        String sql = "insert into administrator (email,password) values (?,?)";
        try {
            pst = conx.prepareStatement(sql);
            pst.setString(1, txt_username_up.getText());
            //pst.setString(2, type_up.getValue().toString());
            pst.setString(2, txt_password_up.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Saved");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }  
    }
    
}
