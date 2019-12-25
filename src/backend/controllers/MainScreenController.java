package backend.controllers;

import backend.app.Main;
import backend.app.fxmlPaths;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.FloatBuffer;
import java.util.ResourceBundle;

import backend.app.fxmlPaths;

import javax.swing.*;


public class MainScreenController implements Initializable  {
    @FXML
    private TextField IPTextField;

    @FXML
    private AnchorPane menuParent;

    @FXML
    private ImageView backgroundImageView, soundButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backgroundImageView.fitWidthProperty().bind(menuParent.widthProperty());
        backgroundImageView.fitHeightProperty().bind(menuParent.heightProperty());
    }

    @FXML
    private void startGame(ActionEvent ae){
        System.out.println("GAME STARTED");

        try {
            Main.initServer();
            //get the stage
            Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow();
            Main.waitScreen = FXMLLoader.load(getClass().getResource(fxmlPaths.waitMenu));
            stage.getScene().setRoot(Main.waitScreen);
            stage.sizeToScene();

        } catch (Exception e) {
            System.out.println( "Exception");
            e.printStackTrace();
        }
    }

    @FXML
    private void joinGame(ActionEvent ae){
        if (IPTextField.getText().equals("")) {
            System.out.println("Enter a URL, dummy..");
            Alert alert = new Alert(Alert.AlertType.NONE, "Enter a URL, dummy..", ButtonType.CLOSE);
            alert.showAndWait();
            return;
        } else{
            String ip = IPTextField.getText();
            Main.initClient( ip);

            try {
                Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow();
                Parent page = FXMLLoader.load(getClass().getResource(fxmlPaths.waitMenu));
                stage.getScene().setRoot(page);
                stage.sizeToScene();
            } catch (IOException e) {
                System.out.println("IOException");
                e.printStackTrace();
            }


        }
    }
}
