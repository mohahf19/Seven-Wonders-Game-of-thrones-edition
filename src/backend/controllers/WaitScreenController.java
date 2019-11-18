package backend.controllers;

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

import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WaitScreenController implements Initializable {
    @FXML
    private Button exitGameButton;

    @FXML
    private Button startGameButton;

    @FXML
    private Label house0, house1, house2, house3, house4, house5, house6;

    @FXML
    private ImageView image0, image1, image2, image3, image4, image5, image6;

    public static ArrayList<Label> labels = new ArrayList<>();

    public static ArrayList<ImageView> images = new ArrayList<>();

    @FXML
    private void startGame(ActionEvent ae){

    }

    public static void updateHouses(ArrayList<String> houses){

        if( houses != null && houses.size() > 0){
            for(  int i = 0; i < 7; i++){
                if( i < houses.size()){
                    labels.get(i).setText( "" + houses.get(i));
                    images.get(i).setVisible( true);
                } else {
                    labels.get(i).setText( "");
                    images.get(i).setVisible( false);
                }
            }
        }

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
        WaitScreenController.labels.add( house0);
        WaitScreenController.labels.add( house1);
        WaitScreenController.labels.add( house2);
        WaitScreenController.labels.add( house3);
        WaitScreenController.labels.add( house4);
        WaitScreenController.labels.add( house5);
        WaitScreenController.labels.add( house6);

        WaitScreenController.images.add( image0);
        WaitScreenController.images.add( image1);
        WaitScreenController.images.add( image2);
        WaitScreenController.images.add( image3);
        WaitScreenController.images.add( image4);
        WaitScreenController.images.add( image5);
        WaitScreenController.images.add( image6);

    }
}
