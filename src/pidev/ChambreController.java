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
import Utulitaire.FtpUpload;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;

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
    private TableColumn<Chambre , Integer> numero;
    private TableColumn<Chambre, String> type;
    private TableColumn<Chambre, Long> prix;
    private TableColumn<Chambre, Integer> hotel;
    private TableColumn<Chambre, String> image;
    private TableView<Chambre> tablechambre;
    @FXML
    private ImageView imageview;
    @FXML
    private Label textid;
    @FXML
    private Button btn_GHome;
    @FXML
    private MenuItem btnhotel;
    @FXML
    private MenuItem btnchambre;
    @FXML
    private MenuItem btntransport;
    @FXML
    private Button btn_Home;
    @FXML
    private Button btn_produit;
    @FXML
    private Button btn_sauthentifier;
    @FXML
    private Button btn_Home4;
    @FXML
    private ImageView imgbutton;
    @FXML
    private ScrollPane chambrescroll;
    @FXML
    private GridPane chmabregrid;

    
    String url,nom;
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
       
       
         chambrescroll.setVisible(true);
        chmabregrid.getChildren().clear();
     
        
  List<Chambre> lh = hs.Lister();
    
        int column = 0;
        int row = 1;
        try {
            System.out.println(lh.size());
            for (int i = 0; i < lh.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemChambreBack.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemChambreBackController itemController = fxmlLoader.getController();
                itemController.setData(lh.get(i),this);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                chmabregrid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                chmabregrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                chmabregrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                chmabregrid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                chmabregrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                chmabregrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                chmabregrid.setMaxHeight(Region.USE_PREF_SIZE);
                 chmabregrid.setMargin(anchorPane, new Insets(10));

              //  GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
                        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);

            
        }
 
       
 
        
        
        
        
        
    }    
    public void aa(){
        
        
    ChambreService hs = new ChambreService() ;
        HotelService hots=new HotelService();
       
        ObservableList<String> list;
        list = FXCollections.observableArrayList(hots.ListerNom());
        hoteltext.setItems(list);
ObservableList<String> list1;
        list1 = FXCollections.observableArrayList("single room","double room" );       
        typetext.setItems(list1);
       
       
         chambrescroll.setVisible(true);
        chmabregrid.getChildren().clear();
     
        
  List<Chambre> lh = hs.Lister();
    
        int column = 0;
        int row = 1;
        try {
            System.out.println(lh.size());
            for (int i = 0; i < lh.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemChambreBack.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemChambreBackController itemController = fxmlLoader.getController();
                itemController.setData(lh.get(i),this);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                chmabregrid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                chmabregrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                chmabregrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                chmabregrid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                chmabregrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                chmabregrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                chmabregrid.setMaxHeight(Region.USE_PREF_SIZE);
                 chmabregrid.setMargin(anchorPane, new Insets(10));

              //  GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
                        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);

            
        }
 
       
 
        
        
    
    
    
    
    
    }

    @FXML
    private void importimg(MouseEvent event) throws SQLException {
           FileChooser fc = new FileChooser();

        File selectedFile = fc.showOpenDialog(null);
        url=selectedFile.getAbsolutePath().replace("\\", "\\\\");;
            nom= selectedFile.getName();
             imagetext.setText(nom);
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
        FtpUpload ftp= new FtpUpload();
          ftp.Upload(url, nom);
         Chambre H=new Chambre(Integer.parseInt(numerotext.getText()),typetext.getValue(),Long.parseLong(prixtext.getText()),imagetext.getText(),hots.getIdNom(hoteltext.getValue()),"non occupe");
         hs.Ajouter(H);
          this.initForm();
          this.aa();
        }
        
       
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
        FtpUpload ftp= new FtpUpload();
          ftp.Upload(url, nom);
         Chambre H=new Chambre(Integer.parseInt(textid.getText()),Integer.parseInt(numerotext.getText()),typetext.getValue(),Long.parseLong(prixtext.getText()),imagetext.getText(),hots.getIdNom(hoteltext.getValue()));
         hs.Modifier(H);
         this.initForm();
        this.aa();}
       

        
        
        
        
    }

    @FXML
    private void supprimerChambre(MouseEvent event) {
         Chambre t=tablechambre.getSelectionModel().getSelectedItem();
        ChambreService hs = new ChambreService();
        hs.Supprimer(t);
         
        initForm();
           

        
    }

    /*private void afficherForm(MouseEvent event) {
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

    }*/
   
     private void initForm() {
        numerotext.setText("");
        prixtext.setText("");
        imagetext.setText("");
        typetext.setValue("type");
        hoteltext.setValue("hotel");
                        imageview.setImage(null);

       

        
    }

    @FXML
    private void GHome(ActionEvent event) {
    }

    @FXML
    private void depense(MouseEvent event) {
    }

    @FXML
    private void facture(MouseEvent event) {
    }

    @FXML
    private void gotohotel(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hotel.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btn_Home.getScene().setRoot(root);
    }

    @FXML
    private void gotochambre(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Chambre.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btn_Home.getScene().setRoot(root);
    }

    @FXML
    private void gototransport(ActionEvent event) {
    }

    @FXML
    private void GProduit(ActionEvent event) {
    }

    @FXML
    private void sauthentifier(ActionEvent event) {
    }

    @FXML
    private void display(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btn_Home.getScene().setRoot(root);
    }
     public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
      //  File dir = new File("C:/Users/USER/Document/GitHub/5/Oxyvia-Tours/public/picture");
        File dir1 = new File("C:/Users/USER/Documents/NetBeansProjects/pidev/src/images");
        //String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        String name1 = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
      //  File outputFile = new File(dir, name);
        File outputFile1 = new File(dir1, name1);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        BufferedImage bImage1 = SwingFXUtils.fromFXImage(image, null);
        try {
         //   ImageIO.write(bImage, "png", outputFile);
               ImageIO.write(bImage1, "png", outputFile1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name1;
    }
     
 
     
     public void update(Chambre C){
         HotelService hs=new HotelService();
         
         textid.setText(String.valueOf(C.getId()));
         numerotext.setText(String.valueOf(C.getNumero()));
         prixtext.setText(String.valueOf(C.getPrix()));
         typetext.setValue(C.getType());
         hoteltext.setValue(hs.getNom(C.getIdh()));
         imagetext.setText(C.getImage());
         
         
         
     }
}
