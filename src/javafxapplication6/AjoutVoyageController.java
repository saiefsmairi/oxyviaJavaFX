/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import Entities.Hotel;
import Entities.Place;
import Entities.Voyage;
import Services.VoyageService;
import Services.HotelService;

import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.xml.sax.Parser;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import javafx.util.Callback;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLInputElement;

/**
 * FXML Controller class
 *
 * @author PC-Saif
 */
public class AjoutVoyageController implements Initializable {

    @FXML
    private TextField nomvoyage;
    @FXML
    private DatePicker datedebutvoyage;
    @FXML
    private DatePicker datefinvoyage;
    @FXML
    private TextField villevoyage;
    @FXML
    private TextArea descvoyage;
    @FXML
    private TextField nbpersonnevoyage;
    @FXML
    private TextField prixpersonnevoyage;
    VoyageService voyageService = new VoyageService();
    HotelService hotelService = new HotelService();
    @FXML
    private ComboBox<Hotel> comboHotel;
    private boolean EDIT = false, ADD = true;
    private int ID;

    private Hotel idSelectedHotel;
    @FXML
    private TextField txtaltitude;
    @FXML
    private TextField txtlong;
    @FXML
    private TextField txtplace1;
    MyBrowser myBrowser;
    @FXML
    private ListView<Voyage> listviewvoy;

    private int indexSelectHotel;
    ObservableList<Voyage> lstvoyage;
    ObservableList<Voyage> lstvoyage1;

    Voyage v = null;
    @FXML
    private Button saveVoyageForupdate;
    String test;
    @FXML
    private BorderPane ListeCadeauxBorderPane;

    @FXML
    private TextField chercher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
txtaltitude.setVisible(false);
txtlong.setVisible(false);

        loadDate();

        List<Hotel> liste = hotelService.AfficherNomsHotels();

        comboHotel.setConverter(new StringConverter<Hotel>() {
            @Override
            public String toString(Hotel object) {
                return object.getName();
            }

            @Override
            public Hotel fromString(String string) {
                return null;
            }
        });

        comboHotel.setItems(FXCollections.observableArrayList(liste));

        comboHotel.valueProperty().addListener((obs, oldVal, newVal) -> {
            idSelectedHotel = newVal;
            indexSelectHotel = comboHotel.getSelectionModel().getSelectedIndex();

        });

    }

    public void loadDate() {
        lstvoyage = voyageService.AfficherVoyages();

        listviewvoy.getItems().addAll(lstvoyage);
        listviewvoy.setCellFactory(new Callback<ListView<Voyage>, ListCell<Voyage>>() {

            @Override
            public ListCell<Voyage> call(ListView<Voyage> arg0) {
                return new ListCell<Voyage>() {

                    @Override
                    protected void updateItem(Voyage item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {
                            int column = 0;
                            int row = 1;
                            GridPane gridPane = new GridPane();

                            Label NomVoy = new Label();
                            Label Villevoy = new Label();
                            Label prix = new Label();
                            Label datedebut = new Label();
                            Label datefin = new Label();
                            Label nbperso = new Label();
                            Label place = new Label();

                            Image img5 = new Image("/image/ville.png");
                            ImageView view5 = new ImageView(img5);
                            view5.setFitHeight(40);
                            view5.setPreserveRatio(true);
                            Villevoy.setGraphic(view5);

                            Image img = new Image("/image/money.png");
                            ImageView view = new ImageView(img);
                            view.setFitHeight(40);
                            view.setPreserveRatio(true);
                            prix.setGraphic(view);

                            Image img2 = new Image("/image/nbpers.png");
                            ImageView view2 = new ImageView(img2);
                            view2.setFitHeight(40);
                            view2.setPreserveRatio(true);
                            nbperso.setGraphic(view2);

                            Image img6 = new Image("/image/travel.png");
                            ImageView view6 = new ImageView(img6);
                            view6.setFitHeight(40);
                            view6.setPreserveRatio(true);
                            place.setGraphic(view6);

                            Image img3 = new Image("/image/date.png");
                            ImageView view3 = new ImageView(img3);
                            view3.setFitHeight(30);
                            view3.setPreserveRatio(true);
                            datedebut.setGraphic(view3);

                            Image img4 = new Image("/image/date.png");
                            ImageView view4 = new ImageView(img4);
                            view4.setFitHeight(30);
                            view4.setPreserveRatio(true);
                            datefin.setGraphic(view4);

                            AnchorPane content = new AnchorPane();
                            content.getStyleClass().add("pane");

                            gridPane.setMinWidth(200);
                            content.setStyle("-fx-padding: 10;"
                                    + "-fx-pref-width:80px"
                                    + "-fx-background-radius: 100;" + "-fx-background-color: rgb(0, 115, 183,0.6);"
                                    + "-fx-background-radius: 30;"
                                    + "    -fx-font-family: verdana;  "
                                    + " -fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0);"
                                    + " -fx-background-color: #FFFFFF;\n"
                                    + "        -fx-background-radius: 30;"
                            );

                            gridPane.setStyle(
                                    "-fx-background-radius: 100;"
                                    + "-fx-background-radius: 30;"
                            );

                            NomVoy.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em; ");

                            GridPane.setConstraints(NomVoy, 0, 0, 1, 1);
                            GridPane.setConstraints(Villevoy, 1, 1, 1, 1);

                            GridPane.setConstraints(prix, 1, 2, 1, 1);
                            GridPane.setConstraints(nbperso, 1, 3, 1, 1);

                            GridPane.setConstraints(datedebut, 1, 4, 1, 1);
                            GridPane.setConstraints(datefin, 1, 5, 1, 1);
                            GridPane.setConstraints(place, 1, 6, 1, 1);

                            gridPane.getChildren().setAll(Villevoy, NomVoy, prix, datedebut, datefin, nbperso, place);

                            AnchorPane.setTopAnchor(gridPane, 0d);
                            AnchorPane.setLeftAnchor(gridPane, 0d);
                            AnchorPane.setBottomAnchor(gridPane, 0d);
                            AnchorPane.setRightAnchor(gridPane, 0d);

                            content.getChildren().add(0, gridPane);
                            content.setMaxSize(400, 200);

                            NomVoy.setText(item.getNom());
                            Villevoy.setText(item.getVille());
                            prix.setText(item.getPrix_personne() + "");
                            datedebut.setText(item.getDate_debut());
                            datefin.setText(item.getDate_fin() + "");
                            nbperso.setText(item.getNb_personne() + "");
                            place.setText(voyageService.placeByVoyage(item.getId()).getNom());

                            setText(null);
                            setGraphic(content);
                            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

                        }
                    }

                };
            }
        });
        listviewvoy.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Voyage>() {
            @Override
            public void changed(ObservableValue<? extends Voyage> observable, Voyage oldValue, Voyage newValue) {
                System.out.println(newValue.getNom());
                System.out.println(newValue.getVille());
            }
        });

    }

    private void AjoutVoyage(ActionEvent event) {

        Voyage v = new Voyage(nomvoyage.getText(), villevoyage.getText(), descvoyage.getText(), datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(prixpersonnevoyage.getText()), Integer.parseInt(nbpersonnevoyage.getText()), null, idSelectedHotel);

        Place p = new Place(txtlong.getText(), txtaltitude.getText(), txtplace1.getText());
        v.setHotel(idSelectedHotel);
        voyageService.ajouterVoyage(v, p);

        loadDate();

    }

    @FXML
    private void updateVoyage(ActionEvent event) {
        ADD = false;
        EDIT = true;
        v = listviewvoy.getSelectionModel().getSelectedItem();
        ID = v.getId();
        nomvoyage.setText(v.getNom());
        datedebutvoyage.setValue(LocalDate.parse(v.getDate_debut()));
        datefinvoyage.setValue(LocalDate.parse(v.getDate_debut()));
        nbpersonnevoyage.setText(v.getNb_personne() + "");
        prixpersonnevoyage.setText(v.getPrix_personne() + "");
        villevoyage.setText(v.getVille());
        descvoyage.setText(v.getDescription());

        comboHotel.setValue(voyageService.findHotelByID(v.getIdHotel()));

    }

    private void supprimerVoyage(MouseEvent event) {
        v = listviewvoy.getSelectionModel().getSelectedItem();

        System.out.println(v);
        voyageService.supprimerVoyage(v.getId());
        loadDate();

    }

    private void saveVoyageForupdate(MouseEvent event) {
        System.out.println(idSelectedHotel);

        if (ADD == false && EDIT) {

            Voyage voy = new Voyage(ID, nomvoyage.getText(), villevoyage.getText(), descvoyage.getText(), datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(prixpersonnevoyage.getText()), Integer.parseInt(nbpersonnevoyage.getText()), null, comboHotel.getSelectionModel().getSelectedItem());
            Place p = new Place(txtlong.getText(), txtaltitude.getText(), txtplace1.getText());

            voyageService.modifierVoyage(voy, ID, p);
            loadDate();
        } else {

            Voyage v = new Voyage(nomvoyage.getText(), villevoyage.getText(), descvoyage.getText(), datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(prixpersonnevoyage.getText()), Integer.parseInt(nbpersonnevoyage.getText()), null, idSelectedHotel);

            Place p = new Place(txtlong.getText(), txtaltitude.getText(), txtplace1.getText());
            v.setHotel(idSelectedHotel);
            voyageService.ajouterVoyage(v, p);

            loadDate();

        }
    }

    @FXML
    private void viderChamps(ActionEvent event) {
        nomvoyage.clear();
        datedebutvoyage.setValue(null);
        datefinvoyage.setValue(null);
        nbpersonnevoyage.clear();
        prixpersonnevoyage.clear();
        villevoyage.clear();
        descvoyage.clear();
        txtaltitude.clear();
        txtlong.clear();
        txtplace1.clear();
        comboHotel.getSelectionModel().select(null);

    }

    private void ajoutmap(MouseEvent event) {

        Stage stage = new Stage(StageStyle.DECORATED);
        myBrowser = new MyBrowser();
        Scene scene = new Scene(myBrowser);

        stage.setScene(scene);

        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.hide();
        stage.showAndWait();

    }

    @FXML
    private void saveVoyageForupdate(ActionEvent event) {
    }

    @FXML
    private void ajoutmap(ActionEvent event) {
    }

    @FXML
    private void supprimerVoyage(ActionEvent event) {
        v = listviewvoy.getSelectionModel().getSelectedItem();

        System.out.println(v);
        voyageService.supprimerVoyage(v.getId());

        loadDate();

    }

    @FXML
    private void supprimerVoyage2(MouseEvent event) {
        v = listviewvoy.getSelectionModel().getSelectedItem();

        System.out.println(v);
        voyageService.supprimerVoyage(v.getId());
        listviewvoy.getItems().clear();

        loadDate();

    }

    public boolean estUnEntier(String chaine) {
        try {
            Integer.parseInt(chaine);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    @FXML
    private void saveVoyageForupdate2(MouseEvent event) {
        System.out.println(idSelectedHotel);

        if (ADD == false && EDIT) {

            Voyage voy = new Voyage(ID, nomvoyage.getText(), villevoyage.getText(), descvoyage.getText(), datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(prixpersonnevoyage.getText()), Integer.parseInt(nbpersonnevoyage.getText()), null, comboHotel.getSelectionModel().getSelectedItem());
            Place p = new Place(txtlong.getText(), txtaltitude.getText(), txtplace1.getText());

            voyageService.modifierVoyage(voy, ID, p);
            listviewvoy.getItems().clear();

            loadDate();
        } else {

            if ((nomvoyage.getText().isEmpty()) || (villevoyage.getText().isEmpty()) || (descvoyage.getText().isEmpty()) || (prixpersonnevoyage.getText().isEmpty()) || (nbpersonnevoyage.getText().isEmpty())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("champ invalide");

                alert.setContentText("Veuillez remplir tous les champs ");

                alert.showAndWait();
            } else if (!estUnEntier(prixpersonnevoyage.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("champ invalide");

                alert.setContentText("le champs doit etre un entier ");

                alert.showAndWait();
            } else {

                Voyage v = new Voyage(nomvoyage.getText(), villevoyage.getText(), descvoyage.getText(), datedebutvoyage.getValue().toString(), datefinvoyage.getValue().toString(), Integer.parseInt(prixpersonnevoyage.getText()), Integer.parseInt(nbpersonnevoyage.getText()), null, idSelectedHotel);

                Place p = new Place(txtlong.getText(), txtaltitude.getText(), txtplace1.getText());
                v.setHotel(idSelectedHotel);
                voyageService.ajouterVoyage(v, p);
                listviewvoy.getItems().clear();

                loadDate();
            }
        }

    }

    @FXML
    private void updateVoyage2(MouseEvent event) {
        ADD = false;
        EDIT = true;
        v = listviewvoy.getSelectionModel().getSelectedItem();
        ID = v.getId();
        nomvoyage.setText(v.getNom());
        datedebutvoyage.setValue(LocalDate.parse(v.getDate_debut()));
        datefinvoyage.setValue(LocalDate.parse(v.getDate_debut()));
        nbpersonnevoyage.setText(v.getNb_personne() + "");
        prixpersonnevoyage.setText(v.getPrix_personne() + "");
        villevoyage.setText(v.getVille());
        descvoyage.setText(v.getDescription());

        comboHotel.setValue(voyageService.findHotelByID(v.getIdHotel()));
    }

    @FXML
    private void ajoutmap2(MouseEvent event) {
        Stage stage = new Stage(StageStyle.DECORATED);
        myBrowser = new MyBrowser();
        Scene scene = new Scene(myBrowser);

        stage.setScene(scene);

        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.hide();
        stage.showAndWait();

    }

    @FXML
    private void ChercherCadeauxFonction(KeyEvent event) {

        lstvoyage1 = voyageService.Search(chercher.getText());

        listviewvoy.setItems(lstvoyage1);
        listviewvoy.setCellFactory(new Callback<ListView<Voyage>, ListCell<Voyage>>() {

            @Override
            public ListCell<Voyage> call(ListView<Voyage> arg0) {
                return new ListCell<Voyage>() {

                    @Override
                    protected void updateItem(Voyage item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {
                            int column = 0;
                            int row = 1;
                            GridPane gridPane = new GridPane();

                            Label NomVoy = new Label();
                            Label Villevoy = new Label();
                            Label prix = new Label();
                            Label datedebut = new Label();
                            Label datefin = new Label();
                            Label nbperso = new Label();
                            Label place = new Label();

                            Image img5 = new Image("/image/ville.png");
                            ImageView view5 = new ImageView(img5);
                            view5.setFitHeight(40);
                            view5.setPreserveRatio(true);
                            Villevoy.setGraphic(view5);

                            Image img = new Image("/image/money.png");
                            ImageView view = new ImageView(img);
                            view.setFitHeight(40);
                            view.setPreserveRatio(true);
                            prix.setGraphic(view);

                            Image img2 = new Image("/image/nbpers.png");
                            ImageView view2 = new ImageView(img2);
                            view2.setFitHeight(40);
                            view2.setPreserveRatio(true);
                            nbperso.setGraphic(view2);

                            Image img3 = new Image("/image/date.png");
                            ImageView view3 = new ImageView(img3);
                            view3.setFitHeight(30);
                            view3.setPreserveRatio(true);
                            datedebut.setGraphic(view3);

                            Image img4 = new Image("/image/date.png");
                            ImageView view4 = new ImageView(img4);
                            view4.setFitHeight(30);
                            view4.setPreserveRatio(true);
                            datefin.setGraphic(view4);

                            AnchorPane content = new AnchorPane();
                            content.getStyleClass().add("pane");
                            content.setStyle("-fx-padding: 10;"
                                    + "-fx-pref-width:80px"
                                    + "-fx-background-radius: 100;" + "-fx-background-color: rgb(0, 115, 183,0.6);"
                                    + "-fx-background-radius: 30;"
                                    + "    -fx-font-family: verdana;  "
                            );

                            gridPane.setStyle(
                                    "-fx-background-radius: 100;"
                                    + "-fx-background-radius: 30;"
                            );

                            NomVoy.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em; ");

                            GridPane.setConstraints(NomVoy, 0, 0, 1, 1);
                            GridPane.setConstraints(Villevoy, 1, 1, 1, 1);

                            GridPane.setConstraints(prix, 1, 2, 1, 1);
                            GridPane.setConstraints(nbperso, 1, 3, 1, 1);

                            GridPane.setConstraints(datedebut, 1, 4, 1, 1);
                            GridPane.setConstraints(datefin, 1, 5, 1, 1);
                            GridPane.setConstraints(place, 1, 6, 1, 1);

                            gridPane.getChildren().setAll(Villevoy, NomVoy, prix, datedebut, datefin, nbperso, place);

                            AnchorPane.setTopAnchor(gridPane, 0d);
                            AnchorPane.setLeftAnchor(gridPane, 0d);
                            AnchorPane.setBottomAnchor(gridPane, 0d);
                            AnchorPane.setRightAnchor(gridPane, 0d);

                            content.getChildren().add(0, gridPane);
                            content.setMaxSize(400, 200);

                            NomVoy.setText(item.getNom());
                            Villevoy.setText(item.getVille());
                            prix.setText(item.getPrix_personne() + "");
                            datedebut.setText(item.getDate_debut());
                            datefin.setText(item.getDate_fin() + "");
                            nbperso.setText(item.getNb_personne() + "");
                            place.setText(voyageService.placeByVoyage(item.getId()).getNom());

                            setText(null);
                            setGraphic(content);
                            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

                        }
                    }

                };
            }
        });
        listviewvoy.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Voyage>() {
            @Override
            public void changed(ObservableValue<? extends Voyage> observable, Voyage oldValue, Voyage newValue) {
                System.out.println(newValue.getNom());
                System.out.println(newValue.getVille());
            }
        });

        for (Voyage voyage : lstvoyage1) {
            System.out.println(voyage);
        }

    }

    class MyBrowser extends Pane {

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        public MyBrowser() {

            final URL urlGoogleMaps = getClass().getResource("openMap.html");
            webEngine.load(urlGoogleMaps.toExternalForm());

            webEngine.setJavaScriptEnabled(true);

            Button btn = new Button("Enregistrer");

            VBox hbBtn = new VBox();
            hbBtn.setSpacing(10);
            System.out.println(hbBtn.alignmentProperty());

            Label lbl = new Label("Choix Place du voyage :");
            lbl.setFont(Font.font("Amble CN", FontWeight.BOLD, 22));

            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);

            lbl.setStyle("  -fx-background-color: \n"
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

            hbBtn.setStyle("  -fx-background-color: \n"
                    + "        #000000,\n"
                    + "        linear-gradient(#7ebcea, #2f4b8f),\n"
                    + "        linear-gradient(#426ab7, #263e75),\n"
                    + "        linear-gradient(#395cab, #223768);\n"
                    + "    -fx-background-insets: 0,1,2,3;\n"
                    + "    -fx-background-radius: 3,2,2,2;\n"
                    + "    -fx-padding: 12 30 12 30;\n"
                    + "    -fx-text-fill: white;\n"
                    + "    -fx-font-size: 12px;");

            webEngine.getLoadWorker().stateProperty().addListener(
                    new ChangeListener<Worker.State>() {
                @Override
                public void changed(ObservableValue<? extends Worker.State> ov,
                        Worker.State oldState, Worker.State newState) {
                    if (newState == Worker.State.SUCCEEDED) {
                        Element emailField = webEngine.getDocument().getElementById("output");

                        System.out.println("wiw" + emailField.getTextContent());

                    }
                }
            }
            );

            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {

                    Double latidude = (Double) webEngine.executeScript("latidude;");
                    Double longtitude = (Double) webEngine.executeScript("longtitude;");

                    Element place = webEngine.getDocument().getElementById("output");

                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource(
                                    "AjoutVoyage.fxml"
                            )
                    );
                    Stage stage = new Stage(StageStyle.DECORATED);
                    try {
                        stage.setScene(
                                new Scene(loader.load())
                        );
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    AjoutVoyageController controller = loader.getController();
                    controller.initData2(latidude, longtitude, place.getTextContent());

                    ///button resever works now i have to insert res hotel to db
                    stage.show();
                    Stage stage2 = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    stage2.hide();

                }
            });
            hbBtn.getChildren().addAll(lbl, btn);

            getChildren().addAll(webView, hbBtn);

        }
    }

    protected void initData2(Double latidude, Double latidude2, String place) {
        // TODO Auto-generated method stub
        txtlong.setText(Double.toString(latidude));
        txtaltitude.setText(Double.toString(latidude2));
        txtplace1.setText(place);

    }
}
