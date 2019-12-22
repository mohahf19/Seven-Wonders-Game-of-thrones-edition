package backend.controllers;

import backend.models.Card;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PlayedCardView extends HBox {
    private VBox resources;
    private VBox science;
    private VBox commerce;
    private VBox civic;
    private VBox military;
    private VBox crisis;

    public PlayedCardView(){
        initComponents();

        this.setSpacing(6);

        addComponents();
    }

    public PlayedCardView(ArrayList<Card> cards){
        initComponents();

        this.setSpacing(6);

        populateCategories(cards);

        addComponents();
    }

    private void addComponents() {
        this.getChildren().addAll(resources, science, commerce, civic, military, crisis);
    }

    private void initComponents() {
        resources = new VBox();
        science = new VBox();
        commerce = new VBox();
        civic = new VBox();
        military = new VBox();
        crisis = new VBox();
        resources.setSpacing(2);
        science.setSpacing(2);
        commerce.setSpacing(2);
        civic.setSpacing(2);
        military.setSpacing(2);
        crisis.setSpacing(2);

    }

    private void populateCategories(ArrayList<Card> cards) {
        if (cards != null) {
            for (Card card : cards) {
                placeInCorrectCategory(card);
            }
        }
    }

    public void addCard(Card card){
        placeInCorrectCategory(card);
    }

    private void placeInCorrectCategory(Card card) {
        MiniCardView view = new MiniCardView(card);
//        Commerce c = (Commerce) card;
        if (card.isResource()){
            resources.getChildren().add(view);
        } else if (card.isScience()){
            science.getChildren().add(view);
        } else if (card.isCommerce()){
            commerce.getChildren().add(view);
        } else if (card.isCivic()){
            civic.getChildren().add(view);
        } else if (card.isMilitary()){
            military.getChildren().add(view);
        } else {
            crisis.getChildren().add(view);
        }
    }

    @Override protected double computeMinWidth(double height){
        return 750;
    }

    @Override protected double computeMinHeight(double width){
        return 450;
    }

    @Override protected double computeMaxWidth(double height){
        return 750;
    }

    @Override protected double computeMaxHeight(double width){
        return 450;
    }

    @Override protected double computePrefWidth(double height){
         return 750;
    }

    @Override protected double computePrefHeight(double width){
        return 450;
    }

    public void display(ArrayList<Card> cards) {
        clearAll();
        populateCategories(cards);
    }

    private void clearAll() {
        resources.getChildren().clear();
        science.getChildren().clear();
        civic.getChildren().clear();
        military.getChildren().clear();
        commerce.getChildren().clear();
        crisis.getChildren().clear();
    }
}
