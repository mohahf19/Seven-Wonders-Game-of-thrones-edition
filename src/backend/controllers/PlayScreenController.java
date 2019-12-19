package backend.controllers;

import backend.app.Main;
import backend.app.fxmlPaths;
import backend.models.Card;
import backend.models.Player;
import com.google.gson.JsonObject;
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
    private ImageView card1, card2, card3, soundButton, header1, seasonBanner, ageButton;
    private static ImageView seasonBannerSt, ageButtonSt;

    @FXML
    private AnchorPane parentPane;

    @FXML
    private HBox cardHolder;

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

        test();

        soundButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new soundMouseHoverListener());
        soundButton.addEventHandler(MouseEvent.MOUSE_EXITED, new soundMouseExitListener());

        setHeaders(Main.gameEngine.getCurrentPlayer().id);

        //don't change anything below
        notifyViewLoaded();
    }
    public static void updateDeck( ArrayList<Card> cards){
        //write something
        System.out.println( "Cards geldi");
    }

    public static void updateSeasonImage( int currSeason) {
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

    public static void updateAgeImage( int currAge) {
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


    public void test() {
        //TEST
        cardHolder.setAlignment(Pos.CENTER);
        cardHolder.setSpacing(5);
        for (int i = 0; i < 1; i++) {
            ImageView cardImg = new ImageView();
            cardImg.setFitHeight(200);
            cardImg.setFitWidth(150);
            Image img = new Image("/assets/Lumberyard", true);
            cardImg.setImage(img);
            cardHolder.getChildren().add(cardImg);
        }

        backend.models.Cost cost = new backend.models.Cost(10, "I AM PREREQ", 17*2*2*3*5);

        backend.models.Card card = new backend.models.Card("Card Titled",cost,
                "file:///C:/Users/Bilal/Desktop/7%20Houses%20Resources/Cards/Card%20Icon/brownBG.jpg",
                "file:///C:/Users/Bilal/Desktop/7%20Houses%20Resources/Game%20Icons/Resources%20Icons/wood.png",
                "file:///C:/Users/Bilal/Desktop/7%20Houses%20Resources/Cards/Card%20Icon/brownTop.jpg");
        CardView cv = new CardView(card);
        cv.update(card);
        cardHolder.getChildren().addAll(cv);

        //TESTEND
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
