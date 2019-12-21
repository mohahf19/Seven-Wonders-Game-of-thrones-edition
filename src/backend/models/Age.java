package backend.models;

public class Age {
    public Deck deck;
    public int seasonalEvents;

    public Age( Deck deck){
        this.deck = deck;
    }
    public Deck getDeck(){
        return deck;
    }
}
