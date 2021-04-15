/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.gui;

import Oxyvia.entities.Depense;


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
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author smp
 */
public class AfficheDepenseController implements Initializable {

    @FXML
    private TableView<Depense> tableDepense;
    @FXML
    private TableColumn<Depense, String> c1Col;
    @FXML
    private TableColumn<Depense, String> c2Col;
    @FXML
    private TableColumn<Depense, String> c3Col;
    @FXML
    private TableColumn<Depense, String> c4Col;
    @FXML
    private TableColumn<Depense, String> c5Col;
    @FXML
    private TableColumn<Depense, String> c6Col;
    @FXML
    private TableColumn<Depense, String> c7Col;
    @FXML
    private TableColumn<Depense, String> c8Col;
    @FXML
    private TableColumn<Depense, String> c9Col;
    @FXML
    private TableColumn<Depense, String> c10Col;
    @FXML
    private TableColumn<Depense, String> c11Col;
    private TableColumn<Depense, String> c13Col;
    @FXML
    private TableColumn<Depense, String> editCol;
    
    String query=null;
    Connection conx = null;
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    Depense depense = null;
    ObservableList<Depense> DepenseList = FXCollections.observableArrayList();
   
    @FXML
    private TableColumn<Depense, String> c12Col;

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
       c2Col.setCellValueFactory(new PropertyValueFactory<>("id_personnel_id"));
       c3Col.setCellValueFactory(new PropertyValueFactory<>("nom_prenom"));
       c4Col.setCellValueFactory(new PropertyValueFactory<>("email"));
       c5Col.setCellValueFactory(new PropertyValueFactory<>("picture"));
       c6Col.setCellValueFactory(new PropertyValueFactory<>("occupation"));
       c7Col.setCellValueFactory(new PropertyValueFactory<>("salaire"));
       c8Col.setCellValueFactory(new PropertyValueFactory<>("horaire_reguliere"));
       c9Col.setCellValueFactory(new PropertyValueFactory<>("horaire_sup"));
       c10Col.setCellValueFactory(new PropertyValueFactory<>("exempte"));
       c11Col.setCellValueFactory(new PropertyValueFactory<>("date_depense"));
       c12Col.setCellValueFactory(new PropertyValueFactory<>("enabled"));
       
      
  
       
       // add cell of button edit
        //add cell of button edit 
         Callback<TableColumn<Depense, String>, TableCell<Depense, String>> cellFactory;
        cellFactory = (TableColumn<Depense, String> param) -> {
            // make cell containing buttons
            final TableCell<Depense, String> cell = new TableCell<Depense, String>() {
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
                                    depense = tableDepense.getSelectionModel().getSelectedItem();
                                    query = "DELETE FROM `compte_personnel` WHERE id  ="+depense.getId();
                                    conx = BDConnector.driverBD();
                                    preparedStatement = conx.prepareStatement(query);
                                    preparedStatement.execute();
                                    refreshTable();
                                    //search_cinema();
                                    
                                } catch (SQLException ex) {
                                    Logger.getLogger(AfficheDepenseController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                
                            } //fin supp
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            
                            
                            
                            
                            depense = tableDepense.getSelectionModel().getSelectedItem();
                            
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/Oxyvia/gui/addDepense.fxml"));
                            try {
                                loader.load();
                              
                            } catch (IOException ex) {
                                Logger.getLogger(AfficheDepenseController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            AddDepenseController addDepenseController = loader.getController();
                            addDepenseController.setUpdate(true);
                            // addFactureController.setTextField(0, item, item, LocalDate.MAX, item, item, item, item, item, item, LocalDate.MAX, item, item, empty, item);
                            addDepenseController.setTextField(depense.getId(), depense.getId_personnel_id(), depense.getNom_prenom(), depense.getEmail(), depense.getPicture(), depense.getOccupation(), depense.getSalaire(),depense.getHoraire_reguliere(), depense.getHoraire_sup(), depense.getExempte(), depense.getDate_depense().toLocalDate(), depense.getEnabled());
                            
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
         tableDepense.setItems(DepenseList);
         
    
    }
       
               
      
       
       
       
            


       
       
       
       
       
       
       
       
       
       
       //tableFacture.setItems(FactureList);
       
    


    @FXML
    private void refreshTable() {
        try {
            DepenseList.clear();
            query= "SELECT * FROM `compte_personnel`";
            preparedStatement = conx.prepareStatement(query);
            result = preparedStatement.executeQuery();
            
            
            while(result.next()){
            DepenseList.add(new Depense(
                    result.getInt("id"), 
                    result.getString("id_personnel_id"), 
                    result.getString("nom_prenom"), 
                    result.getString("email"), 
                    result.getString("picture"), 
                    result.getString("occupation"), 
                    result.getString("salaire"), 
                    result.getString("horaire_reguliere"), 
                    result.getString("horaire_sup"), 
                    result.getString("exempte"), 
                    result.getDate("date_depense"), 
                    result.getString("enabled") 
                    
               
                    
                    
            ));
            
            /*ImageView v=new ImageView();
                   v.setImage(new Image("file:/C:/Users/smp/Documents/3Aa/Semestre  2/Java Projet/pi/pi/src/"+ result.getString("picture")));
                   v.setFitHeight(100);
                   v.setFitWidth(100);*/
                //p.setPhoto(v);
            
            
            tableDepense.setItems(DepenseList);
            
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficheDepenseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addNew(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/addDepense.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficheDepenseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
