package backend.controllers;

import backend.models.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PlayerSummaryView extends Pane {
    private ImageView backImageView;
    private ImageView coinsImageView;
    private ImageView shieldsImageView;
    private Label nameLabel;
    private Label coinsLabel;
    private Label shieldsLabel;

    public PlayerSummaryView(){
        initComponents();

        addChildren();
    }

    public PlayerSummaryView(Player p){
        initComponents();

        nameLabel.setText(p.house.name);
        coinsLabel.setText("" + p.house.coins);
        shieldsLabel.setText("" + p.house.militaryShields);

        // Initializing images
        Image image;
        image = new Image("assets/Headers/coin.png", true);
        coinsImageView.setImage(image);
        image = new Image("assets/Headers/militaryShield.png", true);
        shieldsImageView.setImage(image);
        switch (p.house.name) {
            case "Stark":
                image = new Image("assets/Headers/starkTop.jpg", true);
                backImageView.setImage(image);
                break;
            case "Baratheon":
                image = new Image("assets/Headers/baratheonTop.jpg", true);
                backImageView.setImage(image);
                break;
            case "Greyjoy":
                image = new Image("assets/Headers/greyjoyTop.jpg", true);
                backImageView.setImage(image);
                break;
            case "Lannister":
                image = new Image("assets/Headers/lannisterTop.jpg", true);
                backImageView.setImage(image);
                break;
            case "Targaryen":
                image = new Image("assets/Headers/targaryenTop.jpg", true);
                backImageView.setImage(image);
                break;
            case "Tyrell":
                image = new Image("assets/Headers/tyrellTop.jpg", true);
                backImageView.setImage(image);
                break;
            case "White Walkers":
                image = new Image("assets/Headers/whiteTop.jpg", true);
                backImageView.setImage(image);
                break;
        }

        addChildren();
    }

    private void addChildren() {
        this.getChildren().add(backImageView);
        this.getChildren().add(coinsImageView);
        this.getChildren().add(shieldsImageView);
        this.getChildren().addAll(nameLabel, coinsLabel, shieldsLabel);
    }

    private void initComponents() {
        //backgound
        backImageView = new ImageView();
        backImageView.setFitWidth(240);
        backImageView.setFitHeight(100);

        //coins
        coinsImageView = new ImageView();
        coinsImageView.setFitWidth(50);
        coinsImageView.setFitHeight(50);

        //shields
        shieldsImageView = new ImageView();
        shieldsImageView.setFitWidth(50);
        shieldsImageView.setFitHeight(50);

        //labels
        nameLabel = new Label("nameHere");
        coinsLabel = new Label("0");
        shieldsLabel = new Label("0");
        nameLabel.setAlignment(Pos.CENTER);
        coinsLabel.setAlignment(Pos.CENTER);
        shieldsLabel.setAlignment(Pos.CENTER);

    }

    //updates this view
    public void update(Player p){
        coinsLabel.setText(""+ p.house.coins);
        shieldsLabel.setText(""+p.house.militaryShields);
    }

    @Override protected double computeMinWidth(double height){
        return 240;
    }

    @Override protected double computeMinHeight(double width){
        return 100;
    }

    @Override protected double computeMaxWidth(double height){
        return 240;
    }

    @Override protected double computeMaxHeight(double width){
        return 100;
    }

    @Override protected double computePrefWidth(double height){
        return 240;
    }

    @Override protected double computePrefHeight(double width){
        return 100;
    }

    @Override protected void layoutChildren(){
        backImageView.resizeRelocate(0,0, 240, 100);
        coinsImageView.resizeRelocate(50, 40, 50, 50);
        shieldsImageView.resizeRelocate(140, 40, 50, 50);
        nameLabel.resizeRelocate(0,0, 240, 30);
        coinsLabel.resizeRelocate(50,40, 50, 50);
        shieldsLabel.resizeRelocate(140, 40, 50, 50);
    }
}
