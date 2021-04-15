/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entity.Hotel;
import Service.HotelService;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class HotelController implements Initializable {

    @FXML
    private TextField textpays;
    @FXML
    private TextField textname;
    @FXML
    private TextField textadresse;
    @FXML
    private TextField textnbetoile;
    @FXML
    private TextField textemail;
    @FXML
    private TextField textnum;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
      @FXML
    private TableView<Hotel> tablehotel;
    @FXML
    private TableColumn<Hotel, String> name;
    @FXML
    private TableColumn<Hotel, String> pays;
    @FXML
    private TableColumn<Hotel, String> adresse;
    @FXML
    private TableColumn<Hotel, Integer> nbetoile;
    @FXML
    private TableColumn<Hotel, Integer> num;
    @FXML
    private TableColumn<Hotel, String> email;
    @FXML
    private Label textid;
    @FXML
    private TextField imagetext;
    @FXML
    private TableColumn<Hotel, String> image;
    @FXML
    private ImageView imageview;
    @FXML
    private Label labelnbetoile;
    @FXML
    private Label labelnum;
    @FXML
    private Label labelemail;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HotelService hs = new HotelService();

        List<Hotel> lh = hs.Lister();

        ObservableList<Hotel> data
                = FXCollections.observableArrayList(lh);

        name.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        pays.setCellValueFactory(
                new PropertyValueFactory<>("pays"));

        adresse.setCellValueFactory(
                new PropertyValueFactory<>("adresse"));

        nbetoile.setCellValueFactory(
                new PropertyValueFactory<>("nbEtoile"));

        num.setCellValueFactory(
                new PropertyValueFactory<>("num"));
        email.setCellValueFactory(
                new PropertyValueFactory<>("email"));
        image.setCellValueFactory(
                new PropertyValueFactory<>("image"));
      
        tablehotel.setItems(data);
    }    
     

    @FXML
    private void ajouthotel(MouseEvent event) {
        int a=0;
         HotelService hs = new HotelService();
         if ((textname.getText().isEmpty())||(textpays.getText().isEmpty())||(textadresse.getText().isEmpty())||(textnbetoile.getText().isEmpty())||(textnum.getText().isEmpty())||(textemail.getText().isEmpty())||(imagetext.getText().isEmpty())){
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("champ invalide");
        
        alert.setContentText("Veuillez remplir tous les champs "); 
     
        alert.showAndWait();
        
        }
         else if (!hs.estUnEntier(textnbetoile.getText())||Integer.parseInt(textnbetoile.getText())<1||Integer.parseInt(textnbetoile.getText())>5)
          {labelnbetoile.setText("donnee invalide");
          labelnum.setText("");
          labelemail.setText("");}
      else    if (!hs.estUnEntier(textnum.getText())||(textnum.getText().length())!=8)
          {labelnum.setText("donnee invalide");
          labelnbetoile.setText("");
          labelemail.setText("");
          }
      else if((!textemail.getText().contains("@gmail.com"))){
      labelemail.setText("donnee invalide");
          labelnbetoile.setText("");
          labelnum.setText("");
      }
      
    
         else{
         Hotel H=new Hotel(textname.getText(),textpays.getText(),textadresse.getText(),Integer.parseInt(textnbetoile.getText()),Integer.parseInt(textnum.getText()),textemail.getText(),imagetext.getText(),1,1);
         hs.Ajouter(H);
          a=1;}
         List<Hotel> lh = hs.Lister();

        ObservableList<Hotel> data
                = FXCollections.observableArrayList(lh);
        tablehotel.setItems(data);
        if (a==1)
        initForm();
    }

    @FXML
    private void modifierhotel(MouseEvent event) {
        int a=0;
         HotelService hs = new HotelService();
         if ((textname.getText().isEmpty())||(textpays.getText().isEmpty())||(textadresse.getText().isEmpty())||(textnbetoile.getText().isEmpty())||(textnum.getText().isEmpty())||(textemail.getText().isEmpty())||(imagetext.getText().isEmpty())){
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("champ invalide");
        
        alert.setContentText("Veuillez remplir tous les champs "); 
     
        alert.showAndWait();
        
        
        }
         else if (!hs.estUnEntier(textnbetoile.getText())||Integer.parseInt(textnbetoile.getText())<1||Integer.parseInt(textnbetoile.getText())>5)
          {labelnbetoile.setText("donnee invalide");
          labelnum.setText("");
          labelemail.setText("");}
      else    if (!hs.estUnEntier(textnum.getText())||(textnum.getText().length())!=8)
          {labelnum.setText("donnee invalide");
          labelnbetoile.setText("");
          labelemail.setText("");
          }
       else if((!textemail.getText().contains("@gmail.com"))){
      labelemail.setText("donnee invalide");
          labelnbetoile.setText("");
          labelnum.setText("");
      }
         else{
         Hotel H=new Hotel(Integer.parseInt(textid.getText()),textname.getText(),textpays.getText(),textadresse.getText(),Integer.parseInt(textnbetoile.getText()),Integer.parseInt(textnum.getText()),textemail.getText(),imagetext.getText(),1,1);
         hs.Modifier(H);
       a=1;}
         List<Hotel> lh = hs.Lister();

        ObservableList<Hotel> data
                = FXCollections.observableArrayList(lh);
        tablehotel.setItems(data);
        if (a==1)
        initForm();
    }

    @FXML
    private void supprimerhotel(MouseEvent event) {
        Hotel t=tablehotel.getSelectionModel().getSelectedItem();
        HotelService hs = new HotelService();
        hs.Supprimer(t);
          List<Hotel> lh = hs.Lister();

        ObservableList<Hotel> data
                = FXCollections.observableArrayList(lh);
        tablehotel.setItems(data);
        initForm();
        
        
    }
    
    

    @FXML
    private void afficherhotel(MouseEvent event) {
         Hotel t=tablehotel.getSelectionModel().getSelectedItem();
         textid.setText(String.valueOf(t.getId()));
        textname.setText(t.getName());
        textpays.setText(t.getPays());
        textadresse.setText(t.getAdresse());
        textnbetoile.setText(String.valueOf(t.getNbEtoile()));
        textnum.setText(String.valueOf(t.getNum()));
        textemail.setText(t.getEmail());
        imagetext.setText(t.getImage());
        String imgg = t.getImage();
        String ch="/images/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        imageview.setImage(imageF);

    }
    
      private void initForm() {
        textname.setText("");
        textpays.setText("");
        textadresse.setText("");
        textnbetoile.setText("");
        textnum.setText("");
        textemail.setText("");
                imagetext.setText("");
                imageview.setImage(null);
                labelnbetoile.setText("");
                labelnum.setText("");
                labelemail.setText("");

        
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



}
