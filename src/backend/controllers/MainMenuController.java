package backend.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class MainMenuController {

    public TextField IPTextField;

    public void startGame(ActionEvent ae){
        System.out.println("GAME STARTED");

        Alert alert = new Alert(Alert.AlertType.NONE, "GAME IS STARTING", ButtonType.CLOSE);
        alert.showAndWait();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/WaitScreen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
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
