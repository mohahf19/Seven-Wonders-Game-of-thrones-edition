package backend.controllers;

import backend.app.fxmlPaths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WaitScreenController{
    @FXML
    private Button exitGameButton;

    @FXML
    private Button startGameButton;

    @FXML
    private Label house1, house2, house3, house4, house5, house6, house7;

    @FXML
    private void startGame(ActionEvent ae){
        house1.setText( "ABCDDDDD");
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
}
