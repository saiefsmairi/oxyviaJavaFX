/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;
import Entity.Hotel;
import Service.HotelService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ItemHotelBackController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label numLabel;
    @FXML
    private Label typeLable;
    @FXML
    private Label prixlabel;
    @FXML
    private Button suppbtn;
    @FXML
    private Button modbtn;
    
 
    private HotelController hh; 
    @FXML
    private Label nblabel;
    @FXML
    private Label nummlabel;
    @FXML
    private Label emaillabel;
    
     private Hotel Hotel;
    private HotelController hc; 
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
        public void setData(Hotel hotel,HotelController hc) {
        
        this.Hotel = hotel;
        this.hh=hc;   

        
        numLabel.setText("name:"+" "+hotel.getName());
        typeLable.setText("pays:"+" "+hotel.getPays());
        prixlabel.setText("adresse:"+" "+hotel.getAdresse());
        nblabel.setText("nbetoile:"+" "+hotel.getNbEtoile());
        nummlabel.setText("num:"+" "+hotel.getNum());
        emaillabel.setText("email:"+" "+hotel.getEmail());
        
        
        
        
        String imgg= hotel.getImage();
        String ch="/images/";
        String imgF= ch+imgg;
        
        Image imageF = new Image("ftp://user:123456789@192.168.1.13/"+hotel.getImage());
        img.setImage(imageF);
        
    }
        
        

    @FXML
    private void supp(MouseEvent event) {
       HotelService cs=new HotelService();
        cs.Supprimer(Hotel);
        this.hh.aa();
    }

    @FXML
    private void modi(MouseEvent event) {
          this.hh.update(this.Hotel);
        this.hh.aa();
    }
}

