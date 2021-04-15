/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entity.Chambre;
import Entity.Hotel;
import Service.ChambreService;
import Service.HotelService;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ChambreController implements Initializable {

    @FXML
    private TextField numerotext;
    @FXML
    private TextField prixtext;
    @FXML
    private TextField imagetext;
    @FXML
    private ComboBox<String> typetext;
    @FXML
    private ComboBox<String> hoteltext;
    @FXML
    private Button ajouterbtn;
    @FXML
    private Button modifierbtn;
    @FXML
    private Button supprimerbtn;
    @FXML
    private TableColumn<Chambre , Integer> numero;
    @FXML
    private TableColumn<Chambre, String> type;
    @FXML
    private TableColumn<Chambre, Long> prix;
    @FXML
    private TableColumn<Chambre, Integer> hotel;
    @FXML
    private TableColumn<Chambre, String> image;
    @FXML
    private TableView<Chambre> tablechambre;
    @FXML
    private ImageView imageview;
    @FXML
    private Label textid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ChambreService hs = new ChambreService() ;
        HotelService hots=new HotelService();
       
        ObservableList<String> list;
        list = FXCollections.observableArrayList(hots.ListerNom());
        hoteltext.setItems(list);
ObservableList<String> list1;
        list1 = FXCollections.observableArrayList("single room","double room" );       
        typetext.setItems(list1);
        
 
        // Create a combo box
        
        List<Chambre> lh = hs.Lister();

        ObservableList<Chambre> data
                = FXCollections.observableArrayList(lh);

        numero.setCellValueFactory(
                new PropertyValueFactory<>("numero"));

        type.setCellValueFactory(
                new PropertyValueFactory<>("type"));

        prix.setCellValueFactory(
                new PropertyValueFactory<>("prix"));

        hotel.setCellValueFactory(
                new PropertyValueFactory<>("idh"));
//        hotel.setCellFactory(new Callback<CellDataFeatures<Chambre, Integer>, ObservableValue<Integer>>(){
//            @Override
//            public ObservableValue<Integer> call(CellDataFeatures<Chambre, Integer> param) {
//                
//            }
//      
//            
//    })

       
        image.setCellValueFactory(
                new PropertyValueFactory<>("image"));
      
        
        tablechambre.setItems(data);
       
 
        
        
        
        
        
    }    

    @FXML
    private void importimg(MouseEvent event) {
         FileChooser fc = new FileChooser();
        File selected = fc.showOpenDialog(null);
        if(selected !=null )
        {
            String extension = selected.getAbsolutePath().substring(selected.getAbsolutePath().length()-3,selected.getAbsolutePath().length());
            System.out.println(extension);
            if(!extension.equals( "jpg") && !extension.equals("png"))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("image invalide");
        
        alert.setContentText("Invalid picture format (only jgp/png available)"); 
     
        alert.showAndWait();
        imagetext.setText("");
            }else
            imagetext.setText(selected.getName());
        }
    }

    @FXML
    private void ajouterChambre(MouseEvent event) {
        ChambreService hs = new ChambreService();
        HotelService hots = new HotelService();
        if ((numerotext.getText().isEmpty())||(typetext.getValue().isEmpty())||(prixtext.getText().isEmpty())||(imagetext.getText().isEmpty())||(hoteltext.getValue().isEmpty())){
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("champ invalide");
        
        alert.setContentText("Veuillez remplir tous les champs "); 
     
        alert.showAndWait();
        
        }
        else{
        
         Chambre H=new Chambre(Integer.parseInt(numerotext.getText()),typetext.getValue(),Long.parseLong(prixtext.getText()),imagetext.getText(),hots.getIdNom(hoteltext.getValue()));
         hs.Ajouter(H);
        }
         List<Chambre> lh = hs.Lister();

        ObservableList<Chambre> data
                = FXCollections.observableArrayList(hs.Lister());
        List<String> nomHotel=new ArrayList<>();
        
        
        tablechambre.setItems(data);
        initForm();
    }

    @FXML
    private void modifierChambre(MouseEvent event) {
         ChambreService hs = new ChambreService();
        HotelService hots = new HotelService();
        if ((numerotext.getText().isEmpty())||(typetext.getValue().isEmpty())||(prixtext.getText().isEmpty())||(imagetext.getText().isEmpty())||(hoteltext.getValue().isEmpty())){
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("champ invalide");
        
        alert.setContentText("Veuillez remplir tous les champs "); 
     
        alert.showAndWait();
        
        }
        else{
        
         Chambre H=new Chambre(Integer.parseInt(textid.getText()),Integer.parseInt(numerotext.getText()),typetext.getValue(),Long.parseLong(prixtext.getText()),imagetext.getText(),hots.getIdNom(hoteltext.getValue()));
         hs.Modifier(H);}
         List<Chambre> lh = hs.Lister();

        ObservableList<Chambre> data
                = FXCollections.observableArrayList(hs.Lister());
        List<String> nomHotel=new ArrayList<>();
        
        
        tablechambre.setItems(data);
        initForm();
    }

    @FXML
    private void supprimerChambre(MouseEvent event) {
         Chambre t=tablechambre.getSelectionModel().getSelectedItem();
        ChambreService hs = new ChambreService();
        hs.Supprimer(t);
         
        initForm();
            List<Chambre> lh = hs.Lister();

        ObservableList<Chambre> data
                = FXCollections.observableArrayList(hs.Lister());
        List<String> nomHotel=new ArrayList<>();
        
        
        tablechambre.setItems(data);
    }

    @FXML
    private void afficherForm(MouseEvent event) {
        Chambre t = tablechambre.getSelectionModel().getSelectedItem(); 
        HotelService s= new HotelService();
        textid.setText(String.valueOf(t.getId()));
        numerotext.setText(String.valueOf(t.getNumero()));
        prixtext.setText(String.valueOf(t.getPrix()));
        imagetext.setText(t.getImage());
        typetext.setValue(t.getType());
        hoteltext.setValue(s.getNom(t.getIdh()));
        System.out.println(t.getIdh());
       String imgg = t.getImage();
        String ch="/images/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        imageview.setImage(imageF);

    }
   
     private void initForm() {
        numerotext.setText("");
        prixtext.setText("");
        imagetext.setText("");
        typetext.setValue("type");
        hoteltext.setValue("hotel");
                        imageview.setImage(null);

       

        
    }
}
