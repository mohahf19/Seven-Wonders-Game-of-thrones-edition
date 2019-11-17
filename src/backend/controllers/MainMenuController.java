package backend.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable  {
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

        public void startGame(ActionEvent ae){
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
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/ui/WaitScreen.fxml"));
            menuParent.getChildren().setAll(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void joinGame(ActionEvent ae){
        if (IPTextField.getText().equals("")) {
            System.out.println("Enter a URL, dummy..");
            Alert alert = new Alert(Alert.AlertType.NONE, "Enter a URL, dummy..", ButtonType.CLOSE);
            alert.showAndWait();
            return;
        } else{
            System.out.println("JOIN THE GAME!!");
            Alert alert = new Alert(Alert.AlertType.NONE, "JOIN THE GAME!!", ButtonType.CLOSE);
            alert.showAndWait();

        }
    }
}
