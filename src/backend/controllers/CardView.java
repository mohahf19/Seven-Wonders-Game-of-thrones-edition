package backend.controllers;

import backend.models.Card;
import backend.models.Numbers;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

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
        iconImageView.setFitWidth(150);
        iconImageView.setFitHeight(50);

        //background
        backImageView = new ImageView();
        backImageView.setFitHeight(50);
        backImageView.setFitWidth(150);

        topPane.getChildren().add(backImageView);
        topPane.getChildren().add(iconImageView);

        System.out.println(card.imagePath);
        cardImageView.setImage(new Image(card.imagePath));
        System.out.println(card.backPath);
        backImageView.setImage(new Image(card.backPath));
        System.out.println(card.iconPath);
        iconImageView.setImage(new Image(card.iconPath));


        prereqCardLabel = new Label(" " + card.cost.getPrereq());
        cardNameLabel = new Label(" " + card.name);

        chain1Label = new Label(card.chain1 + "  ");

        chain2Label = new Label(card.chain2 + "  ");

        styleLabels(card.cost.getPrereq(), card.chain1, card.chain2);
        prereqHBox = new HBox();
        fillPreReq(card.cost.getResources(), card.cost.getCoins());

        addChildren();

    }

    private void styleLabels(String pre, String c1, String c2) {
        if(!pre.equals((""))) {
            prereqCardLabel.setStyle("" +
                    "-fx-background-image: url(\"/assets/prereqLabel.png\");"
            );
        }

        cardNameLabel.setStyle("" +
                "-fx-background-image: url(\"/assets/nameLabel.png\");"
        );

        if(!c1.equals("")) {
            String img = chooseBorder(c1);
            chain1Label.setAlignment(Pos.CENTER_RIGHT);
            chain1Label.setStyle("" +
                    "-fx-background-image: url(" + img + ");"
            );
        }

        if(!c2.equals("")) {
            String img = chooseBorder(c2);
            chain2Label.setAlignment(Pos.CENTER_RIGHT);
            chain2Label.setStyle("" +
                    "-fx-background-image: url(" + img + ");"
            );
        }
    }

    private String chooseBorder(String c1) {
        String[] civic = {"aqueduct", "temple", "statue", "pantheon", "gardens", "courthouse", "senate"};
        String[] commerce = {"forum", "caravansery", "haven", "arena", "lighthouse"};
        String[] mil = {"fortifications", "circus", "stables", "archery range",
                "siege workshop"};
        String[] science = {"dispensary", "laboratory", "observatory", "lodge", "library", "university",
                "academy", "study"};
        for(int i = 0; i < civic.length; i++){
            if (c1.equalsIgnoreCase(civic[i]))
                return "\"/assets/chainLabelBlue.png\"";
        }

        for(int i = 0; i < commerce.length; i++){
            if (c1.equalsIgnoreCase(commerce[i]))
                return "\"/assets/chainLabelYellow.png\"";
        }

        for(int i = 0; i < mil.length; i++){
            if (c1.equalsIgnoreCase(mil[i]))
                return "\"/assets/chainLabelRed.png\"";
        }

        for(int i = 0; i < science.length; i++){
            if (c1.equalsIgnoreCase(science[i]))
                return "\"/assets/chainLabelGreen.png\"";
        }
        return "\"/assets/chainLabelNormal.png\"";
    }

    private void fillPreReq(int resource, int money) {
        ArrayList<Integer> res = Numbers.factorizeResources(resource);
        String path = "/assets/cards/";
        prereqHBox.getChildren().clear();

        for(int i = -1; i < res.size(); i++){
            ImageView imgView = new ImageView();
            imgView.setFitHeight(20);
            imgView.setFitWidth(20);
            int val;
            if (i > -1) {
                val = res.get(i);
                imgView.setImage(new Image(path + val + ".png"));
                prereqHBox.getChildren().add(imgView);
            } else {
                if (money > 0) {
                    imgView.setImage(new Image(path + "smallcoin.png"));
                    StackPane st = new StackPane();
                    Label label = new Label("" + money);
                    label.setAlignment(Pos.CENTER);
                    st.getChildren().add(imgView);
                    st.getChildren().add(label);
                    prereqHBox.getChildren().add(st);
                }
            }
        }
    }

    public void update(Card card){
        prereqCardLabel.setText(" "+ card.cost.getPrereq());
        cardNameLabel.setText(" " + card.name);
        chain1Label.setText(card.chain1 + "  ");
        chain2Label.setText(card.chain2 + "  ");
        prereqHBox.getChildren().clear(); //clear prereq HBox
        fillPreReq(card.cost.getResources(), card.cost.getCoins());
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
        prereqCardLabel.resizeRelocate(0, 75, 104, 20);
        chain1Label.resizeRelocate(60, 127, 90, 20);
        chain2Label.resizeRelocate(60, 150, 90, 20);
        cardNameLabel.resizeRelocate(0, 175, 150, 20);
    }
}
