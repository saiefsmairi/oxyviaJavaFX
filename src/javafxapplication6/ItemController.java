/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import Entities.Reservation;
import Entities.Voyage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Services.MyListener;

/**
 * FXML Controller class
 *
 * @author PC-Saif
 */
public class ItemController implements Initializable {

    private Label nameLabel;
    private Label priceLable;

    private Reservation r;
    private Label nameLabel1;
    private Label priceLable1;
    @FXML
    private Label dd;
    @FXML
    private Label df;
    @FXML
    private Label sinlge;
    @FXML
    private Label doubletxt;
    @FXML
    private Label adulte;
    @FXML
    private Label enfants;
    @FXML
    private Label prixval;

    private MyListener myListener;
    @FXML
    private Label nomhotel;
    @FXML
    private Label typeres;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Reservation r, MyListener myListener) {
        this.r = r;
        this.myListener = myListener;
        doubletxt.setText(r.getNbChambreDoubleReserve() + "");
        adulte.setText(r.getNb_adulte() + "");
        sinlge.setText(r.getNbChambreSingleReserve() + "");
        enfants.setText(r.getNb_enfants() + "");
        prixval.setText(r.getPrix() + "");
        dd.setText(r.getDate_debut());
        df.setText(r.getDate_fin() + "");
          nomhotel.setText( r.getNomHotel() + "");
           typeres.setText( r.getType() + "");
        //Image image = new Image(getClass().getResourceAsStream("/image/nbpers.png"));
       // imm.setImage(image);
 
    }

    @FXML
    private void click(MouseEvent event) {
        myListener.onClickListener(r);

    }

}
