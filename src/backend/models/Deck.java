package backend.models;

public class Deck {
    private boolean direction;
    private Card[] cards;

    public Deck(){

    }
    public Card[] getCards() {
        return cards;
    }
    public void setCards(Card[] cards) {
        this.cards = cards;
    }
}
