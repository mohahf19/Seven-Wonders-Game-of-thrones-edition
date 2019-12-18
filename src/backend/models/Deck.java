package backend.models;

import java.util.ArrayList;

import static backend.models.Numbers.arr;

public class Deck {
    int o = 2;
    int w = 3;
    int c = 5;
    int so = 7;
    int d = 11;
    int p = 13;
    int si = 17;
    private boolean direction;
    private ArrayList<Card> cards;

    public Deck(int noOfPlayers, int age){
        //ArrayList<Card> currAgeCards;
        //TODO ?= init Cards(age);
        /*for(int i = 0; i < currAgeCards.size(); i++)
        {
            if( currAgeCards.get(i).cardFreq <= noOfPlayers)
                cards.add(currAgeCards.get(i));
        }*/
    }
    public ArrayList<Card> getCards() {
        return cards;
    }

    private ArrayList<Card> initAge1(){
        String path = "" ;
        ArrayList<Card> cards1 = new ArrayList<Card>();
        cards1.add(new Resource("Lumber Yard",
                3,
                1,
                new Cost(0,"",1),
                path + "Lumber Yard" + "image",
                path + "Lumber Yard" + "icon",
                path + "Lumber Yard" + "back",
                "",
                "",
                arr(3)
                ));
        cards1.add(new Resource("Lumber Yard",
                4,
                1,
                new Cost(0,"",1),
                path + "Lumber Yard" + "image",
                path + "Lumber Yard" + "icon",
                path + "Lumber Yard" + "back",
                "",
                "",
                arr(3)
        ));
        cards1.add(new Civic("Pawnshop",
                4,
                1,
                new Cost(0, "", 1),
                path + "Pawnshop" + "image",
                path + "Pawnshop" + "icon",
                path + "Pawnshop" + "back",
                "",
                "",
                3,
                0
                ));
        cards1.add(new Civic("Pawnshop",
                7,
                1,
                new Cost(0, "", 1),
                path + "Pawnshop" + "image",
                path + "Pawnshop" + "icon",
                path + "Pawnshop" + "back",
                "",
                "",
                3,
                0
        ));
        cards1.add(new Civic("Baths",
                3,
                1,
                new Cost(0, "", si),
                path + "Baths" + "image",
                path + "Baths" + "icon",
                path + "Baths" + "back",
                "Aquaduct",
                "",
                3,
                0
        ));
        cards1.add(new Civic("Baths",
                7,
                1,
                new Cost(0, "", si),
                path + "Baths" + "image",
                path + "Baths" + "icon",
                path + "Baths" + "back",
                "Aquaduct",
                "",
                3,
                0
        ));
        cards1.add(new Civic("Altar",
                3,
                1,
                new Cost(0, "", 1),
                path + "Altar" + "image",
                path + "Altar" + "icon",
                path + "Altar" + "back",
                "Temple",
                "",
                2,
                0
        ));
        cards1.add(new Civic("Altar",
                5,
                1,
                new Cost(0, "", 1),
                path + "Altar" + "image",
                path + "Altar" + "icon",
                path + "Altar" + "back",
                "Temple",
                "",
                2,
                0
        ));
        cards1.add(new Civic("Theater",
                3,
                1,
                new Cost(0, "", 1),
                path + "Theater" + "image",
                path + "Theater" + "icon",
                path + "Theater" + "back",
                "Statue",
                "",
                2,
                0
        ));
        cards1.add(new Civic("Theater",
                6,
                1,
                new Cost(0, "", 1),
                path + "Theater" + "image",
                path + "Theater" + "icon",
                path + "Theater" + "back",
                "Statue",
                "",
                2,
                0
        ));
        cards1.add(new Civic("Theater",
                6,
                1,
                new Cost(0, "", 1),
                path + "Theater" + "image",
                path + "Theater" + "icon",
                path + "Theater" + "back",
                "Statue",
                "",
                2,
                0
        ));
        return cards1;
    }
    private ArrayList<Card> initAge2(){
        String path = "" ;
        ArrayList<Card> cards1 = new ArrayList<Card>();
        cards1.add(new Civic("Aquaduct",
                3,
                2,
                new Cost(0, "Baths", si*si*si),
                path + "Aquaduct" + "image",
                path + "Aquaduct" + "icon",
                path + "Aquaduct" + "back",
                "Statue",
                "",
                5,
                0
        ));
        cards1.add(new Civic("Aquaduct",
                7,
                2,
                new Cost(0, "Baths", si*si*si),
                path + "Aquaduct" + "image",
                path + "Aquaduct" + "icon",
                path + "Aquaduct" + "back",
                "Statue",
                "",
                5,
                0
        ));
        cards1.add(new Civic("Temple",
                3,
                2,
                new Cost(0, "Altar", w*c*d),
                path + "Temple" + "image",
                path + "Temple" + "icon",
                path + "Temple" + "back",
                "Pantheon",
                "",
                3,
                0
        ));
        cards1.add(new Civic("Temple",
                6,
                2,
                new Cost(0, "Altar", w*c*d),
                path + "Temple" + "image",
                path + "Temple" + "icon",
                path + "Temple" + "back",
                "Pantheon",
                "",
                3,
                0
        ));
        cards1.add(new Civic("Statue",
                3,
                2,
                new Cost(0, "Theater", o*o*w),
                path + "Statue" + "image",
                path + "Statue" + "icon",
                path + "Statue" + "back",
                "Gardens",
                "",
                4,
                0
        ));
        cards1.add(new Civic("Statue",
                7,
                2,
                new Cost(0, "Theater", o*o*w),
                path + "Statue" + "image",
                path + "Statue" + "icon",
                path + "Statue" + "back",
                "Gardens",
                "",
                4,
                0
        ));
        cards1.add(new Civic("Courthouse",
                3,
                2,
                new Cost(0, "Scriptorum", c*c*si),
                path + "Courthouse" + "image",
                path + "Courthouse" + "icon",
                path + "Courthouse" + "back",
                "",
                "",
                4,
                0
        ));
        cards1.add(new Civic("Courthouse",
                5,
                2,
                new Cost(0, "Scriptorum", c*c*si),
                path + "Courthouse" + "image",
                path + "Courthouse" + "icon",
                path + "Courthouse" + "back",
                "",
                "",
                4,
                0
        ));
        return cards1;
    }
    private ArrayList<Card> initAge3() {
        String path = "";
        ArrayList<Card> cards1 = new ArrayList<Card>();
        cards1.add(new Civic("Pantheon",
                3,
                3,
                new Cost(0, "Temple", c*c*si*o*d*p),
                path + "Pantheon" + "image",
                path + "Pantheon" + "icon",
                path + "Pantheon" + "back",
                "",
                "",
                7,
                0
        ));
        cards1.add(new Civic("Pantheon",
                6,
                3,
                new Cost(0, "Temple", c*c*si*o*d*p),
                path + "Pantheon" + "image",
                path + "Pantheon" + "icon",
                path + "Pantheon" + "back",
                "",
                "",
                7,
                0
        ));
        cards1.add(new Civic("Gardens",
                3,
                3,
                new Cost(0, "Statue", c*c*w),
                path + "Gardens" + "image",
                path + "Gardens" + "icon",
                path + "Gardens" + "back",
                "",
                "",
                5,
                0
        ));
        cards1.add(new Civic("Gardens",
                4,
                3,
                new Cost(0, "Statue", c*c*w),
                path + "Gardens" + "image",
                path + "Gardens" + "icon",
                path + "Gardens" + "back",
                "",
                "",
                5,
                0
        ));
        cards1.add(new Civic("Town Hall",
                3,
                3,
                new Cost(0, "", d*o*so*so),
                path + "Town Hall" + "image",
                path + "Town Hall" + "icon",
                path + "Town Hall" + "back",
                "",
                "",
                6,
                0
        ));
        cards1.add(new Civic("Town Hall",
                5,
                3,
                new Cost(0, "", d*o*so*so),
                path + "Town Hall" + "image",
                path + "Town Hall" + "icon",
                path + "Town Hall" + "back",
                "",
                "",
                6,
                0
        ));
        cards1.add(new Civic("Town Hall",
                6,
                3,
                new Cost(0, "", d*o*so*so),
                path + "Town Hall" + "image",
                path + "Town Hall" + "icon",
                path + "Town Hall" + "back",
                "",
                "",
                6,
                0
        ));
        cards1.add(new Civic("Palace",
                3,
                3,
                new Cost(0, "", d*o*so*p*si*c*w),
                path + "Palace" + "image",
                path + "Palace" + "icon",
                path + "Palace" + "back",
                "",
                "",
                8,
                0
        ));
        return cards1;
    }
}
