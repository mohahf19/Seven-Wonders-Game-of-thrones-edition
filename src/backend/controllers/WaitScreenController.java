package backend.controllers;

import backend.app.Main;
import backend.app.fxmlPaths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WaitScreenController implements Initializable {
    @FXML
    private Button exitGameButton;

    @FXML
    private Button startGameButton;

    @FXML
    private static Label house0, house1, house2, house3, house4, house5, house6;

    @FXML
    private static ImageView image0, image1, image2, image3, image4, image5, image6;

    public static ArrayList<Label> labels = new ArrayList<>();

    public static ArrayList<ImageView> images = new ArrayList<>();

    @FXML
    private void startGame(ActionEvent ae){

    }

    public static void updateHouses(ArrayList<String> houses){
//        labels.clear();
//        images.clear();
//        labels.add(house0);
//        labels.add(house1);
//        labels.add(house2);
//        labels.add(house3);
//        labels.add(house4);
//        labels.add(house5);
//        labels.add(house6);
//
//        images.add(image0);
//        images.add(image1);
//        images.add(image2);
//        images.add(image3);
//        images.add(image4);
//        images.add(image5);
//        images.add(image6);
//        for(int i = 0; i < houses.size(); i++){
//            labels.get(i).setText(houses.get(i));
//        }
    }

    @FXML
    private void exitGame(ActionEvent ae){
        //add an alert
        try {
            Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
            Parent page = FXMLLoader.load(getClass().getResource(fxmlPaths.mainMenu));
            stage.getScene().setRoot(page);
            stage.sizeToScene();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Main.game.conn.server == null) {
            startGameButton.setVisible(false);
        }
    }
}
