/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.gui;

import Oxyvia.Service.service_depense;
import Oxyvia.entities.ComptePersonnel;
import Oxyvia.entities.Depense;


import Oxyvia.utils.BDConnector;
//import com.stripe.model.Account;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
    @FXML
    private TableColumn<Depense, String> editCol;
    
    String query=null;
    Connection conx = null;
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    Depense depense = null;
    ComptePersonnel compte_personnel;
    ObservableList<Depense> DepenseList = FXCollections.observableArrayList();
    ObservableList<ComptePersonnel> PersonnelList = FXCollections.observableArrayList();
   ObservableList<Depense> dataList;
    @FXML
    private TextField filterField;
    @FXML
    private ImageView salarié;
    @FXML
    private ImageView sms;
    @FXML
    private ImageView send_sms;
    private TableColumn<Depense, String> c12Col;
    private TableColumn<Depense, String> c13Col;
    @FXML
    private ImageView refreshTable;
    @FXML
    private TableView<ComptePersonnel> tablePersonnel;
    @FXML
    private TableColumn<ComptePersonnel, Integer> c2Col1;
    @FXML
    private TableColumn<ComptePersonnel, String> c3Col1;
    @FXML
    private TableColumn<ComptePersonnel, String> c4Col1;
    @FXML
    private TableColumn<ComptePersonnel, String> c5Col1;
    @FXML
    private TableColumn<ComptePersonnel, String> c6Col1;
    @FXML
    private TableColumn<ComptePersonnel, String> c7Col1;
    @FXML
    private TableColumn<ComptePersonnel, String> editCol1;
    private AnchorPane btn_dashboard;
    @FXML
    private Button btn_produit;
    @FXML
    private Button btn_sauthentifier;
    @FXML
    private Button btn_Home4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadDate(); 
         loadDate2();
          refreshTable();
         refreshTable2();
         search_depense();
          
        // TODO
    }  
     void search_depense() {    
       //c1Col.setCellValueFactory(new PropertyValueFactory<>("id"));
       c2Col.setCellValueFactory(new PropertyValueFactory<>("email"));
       c3Col.setCellValueFactory(new PropertyValueFactory<>("nom"));
       c4Col.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       c5Col.setCellValueFactory(new PropertyValueFactory<>("picture"));
       c6Col.setCellValueFactory(new PropertyValueFactory<>("occupation"));
       c7Col.setCellValueFactory(new PropertyValueFactory<>("salaire"));
       c8Col.setCellValueFactory(new PropertyValueFactory<>("horaire_reguliere"));
       c9Col.setCellValueFactory(new PropertyValueFactory<>("horaire_sup"));
       c10Col.setCellValueFactory(new PropertyValueFactory<>("exempte"));
       c11Col.setCellValueFactory(new PropertyValueFactory<>("date_depense"));
//       c12Col.setCellValueFactory(new PropertyValueFactory<>("nom"));
//       c13Col.setCellValueFactory(new PropertyValueFactory<>("prenom"));
   
        

        dataList = BDConnector.getDataDepense();
        
       
        tableDepense.setItems(dataList);
        FilteredList<Depense> filteredData = new FilteredList<>(dataList, b -> true);  
      filterField.textProperty().addListener((observable, oldValue, newValue) -> {
       filteredData.setPredicate(depense -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    /*if (depense.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    }*/
     if (depense.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (depense.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (depense.getPicture().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (depense.getSalaire().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (depense.getHoraire_reguliere().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (depense.getHoraire_sup().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (depense.getExempte().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (depense.getDate_depense().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (String.valueOf(depense.getOccupation()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Depense> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tableDepense.comparatorProperty());  
  tableDepense.setItems(sortedData);      
    }

    private void loadDate() {
        
        conx = BDConnector.driverBD();
      refreshTable();
       //c1Col.setCellValueFactory(new PropertyValueFactory<>("id"));
       c2Col.setCellValueFactory(new PropertyValueFactory<>("email"));
       c3Col.setCellValueFactory(new PropertyValueFactory<>("nom"));
       c4Col.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       c5Col.setCellValueFactory(new PropertyValueFactory<>("picture"));
       c6Col.setCellValueFactory(new PropertyValueFactory<>("occupation"));
       c7Col.setCellValueFactory(new PropertyValueFactory<>("salaire"));
       c8Col.setCellValueFactory(new PropertyValueFactory<>("horaire_reguliere"));
       c9Col.setCellValueFactory(new PropertyValueFactory<>("horaire_sup"));
       c10Col.setCellValueFactory(new PropertyValueFactory<>("exempte"));
       c11Col.setCellValueFactory(new PropertyValueFactory<>("date_depense"));
       
       /*c12Col.setCellValueFactory(new PropertyValueFactory<>("nom"));
       c13Col.setCellValueFactory(new PropertyValueFactory<>("bb"));*/
       //c12Col.setCellValueFactory(new PropertyValueFactory<>("enabled"));
       //c13Col.setCellValueFactory(new PropertyValueFactory<>("color"));
       
       
      
  
       
       // add cell of button edit
        //add cell of button edit 
         Callback<TableColumn<Depense, String>, TableCell<Depense, String>> cellFactory;
         cellFactory = (TableColumn<Depense, String> param) -> {
            // make cell containing buttons
            final TableCell<Depense, String> cell;
            cell = new TableCell<Depense, String>() {
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
                                    query = "DELETE FROM `depense` WHERE id  ="+depense.getId();
                                    conx = BDConnector.driverBD();
                                    preparedStatement = conx.prepareStatement(query);
                                    preparedStatement.execute();
                                    refreshTable();
                                    search_depense();
                                    
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

                            addDepenseController.setTextField(depense.getId(),depense.getEmail(),depense.getPicture(), depense.getOccupation(), depense.getSalaire(), depense.getHoraire_reguliere(), depense.getHoraire_sup(), depense.getExempte(), depense.getDate_depense(), depense.getNom(), depense.getPrenom(), depense.getColor());
                            
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            refreshTable();
                            search_depense();
                            
                            
                            
                            
                            
                            
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
       
               
      
       
       
       private void loadDate2() {
        
        conx = BDConnector.driverBD();
      refreshTable2();
       //c1Col.setCellValueFactory(new PropertyValueFactory<>("id"));
       c2Col1.setCellValueFactory(new PropertyValueFactory<>("email"));
       c3Col1.setCellValueFactory(new PropertyValueFactory<>("nom"));
       c4Col1.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       c5Col1.setCellValueFactory(new PropertyValueFactory<>("tel"));
       c6Col1.setCellValueFactory(new PropertyValueFactory<>("occupation"));
       c7Col1.setCellValueFactory(new PropertyValueFactory<>("salaire_annuel"));
       
       
       /*c12Col.setCellValueFactory(new PropertyValueFactory<>("nom"));
       c13Col.setCellValueFactory(new PropertyValueFactory<>("bb"));*/
       //c12Col.setCellValueFactory(new PropertyValueFactory<>("enabled"));
       //c13Col.setCellValueFactory(new PropertyValueFactory<>("color"));
       
       
      
  
       
       // add cell of button edit
        //add cell of button edit 
         Callback<TableColumn<ComptePersonnel, String>, TableCell<ComptePersonnel, String>> cellFactory;
         cellFactory = (TableColumn<ComptePersonnel, String> param) -> {
            // make cell containing buttons
            final TableCell<ComptePersonnel, String> cell;
            cell = new TableCell<ComptePersonnel, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                       // FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                       
                       /* deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );*/
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        
                        /*deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            //pour supprimer 
                            Alert alert = new Alert( Alert.AlertType.CONFIRMATION) ;
                            alert.setTitle("Confirmation de suppression  !!");
                            alert.setHeaderText(null);
                            alert.setContentText("Voulez-vous vraiment supprimer ");
                            Optional<ButtonType> action = alert.showAndWait();
                            if ( action.get() == ButtonType.OK) {
                                
                                try {
                                    depense = tableDepense.getSelectionModel().getSelectedItem();
                                    query = "DELETE FROM `depense` WHERE id  ="+depense.getId();
                                    conx = BDConnector.driverBD();
                                    preparedStatement = conx.prepareStatement(query);
                                    preparedStatement.execute();
                                    refreshTable();
                                    search_depense();
                                    
                                } catch (SQLException ex) {
                                    Logger.getLogger(AfficheDepenseController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                
                            } //fin supp
                        });*/
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            
                            
                            
                            
                            compte_personnel = tablePersonnel.getSelectionModel().getSelectedItem();
                           
                            
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/Oxyvia/gui/addDepense.fxml"));
                            try {
                                loader.load();
                              
                            } catch (IOException ex) {
                                Logger.getLogger(AfficheDepenseController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            AddDepenseController addDepenseController = loader.getController();
                            addDepenseController.setUpdate(false);

                            addDepenseController.setTextField(compte_personnel.getId(),compte_personnel.getEmail(),compte_personnel.getNom(), compte_personnel.getPrenom(), compte_personnel.getTel(), compte_personnel.getOccupation() , compte_personnel.getSalaire_annuel());
                            
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            refreshTable();
                            search_depense();
                            
                            
                            
                            
                            
                            
                        });
                       

                        HBox managebtn = new HBox(editIcon);
                        managebtn.setStyle("-fx-alignment:center");
                       // HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
                 editCol1.setCellFactory(cellFactory);
         tablePersonnel.setItems(PersonnelList);
         
    
    }
            


       
       
       
       
       
       
       
       
       
       
     
       
    


    @FXML
    private void refreshTable() {
        try {
            //DepenseList.clear();
            //facture join `compte_personnel on facture.identifiant = compte_personnel.id
            query="SELECT d.email, d.nom, d.prenom ,d.picture, d.occupation, d.salaire, d.horaire_reguliere, d.horaire_sup, d.exempte, d.date_depense from depense d";
          // query= "SELECT * FROM depense join compte_personnel on depense.id_personnel_id = compte_personnel.id_personnel";
            preparedStatement = conx.prepareStatement(query);
            result = preparedStatement.executeQuery();
            
            
            while(result.next()){
            DepenseList.add(new Depense(
                   // result.getInt("id"), 
                    result.getString(1),
                    result.getString(2), 
                    result.getString(3),
                    result.getString(4), 
                    result.getString(5), 
                    result.getString(6), 
                    result.getString(7), 
                    result.getString(8), 
                    result.getString(9), 
                    result.getString(10)  
                    //result.getString(11),
                    //result.getString(12)
                    
               
                    
                    
               
                    
                    
            ));
            
            
            
            
            tableDepense.setItems(DepenseList);
       
            search_depense();
            
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

    @FXML
    private void trier_depense(ActionEvent event) {
         
        service_depense ps = new service_depense();
        ResultSet rs =ps.tri_depense();
        List<Depense> p1 = new ArrayList<Depense>();
        try {
            while(rs.next()){
                Depense p = new Depense();
                //p.setId(rs.getInt("id"));
            p.setEmail(rs.getString("email"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setPicture(rs.getString("picture"));
            p.setOccupation(rs.getString("occupation"));
            p.setSalaire(rs.getString("salaire"));
            p.setHoraire_reguliere(rs.getString("horaire_reguliere"));
            p.setHoraire_sup(rs.getString("horaire_sup"));
            p.setExempte(rs.getString("exempte"));
            p.setDate_depense(rs.getString("date_depense"));
            //p.setEnabled(rs.getBoolean("enabled"));
            //p.setColor(rs.getString("color"));
               
                p1.add(p);
                
               
                }
            ObservableList<Depense> listdepense1 = FXCollections.observableArrayList(p1);
//        tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       // c1Col.setCellValueFactory(new PropertyValueFactory<>("id"));
       c2Col.setCellValueFactory(new PropertyValueFactory<>("email"));
       c3Col.setCellValueFactory(new PropertyValueFactory<>("nom"));
       c4Col.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       c5Col.setCellValueFactory(new PropertyValueFactory<>("picture"));
       c6Col.setCellValueFactory(new PropertyValueFactory<>("occupation"));
       c7Col.setCellValueFactory(new PropertyValueFactory<>("salaire"));
       c8Col.setCellValueFactory(new PropertyValueFactory<>("horaire_reguliere"));
       c9Col.setCellValueFactory(new PropertyValueFactory<>("horaire_sup"));
       c10Col.setCellValueFactory(new PropertyValueFactory<>("exempte"));
       c11Col.setCellValueFactory(new PropertyValueFactory<>("date_depense"));
       //c12Col.setCellValueFactory(new PropertyValueFactory<>("enabled"));
       //c13Col.setCellValueFactory(new PropertyValueFactory<>("color"));
      
        tableDepense.setItems(listdepense1);
        } catch (SQLException ex) {
            Logger.getLogger(AfficheDepenseController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
             }
               
           

    /*@FXML
    private void rech_depense(ActionEvent event) {
        service_depense ps = new service_depense();
        ResultSet resultset=ps.getall_depense();
        
        ObservableList<Depense> listdepense = FXCollections.observableArrayList(ps.recherche_depense((id_supp.getText())));
//        tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        //c1Col.setCellValueFactory(new PropertyValueFactory<>("id"));
       c2Col.setCellValueFactory(new PropertyValueFactory<>("id_personnel_id"));
       c3Col.setCellValueFactory(new PropertyValueFactory<>("nom"));
       c4Col.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       c5Col.setCellValueFactory(new PropertyValueFactory<>("picture"));
       c6Col.setCellValueFactory(new PropertyValueFactory<>("occupation"));
       c7Col.setCellValueFactory(new PropertyValueFactory<>("salaire"));
       c8Col.setCellValueFactory(new PropertyValueFactory<>("horaire_reguliere"));
       c9Col.setCellValueFactory(new PropertyValueFactory<>("horaire_sup"));
       c10Col.setCellValueFactory(new PropertyValueFactory<>("exempte"));
       c11Col.setCellValueFactory(new PropertyValueFactory<>("date_depense"));
       //c12Col.setCellValueFactory(new PropertyValueFactory<>("enabled"));
       //c13Col.setCellValueFactory(new PropertyValueFactory<>("color"));
        
        tableDepense.setItems(listdepense);
    }*/

    


    @FXML
    private void sms(MouseEvent event) throws TwilioRestException {
         //TwilioRestClient client = new TwilioRestClient("AC9567a7216e930328b653d592b34f29b5","0b49e38f83ebf6009643ec295e1a9643");
         TwilioRestClient client = new TwilioRestClient("AC9567a7216e930328b653d592b34f29b5","d2be7d8a195ea0e8b0abca051ea5a99c");
        com.twilio.sdk.resource.instance.Account account;
        account = client.getAccount();
        SmsFactory factory = account.getSmsFactory();
        HashMap<String, String> message = new HashMap<>();
        
        message.put("To","+21624335677");
        message.put("From","+19893037850");
        message.put("Body","Notification paiement!");
        factory.create(message);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Your SMS Message well sent!  ");
            //alert.showingProperty();
            alert.showAndWait();
    }

    @FXML
    private void sms_send(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/Sms.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficheDepenseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void save(MouseEvent event) {
    }

    /*@FXML
    private void paid(ActionEvent event) {
        service_depense ps = new service_depense();
        ResultSet rs =ps.paid_depense();
        List<Depense> p1 = new ArrayList<Depense>();
        try {
            while(rs.next()){
                Depense p = new Depense();
                //p.setId(rs.getInt("id"));
            p.setId_personnel_id(rs.getString("id_personnel_id"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setPicture(rs.getString("picture"));
            p.setOccupation(rs.getString("occupation"));
            p.setSalaire(rs.getString("salaire"));
            p.setHoraire_reguliere(rs.getString("horaire_reguliere"));
            p.setHoraire_sup(rs.getString("horaire_sup"));
            p.setExempte(rs.getString("exempte"));
            p.setDate_depense(rs.getString("date_depense"));
            //p.setEnabled(rs.getBoolean("enabled"));
            //p.setColor(rs.getString("color"));
               
                p1.add(p);
                
               
                }
            ObservableList<Depense> listdepense1 = FXCollections.observableArrayList(p1);
//        tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       // c1Col.setCellValueFactory(new PropertyValueFactory<>("id"));
         c2Col.setCellValueFactory(new PropertyValueFactory<>("id_personnel_id"));
       c3Col.setCellValueFactory(new PropertyValueFactory<>("nom"));
       c4Col.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       c5Col.setCellValueFactory(new PropertyValueFactory<>("picture"));
       c6Col.setCellValueFactory(new PropertyValueFactory<>("occupation"));
       c7Col.setCellValueFactory(new PropertyValueFactory<>("salaire"));
       c8Col.setCellValueFactory(new PropertyValueFactory<>("horaire_reguliere"));
       c9Col.setCellValueFactory(new PropertyValueFactory<>("horaire_sup"));
       c10Col.setCellValueFactory(new PropertyValueFactory<>("exempte"));
       c11Col.setCellValueFactory(new PropertyValueFactory<>("date_depense"));
       //c12Col.setCellValueFactory(new PropertyValueFactory<>("enabled"));
       //c13Col.setCellValueFactory(new PropertyValueFactory<>("color"));
      
        tableDepense.setItems(listdepense1);
        } catch (SQLException ex) {
            Logger.getLogger(AfficheDepenseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

   /* @FXML
    private void unpaid(ActionEvent event) {
        
        service_depense ps = new service_depense();
        ResultSet rs =ps.unpaid_depense();
        List<Depense> p1 = new ArrayList<Depense>();
        try {
            while(rs.next()){
                Depense p = new Depense();
                //p.setId(rs.getInt("id"));
            p.setId_personnel_id(rs.getString("id_personnel_id"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setPicture(rs.getString("picture"));
            p.setOccupation(rs.getString("occupation"));
            p.setSalaire(rs.getString("salaire"));
            p.setHoraire_reguliere(rs.getString("horaire_reguliere"));
            p.setHoraire_sup(rs.getString("horaire_sup"));
            p.setExempte(rs.getString("exempte"));
            p.setDate_depense(rs.getString("date_depense"));
            //p.setEnabled(rs.getBoolean("enabled"));
            //p.setColor(rs.getString("color"));
               
                p1.add(p);
                
               
                }
            ObservableList<Depense> listdepense1 = FXCollections.observableArrayList(p1);
//        tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       // c1Col.setCellValueFactory(new PropertyValueFactory<>("id"));
         c2Col.setCellValueFactory(new PropertyValueFactory<>("id_personnel_id"));
       c3Col.setCellValueFactory(new PropertyValueFactory<>("nom"));
       c4Col.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       c5Col.setCellValueFactory(new PropertyValueFactory<>("picture"));
       c6Col.setCellValueFactory(new PropertyValueFactory<>("occupation"));
       c7Col.setCellValueFactory(new PropertyValueFactory<>("salaire"));
       c8Col.setCellValueFactory(new PropertyValueFactory<>("horaire_reguliere"));
       c9Col.setCellValueFactory(new PropertyValueFactory<>("horaire_sup"));
       c10Col.setCellValueFactory(new PropertyValueFactory<>("exempte"));
       c11Col.setCellValueFactory(new PropertyValueFactory<>("date_depense"));
       //c12Col.setCellValueFactory(new PropertyValueFactory<>("enabled"));
       //c13Col.setCellValueFactory(new PropertyValueFactory<>("color"));
      
        tableDepense.setItems(listdepense1);
        } catch (SQLException ex) {
            Logger.getLogger(AfficheDepenseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/


  private void refreshTable2() {
        try {
            //DepenseList.clear();
            //facture join `compte_personnel on facture.identifiant = compte_personnel.id
            query="SELECT c.email, c.nom, c.prenom ,c.tel, c.occupation, c.salaire_annuel from compte_personnel c";
          // query= "SELECT * FROM depense join compte_personnel on depense.id_personnel_id = compte_personnel.id_personnel";
            preparedStatement = conx.prepareStatement(query);
            result = preparedStatement.executeQuery();
            
            
            while(result.next()){
            PersonnelList.add(new ComptePersonnel(
                   // result.getInt("id"), 
                    result.getString(1),
                    result.getString(2), 
                    result.getString(3),
                    result.getString(4), 
                    result.getString(5), 
                    result.getString(6) 
                    //result.getString(11),
                    //result.getString(12)
                          
                    
            ));
            
            
            
       // c1Col.setCellValueFactory(new PropertyValueFactory<>("id"));
       c2Col1.setCellValueFactory(new PropertyValueFactory<>("email"));
       c3Col1.setCellValueFactory(new PropertyValueFactory<>("nom"));
       c4Col1.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       c5Col1.setCellValueFactory(new PropertyValueFactory<>("tel"));
       c6Col1.setCellValueFactory(new PropertyValueFactory<>("occupation"));
       c7Col1.setCellValueFactory(new PropertyValueFactory<>("salaire_annuel"));
       
       //c12Col.setCellValueFactory(new PropertyValueFactory<>("enabled"));
       //c13Col.setCellValueFactory(new PropertyValueFactory<>("color"));
      
            
            tablePersonnel.setItems(PersonnelList);
       
            //search_depense();
            
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficheDepenseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GProduit(ActionEvent event) {
    }

    @FXML
    private void sauthentifier(ActionEvent event) {
    }

    @FXML
    private void display(ActionEvent event) throws IOException {
        btn_Home4.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/Dashboard.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void dashboard(MouseEvent event)  {
        
    }

   

    
}
