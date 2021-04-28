///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Oxyvia.gui;
//
//import Oxyvia.Service.DepenseService;
//import Oxyvia.entities.Depense;
//import Oxyvia.utils.BDConnector;
//import java.io.File;
//import java.net.URL;
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.ToggleGroup;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.stage.FileChooser;
//
///**
// * FXML Controller class
// *
// * @author smp
// */
//public class DepenseController implements Initializable {
//
//    @FXML
//    private TextField FC3;
//    @FXML
//    private ComboBox FC5;
//    @FXML
//    private ComboBox FC6;
//    @FXML
//    private ComboBox FC7;
//    @FXML
//    private TextField FC4;
//    @FXML
//    private TextField FC2;
//    @FXML
//    private ComboBox FC11;
//    @FXML
//    private TextField FC12;
//    @FXML
//    private Button image;
//    @FXML
//    private TextField FC8;
//    @FXML
//    private TextField FC10;
//    @FXML
//    private TextField FC9;
//    @FXML
//    private ComboBox FC1;
//    @FXML
//    private TableView<Depense> tableDepense;
//    @FXML
//    private TableColumn<Depense, String> c1Col;
//    @FXML
//    private TableColumn<Depense, String> c2Col;
//    @FXML
//    private TableColumn<Depense, String> c3Col;
//    @FXML
//    private TableColumn<Depense, String> c4Col;
//    @FXML
//    private TableColumn<Depense, String> c5Col;
//    @FXML
//    private TableColumn<Depense, String> c6Col;
//    @FXML
//    private TableColumn<Depense, String> c7Col;
//    @FXML
//    private TableColumn<Depense, String> c8Col;
//    @FXML
//    private TableColumn<Depense, String> c9Col;
//    @FXML
//    private TableColumn<Depense, String> c10Col;
//    @FXML
//    private TableColumn<Depense, String> c11Col;
//    @FXML
//    private TableColumn<Depense, String> c12Col;
//    @FXML
//    private TableColumn<Depense, String> c13Col;
//    @FXML
//    private ImageView imgg;
//  
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//      public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//        DepenseService hs = new DepenseService() ;
//        //HotelService hots=new HotelService();
//       
//        ObservableList<String> list;
//        /*list = FXCollections.observableArrayList(hots.ListerNom());
//        hoteltext.setItems(list);
//ObservableList<String> list1;*/
//        
//         FC1.getItems().addAll("48","47");
//        FC5.getItems().addAll("Exelente","Acceptable","Insuffisante");
//        FC6.getItems().addAll("Bonne","90%","80%","70%","Moyenne","60%","70%","80%","Faible","30%","20%","10%");
//        FC7.getItems().addAll("Absenté (Absence personnel)","Absenté (Absence personnelisé)","Absenté (Suite au maladie)");
//         FC11.getItems().addAll(0,1);
//      
// 
//        // Create a combo box
//        
//        List<Depense> lh = hs.Lister();
//
//        ObservableList<Depense> data
//                = FXCollections.observableArrayList(lh);
//       Connection conx = BDConnector.driverBD();
//        
//       c1Col.setCellValueFactory(new PropertyValueFactory<>("id"));
//       c2Col.setCellValueFactory(new PropertyValueFactory<>("id_personnel_id"));
//       c3Col.setCellValueFactory(new PropertyValueFactory<>("picture"));
//       c4Col.setCellValueFactory(new PropertyValueFactory<>("occupation"));
//       c5Col.setCellValueFactory(new PropertyValueFactory<>("salaire"));
//       c6Col.setCellValueFactory(new PropertyValueFactory<>("horaire_reguliere"));
//       c7Col.setCellValueFactory(new PropertyValueFactory<>("horaire_sup"));
//       c8Col.setCellValueFactory(new PropertyValueFactory<>("exempte"));
//       c9Col.setCellValueFactory(new PropertyValueFactory<>("date_depense"));
//       c10Col.setCellValueFactory(new PropertyValueFactory<>("nom"));
//       c11Col.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//       c12Col.setCellValueFactory(new PropertyValueFactory<>("enabled"));
//       c13Col.setCellValueFactory(new PropertyValueFactory<>("color"));
//
//        
//        
//        tableDepense.setItems(data);
//       
// 
//        
//        
//        
//        
//        
//    }    
//
//   
//
//    @FXML
//    private void save(MouseEvent event) {
//        DepenseService hs = new DepenseService();
//        //HotelService hots = new HotelService();
//      if ((FC10.getText().isEmpty())||(FC3.getText().isEmpty())||(FC4.getText().isEmpty())||(FC9.getText().isEmpty())){
//         Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.setTitle("champ invalide");
//        
//        alert.setContentText("Veuillez remplir tous les champs "); 
//     
//        alert.showAndWait();
//        
//        }
//        else{
//        //Depense D=new Depense(FC1.getValue().toString(),FC2.getText(),FC3.getText(),FC4.getText(),FC5.getValue().toString(),FC6.getValue().toString(),FC7.getValue().toString(),FC8.getText(),FC9.getText(),FC10.getText(),FC11.getValue().toString(),FC12.getText());
//       // String id_personnel_id, String picture, String occupation, String salaire, String horaire_reguliere, String horaire_sup, String exempte, String date_depense, String nom, String prenom, boolean enabled, String color
//         Depense D = new Depense(FC1.getValue().toString(),FC2.getText(),FC3.getText(),FC4.getText(),FC5.getValue().toString(),FC6.getValue().toString(),FC7.getValue().toString(),FC8.getText(),FC9.getText(),FC10.getText(),(boolean)FC11.getValue(),FC12.getText());
//         hs.Ajouter(D);
//        }
//         List<Depense> lh = hs.Lister();
//
//        ObservableList<Depense> data
//                = FXCollections.observableArrayList(hs.Lister());
//       
//        
//        
//        tableDepense.setItems(data);
//        initForm();
//    }
//
//    @FXML
//    private void Update(MouseEvent event) {
//         DepenseService hs = new DepenseService();
//       
//        if ((FC10.getText().isEmpty())||(FC3.getText().isEmpty())||(FC4.getText().isEmpty())||(FC9.getText().isEmpty())){
//         Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.setTitle("champ invalide");
//        
//        alert.setContentText("Veuillez remplir tous les champs "); 
//     
//        alert.showAndWait();
//        
//        }
//        else{
//        
//         Depense D=new Depense(FC1.getValue().toString(),FC2.getText(),FC3.getText(),FC4.getText(),FC5.getValue().toString(),FC6.getValue().toString(),FC7.getValue().toString(),FC8.getText(),FC9.getText(),FC10.getText(),(boolean)FC11.getValue(),FC12.getText());
//         hs.Modifier(D);}
//         List<Depense> lh = hs.Lister();
//
//        ObservableList<Depense> data
//                = FXCollections.observableArrayList(hs.Lister());
//      
//        
//        
//        tableDepense.setItems(data);
//        initForm();
//    }
//
//    @FXML
//    private void Delete(MouseEvent event) {
//         Depense t=tableDepense.getSelectionModel().getSelectedItem();
//        DepenseService hs = new DepenseService();
//        hs.Supprimer(t);
//         
//        initForm();
//            List<Depense> lh = hs.Lister();
//
//        ObservableList<Depense> data
//                = FXCollections.observableArrayList(hs.Lister());
//        
//        
//        
//        tableDepense.setItems(data);
//    }
//
//    private void afficherForm(MouseEvent event) {
//        Depense t = tableDepense.getSelectionModel().getSelectedItem(); 
//     
//        FC1.setValue(String.valueOf(t.getId_personnel_id()));
//        FC2.setText(t.getPicture());
//        FC3.setText(t.getOccupation());
//        FC4.setText(t.getSalaire());
//        FC5.setValue(String.valueOf(t.getHoraire_reguliere()));
//        FC6.setValue(String.valueOf(t.getHoraire_sup()));
//        FC7.setValue(String.valueOf(t.getExempte()));
//        
//       String imggg = t.getPicture();
//        String ch="/Oxyvia/image/";
//        String imgF= ch+imggg;
//        
//        Image imageF = new Image(getClass().getResourceAsStream(imgF));
//        imgg.setImage(imageF);
//        FC8.setText(t.getDate_depense());
//        FC9.setText(t.getNom());
//        FC10.setText(t.getPrenom());
//        FC11.setValue(t.isEnabled());
//        FC12.setText(t.getColor());
//        
//
//    }
//   
//     private void initForm() {
//        FC1.setValue("");
//        FC2.setText("");
//        FC3.setText("");
//        FC5.setValue("");
//        FC6.setValue("");
//        FC7.setValue("");
//        FC8.setText("");
//        FC9.setText("");
//        FC10.setText("");
//        FC11.setValue("");
//        FC12.setText("");
//        imgg.setImage(null);
//
//       
//
//        
//    } 
//
//    @FXML
//    private void Telech(MouseEvent event) {
//        FileChooser fc = new FileChooser();
//        File selected = fc.showOpenDialog(null);
//        if(selected !=null )
//        {
//            String extension = selected.getAbsolutePath().substring(selected.getAbsolutePath().length()-3,selected.getAbsolutePath().length());
//            System.out.println(extension);
//            if(!extension.equals( "jpg") && !extension.equals("png"))
//            {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.setTitle("image invalide");
//        
//        alert.setContentText("Invalid picture format (only jgp/png available)"); 
//     
//        alert.showAndWait();
//        FC2.setText("");
//            }else
//            FC2.setText(selected.getName());
//        }
//    }
//
//    @FXML
//    private void upload(ActionEvent event) {
//    }
//
//   
//
//   
//    
//}
