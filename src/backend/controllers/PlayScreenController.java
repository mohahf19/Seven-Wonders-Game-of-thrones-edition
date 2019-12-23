package backend.controllers;

import backend.app.Main;
import backend.models.Card;
import backend.models.Player;
import backend.models.Scoreboard;
import com.google.gson.JsonObject;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class PlayScreenController implements Initializable {
    @FXML
    private ImageView soundButton, scoreboardButton, seasonBanner, ageButton, homeButton, waitingAnimation, tick1, tick2, tick3, initialResource;
    private static ImageView seasonBannerSt, ageButtonSt, waitingAnimationSt, tick1St, tick2St, tick3St;

    @FXML
    private AnchorPane parentPane, scoreboardPane;
    private static AnchorPane scoreboardPaneSt;

    @FXML
    private HBox cardHolder, headerHolder, wonderHolder;
    private static HBox cardHolderSt, headerHolderSt;

    @FXML
    private VBox scoreboardHolder;
    private static VBox scoreboardHolderSt;

    @FXML
    private AnchorPane waitLabel;
    private static AnchorPane waitLabelSt;

    @FXML
    private Label waitingText, coinLabel, militaryLabel, idLabel;
    private static Label coinLabelSt, militaryLabelSt;

    @FXML
    private AnchorPane pvcPane;

    private static PlayedCardView pvc;

    //for dragging
    private static double orgSceneX, orgSceneY, orgX, orgY, orgTranslateX, orgTranslateY;
    private static boolean isHome = true;

    private static ArrayList<Player> updatedPlayers;

    CardView sampleCard;

    public static void updateScoreboard(Scoreboard scoreboard) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                for (Node v : scoreboardHolderSt.getChildren()) {
                    ((ScoreboardView) v).updateView(scoreboard.scores.get(count));
                    count++;
                }

                updatedPlayers = new ArrayList<>();
                Player user, userleft, userright;
                user = Main.gameEngine.players.get(Main.gameEngine.getCurrentPlayer().id);

                userleft = Main.gameEngine.players.get((((Main.gameEngine.getCurrentPlayer().id - 1) % Main.gameEngine.players.size()) +
                        Main.gameEngine.players.size()) % Main.gameEngine.players.size());
                userright = Main.gameEngine.players.get((Main.gameEngine.getCurrentPlayer().id + 1) % Main.gameEngine.players.size());

                updatedPlayers.add(userleft);
                for (int i = 0; i < Main.gameEngine.players.size(); i++) {
                    if (Main.gameEngine.players.get(i) != user && Main.gameEngine.players.get(i) != userright && Main.gameEngine.players.get(i) != userleft) {
                        updatedPlayers.add(Main.gameEngine.players.get(i));
                    }
                }
                updatedPlayers.add(userright);

                count = 0;
                for (Node v : headerHolderSt.getChildren()) {
                    ((PlayerSummaryView) v).update(updatedPlayers.get(count));
                    count++;
                }
            }});
    }

    public void notifyViewLoaded(){
        JsonObject req = new JsonObject();
        req.addProperty("op_code", 3);
        Main.gameEngine.client.sendRequest( req);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idLabel.setAlignment(Pos.CENTER);
        //static walkaround
        seasonBannerSt = seasonBanner;
        ageButtonSt = ageButton;
        cardHolderSt = cardHolder;
        waitLabelSt = waitLabel;
        scoreboardHolderSt = scoreboardHolder;
        waitingAnimationSt = waitingAnimation;
        coinLabelSt = coinLabel;
        scoreboardPaneSt = scoreboardPane;
        militaryLabelSt = militaryLabel;
        headerHolderSt = headerHolder;
        tick1St = tick1;
        tick2St = tick2;
        tick3St = tick3;

        Image backgroundImage = new Image ("assets/scoreboardBackground.png");
        scoreboardHolder.setBackground(new Background(new BackgroundImage(backgroundImage,BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        scoreboardHolderSt.setCache(true);
        scoreboardHolderSt.setCacheShape(true);
        scoreboardHolderSt.setCacheHint(CacheHint.SPEED);

        waitLabel.setStyle("-fx-background-color: #580303; -fx-border-radius: 20;");
        waitingText.setStyle("-fx-font-size: 35px; -fx-text-fill: white");

        // Event listeners for sound button
        soundButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new soundMouseHoverListener());
        soundButton.addEventHandler(MouseEvent.MOUSE_EXITED, new soundMouseExitListener());
        soundButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new soundMouseClickListener());
        homeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new homeMouseClickListener());
        //scoreboardButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new scoreboardMouseClickListener());

        setHeaders(Main.gameEngine.getCurrentPlayer().id);
        displayWonder();

        //scoreboard test
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                initScoreboard();
            }
        },1000);
        pvc = new PlayedCardView();

        pvcPane.getChildren().add(pvc);
        
        updateLabels();
        initializeResource(Main.gameEngine.getCurrentPlayer().house.name);

        //don't change anything below
        notifyViewLoaded();
    }


    public void initializeResource(String name) {
        Image image;
        switch (name) {
            case "Stark":
                image = new Image("assets/initialResources/starkInitial.png");
                initialResource.setImage(image);
                break;
            case "Baratheon":
                image = new Image("assets/initialResources/baratheonInitial.png");
                initialResource.setImage(image);
                break;
            case "Greyjoy":
                image = new Image("assets/initialResources/greyjoyInitial.png");
                initialResource.setImage(image);
                break;
            case "Lannister":
                image = new Image("assets/initialResources/lannisterInitail.png");
                initialResource.setImage(image);
                break;
            case "Targaryen":
                image = new Image("assets/initialResources/targaryenInitial.png");
                initialResource.setImage(image);
                break;
            case "Tyrell":
                image = new Image("assets/initialResources/tyrellInitial.png");
                initialResource.setImage(image);
                break;
            case "White Walkers":
                image = new Image("assets/initialResources/whiteInitial.png");
                initialResource.setImage(image);
                break;
        }

    }

    public void initScoreboard() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                scoreboardHolderSt.getChildren().clear();
                for( Player p: Main.gameEngine.players){
                    ScoreboardView a = new ScoreboardView("" + p.house.name);
                    scoreboardHolderSt.getChildren().add( a);
                }
            }});
    }

    public void displayWonder() {
        wonderHolder.setAlignment(Pos.CENTER);
        wonderHolder.setSpacing(30);
        Image image;
        ImageView iv;
        switch (Main.gameEngine.getCurrentPlayer().house.name) {
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
                idLabel.setText("Stark");
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
                idLabel.setText("Baratheon");
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
                idLabel.setText("Greyjoy");
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
                idLabel.setText("Lannister");
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
                idLabel.setText("Targaryen");
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
                idLabel.setText("Tyrell");
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
                idLabel.setText("White Walkers");
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
                cardHolderSt.setSpacing(15);

                //clear previous cards
                cardHolderSt.getChildren().clear();

                System.out.println( "Cards geldi");

                for (int i = 0; i < cards.size(); i++) {
                    int color = Main.gameEngine.canBuild(cards.get(i));
                    CardView cv = new CardView(cards.get(i), i);
                    color(cv, color);
//                    int res = Main.gameEngine.getCurrentPlayer().canBuild(cards.get(i).cost);
//                    cv.setDisable(res == 0);
//                    System.out.println("can build " + cards.get(i).name + ": " + res);

                    cv.setCursor(Cursor.HAND);
                    cv.setOnMousePressed(e ->{
                        if( isHome){
                            orgX = e.getX();
                            orgY = e.getY();
                            orgSceneX = e.getSceneX();
                            orgSceneY = e.getSceneY();
                            orgTranslateX = cv.getTranslateX();
                            orgTranslateY = cv.getTranslateY();
                            e.consume();
                        } else {
                            JOptionPane.showMessageDialog( null, "Please return to home before playing your turn.", "", JOptionPane.PLAIN_MESSAGE);
                        }
                    });

                    cv.setOnMouseDragged(e->{
                        if( isHome){
                            int region = decideRegion(e.getSceneX(), e.getSceneY());
                            switch (region){
                                case -1:
                                    cv.reset(); //do nothing
                                    break;
                                case 0:
                                    cv.tradingLeft();
                                    break;
                                case 1:
                                    cv.tradingRight();
                                    break;
                                case 2:
                                    cv.playingCard();
                                    break;
                                case 3:
                                    cv.playingWonder();
                                    break;
                                case 4:
                                    cv.discardingCard();
                                    break;
                            }
                            cv.setTranslateX(orgTranslateX + e.getSceneX() - orgSceneX);
                            cv.setTranslateY(orgTranslateY + e.getSceneY() - orgSceneY);
                        }
                        e.consume();
                    });
                    cv.setOnMouseReleased(e->{
                        if( isHome){
                            int region = decideRegion(e.getSceneX(), e.getSceneY());
                            int cardIndex = cv.id;
                            Card card = cards.get(cv.id);
                            //0 if can't build, 1 if can without trading, 2 if left trading is required
                            //3 if right trading
                            int canBuild = Main.gameEngine.canBuild(cards.get(cv.id));
                            switch (region){
                                case -1:
                                    System.out.println("dropped nowhere important..");
                                    break;
                                case 0:
                                    //TODO trade left
                                    System.out.println("left trading!");
                                    if(canBuild == 2){
                                        Main.gameEngine.tradeLeft(cardIndex);
                                        cardHolderSt.getChildren().remove(cv);
                                        pvc.addCard(card);
                                        waitLabelSt.setVisible( true);
                                    } else {
                                        cv.reset();
                                    }
                                    break;
                                case 1:
                                    //TODO trade right
                                    System.out.println("right trading!");
                                    if(canBuild == 3){
                                        Main.gameEngine.tradeRight(cardIndex);
                                        cardHolderSt.getChildren().remove(cv);
                                        pvc.addCard(card);
                                        waitLabelSt.setVisible( true);
                                    } else {
                                        cv.reset();
                                    }
                                    //TODO trade right
                                    break;
                                case 2:
                                    System.out.println("playing card!");
                                    if(canBuild == 1){
                                        Main.gameEngine.playCard(cardIndex);
                                        cardHolderSt.getChildren().remove(cv);
                                        pvc.addCard(card);
                                        waitLabelSt.setVisible( true);
                                    } else {
                                        cv.reset();
                                    }
                                    break;
                                case 3:
                                    System.out.println("building wonder!");
                                    if (Main.gameEngine.canBuildWonder() > 0){
                                        Main.gameEngine.buildWonder(cardIndex);
                                        cardHolderSt.getChildren().remove(cv);
                                        if (Main.gameEngine.getCurrentPlayer().house.getWonders().get(0).built) {
                                            tick1St.setVisible(true);
                                        }
                                        if (Main.gameEngine.getCurrentPlayer().house.getWonders().get(1).built) {
                                            tick2St.setVisible(true);
                                        }
                                        if (Main.gameEngine.getCurrentPlayer().house.getWonders().get(2).built) {
                                            tick3St.setVisible(true);
                                        }
                                        waitLabelSt.setVisible( true);
                                    } else {
                                        cv.reset();
                                    }
                                    break;
                                case 4:
                                    System.out.println("discarding card!");
                                    Main.gameEngine.discardCard(cardIndex);
                                    cardHolderSt.getChildren().remove(cv);
                                    waitLabelSt.setVisible( true);
                                    break;
                            }
                            System.out.println("e.getSceneX():" + e.getSceneX() + "\te.getSceneY():" +  e.getSceneY());
                            cv.relocate(orgSceneX - orgX, orgSceneY - orgY);
                            cv.setTranslateX(0);
                            cv.setTranslateY(0);
                            updateLabels();
                        }
                        e.consume();
                    });
                    cv.update(cards.get(i));
                    cardHolderSt.getChildren().addAll(cv);
                }
                updateLabels();
            }
        });
    }

    private static void color(CardView cv, int canBuild) {
        System.out.println("Coloring card " + cv.getName() + " with " + canBuild);
        switch (canBuild){
            case 0:
                cv.setUnPlayable();
                break;
            case 1:
                cv.setPlayable();
                break;
            case 2:
            case 3:
                cv.setTradeable();
                break;
        }

    }

    private static void updateLabels() {
        coinLabelSt.setText(Main.gameEngine.getCoins());
        militaryLabelSt.setText(Main.gameEngine.getShields());
    }


    //@param x x coordinate, y y coordinate
    //returns -1 if in no region, 0 if in left trading
    //1 if in right trading, 2 if in playing card
    //3 if in wonder playing, 4 if card discard
    private static int decideRegion(double x, double y) {
        if( 0 <= x && x <240 && 100<=y && y<550){
            return 0;
        } else if ( 1200 <= x && x <=1440 && 100<=y && y<550){
            return 1;
        } else if (240 <= x && x <1200 && 100<=y && y<550){
            return 2;
        } else if (240 <= x && x <1200 && 550<=y && y<625){
            return 3;
        }else if (1200 <= x && x <=1440 && 550<=y && y<640){
            return 4;
        } else {
            return -1;
        }
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

    public static void showMilitaryConflict(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                waitingAnimationSt.setVisible( true);
                Image image = new Image("assets/militaryConflict/militaryConflict.png");
                waitingAnimationSt.setImage(image);
            }});
    }
    public static void hideMilitaryConflict(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //check if new age
                int size = Main.gameEngine.getCurrentPlayer().cards.size();
                if((size < 7 && size > 1) || (size <= 1 && Main.gameEngine.currentAge == 3))
                    waitingAnimationSt.setVisible( false);
            }});
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

                            waitingAnimationSt.setVisible( true);
                            image = new Image("assets/ages/age1Load.gif");
                            waitingAnimationSt.setImage(image);
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    waitingAnimationSt.setVisible( false);
                                }
                            }, 2500);
                            break;
                        }
                        case 2: {
                            image = new Image("assets/ages/age2.jpg", true);
                            ageButtonSt.setImage(image);

                            waitingAnimationSt.setVisible( true);
                            image = new Image("assets/ages/age2Load.gif");
                            waitingAnimationSt.setImage(image);
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    waitingAnimationSt.setVisible( false);
                                }
                            }, 2500);
                            break;
                        }
                        case 3: {
                            image = new Image("assets/ages/age3.jpg", true);
                            ageButtonSt.setImage(image);

                            waitingAnimationSt.setVisible( true);
                            image = new Image("assets/ages/age3Load.gif");
                            waitingAnimationSt.setImage(image);
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    waitingAnimationSt.setVisible( false);
                                }
                            }, 2500);
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

        updatedPlayers = new ArrayList<>();
        Player user, userleft, userright;
        user = Main.gameEngine.players.get(userID);

        userleft = Main.gameEngine.players.get((((userID - 1) % Main.gameEngine.players.size()) +
                Main.gameEngine.players.size()) % Main.gameEngine.players.size());
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
            psv.addEventHandler(MouseEvent.MOUSE_CLICKED, new psvMouseClickListener(i));
            headerHolder.getChildren().addAll(psv);
        }
    }

    public class psvMouseClickListener implements EventHandler<MouseEvent> {
        int id;
        psvMouseClickListener(int i ){
            id = i;
        }
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
            //TODO add stuff
            if (isHome)
                isHome = false;
            homeButton.setVisible(true);
            pvc.display(PlayScreenController.updatedPlayers.get(id).playedCards);
            initializeResource(PlayScreenController.updatedPlayers.get(id).house.name);
        }
    }

    public class homeMouseClickListener implements EventHandler<MouseEvent> {
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
            //TODO
            if (!isHome)
                isHome = true;
            homeButton.setVisible(false);
            pvc.display(Main.gameEngine.getPlayedCards());
            initializeResource(Main.gameEngine.getCurrentPlayer().house.name);
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
            SoundController.toggleMainSound();
        }
    }

    @FXML
    public void showScoreboard(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (scoreboardPaneSt.isVisible()) {
                    scoreboardPaneSt.setVisible(false);
                }
                else {
                    scoreboardPaneSt.setVisible(true);
                }
            }
        });
    }

    public static void endGame(){
        System.out.println("End game");
        waitLabelSt.setVisible( false);
        scoreboardPaneSt.setVisible( true);
    }
}
