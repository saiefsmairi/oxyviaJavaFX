/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entity.Comment;
import Entity.Hotel;
import Service.CommentService;
import Service.HotelService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class SingleController implements Initializable {

    @FXML
    private Button btn_Home;
    @FXML
    private Button btn_produit;
    @FXML
    private Button btn_Home3;
    @FXML
    private Button btn_Home4;
    @FXML
    private Button btn_sauthentifier;
    @FXML
    private TextArea textcomment;
    @FXML
    private ScrollPane affcomment;
    @FXML
    private ImageView img;
    @FXML
    private Button addcomment;
     HotelService h=new HotelService();
     CommentService s=new CommentService();
        Hotel H=new Hotel();
    @FXML
    private Rating rating;
    @FXML
    private ImageView retour;
        
        
            // TODO
           
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            H=h.ListerH(Hotel.id1);
        } catch (SQLException ex) {
            Logger.getLogger(SingleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List <Comment> liste2= s.Lister(Hotel.id1);

       
        System.out.println(H.getId());
        try {
            rating.setRating(h.moyenneratingparhotel(H.getId()));
        } catch (SQLException ex) {
            Logger.getLogger(SingleController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String imgg = H.getImage();
        String ch="/images/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        img.setImage(imageF);
        
        VBox comm = new VBox();
       
        for (Comment cc : liste2) {
            HBox hbb = new HBox();
            
            Label l = new Label(s.Listernomutilisateur(cc.getIdc())+" " +"commented  :"+" "+cc.getContent()+" "+"at"+" "+cc.getCreated_at());
            System.out.println(s.Listernomutilisateur(cc.getIdc()));
            hbb.getChildren().add(l);
            comm.getChildren().add(hbb);
       
        
        affcomment.setContent(comm);
            try {
                System.out.println(   h.moyenneratingparhotel(H.getId())            );
            } catch (SQLException ex) {
                Logger.getLogger(SingleController.class.getName()).log(Level.SEVERE, null, ex);
            }

        
        
        
    }
    }
        
       

    @FXML
    private void GHome(ActionEvent event) {
    }

    @FXML
    private void payment(MouseEvent event) {
    }

    @FXML
    private void GProduit(ActionEvent event) {
    }

    @FXML
    private void sauthentifier(ActionEvent event) {
    }

    @FXML
    private void comment(MouseEvent event) {
        
java.util.Date datej = new java.util.Date();
                java.util.Date utilDate = new java.util.Date();
                //  java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String created_at = dtf.format(now);
                
        Comment e=new Comment(textcomment.getText(),created_at,H.getId(),1);
        CommentService C=new CommentService();
        C.Ajouter(e);
        textcomment.setText("");
        Notifications  notif= Notifications.create();
                 
                 notif.title("Commentaire ajoute");
notif.text(s.Listernomutilisateur(1) + " "+"vous avez commente avec succes ");
notif.graphic(null);
notif.hideAfter(Duration.seconds(15));
notif.position(Pos.TOP_RIGHT);
notif.showConfirm();
                List <Comment> liste1= s.Lister(Hotel.id1);
                

       VBox commm = new VBox();
       
        for (Comment cc : liste1) {
            HBox hbb = new HBox();
            
            Label l = new Label(s.Listernomutilisateur(cc.getIdc())+" " +"commented  :"+" "+cc.getContent()+" "+"at"+" "+cc.getCreated_at());
            System.out.println(s.Listernomutilisateur(cc.getIdc()));
            hbb.getChildren().add(l);
            commm.getChildren().add(hbb);
       
        
        affcomment.setContent(commm);

        
        
    }}

    @FXML
    private void rater(MouseEvent event) throws SQLException {
        
        HotelService s=new HotelService();

if (!s.ratedornot(8))
        
        s.Ajouterrating(H.getId(), 8, rating.getRating());
else
    s.modifiererrating(H.getId(), rating.getRating(),8);
    }

    @FXML
    private void goretour(MouseEvent event) throws IOException {
        
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btn_Home.getScene().setRoot(root);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
    
    

