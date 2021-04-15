/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.gui;

import Oxyvia.entities.Facture;
import Oxyvia.utils.BDConnector;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author smp
 */
public class AfficheFactureController implements Initializable {

    @FXML
    private TableView<Facture> tableFacture;
    @FXML
    private TableColumn<Facture, String> c1Col;
    @FXML
    private TableColumn<Facture, String> c2Col;
    @FXML
    private TableColumn<Facture, String> c3Col;
    @FXML
    private TableColumn<Facture, String> c4Col;
    @FXML
    private TableColumn<Facture, String> c5Col;
    @FXML
    private TableColumn<Facture, String> c6Col;
    @FXML
    private TableColumn<Facture, String> c7Col;
    @FXML
    private TableColumn<Facture, String> c8Col;
    @FXML
    private TableColumn<Facture, String> c9Col;
    @FXML
    private TableColumn<Facture, String> c10Col;
    @FXML
    private TableColumn<Facture, String> c11Col;
    @FXML
    private TableColumn<Facture, String> c12Col;
    @FXML
    private TableColumn<Facture, String> c13Col;
    @FXML
    private TableColumn<Facture, String> c14Col;
   
    
    String query=null;
    Connection conx = null;
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    Facture facture = null;
    ObservableList<Facture> FactureList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Facture, String> c15Col;
    @FXML
    private TableColumn<Facture, String> editCol;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDate(); 
        // TODO
    }    

    private void loadDate() {
        
        conx = BDConnector.driverBD();
        refreshTable();
        
       c1Col.setCellValueFactory(new PropertyValueFactory<>("id"));
       c2Col.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
       c3Col.setCellValueFactory(new PropertyValueFactory<>("montant"));
       c4Col.setCellValueFactory(new PropertyValueFactory<>("date_paiement"));
       c5Col.setCellValueFactory(new PropertyValueFactory<>("devise"));
       c6Col.setCellValueFactory(new PropertyValueFactory<>("moyen_paiement"));
       c7Col.setCellValueFactory(new PropertyValueFactory<>("mode_paiement"));
       c8Col.setCellValueFactory(new PropertyValueFactory<>("type_cb"));
       c9Col.setCellValueFactory(new PropertyValueFactory<>("ncb"));
       c10Col.setCellValueFactory(new PropertyValueFactory<>("code_securite"));
       c11Col.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
       c12Col.setCellValueFactory(new PropertyValueFactory<>("location"));
       c13Col.setCellValueFactory(new PropertyValueFactory<>("pays"));
       c14Col.setCellValueFactory(new PropertyValueFactory<>("enabled"));
       c15Col.setCellValueFactory(new PropertyValueFactory<>("color"));
       
       // add cell of button edit
        //add cell of button edit 
         Callback<TableColumn<Facture, String>, TableCell<Facture, String>> cellFactory = (TableColumn<Facture, String> param) -> {
            // make cell containing buttons
            final TableCell<Facture, String> cell = new TableCell<Facture, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                             //pour supprimer 
                            Alert alert = new Alert( Alert.AlertType.CONFIRMATION) ;
                            alert.setTitle("Confirmation de suppression  !!");
                            alert.setHeaderText(null);
                            alert.setContentText("Voulez-vous vraiment supprimer ");
                            Optional<ButtonType> action = alert.showAndWait();
                            if ( action.get() == ButtonType.OK) {
                            
                            try {
                                facture = tableFacture.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `facture` WHERE id  ="+facture.getId();
                               conx = BDConnector.driverBD();
                                preparedStatement = conx.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                                //search_cinema();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(AfficheFactureController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            
} //fin supp                         
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            
                            
                                 
                            
                            facture = tableFacture.getSelectionModel().getSelectedItem();
                  
                             FXMLLoader loader = new FXMLLoader ();
                             loader.setLocation(getClass().getResource("/Oxyvia/gui/UpdateFacture.fxml"));
                            try {
                                loader.load();
                              
                            } catch (IOException ex) {
                                Logger.getLogger(AfficheFactureController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            UpdateFactureController addFactureController = loader.getController();
                            addFactureController.setUpdate(true);
                           // addFactureController.setTextField(0, item, item, LocalDate.MAX, item, item, item, item, item, item, LocalDate.MAX, item, item, empty, item);
                            addFactureController.setTextField(facture.getId(),facture.getIdentifiant(), facture.getMontant(), facture.getDate_paiement().toLocalDate(), facture.getDevise(), facture.getMoyen_paiement(), facture.getMode_paiement(), facture.getType_cb(), facture.getNcb(), facture.getCode_securite(), facture.getDate_expiration().toLocalDate(), facture.getLocation(), facture.getPays(), facture.isEnabled(), facture.getColor());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            
                            
                            
                            
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         editCol.setCellFactory(cellFactory);
         tableFacture.setItems(FactureList);
         
    
    }
       
               
      
       
       
       
            


       
       
       
       
       
       
       
       
       
       
       //tableFacture.setItems(FactureList);
       
    


    @FXML
    private void refreshTable() {
        try {
            FactureList.clear();
            query= "SELECT * FROM `facture`";
            preparedStatement = conx.prepareStatement(query);
            result = preparedStatement.executeQuery();
            
            while(result.next()){
            FactureList.add(new Facture(result.getInt("id"), 
                    result.getString("identifiant"), 
                    result.getString("montant"), 
                    result.getDate("date_paiement"), 
                    result.getString("devise"), 
                    result.getString("moyen_paiement"), 
                    result.getString("mode_paiement"), 
                    result.getString("type_cb"), 
                    result.getString("ncb"), 
                    result.getString("code_securite"), 
                    result.getDate("date_expiration"), 
                    result.getString("location"), 
                    result.getString("pays"), 
                    result.getBoolean("enabled"), 
                    result.getString("color")
            ));
            
            tableFacture.setItems(FactureList);
            
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficheFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addNew(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/addFacture.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficheFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
