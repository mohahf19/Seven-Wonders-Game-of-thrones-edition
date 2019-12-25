package backend.controllers;

import backend.app.Main;
import backend.app.fxmlPaths;
import comm.ServerController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WaitScreenController implements Initializable {
    @FXML
    private Button exitGameButton;

    @FXML
    private Button startGameButton;

    @FXML
    private Label house0, house1, house2, house3, house4, house5, house6, ipLabel;

    @FXML
    private ImageView image0, image1, image2, image3, image4, image5, image6;

    @FXML
    private TextField ipText;

    public static ArrayList<Label> labels = new ArrayList<>();

    public static ArrayList<ImageView> images = new ArrayList<>();

    @FXML
    private void startGame(){
        if( Main.serverController != null && Main.serverController.players.size() > 0){
            Main.serverController.startGame();
        } else {
            JOptionPane.showMessageDialog( null, "Atleast 3 players needed to play game.", "", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void showMainScreen(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try{
                    Stage stage = (Stage) Main.window;
                    Parent page = FXMLLoader.load(getClass().getResource(fxmlPaths.playMenu));
                    stage.getScene().setRoot(page);
                    stage.sizeToScene();
                }catch (Exception e){
                    System.out.println("Exception");
                    e.printStackTrace();
                }
            }
        });
    }

    public static void updateHouses(ArrayList<String> houses){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if( houses != null && houses.size() > 0){
                    for(  int i = 0; i < 7; i++){
                        if( i < houses.size()){
                            labels.get(i).setText( "" + houses.get(i));
                            //labels.get(i).setAlignment(Pos.CENTER);
                            images.get(i).setImage( new Image( "/assets/" + houses.get(i) + ".jpg"));
                            images.get(i).setVisible( true);
                        } else {
                            labels.get(i).setText( "");
                            images.get(i).setVisible( false);
                        }
                    }
                }
            }
        });
    }

    @FXML
    private void exitGame(ActionEvent ae){
        try {
            if( Main.state == 1){
                Main.serverController.host.quitHost();
                Main.gameEngine.client.quitGame();
                Main.serverController = null;
                Main.gameEngine = null;
            } else {
                Main.gameEngine.client.quitGame();
                Main.gameEngine = null;
            }

            Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
            Parent page = FXMLLoader.load(getClass().getResource(fxmlPaths.mainMenu));
            stage.getScene().setRoot(page);
            stage.sizeToScene();
        } catch (Exception e){
            System.out.println("Exception");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Main.serverController == null) {
            startGameButton.setVisible(false);
            ipText.setVisible( false);
            ipLabel.setVisible( false);
        } else {
            ipText.setText( "" + Main.serverController.host.serverIP);
            ipText.setVisible( true);
            ipText.setEditable( true);
            ipText.setDisable( false);
            ipLabel.setVisible( true);
        }

        WaitScreenController.labels.add( house0);
        WaitScreenController.labels.add( house1);
        WaitScreenController.labels.add( house2);
        WaitScreenController.labels.add( house3);
        WaitScreenController.labels.add( house4);
        WaitScreenController.labels.add( house5);
        WaitScreenController.labels.add( house6);

        house0.setAlignment(Pos.CENTER);
        house1.setAlignment(Pos.CENTER);
        house2.setAlignment(Pos.CENTER);
        house3.setAlignment(Pos.CENTER);
        house4.setAlignment(Pos.CENTER);
        house5.setAlignment(Pos.CENTER);
        house6.setAlignment(Pos.CENTER);


        WaitScreenController.images.add( image0);
        WaitScreenController.images.add( image1);
        WaitScreenController.images.add( image2);
        WaitScreenController.images.add( image3);
        WaitScreenController.images.add( image4);
        WaitScreenController.images.add( image5);
        WaitScreenController.images.add( image6);

    }
}
