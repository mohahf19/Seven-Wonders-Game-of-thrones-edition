package backend.controllers;

import backend.app.Main;
import backend.app.fxmlPaths;
import backend.models.Card;
import backend.models.Deck;
import backend.models.Player;
import com.google.gson.JsonObject;
import javafx.application.Platform;
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
    private ImageView card1, card2, card3, soundButton, seasonBanner, ageButton;
    private static ImageView seasonBannerSt, ageButtonSt;

    @FXML
    private AnchorPane parentPane;

    @FXML
    private HBox cardHolder, headerHolder;
    private static HBox cardHolderSt;

    CardView sampleCard;

    public void notifyViewLoaded(){
        JsonObject req = new JsonObject();
        req.addProperty("op_code", 3);
        Main.gameEngine.client.sendRequest( req);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        seasonBannerSt = seasonBanner;
        ageButtonSt = ageButton;
        cardHolderSt = cardHolder;

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

        soundButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new soundMouseHoverListener());
        soundButton.addEventHandler(MouseEvent.MOUSE_EXITED, new soundMouseExitListener());
        soundButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new soundMouseClickListener());

        setHeaders(Main.gameEngine.getCurrentPlayer().id);

        //don't change anything below
        notifyViewLoaded();
    }
    public static void updateDeck( ArrayList<Card> cards){
        //write something

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                cardHolderSt.setAlignment(Pos.CENTER);
                cardHolderSt.setSpacing(5);

                Deck d = new Deck(7, 3);


                System.out.println( "Cards geldi");

                for (int i = 0; i < cards.size(); i++) {
                    CardView cv = new CardView(cards.get(i));
                    cv.update(cards.get(i));
                    System.out.println(cards.get(i).name);
                    cardHolderSt.getChildren().addAll(cv);
                }
            }
        });
    }


    public static void updateSeasonImage( int currSeason) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Image image;
                try{
                    switch (currSeason) {
                        case 1: {
                            image = new Image("assets/seasons/summer.jpg", true);
                            seasonBannerSt.setImage(image);
                            break;
                        }
                        case 2: {
                            image = new Image("assets/seasons/autum.jpg", true);
                            seasonBannerSt.setImage(image);
                            break;
                        }
                        case 3: {
                            image = new Image("assets/seasons/winter.jpg", true);
                            seasonBannerSt.setImage(image);
                            break;
                        }
                        case 4: {
                            image = new Image("assets/seasons/spring.jpg", true);
                            seasonBannerSt.setImage(image);
                            break;
                        }
                    }
                } catch (Exception e){
                    System.out.println( "CP2" + e.toString() + e.getMessage() + e);
                }
            }
        });
    }

    public static void updateAgeImage( int currAge) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Image image;
                try {
                    switch (currAge) {
                        case 1: {
                            image = new Image("assets/ages/age1.jpg", true);
                            ageButtonSt.setImage(image);
                            break;
                        }
                        case 2: {
                            image = new Image("assets/ages/age2.jpg", true);
                            ageButtonSt.setImage(image);
                            break;
                        }
                        case 3: {
                            image = new Image("assets/ages/age3.jpg", true);
                            ageButtonSt.setImage(image);
                            break;
                        }
                    }
                } catch( Exception e){
                    System.out.println("CP1" + e.toString());
                }
            }
        });
    }

    // Headers need to be in an arraylist
    public void setHeaders(int userID) {
        headerHolder.setAlignment(Pos.CENTER);

        ArrayList<Player> updatedPlayers = new ArrayList<>();
        Player user, userleft, userright;
        user = Main.gameEngine.players.get(userID);

        userleft = Main.gameEngine.players.get((((userID - 1) % Main.gameEngine.players.size()) + Main.gameEngine.players.size()) % Main.gameEngine.players.size());
        userright = Main.gameEngine.players.get((userID + 1) % Main.gameEngine.players.size());

        updatedPlayers.add(userleft);
        for (int i = 0; i < Main.gameEngine.players.size(); i++) {
            if (Main.gameEngine.players.get(i) != user && Main.gameEngine.players.get(i) != userright && Main.gameEngine.players.get(i) != userleft) {
                updatedPlayers.add(Main.gameEngine.players.get(i));
            }
        }
        updatedPlayers.add(userright);

        for (int i = 0; i < updatedPlayers.size(); i++) {
            PlayerSummaryView psv = new PlayerSummaryView(updatedPlayers.get(i));
            psv.update(updatedPlayers.get(i));
            headerHolder.getChildren().addAll(psv);
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

    public class soundMouseClickListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            event.consume();
            try {
                click();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void click() throws IOException {
            System.out.println("CLICKED");
            Main.gameEngine.playCard( 0);

        }
    }

}
