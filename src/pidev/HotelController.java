/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entity.Hotel;
import Service.HotelService;
import Utulitaire.FtpUpload;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.HashMap;
import java.util.Map;
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
    private TableView<Hotel> tablehotel;
    private TableColumn<Hotel, String> name;
    private TableColumn<Hotel, String> pays;
    private TableColumn<Hotel, String> adresse;
    private TableColumn<Hotel, Integer> nbetoile;
    private TableColumn<Hotel, Integer> num;
    private TableColumn<Hotel, String> email;
    @FXML
    private Label textid;
    @FXML
    private TextField imagetext;
    private TableColumn<Hotel, String> image;
    @FXML
    private ImageView imageview;
    @FXML
    private Label labelnbetoile;
    @FXML
    private Label labelnum;
    @FXML
    private Label labelemail;
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
    private Button ajouterbtn2;
    @FXML
    private Button modifierbtn;
    @FXML
    private ScrollPane hotelscroll;
    @FXML
    private GridPane hotelgrid;
  
    String url,nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       HotelService hs = new HotelService() ;
        
       
        ObservableList<String> list;
        
       
       
         hotelscroll.setVisible(true);
        hotelgrid.getChildren().clear();
     
        
  List<Hotel> lh = hs.Lister();
    
        int column = 0;
        int row = 1;
        
        try {
            System.out.println(lh.size());
            for (int i = 0; i < lh.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemHotelBack.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemHotelBackController itemController = fxmlLoader.getController();
                itemController.setData(lh.get(i),this);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                hotelgrid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                hotelgrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                hotelgrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                hotelgrid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                hotelgrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                hotelgrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                hotelgrid.setMaxHeight(Region.USE_PREF_SIZE);
                 hotelgrid.setMargin(anchorPane, new Insets(10));

              //  GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
                        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);

            
        }

       
    }    
     
public static void generate_qr(String image_name, String qrCodeData) {
        try {
            String filePath = "C:/Users/USER/Documents/NetBeansProjects/pidev/src/QrCode/" + image_name + ".png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }
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
            FtpUpload ftp= new FtpUpload();
          ftp.Upload(url, nom);
         Hotel H=new Hotel(Integer.parseInt(textid.getText()),textname.getText(),textpays.getText(),textadresse.getText(),Integer.parseInt(textnbetoile.getText()),Integer.parseInt(textnum.getText()),textemail.getText(),imagetext.getText(),1,1);
         hs.Modifier(H);
       a=1;
        this.initForm();
       this.aa();}
         List<Hotel> lh = hs.Lister();

        ObservableList<Hotel> data
                = FXCollections.observableArrayList(lh);
        tablehotel.setItems(data);
        
            
    }

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
    
    

   /* private void afficherhotel(MouseEvent event) {
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

    }*/
    
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
 public void aa(){
        
        
    HotelService hs = new HotelService() ;
        
       
        ObservableList<String> list;
        
       
       
         hotelscroll.setVisible(true);
        hotelgrid.getChildren().clear();
     
        
  List<Hotel> lh = hs.Lister();
    
        int column = 0;
        int row = 1;
        
        try {
            System.out.println(lh.size());
            for (int i = 0; i < lh.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemHotelBack.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemHotelBackController itemController = fxmlLoader.getController();
                itemController.setData(lh.get(i),this);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                hotelgrid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                hotelgrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                hotelgrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                hotelgrid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                hotelgrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                hotelgrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                hotelgrid.setMaxHeight(Region.USE_PREF_SIZE);
                 hotelgrid.setMargin(anchorPane, new Insets(10));

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

 public void update(Hotel C){
         HotelService hs=new HotelService();
         
         textid.setText(String.valueOf(C.getId()));
         textname.setText(String.valueOf(C.getName()));
         textpays.setText(String.valueOf(C.getPays()));
         textadresse.setText(String.valueOf(C.getAdresse()));
         textnbetoile.setText(String.valueOf(C.getNbEtoile()));
          textemail.setText(String.valueOf(C.getEmail()));
           textnum.setText(String.valueOf(C.getNum()));
         imagetext.setText(C.getImage());
         
          
         
     }
 

    @FXML
    private void ajouterhotel(MouseEvent event) {int a=0;
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
          FtpUpload ftp= new FtpUpload();
          ftp.Upload(url, nom);
             System.out.println("test1");
             System.out.println(url);
             System.out.println(nom);
         Hotel H=new Hotel(textname.getText(),textpays.getText(),textadresse.getText(),Integer.parseInt(textnbetoile.getText()),Integer.parseInt(textnum.getText()),textemail.getText(),imagetext.getText(),1,1);
         hs.Ajouter(H);
             generate_qr(textname.getText(), textname.getText());
          a=1;
      this.initForm();
      this.aa();}
         List<Hotel> lh = hs.Lister();

        ObservableList<Hotel> data
                = FXCollections.observableArrayList(lh);
        tablehotel.setItems(data);
      
        
    }
}
