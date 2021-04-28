/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entity.Chambre;
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
public class ItemChambreBackController implements Initializable {

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
     private Chambre Chambre;
    private ChambreController ch; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   public void setData(Chambre chambre,ChambreController cc) {
        
        this.Chambre = chambre;
        this.ch=cc;   

        
        numLabel.setText("numero:"+" "+chambre.getNumero());
        typeLable.setText("type:"+" "+chambre.getType());
        prixlabel.setText("prix:"+" "+chambre.getPrix());
        
        String imgg= chambre.getImage();
        String ch="/images/";
        String imgF= ch+imgg;
        
        Image imageF = new Image("ftp://user:123456789@192.168.1.13/"+chambre.getImage());
        img.setImage(imageF);
        
    }
   
    @FXML
    private void supp(MouseEvent event) {
        ChambreService cs=new ChambreService();
        cs.Supprimer(Chambre);
        this.ch.aa();
    }
    

    @FXML
    private void modi(MouseEvent event) {
         this.ch.update(this.Chambre);
        this.ch.aa();
    }
}