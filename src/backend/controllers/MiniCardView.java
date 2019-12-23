package backend.controllers;

import backend.app.constants;
import backend.models.Card;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MiniCardView extends Pane {
    private ImageView backImageView;
    private ImageView iconImageView;
    private Label nameLabel;

    public MiniCardView(Card card){
        backImageView = new ImageView(new Image(card.backPath));
        backImageView.setFitWidth(120);
        backImageView.setFitHeight(60);

        iconImageView = new ImageView(new Image(card.iconPath));
        iconImageView.setFitWidth(120);
        iconImageView.setFitHeight(40);

        nameLabel = new Label(" " + card.name);
        nameLabel.setStyle("" +
                "-fx-background-image: url(" + constants.path + "miniNameLabel.png);" +
                "-fx-font-size: 11"
        );


        this.getChildren().add(backImageView);
        this.getChildren().add(iconImageView);
        this.getChildren().add(nameLabel);
    }

    @Override protected double computeMinWidth(double height){
        return 120;
    }

    @Override protected double computeMinHeight(double width){
        return 60;
    }

    @Override protected double computeMaxWidth(double height){
        return 120;
    }

    @Override protected double computeMaxHeight(double width){
        return 60;
    }

    @Override protected double computePrefWidth(double height){
        return 120;
    }

    @Override protected double computePrefHeight(double width){
        return 60;
    }

    @Override protected void layoutChildren(){
        backImageView.resizeRelocate(0,0, 120, 60);
        iconImageView.resizeRelocate(0, 0, 120, 40);
        nameLabel.resizeRelocate(0,40, 120, 20);
    }
}
