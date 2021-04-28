/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entity.Chambre;
import Entity.Hotel;
import Service.ChambreService;
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
public class ItemChambreController  {

    @FXML
    private ImageView img;
    @FXML
    private Label numLabel;
    @FXML
    private Label typeLable;
    @FXML
    private Label prixlabel;
    private Chambre Chambre;
    private ChambreController ch; 
    /**
     * Initializes the controller class.
     */
   
    public void setData(Chambre chambre) {
        
        this.Chambre = chambre;
          

        
        numLabel.setText("numero:"+" "+chambre.getNumero());
        typeLable.setText("type:"+" "+chambre.getType());
        prixlabel.setText("prix:"+" "+chambre.getPrix());
        
        String imgg= chambre.getImage();
        String ch="/images/";
        String imgF= ch+imgg;
        
        Image imageF = new Image("ftp://user:123456789@192.168.1.13/"+chambre.getImage());
        img.setImage(imageF);
        
    }
   
  
}
