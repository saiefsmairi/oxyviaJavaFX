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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class AjoutFXMLController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private DatePicker dpddd;
    @FXML
    private DatePicker dpddf;
    @FXML
    private TextField tfprix;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Evenement evenement = null;
    private boolean update;
    int evenementId;

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
    private void save(MouseEvent event) throws ParseException  {
        
        connection = DBconnect.getConnect();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String Nom=tfnom.getText();
        String Date_De_Debut=String.valueOf(dpddd.getValue());
        String Date_De_Fin=String.valueOf(dpddf.getValue());
        String Prix=tfprix.getText();
        if (Nom.isEmpty() || Date_De_Debut=="null" || Date_De_Fin=="null"||Prix.isEmpty()) {
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
            clear();

        
            Stage stageclose = (Stage)((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("FXMLEvenement.fxml"));
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
    private void clear() {
        tfnom.setText(null);
        dpddd.setValue(null);
        dpddf.setValue(null);
        tfprix.setText(null);
    }
    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `evenements`(`Nom`, `Date_De_Debut`, `Date_De_Fin`, `Prix`) VALUES (?,?,?,?)";

        }else{
            query = "UPDATE `evenements` SET"
                    +"`Nom`=?,"
                    +"`Date_De_Debut`=?,"
                    +"`Date_De_Fin`=?,"
                    +"`Prix`=? "
                    +"WHERE id = '"+evenementId+"'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tfnom.getText());
            preparedStatement.setString(2, String.valueOf(dpddd.getValue()));
            preparedStatement.setString(3, String.valueOf(dpddf.getValue()));
            preparedStatement.setString(4, tfprix.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    void setTextField(int ID, String Nom, LocalDate Date_De_Debut, LocalDate Date_De_Fin, double Prix) {
        evenementId=ID;
        tfnom.setText(Nom);
        dpddd.setValue(Date_De_Debut);
        dpddf.setValue(Date_De_Fin);
        tfprix.setText(Prix+"");
    }

    void setUpdate(boolean b) {
        this.update = b;

    }
    
}
