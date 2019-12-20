package backend.controllers;

import backend.app.Main;
import backend.app.fxmlPaths;
import backend.models.Card;
import backend.models.Deck;
import backend.models.Player;
import backend.models.Scoreboard;
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
import javafx.scene.control.Label;
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
    private HBox cardHolder, headerHolder, wonderHolder;
    private static HBox cardHolderSt;

    @FXML
    private Label waitLabel;
    private static Label waitLabelSt;

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
        waitLabelSt = waitLabel;

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

        // Event listeners for sound button
        soundButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new soundMouseHoverListener());
        soundButton.addEventHandler(MouseEvent.MOUSE_EXITED, new soundMouseExitListener());
        soundButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new soundMouseClickListener());

        setHeaders(Main.gameEngine.getCurrentPlayer().id);

        //wonder test
        displayWonder();

        //don't change anything below
        notifyViewLoaded();
    }

    public void displayWonder() {
        wonderHolder.setAlignment(Pos.CENTER);
        wonderHolder.setSpacing(30);
        Image image;
        ImageView iv;
        switch (Main.gameEngine.players.get(0).house.name) {
            case "Stark":
                image = new Image("assets/wonders/starkWonder1.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                image = new Image("assets/wonders/starkWonder2.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                image = new Image("assets/wonders/starkWonder3.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                break;
            case "Baratheon":
                image = new Image("assets/wonders/baratheonWonder1.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                image = new Image("assets/wonders/baratheonWonder2.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                image = new Image("assets/wonders/baratheonWonder3.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                break;
            case "Greyjoy":
                image = new Image("assets/wonders/greyjoyWonder1.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                image = new Image("assets/wonders/greyjoyWonder2.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                image = new Image("assets/wonders/greyjoyWonder3.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                break;
            case "Lannister":
                image = new Image("assets/wonders/lannisterWonder1.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                image = new Image("assets/wonders/lannisterWonder2.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                image = new Image("assets/wonders/lannisterWonder3.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);;
                break;
            case "Targaryen":
                image = new Image("assets/wonders/targaryenWonder1.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                image = new Image("assets/wonders/targaryenWonder2.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                image = new Image("assets/wonders/targaryenWonder3.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                break;
            case "Tyrell":
                image = new Image("assets/wonders/tyrellWonder1.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                image = new Image("assets/wonders/tyrellWonder2.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                image = new Image("assets/wonders/tyrellWonder3.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                break;
            case "White Walkers":
                image = new Image("assets/wonders/whitewalkersWonder1.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                image = new Image("assets/wonders/whitewalkersWonder2.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                image = new Image("assets/wonders/whitewalkersWonder3.png");
                iv = new ImageView();
                iv.setImage(image);
                wonderHolder.getChildren().addAll(iv);
                break;
        }
    }

    public static void updateDeck( ArrayList<Card> cards){
        //write something

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                waitLabelSt.setVisible( false);

                cardHolderSt.setAlignment(Pos.CENTER);
                cardHolderSt.setSpacing(5);

                System.out.println( "Cards geldi" + cards.size());

                //remove all cards
                cardHolderSt.getChildren().clear();

                for (int i = 0; i < cards.size(); i++) {
                    CardView cv = new CardView(cards.get(i));
                    cv.update(cards.get(i));
                    //System.out.println(cards.get(i).name);
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
    public static void updateScoreboard(Scoreboard scoreboard){
        System.out.println( "Scoreboard to be updated");
    }

    public static void updateAgeImage( int currAge) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Image image;
                try {
                    waitLabelSt.setVisible( false);
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

    // Adds PlayerViewSummaryView to the screen and arranges the neighbour of the player
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
                System.out.println( "IOException");
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
                System.out.println( "IOException");
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
                System.out.println("IOException");
                e.printStackTrace();
            }
        }
        public void click() throws IOException {
            waitLabelSt.setVisible( true);
            Main.gameEngine.playCard( 0);

        }
    }

}
