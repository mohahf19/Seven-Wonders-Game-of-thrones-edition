package backend.controllers;

import backend.app.Main;
import backend.app.fxmlPaths;
import backend.models.Player;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayScreenController implements Initializable {
    @FXML
    private ImageView card1, card2, card3, soundButton, header1;

    @FXML
    private AnchorPane parentPane;

    @FXML
    private HBox cardHolder;



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

        //TEST
        cardHolder.setAlignment(Pos.CENTER);
        cardHolder.setSpacing(5);
        for (int i = 0; i < 5; i++) {
            ImageView cardImg = new ImageView();
            cardImg.setFitHeight(200);
            cardImg.setFitWidth(150);
            Image img = new Image("/assets/Lumberyard.jpg", true);
            cardImg.setImage(img);
            cardHolder.getChildren().add(cardImg);
        }

        //TESTEND
        soundButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new soundMouseHoverListener());
        soundButton.addEventHandler(MouseEvent.MOUSE_EXITED, new soundMouseExitListener());

        setHeaders(Main.gameEngine.client.id);
    }

    // Headers need to be in an arraylist
    public void setHeaders(int userID) {
        ArrayList<Player> updatedPlayers = new ArrayList<>();
        Player user, userleft, userright;
        user = Main.gameEngine.players.get(userID);

        userleft = Main.gameEngine.players.get( (((userID - 1) % Main.gameEngine.players.size()) +  Main.gameEngine.players.size()) %  Main.gameEngine.players.size());
        userright = Main.gameEngine.players.get( (userID + 1) % Main.gameEngine.players.size());

        updatedPlayers.add(userleft);
        for (int i = 0; i < Main.gameEngine.players.size(); i++) {
            if (Main.gameEngine.players.get(i) != user && Main.gameEngine.players.get(i) != userright && Main.gameEngine.players.get(i) != userleft) {
                updatedPlayers.add(Main.gameEngine.players.get(i));
            }
        }
        updatedPlayers.add(userright);

        for (int i = 0; i < updatedPlayers.size(); i++) {
            String houseName = updatedPlayers.get(i).house.name;
            Image image;
            switch (houseName) {
                case "Stark":
                    image = new Image("assets/Headers/starkTop.jpg", true);
                    header1.setImage(image);
                    break;
                case "Baratheon":
                    image = new Image("assets/Headers/baratheonTop.jpg", true);
                    header1.setImage(image);
                    break;
                case "Greyjoy":
                    image = new Image("assets/Headers/greyjoyTop.jpg", true);
                    header1.setImage(image);
                    break;
                case "Lannister":
                    image = new Image("assets/Headers/lannisterTop.jpg", true);
                    header1.setImage(image);
                    break;
                case "Targaryen":
                    image = new Image("assets/Headers/targaryenTop.jpg", true);
                    header1.setImage(image);
                    break;
                case "Tyrell":
                    image = new Image("assets/Headers/tyrellTop.jpg", true);
                    header1.setImage(image);
                    break;
                case "White Walkers":
                    image = new Image("assets/Headers/whiteTop.jpg", true);
                    header1.setImage(image);
                    break;
            }
        }
    }

    public class soundMouseHoverListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void hover() throws IOException {
            Image image = new Image("assets/volumeHover.jpg", true);
            soundButton.setImage(image);
        }
    }

    public class soundMouseExitListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void hover() throws IOException {
            Image image = new Image("assets/volume.jpg", true);
            soundButton.setImage(image);
        }
    }


}
