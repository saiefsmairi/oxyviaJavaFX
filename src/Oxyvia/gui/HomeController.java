/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author smp
 */
public class HomeController implements Initializable {

    @FXML
    private Button btn_Home;
    @FXML
    private Button btn_produit;
    @FXML
    private Button btn_Home3;
    @FXML
    private Button btn_Home4;
 int count;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // slideshow();
    }    

//   public void slideshow() {
//       ArrayList<Image> images = new ArrayList <Image>();
//        images.add(new Image("/Oxyvia/gui/1.png"));
//        images.add(new Image("/Oxyvia/gui/3.jpg"));
//        images.add(new Image("/Oxyvia/gui/4.jpg"));
//        images.add(new Image("/Oxyvia/gui/5.jpg"));
//        images.add(new Image("/Oxyvia/gui/2.jpg"));
//        Timeline timeline = new Timeline (new KeyFrame(Duration.seconds(2.5), event -> {
//       imageView.setImage(images.get(count));
//       count++;
//       if(count == 5)
//           count = 0;
//            
//        }));
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
//        
//    }

    @FXML
    private void payment(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Oxyvia/gui/addFacture.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GProduit(ActionEvent event) {
    }


     @FXML
    private void GHome(ActionEvent event) {
        
    
}
}