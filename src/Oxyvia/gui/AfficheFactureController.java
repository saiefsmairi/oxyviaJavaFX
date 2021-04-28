/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.gui;

import Oxyvia.Service.service_depense;
import Oxyvia.Service.service_facture;
import Oxyvia.entities.Client;
import Oxyvia.entities.Depense;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableColumn<Facture, String> c14Col;
   
    
    String query=null;
    Connection conx = null;
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    Facture facture = null;
    ObservableList<Facture> FactureList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Facture, String> editCol;
    private TableColumn<Facture, String> c16Col;
    @FXML
    private TextField filterField;
    ObservableList<Facture> dataList;
    @FXML
    private Button btn_produit;
    @FXML
    private Button btn_sauthentifier;
    @FXML
    private Button btn_Home41;
    @FXML
    private Button btn_GHome;
    @FXML
    private Label btn_depense;
    @FXML
    private Label btn_facture;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDate(); 
        search_facture();
        // TODO
    }    
     void search_facture() {    
        //c1Col.setCellValueFactory(new PropertyValueFactory<>("id"));
       c2Col.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
       c3Col.setCellValueFactory(new PropertyValueFactory<>("nom_prenom"));
       c4Col.setCellValueFactory(new PropertyValueFactory<>("montant"));
       c5Col.setCellValueFactory(new PropertyValueFactory<>("date_paiement"));
       c6Col.setCellValueFactory(new PropertyValueFactory<>("devise"));
       c7Col.setCellValueFactory(new PropertyValueFactory<>("moyen_paiement"));
       c8Col.setCellValueFactory(new PropertyValueFactory<>("mode_paiement"));
       //c9Col.setCellValueFactory(new PropertyValueFactory<>("type_cb"));
       //c10Col.setCellValueFactory(new PropertyValueFactory<>("ncb"));
       //c11Col.setCellValueFactory(new PropertyValueFactory<>("code_securite"));
       //c12Col.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
      // c13Col.setCellValueFactory(new PropertyValueFactory<>("location"));
       c14Col.setCellValueFactory(new PropertyValueFactory<>("pays"));
       //c15Col.setCellValueFactory(new PropertyValueFactory<>("enabled"));
       //c16Col.setCellValueFactory(new PropertyValueFactory<>("color"));
   
        

        dataList = BDConnector.getDataFacture();
        
       
        tableFacture.setItems(dataList);
        FilteredList<Facture> filteredData = new FilteredList<>(dataList, b -> true);  
      filterField.textProperty().addListener((observable, oldValue, newValue) -> {
       filteredData.setPredicate(facture -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    /*if (facture.getNom_prenom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    }  */ if (facture.getIdentifiant().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (facture.getMontant().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (facture.getMoyen_paiement().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (facture.getMode_paiement().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    
    else if (facture.getDevise().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (String.valueOf(facture.getPays()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Facture> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tableFacture.comparatorProperty());  
  tableFacture.setItems(sortedData);      
    }

    private void loadDate() {
        
        conx = BDConnector.driverBD();
        refreshTable();
        
       //c1Col.setCellValueFactory(new PropertyValueFactory<>("id"));
       c2Col.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
       c3Col.setCellValueFactory(new PropertyValueFactory<>("nom_prenom"));
       c4Col.setCellValueFactory(new PropertyValueFactory<>("montant"));
       c5Col.setCellValueFactory(new PropertyValueFactory<>("date_paiement"));
       c6Col.setCellValueFactory(new PropertyValueFactory<>("devise"));
       c7Col.setCellValueFactory(new PropertyValueFactory<>("moyen_paiement"));
       c8Col.setCellValueFactory(new PropertyValueFactory<>("mode_paiement"));
       c14Col.setCellValueFactory(new PropertyValueFactory<>("pays"));

      // c16Col.setCellValueFactory(new PropertyValueFactory<>("color"));
       
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
                               search_facture();
                                
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
                            addFactureController.setTextField(facture.getId(),facture.getIdentifiant(),facture.getNom_prenom(),facture.getMontant(), facture.getDate_paiement().toLocalDate(), facture.getDevise(), facture.getMoyen_paiement(), facture.getMode_paiement(), facture.getPays(), facture.getColor());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            search_facture();
                            
                            
                            
                            
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
            query="SELECT * FROM facture ";
           // query= "SELECT * FROM `facture`";
            preparedStatement = conx.prepareStatement(query);
            result = preparedStatement.executeQuery();
            
            while(result.next()){
            FactureList.addAll(new Facture(
                    //result.getInt("id"), 
                    result.getString("identifiant"), 
                    result.getString("nom_prenom"),
                    result.getString("montant"), 
                    result.getDate("date_paiement"), 
                    result.getString("devise"), 
                    result.getString("moyen_paiement"), 
                    result.getString("mode_paiement"), 
                    //result.getString("type_cb"), 
                    //result.getDate("date_expiration"), 
                   // result.getString("location"), 
                    result.getString("pays") 
                    //result.getBoolean("enabled")
                    //result.getString("color")
                    
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

    @FXML
    private void trier_facture(ActionEvent event) {
         service_facture ps = new service_facture();
        ResultSet rs =ps.tri_facture();
        List<Facture> p1 = new ArrayList<Facture>();
        try {
            while(rs.next()){
                Facture p = new Facture();
             //p.setId(rs.getInt("id"));
            p.setIdentifiant(rs.getString("identifiant"));
            p.setMontant(rs.getString("montant"));
            p.setDevise(rs.getString("devise"));
            p.setDate_paiement(rs.getDate("date_paiement"));
            p.setMoyen_paiement(rs.getString("moyen_paiement"));
            p.setMode_paiement(rs.getString("mode_paiement"));
            //p.setType_cb(rs.getString("type_cb"));
            //p.setNcb(rs.getString("ncb"));
            //p.setCode_securite(rs.getString("code_securite"));
            //p.setDate_expiration(rs.getDate("date_expiration"));
            //p.setLocation(rs.getString("location"));
            p.setPays(rs.getString("pays"));
           // p.setEnabled(rs.getBoolean("enabled"));
            //p.setColor(rs.getString("color"));
               
                p1.add(p);
                
               
                }
            ObservableList<Facture> listdepense1 = FXCollections.observableArrayList(p1);
      
       //c1Col.setCellValueFactory(new PropertyValueFactory<>("id"));
       c2Col.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
       c3Col.setCellValueFactory(new PropertyValueFactory<>("nom_prenom"));
       c4Col.setCellValueFactory(new PropertyValueFactory<>("montant"));
       c5Col.setCellValueFactory(new PropertyValueFactory<>("date_paiement"));
       c6Col.setCellValueFactory(new PropertyValueFactory<>("devise"));
       c7Col.setCellValueFactory(new PropertyValueFactory<>("moyen_paiement"));
       c8Col.setCellValueFactory(new PropertyValueFactory<>("mode_paiement"));
      // c9Col.setCellValueFactory(new PropertyValueFactory<>("type_cb"));
       //c10Col.setCellValueFactory(new PropertyValueFactory<>("ncb"));
       //c11Col.setCellValueFactory(new PropertyValueFactory<>("code_securite"));
      // c12Col.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
       //c13Col.setCellValueFactory(new PropertyValueFactory<>("location"));
       c14Col.setCellValueFactory(new PropertyValueFactory<>("pays"));
       //c15Col.setCellValueFactory(new PropertyValueFactory<>("enabled"));
       //c16Col.setCellValueFactory(new PropertyValueFactory<>("color"));
      
        tableFacture.setItems(listdepense1);
        } catch (SQLException ex) {
            Logger.getLogger(AfficheFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   /* @FXML
    private void rech_facture(ActionEvent event) {
          service_facture ps = new service_facture();
        ResultSet resultset=ps.getall_facture();
        
        ObservableList<Facture> listdepense = FXCollections.observableArrayList(ps.recherche_facture((id_supp.getText())));
//        tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
      // c1Col.setCellValueFactory(new PropertyValueFactory<>("id"));
       c2Col.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
       c3Col.setCellValueFactory(new PropertyValueFactory<>("nom_prenom"));
       c4Col.setCellValueFactory(new PropertyValueFactory<>("montant"));
       c5Col.setCellValueFactory(new PropertyValueFactory<>("date_paiement"));
       c6Col.setCellValueFactory(new PropertyValueFactory<>("devise"));
       c7Col.setCellValueFactory(new PropertyValueFactory<>("moyen_paiement"));
       c8Col.setCellValueFactory(new PropertyValueFactory<>("mode_paiement"));
       //c9Col.setCellValueFactory(new PropertyValueFactory<>("type_cb"));
       //c10Col.setCellValueFactory(new PropertyValueFactory<>("ncb"));
       //c11Col.setCellValueFactory(new PropertyValueFactory<>("code_securite"));
      // c12Col.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
       //c13Col.setCellValueFactory(new PropertyValueFactory<>("location"));
       c14Col.setCellValueFactory(new PropertyValueFactory<>("pays"));
       //c15Col.setCellValueFactory(new PropertyValueFactory<>("enabled"));
       //c16Col.setCellValueFactory(new PropertyValueFactory<>("color"));
        
        tableFacture.setItems(listdepense);
    }*/

    @FXML
    private void save(MouseEvent event) {
    }

    @FXML
    private void GProduit(ActionEvent event) {
    }

    @FXML
    private void sauthentifier(ActionEvent event) {
    }

    @FXML
    private void display(ActionEvent event) throws IOException {
        btn_Home41.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/Dashboard.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void GHome(ActionEvent event) throws IOException {
        btn_GHome.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/Dashboard.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void depense(MouseEvent event) throws IOException {
        btn_depense.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/AfficheDepense.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
    }

    @FXML
    private void facture(MouseEvent event) throws IOException {
        btn_facture.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/AfficheFacture.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void stats(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/stats.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Stats2(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/Barchar_stats.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
