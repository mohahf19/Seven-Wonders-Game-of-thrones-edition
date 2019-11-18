package backend.controllers;

import backend.app.Main;
import backend.app.fxmlPaths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import backend.app.fxmlPaths;


public class MainScreenController implements Initializable  {
    @FXML
    private TextField IPTextField;

    @FXML
    private AnchorPane menuParent;

    @FXML
    private ImageView backgroundImageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backgroundImageView.fitWidthProperty().bind(menuParent.widthProperty());
        backgroundImageView.fitHeightProperty().bind(menuParent.heightProperty());
    }

    @FXML
    private void startGame(ActionEvent ae){
        System.out.println("GAME STARTED");
//        Alert alert = new Alert(Alert.AlertType.NONE, "GAME IS STARTING", ButtonType.CLOSE);
//        alert.showAndWait();

//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/WaitScreen.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root1));
//            stage.show();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
        try {

            Main.game.conn.initServer();

            //AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlPaths.waitMenu));
            //menuParent.getChildren().setAll(pane);

            //get the stage
            Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow();
            Parent page = FXMLLoader.load(getClass().getResource(fxmlPaths.waitMenu));
            stage.getScene().setRoot(page);
            stage.sizeToScene();

        } catch (Exception e) {
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
            Main.game.conn.initClient( ip);

            Alert alert = new Alert(Alert.AlertType.NONE, "JOIN THE GAME!!", ButtonType.CLOSE);
            alert.showAndWait();

        }
    }
}
