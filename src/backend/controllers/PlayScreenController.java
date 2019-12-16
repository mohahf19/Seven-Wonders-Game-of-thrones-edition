package backend.controllers;

import backend.app.Main;
import backend.app.fxmlPaths;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayScreenController implements Initializable {
    @FXML
    private ImageView card1, card2, card3;

    @FXML
    private AnchorPane parentPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        card1.setOnMouseDragged(new EventHandler() {
            public void handle(Event e) {
                MouseEvent event = ( MouseEvent) e;

                card1.setManaged(false);
                card1.setTranslateX(event.getX() + card1.getTranslateX() - 50);
                card1.setTranslateY(event.getY() + card1.getTranslateY() - 50);
                if( card1.getBoundsInParent().intersects( card3.getBoundsInParent())){
                    card1.setDisable(true);
                }
                event.consume();
            }
        });
        card1.setOnMouseReleased(new EventHandler() {
            public void handle(Event e) {
                MouseEvent event = ( MouseEvent) e;
                if( card1.isDisable()){
                    card1.setManaged(false);
                    card1.setTranslateX(200.0);
                    card1.setTranslateY(-200.0);
                    event.consume();
                } else {
                    card1.setManaged(false);
                    card1.setTranslateX(0.0);
                    card1.setTranslateY(0.0);
                    event.consume();
                }
            }
        });
    }
}
