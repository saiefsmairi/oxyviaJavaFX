/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entity.Chambre;
import Entity.Hotel;
import Entity.chambrestatic;
import Service.ChambreService;
import Service.HotelService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ItemController {

    @FXML
    private ImageView img;
   

 public List <Chambre> listchambres;

    

   
    @FXML
    private Label nameLabel;
    @FXML
    private Label paysLable;
    @FXML
    private Label emailnumlabel;
    @FXML
    private Label adresselabel;
    private Hotel Hotel;
    @FXML
    private Button btnaffchambre;
    @FXML
    private Label labelid;
    @FXML
    private Button btncomment;
     
    private HotelController hh; 
    @FXML
    private Rating rating;

    public void setData(Hotel hotel) throws SQLException {
        HotelService s=new HotelService();
        
        this.Hotel = hotel;
                        labelid.setText(String.valueOf(hotel.getId()));
                        



        
        nameLabel.setText("Nom:"+" "+hotel.getName());
        paysLable.setText("Pays:"+" "+hotel.getPays());
        adresselabel.setText("ADRESSE:"+" "+hotel.getAdresse());
        emailnumlabel.setText("CONTACT:EMAIL:"+" "+hotel.getEmail()+"/"+
                "NUMERO:"+hotel.getNum());
        rating.setRating(s.moyenneratingparhotel(hotel.getId()));
        String imgg= hotel.getImage();
        String ch="/images/";
        String imgF= ch+imgg;
        
        Image imageF = new Image("ftp://user:123456789@192.168.1.13/"+hotel.getImage());
        img.setImage(imageF);
          
        
    }

  

    @FXML
    private void gotochambre(MouseEvent event) throws IOException {


       chambrestatic.id=Integer.parseInt(labelid.getText());
       

         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homechambre.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnaffchambre.getScene().setRoot(root);
    }

    @FXML
    private void gotocomment(MouseEvent event) throws IOException {
        Hotel.id1=Integer.parseInt(labelid.getText());
        System.out.println(Hotel.id1);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("single.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btncomment.getScene().setRoot(root);
    }
    
    
}
