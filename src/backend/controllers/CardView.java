package backend.controllers;

import backend.models.Card;
import backend.models.Numbers;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class CardView extends Pane {

    private StackPane topPane;
    private ImageView cardImageView;
    private ImageView iconImageView;
    private ImageView backImageView;
    private Label prereqCardLabel;
    private Label cardNameLabel;
    private Label chain1Label;
    private Label chain2Label;
    private HBox prereqHBox;


    public CardView(){
        //top pane
        topPane = new StackPane();

        //card
        cardImageView = new ImageView();
        cardImageView.setFitHeight(150);
        cardImageView.setFitWidth(150);

        //icon
        iconImageView = new ImageView();
        iconImageView.setFitWidth(50);
        iconImageView.setFitHeight(50);

        //background
        backImageView = new ImageView();
        backImageView.setFitHeight(50);
        backImageView.setFitWidth(150);

        topPane.getChildren().add(backImageView);
        topPane.getChildren().add(iconImageView);


        prereqCardLabel = new Label("prereqCards");
        cardNameLabel = new Label("cardName");
        chain1Label = new Label("firstChain");
        chain2Label = new Label("secondChain");
        prereqHBox = new HBox();

        addChildren();
    }

    private void addChildren() {
        this.getChildren().addAll(cardImageView, topPane, prereqCardLabel,
                cardNameLabel, chain1Label, chain2Label, prereqHBox);
    }


    public CardView(Card card){
        //top pane
        topPane = new StackPane();

        //card
        cardImageView = new ImageView();
        cardImageView.setFitHeight(150);
        cardImageView.setFitWidth(150);

        //icon
        iconImageView = new ImageView();
        iconImageView.setFitWidth(50);
        iconImageView.setFitHeight(50);

        //background
        backImageView = new ImageView();
        backImageView.setFitHeight(50);
        backImageView.setFitWidth(150);

        topPane.getChildren().add(backImageView);
        topPane.getChildren().add(iconImageView);

        cardImageView.setImage(new Image(card.imagePath));
        backImageView.setImage(new Image(card.backPath));
        iconImageView.setImage(new Image(card.iconPath));


        prereqCardLabel = new Label(card.cost.getPrereq());
        cardNameLabel = new Label(card.name);
        chain1Label = new Label("firstChain");
        chain2Label = new Label("secondChain");
        prereqHBox = new HBox();
        fillPreReq(card.cost.getResources(), card.cost.getMoney());

        addChildren();

    }

    private void fillPreReq(int resource, int money) {
        ArrayList<Integer> res = Numbers.factorizeResources(resource);

        for(int i = -1; i < res.size(); i++){
            int val;
            if (i > -1) {
                val = res.get(i);
            } else {
                val= money;
            }
            Label label = new Label("" + val);
            prereqHBox.getChildren().add(label);
        }
    }


    @Override protected double computeMinWidth(double height){
        return 150;
    }

    @Override protected double computeMinHeight(double width){
        return 200;
    }

    @Override protected double computeMaxWidth(double height){
        return 150;
    }

    @Override protected double computeMaxHeight(double width){
        return 200;
    }

    @Override protected double computePrefWidth(double height){
        return 150;
    }

    @Override protected double computePrefHeight(double width){
        return 200;
    }

    @Override protected void layoutChildren(){
        topPane.resizeRelocate(0, 0, 150, 50);
        cardImageView.resizeRelocate(0, 50, 150, 150);
        prereqHBox.resizeRelocate(0, 50, 150, 20);
        prereqCardLabel.resizeRelocate(0, 70, 75, 20);
        chain1Label.resizeRelocate(75, 140, 75, 20);
        chain2Label.resizeRelocate(75, 160, 75, 20);
        cardNameLabel.resizeRelocate(0, 180, 150, 20);
    }
}
