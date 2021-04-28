/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import Entities.Reservation;
import Entities.Voyage;
import Services.MyListener;
import Services.ReservationService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author PC-Saif
 */
public class GestionResController implements Initializable {

    private Label fruitNameLable;

    private Label fruitPriceLabel;
    private Label fruitNameLable12;

    private Label fruitPriceLabel2;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;
    private ListView<Reservation> lstviewRes;
    ObservableList<Reservation> fruits;
    ObservableList<Reservation> lstreshotels;
    Reservation res;
    ReservationService resservice = new ReservationService();
    private int ID;
    private Label datedebut;
    private Label datefin;
    private Label single;
    private Label doubletxt;
    private Label adulte;
    private Label enf;
    private Label nomh;
    private Label typeres;

    private Label prix;
    private MyListener myListener;
    private Reservation resclick;
    @FXML
    private Button upbtn;
    @FXML
    private Button suppbtn;
    @FXML
    private TextField cherchertxt;
    ObservableList<Reservation> lstvoyage1;

    /**
     * Initializes the controller class.
     */
    private void setChosenFruit(Reservation fruit) {
        doubletxt.setText(fruit.getNbChambreDoubleReserve() + "");
        adulte.setText(fruit.getNb_adulte() + "");
        single.setText(fruit.getNbChambreSingleReserve() + "");
        enf.setText(fruit.getNb_enfants() + "");
        prix.setText(fruit.getPrix() + "");
        datedebut.setText(fruit.getDate_debut());
        datefin.setText(fruit.getDate_fin() + "");
        nomh.setText(fruit.getNomHotel() + "");
        typeres.setText("Reservation " + fruit.getType());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        suppbtn.setStyle("  -fx-background-color: \n"
                + "        linear-gradient(#ffd65b, #e68400),\n"
                + "        linear-gradient(#ffef84, #f2ba44),\n"
                + "        linear-gradient(#ffea6a, #efaa22),\n"
                + "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
                + "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
                + "    -fx-background-radius: 30;\n"
                + "    -fx-background-insets: 0,1,2,3,0;\n"
                + "    -fx-text-fill: #654b00;\n"
                + "    -fx-font-weight: bold;\n"
                + "    -fx-font-size: 14px;\n"
                + "-fx-pref-width:400px"
                + "    -fx-padding: 10 20 10 20;");

        upbtn.setStyle("  -fx-background-color: \n"
                + "        linear-gradient(#ffd65b, #e68400),\n"
                + "        linear-gradient(#ffef84, #f2ba44),\n"
                + "        linear-gradient(#ffea6a, #efaa22),\n"
                + "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
                + "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
                + "    -fx-background-radius: 30;\n"
                + "    -fx-background-insets: 0,1,2,3,0;\n"
                + "    -fx-text-fill: #654b00;\n"
                + "    -fx-font-weight: bold;\n"
                + "    -fx-font-size: 14px;\n"
                + "    -fx-padding: 10 20 10 20;");
        try {
            loadDate();
        } catch (IOException ex) {
            Logger.getLogger(GestionResController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supp1(ActionEvent event) throws IOException {
        System.out.println("heee"+resclick.getId());
        resservice.supprimerRes(resclick.getId());

        fruits = resservice.AfficherReservation();
        myListener = new MyListener() {
            @Override
            public void onClickListener(Reservation res) {
                resclick = res;
                System.out.println(res);
            }
        };
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < fruits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(fruits.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void update1(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "UpdateRes.fxml"
                )
        );

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );

        UpdateResController controller = loader.getController();
        controller.initData(resclick);

        ///button resever works now i have to insert res hotel to db
        stage.showAndWait();
//        lstviewRes.getItems().clear();

        loadDate();
        System.out.println("hey again");
    }
//rigelt on click lezem nsalah fel update 

    private void loadDate() throws IOException {
        fruits = resservice.AfficherReservation();
        myListener = new MyListener() {
            @Override
            public void onClickListener(Reservation res) {
                resclick = res;
                System.out.println(res);
            }
        };
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < fruits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(fruits.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ajout1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "AjoutReservation.fxml"
                )
        );

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );

        stage.show();
    }

    @FXML
    private void search(KeyEvent event) {
     

    }

    @FXML
    private void searchonbutton(ActionEvent event) {
           fruits = resservice.AfficherReservationSearch(cherchertxt.getText());
           for (int i = 0; i < fruits.size(); i++) {
               System.out.println(fruits.get(i));
        }
        myListener = new MyListener() {
            @Override
            public void onClickListener(Reservation res) {
                resclick = res;
                System.out.println(res);
            }
        };
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < fruits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(fruits.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
//mezelt ajout manaarech bech yaaml ajout de voyage ou hotel 
