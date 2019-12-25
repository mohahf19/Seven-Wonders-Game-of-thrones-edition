package backend.controllers;

import backend.app.Main;
import backend.models.Player;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ScoreboardView extends Pane {
    private ImageView playerBanner;
    private Label militaryPointsLabel;
    private Label coinPointsLabel;
    private Label wonderPointsLabel;
    private Label civicPointsLabel;
    private Label commercePointsLabel;
    private Label sciencePointsLabel;
    private Label victoryPointsLabel;

    public  ScoreboardView() {
        initComponents();
        addChildren();
    }

    public ScoreboardView(String name){
        initComponents();
        addChildren();

        Image image;
        switch (name) {
            case "Stark":
                image = new Image("assets/Headers/starkTop.jpg", true);
                playerBanner.setImage(image);
                break;
            case "Baratheon":
                image = new Image("assets/Headers/baratheonTop.jpg", true);
                playerBanner.setImage(image);
                break;
            case "Greyjoy":
                image = new Image("assets/Headers/greyjoyTop.jpg", true);
                playerBanner.setImage(image);
                break;
            case "Lannister":
                image = new Image("assets/Headers/lannisterTop.jpg", true);
                playerBanner.setImage(image);
                break;
            case "Targaryen":
                image = new Image("assets/Headers/targaryenTop.jpg", true);
                playerBanner.setImage(image);
                break;
            case "Tyrell":
                image = new Image("assets/Headers/tyrellTop.jpg", true);
                playerBanner.setImage(image);
                break;
            case "White Walkers":
                image = new Image("assets/Headers/whiteTop.jpg", true);
                playerBanner.setImage(image);
                break;
        }
    }

    private void addChildren() {
        this.getChildren().addAll(playerBanner, militaryPointsLabel, coinPointsLabel, wonderPointsLabel, civicPointsLabel, commercePointsLabel, sciencePointsLabel,victoryPointsLabel);
    }

    private void initComponents() {
        // initialise banner
        playerBanner = new ImageView();
        playerBanner.setFitWidth(240);
        playerBanner.setFitHeight(100);

        // initialise labels
        militaryPointsLabel = new Label("0");
        militaryPointsLabel.setAlignment(Pos.CENTER);
        militaryPointsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 50px; -fx-background-color: #A71F2B");

        coinPointsLabel = new Label("0");
        coinPointsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 50px; -fx-background-color: #FAC64C");
        coinPointsLabel.setAlignment(Pos.CENTER);

        wonderPointsLabel = new Label("0");
        wonderPointsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 50px; -fx-background-color: #FCE877");
        wonderPointsLabel.setAlignment(Pos.CENTER);

        civicPointsLabel = new Label("0");
        civicPointsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 50px; -fx-background-color: #18688D");
        civicPointsLabel.setAlignment(Pos.CENTER);

        commercePointsLabel = new Label("0");
        commercePointsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 50px; -fx-background-color: #B88F25");
        commercePointsLabel.setAlignment(Pos.CENTER);

        sciencePointsLabel = new Label("0");
        sciencePointsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 50px; -fx-background-color: #137546");
        sciencePointsLabel.setAlignment(Pos.CENTER);

        victoryPointsLabel = new Label("0");
        victoryPointsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 50px; -fx-background-color: #FAD994");
        victoryPointsLabel.setAlignment(Pos.CENTER);

    }

    public void updateView(ArrayList<Integer> data){
        militaryPointsLabel.setText(""+ data.get(0));
        coinPointsLabel.setText(""+ data.get(1));
        wonderPointsLabel.setText(""+ data.get(2));
        civicPointsLabel.setText(""+ data.get(3));
        commercePointsLabel.setText(""+ data.get(4));
        sciencePointsLabel.setText(""+ data.get(5));
        victoryPointsLabel.setText(""+ data.get(6));

        Image image;
        Main.gameEngine.players.get(data.get(7)).house.name.toLowerCase();
        switch (Main.gameEngine.players.get(data.get(7)).house.name.toLowerCase()) {
            case "stark":
                image = new Image("assets/Headers/starkTop.jpg", true);
                playerBanner.setImage(image);
                break;
            case "baratheon":
                image = new Image("assets/Headers/baratheonTop.jpg", true);
                playerBanner.setImage(image);
                break;
            case "greyjoy":
                image = new Image("assets/Headers/greyjoyTop.jpg", true);
                playerBanner.setImage(image);
                break;
            case "lannister":
                image = new Image("assets/Headers/lannisterTop.jpg", true);
                playerBanner.setImage(image);
                break;
            case "targaryen":
                image = new Image("assets/Headers/targaryenTop.jpg", true);
                playerBanner.setImage(image);
                break;
            case "tyrell":
                image = new Image("assets/Headers/tyrellTop.jpg", true);
                playerBanner.setImage(image);
                break;
            case "white walkers":
                image = new Image("assets/Headers/whiteTop.jpg", true);
                playerBanner.setImage(image);
                break;
        }

    }

    @Override protected double computeMinWidth(double height){
        return 940;
    }

    @Override protected double computeMinHeight(double width){
        return 100;
    }

    @Override protected double computeMaxWidth(double height){
        return 940;
    }

    @Override protected double computeMaxHeight(double width){
        return 100;
    }

    @Override protected double computePrefWidth(double height){
        return 940;
    }

    @Override protected double computePrefHeight(double width){
        return 100;
    }

    @Override protected void layoutChildren(){
        playerBanner.resizeRelocate(0,0, 240, 100);
        militaryPointsLabel.resizeRelocate(240,0, 100,100);
        coinPointsLabel.resizeRelocate(340,0, 100,100);
        wonderPointsLabel.resizeRelocate(440,0, 100,100);
        civicPointsLabel.resizeRelocate(540,0, 100,100);
        commercePointsLabel.resizeRelocate(640,0, 100,100);
        sciencePointsLabel.resizeRelocate(740,0, 100,100);
        victoryPointsLabel.resizeRelocate(840,0, 100,100);
    }
}
