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
    private boolean direction; //true for cw, false for ccw
    private ArrayList<Card> cards;


    public Deck(int noOfPlayers, int age) {
        ArrayList<Card> currAgeCards;
        cards = new ArrayList<Card>();
        if ( age == 1) {
            currAgeCards = this.initAge1();
            for (int i = 0; i < currAgeCards.size(); i++) {
                if (currAgeCards.get(i).cardFreq <= noOfPlayers)
                    cards.add(currAgeCards.get(i));
            }
            direction = true;
        }
        else if (age == 2) {
            currAgeCards = this.initAge2();

            for (int i = 0; i < currAgeCards.size(); i++) {
                if (currAgeCards.get(i).cardFreq <= noOfPlayers)
                    cards.add(currAgeCards.get(i));
            }
            direction = false;
        }
        else {
            currAgeCards = this.initAge3();
            for (int i = 0; i < currAgeCards.size(); i++) {
                if (currAgeCards.get(i).cardFreq <= noOfPlayers)
                    cards.add(currAgeCards.get(i));
            }
            cards.addAll(initAndRandomizeCrisis(noOfPlayers));
            direction = true;
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    private ArrayList<Card> initAge1() {
        String path = "";
        ArrayList<Card> cards1 = new ArrayList<Card>();
        //Resources Age 1
        cards1.add(new Resource("Lumber Yard",
                3,
                1,
                new Cost(0, "", 1),
                path + "Lumber Yard" + "image",
                path + "Lumber Yard" + "icon.png",
                path + "Lumber Yard" + "back",
                "",
                "",
                arr(w)
        ));
        cards1.add(new Resource("Lumber Yard",
                4,
                1,
                new Cost(0, "", 1),
                path + "Lumber Yard" + "image",
                path + "Lumber Yard" + "icon.png",
                path + "Lumber Yard" + "back",
                "",
                "",
                arr(w)
        ));
        cards1.add(new Resource("Stone Pit",
                3,
                1,
                new Cost(0, "", 1),
                path + "Stone Pit" + "image",
                path + "Stone Pit" + "icon.png",
                path + "Stone Pit" + "back",
                "",
                "",
                arr(so)
        ));
        cards1.add(new Resource("Stone Pit",
                5,
                1,
                new Cost(0, "", 1),
                path + "Stone Pit" + "image",
                path + "Stone Pit" + "icon.png",
                path + "Stone Pit" + "back",
                "",
                "",
                arr(so)
        ));
        cards1.add(new Resource("Clay Pool",
                3,
                1,
                new Cost(0, "", 1),
                path + "Clay Pool" + "image",
                path + "Clay Pool" + "icon.png",
                path + "Clay Pool" + "back",
                "",
                "",
                arr(c)
        ));
        cards1.add(new Resource("Clay Pool",
                5,
                1,
                new Cost(0, "", 1),
                path + "Clay Pool" + "image",
                path + "Clay Pool" + "icon.png",
                path + "Clay Pool" + "back",
                "",
                "",
                arr(c)
        ));
        cards1.add(new Resource("Ore Vein",
                3,
                1,
                new Cost(0, "", 1),
                path + "Ore Vein" + "image",
                path + "Ore Vein" + "icon.png",
                path + "Ore Vein" + "back",
                "",
                "",
                arr(o)
        ));
        cards1.add(new Resource("Ore Vein",
                4,
                1,
                new Cost(0, "", 1),
                path + "Ore Vein" + "image",
                path + "Ore Vein" + "icon.png",
                path + "Ore Vein" + "back",
                "",
                "",
                arr(o)
        ));
        cards1.add(new Resource("Tree Farm",
                6,
                1,
                new Cost(1, "", 1),
                path + "Tree Farm" + "image",
                path + "Tree Farm" + "icon.png",
                path + "Tree Farm" + "back",
                "",
                "",
                arr(w, c)
        ));
        cards1.add(new Resource("Excavation",
                4,
                1,
                new Cost(1, "", 1),
                path + "Excavation" + "image",
                path + "Excavation" + "icon.png",
                path + "Excavation" + "back",
                "",
                "",
                arr(so, c)
        ));
        cards1.add(new Resource("Clay Pit",
                3,
                1,
                new Cost(1, "", 1),
                path + "Clay Pit" + "image",
                path + "Clay Pit" + "icon.png",
                path + "Clay Pit" + "back",
                "",
                "",
                arr(o, c)
        ));
        cards1.add(new Resource("Timber Yard",
                3,
                1,
                new Cost(1, "", 1),
                path + "Timber Yard" + "image",
                path + "Timber Yard" + "icon.png",
                path + "Timber Yard" + "back",
                "",
                "",
                arr(so, w)
        ));
        cards1.add(new Resource("Forest Cave",
                5,
                1,
                new Cost(1, "", 1),
                path + "Forest Cave" + "image",
                path + "Forest Cave" + "icon.png",
                path + "Forest Cave" + "back",
                "",
                "",
                arr(o, w)
        ));
        cards1.add(new Resource("Mine",
                6,
                1,
                new Cost(1, "", 1),
                path + "Mine" + "image",
                path + "Mine" + "icon.png",
                path + "Mine" + "back",
                "",
                "",
                arr(so, o)
        ));
        cards1.add(new Resource("Loom",
                3,
                1,
                new Cost(0, "", 1),
                path + "Loom" + "image",
                path + "Loom" + "icon.png",
                path + "Loom" + "back",
                "",
                "",
                arr(si)
        ));
        cards1.add(new Resource("Loom",
                6,
                1,
                new Cost(0, "", 1),
                path + "Loom" + "image",
                path + "Loom" + "icon.png",
                path + "Loom" + "back",
                "",
                "",
                arr(si)
        ));
        cards1.add(new Resource("Glassworks",
                3,
                1,
                new Cost(0, "", 1),
                path + "Glassworks" + "image",
                path + "Glassworks" + "icon.png",
                path + "Glassworks" + "back",
                "",
                "",
                arr(d)
        ));
        cards1.add(new Resource("Glassworks",
                6,
                1,
                new Cost(0, "", 1),
                path + "Glassworks" + "image",
                path + "Glassworks" + "icon.png",
                path + "Glassworks" + "back",
                "",
                "",
                arr(d)
        ));
        cards1.add(new Resource("Press",
                3,
                1,
                new Cost(0, "", 1),
                path + "Press" + "image",
                path + "Press" + "icon.png",
                path + "Press" + "back",
                "",
                "",
                arr(p)
        ));
        cards1.add(new Resource("Press",
                6,
                1,
                new Cost(0, "", 1),
                path + "Press" + "image",
                path + "Press" + "icon.png",
                path + "Press" + "back",
                "",
                "",
                arr(p)
        ));
        //Civics Age 1
        cards1.add(new Civic("Pawnshop",
                4,
                1,
                new Cost(0, "", 1),
                path + "Pawnshop" + "image",
                path + "Pawnshop" + "icon.png",
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
                path + "Pawnshop" + "icon.png",
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
                path + "Baths" + "icon.png",
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
                path + "Baths" + "icon.png",
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
                path + "Altar" + "icon.png",
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
                path + "Altar" + "icon.png",
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
                path + "Theater" + "icon.png",
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
                path + "Theater" + "icon.png",
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
                path + "Theater" + "icon.png",
                path + "Theater" + "back",
                "Statue",
                "",
                2,
                0
        ));
        //Military Age 1
        cards1.add(new Military("Stockade",
                3,
                1,
                new Cost(0, "", w),
                path + "Stockade" + "image",
                path + "Stockade" + "icon.png",
                path + "Stockade" + "back",
                "",
                "",
                1

        ));
        cards1.add(new Military("Stockade",
                7,
                1,
                new Cost(0, "", w),
                path + "Stockade" + "image",
                path + "Stockade" + "icon.png",
                path + "Stockade" + "back",
                "",
                "",
                1

        ));
        cards1.add(new Military("Barracks",
                3,
                1,
                new Cost(0, "", o),
                path + "Barracks" + "image",
                path + "Barracks" + "icon.png",
                path + "Barracks" + "back",
                "",
                "",
                1

        ));
        cards1.add(new Military("Barracks",
                5,
                1,
                new Cost(0, "", o),
                path + "Barracks" + "image",
                path + "Barracks" + "icon.png",
                path + "Barracks" + "back",
                "",
                "",
                1

        ));
        cards1.add(new Military("Guard Tower",
                3,
                1,
                new Cost(0, "", c),
                path + "Guard Tower" + "image",
                path + "Guard Tower" + "icon.png",
                path + "Guard Tower" + "back",
                "",
                "",
                1

        ));
        cards1.add(new Military("Guard Tower",
                4,
                1,
                new Cost(0, "", c),
                path + "Guard Tower" + "image",
                path + "Guard Tower" + "icon.png",
                path + "Guard Tower" + "back",
                "",
                "",
                1

        ));
        //Science Age 1
        cards1.add(new Science("Apothecary",
                3,
                1,
                new Cost(0, "", si),
                path + "Apothecary" + "image",
                path + "Apothecary" + "icon.png",
                path + "Apothecary" + "back",
                "Stables",
                "Dispensary",
                "Ruler"
        ));
        cards1.add(new Science("Apothecary",
                5,
                1,
                new Cost(0, "", si),
                path + "Apothecary" + "image",
                path + "Apothecary" + "icon.png",
                path + "Apothecary" + "back",
                "Stables",
                "Dispensary",
                "Ruler"
        ));
        cards1.add(new Science("Workshop",
                3,
                1,
                new Cost(0, "", d),
                path + "Workshop" + "image",
                path + "Workshop" + "icon.png",
                path + "Workshop" + "back",
                "Archery Range",
                "Laboratory",
                "Gear"
        ));
        cards1.add(new Science("Workshop",
                7,
                1,
                new Cost(0, "", d),
                path + "Workshop" + "image",
                path + "Workshop" + "icon.png",
                path + "Workshop" + "back",
                "Archery Range",
                "Laboratory",
                "Gear"
        ));
        cards1.add(new Science("Scriptorium",
                3,
                1,
                new Cost(0, "", p),
                path + "Scriptorium" + "image",
                path + "Scriptorium" + "icon.png",
                path + "Scriptorium" + "back",
                "Courthouse",
                "Library",
                "Tablet"
        ));
        cards1.add(new Science("Scriptorium",
                4,
                1,
                new Cost(0, "", p),
                path + "Scriptorium" + "image",
                path + "Scriptorium" + "icon.png",
                path + "Scriptorium" + "back",
                "Courthouse",
                "Library",
                "Tablet"
        ));
        //Commerce Age 1

        cards1.add(new Commerce("Marketplace",
                3,
                1,
                new Cost(0, "", 1),
                path + "Marketplace" + "image",
                path + "Marketplace" + "icon.png",
                path + "Marketplace" + "back",
                "Caravansery",
                "",
                0,
                arr(1),
                0,
                d * si * p,
                true,
                true,
                false,
                false,
                ""
        ));
        cards1.add(new Commerce("Marketplace",
                6,
                1,
                new Cost(0, "", 1),
                path + "Marketplace" + "image",
                path + "Marketplace" + "icon.png",
                path + "Marketplace" + "back",
                "Caravansery",
                "",
                0,
                arr(1),
                0,
                d * si * p,
                true,
                true,
                false,
                false,
                ""
        ));
        cards1.add(new Commerce("Tavern",
                4,
                1,
                new Cost(0, "", 1),
                path + "Tavern" + "image",
                path + "Tavern" + "icon.png",
                path + "Tavern" + "back",
                "",
                "",
                0,
                arr(1),
                5,
                0,
                false,
                false,
                false,
                false,
                ""
        ));
        cards1.add(new Commerce("Tavern",
                5,
                1,
                new Cost(0, "", 1),
                path + "Tavern" + "image",
                path + "Tavern" + "icon.png",
                path + "Tavern" + "back",
                "",
                "",
                0,
                arr(1),
                5,
                0,
                false,
                false,
                false,
                false,
                ""
        ));
        cards1.add(new Commerce("Tavern",
                7,
                1,
                new Cost(0, "", 1),
                path + "Tavern" + "image",
                path + "Tavern" + "icon.png",
                path + "Tavern" + "back",
                "",
                "",
                0,
                arr(1),
                5,
                0,
                false,
                false,
                false,
                false,
                ""
        ));
        cards1.add(new Commerce("East Trading Post",
                3,
                1,
                new Cost(0, "", 1),
                path + "East Trading Post" + "image",
                path + "East Trading Post" + "icon.png",
                path + "East Trading Post" + "back",
                "Forum",
                "",
                0,
                arr(1),
                0,
                c*so*o*w,
                false,
                true,
                false,
                false,
                ""
        ));
        cards1.add(new Commerce("East Trading Post",
                7,
                1,
                new Cost(0, "", 1),
                path + "East Trading Post" + "image",
                path + "East Trading Post" + "icon.png",
                path + "East Trading Post" + "back",
                "Forum",
                "",
                0,
                arr(1),
                0,
                c*so*o*w,
                false,
                true,
                false,
                false,
                ""
        ));
        cards1.add(new Commerce("West Trading Post",
                7,
                1,
                new Cost(0, "", 1),
                path + "West Trading Post" + "image",
                path + "West Trading Post" + "icon.png",
                path + "West Trading Post" + "back",
                "Forum",
                "",
                0,
                arr(1),
                0,
                c*so*o*w,
                true,
                false,
                false,
                false,
                ""
        ));
        cards1.add(new Commerce("West Trading Post",
                3,
                1,
                new Cost(0, "", 1),
                path + "West Trading Post" + "image",
                path + "West Trading Post" + "icon.png",
                path + "West Trading Post" + "back",
                "Forum",
                "",
                0,
                arr(1),
                0,
                c*so*o*w,
                true,
                false,
                false,
                false,
                ""
        ));

        return cards1;
    }

    private ArrayList<Card> initAge2() {
        String path = "";
        ArrayList<Card> cards1 = new ArrayList<Card>();
        //Resources Age 2
        cards1.add(new Resource("Sawmill",
                3,
                2,
                new Cost(1, "", 1),
                path + "Sawmill" + "image",
                path + "Sawmill" + "icon.png",
                path + "Sawmill" + "back",
                "",
                "",
                arr(w * w)
        ));
        cards1.add(new Resource("Sawmill",
                4,
                2,
                new Cost(1, "", 1),
                path + "Sawmill" + "image",
                path + "Sawmill" + "icon.png",
                path + "Sawmill" + "back",
                "",
                "",
                arr(w * w)
        ));
        cards1.add(new Resource("Quarry",
                3,
                1,
                new Cost(1, "", 1),
                path + "Quarry" + "image",
                path + "Quarry" + "icon.png",
                path + "Quarry" + "back",
                "",
                "",
                arr(so * so)
        ));
        cards1.add(new Resource("Quarry",
                4,
                2,
                new Cost(1, "", 1),
                path + "Quarry" + "image",
                path + "Quarry" + "icon.png",
                path + "Quarry" + "back",
                "",
                "",
                arr(so * so)
        ));
        cards1.add(new Resource("Brickyard",
                3,
                2,
                new Cost(1, "", 1),
                path + "Brickyard" + "image",
                path + "Brickyard" + "icon.png",
                path + "Brickyard" + "back",
                "",
                "",
                arr(c * c)
        ));
        cards1.add(new Resource("Brickyard",
                4,
                2,
                new Cost(1, "", 1),
                path + "Brickyard" + "image",
                path + "Brickyard" + "icon.png",
                path + "Brickyard" + "back",
                "",
                "",
                arr(c * c)
        ));
        cards1.add(new Resource("Foundry",
                3,
                2,
                new Cost(1, "", 1),
                path + "Foundry" + "image",
                path + "Foundry" + "icon.png",
                path + "Foundry" + "back",
                "",
                "",
                arr(c * c)
        ));
        cards1.add(new Resource("Foundry",
                4,
                2,
                new Cost(1, "", 1),
                path + "Foundry" + "image",
                path + "Foundry" + "icon.png",
                path + "Foundry" + "back",
                "",
                "",
                arr(c * c)
        ));
        cards1.add(new Resource("Loom",
                3,
                2,
                new Cost(0, "", 1),
                path + "Loom" + "image",
                path + "Loom" + "icon.png",
                path + "Loom" + "back",
                "",
                "",
                arr(si)
        ));
        cards1.add(new Resource("Loom",
                5,
                2,
                new Cost(0, "", 1),
                path + "Loom" + "image",
                path + "Loom" + "icon.png",
                path + "Loom" + "back",
                "",
                "",
                arr(si)
        ));
        cards1.add(new Resource("Glassworks",
                3,
                2,
                new Cost(0, "", 1),
                path + "Glassworks" + "image",
                path + "Glassworks" + "icon.png",
                path + "Glassworks" + "back",
                "",
                "",
                arr(d)
        ));
        cards1.add(new Resource("Glassworks",
                5,
                2,
                new Cost(0, "", 1),
                path + "Glassworks" + "image",
                path + "Glassworks" + "icon.png",
                path + "Glassworks" + "back",
                "",
                "",
                arr(d)
        ));
        cards1.add(new Resource("Press",
                3,
                2,
                new Cost(0, "", 1),
                path + "Press" + "image",
                path + "Press" + "icon.png",
                path + "Press" + "back",
                "",
                "",
                arr(p)
        ));
        cards1.add(new Resource("Press",
                5,
                2,
                new Cost(0, "", 1),
                path + "Press" + "image",
                path + "Press" + "icon.png",
                path + "Press" + "back",
                "",
                "",
                arr(p)
        ));
        //Civics Age 2
        cards1.add(new Civic("Aquaduct",
                3,
                2,
                new Cost(0, "Baths", si * si * si),
                path + "Aquaduct" + "image",
                path + "Aquaduct" + "icon.png",
                path + "Aquaduct" + "back",
                "Statue",
                "",
                5,
                0
        ));
        cards1.add(new Civic("Aquaduct",
                7,
                2,
                new Cost(0, "Baths", si * si * si),
                path + "Aquaduct" + "image",
                path + "Aquaduct" + "icon.png",
                path + "Aquaduct" + "back",
                "Statue",
                "",
                5,
                0
        ));
        cards1.add(new Civic("Temple",
                3,
                2,
                new Cost(0, "Altar", w * c * d),
                path + "Temple" + "image",
                path + "Temple" + "icon.png",
                path + "Temple" + "back",
                "Pantheon",
                "",
                3,
                0
        ));
        cards1.add(new Civic("Temple",
                6,
                2,
                new Cost(0, "Altar", w * c * d),
                path + "Temple" + "image",
                path + "Temple" + "icon.png",
                path + "Temple" + "back",
                "Pantheon",
                "",
                3,
                0
        ));
        cards1.add(new Civic("Statue",
                3,
                2,
                new Cost(0, "Theater", o * o * w),
                path + "Statue" + "image",
                path + "Statue" + "icon.png",
                path + "Statue" + "back",
                "Gardens",
                "",
                4,
                0
        ));
        cards1.add(new Civic("Statue",
                7,
                2,
                new Cost(0, "Theater", o * o * w),
                path + "Statue" + "image",
                path + "Statue" + "icon.png",
                path + "Statue" + "back",
                "Gardens",
                "",
                4,
                0
        ));
        cards1.add(new Civic("Courthouse",
                3,
                2,
                new Cost(0, "Scriptorum", c * c * si),
                path + "Courthouse" + "image",
                path + "Courthouse" + "icon.png",
                path + "Courthouse" + "back",
                "",
                "",
                4,
                0
        ));
        cards1.add(new Civic("Courthouse",
                5,
                2,
                new Cost(0, "Scriptorum", c * c * si),
                path + "Courthouse" + "image",
                path + "Courthouse" + "icon.png",
                path + "Courthouse" + "back",
                "",
                "",
                4,
                0
        ));
        //Military Age 2
        cards1.add(new Military("Walls",
                3,
                2,
                new Cost(0, "", so * so * so),
                path + "Walls" + "image",
                path + "Walls" + "icon.png",
                path + "Walls" + "back",
                "Fortifications",
                "",
                2

        ));
        cards1.add(new Military("Walls",
                7,
                2,
                new Cost(0, "", so * so * so),
                path + "Walls" + "image",
                path + "Walls" + "icon.png",
                path + "Walls" + "back",
                "Fortifications",
                "",
                2

        ));
        cards1.add(new Military("Training Ground",
                4,
                2,
                new Cost(0, "", o * o * w),
                path + "Training Ground" + "image",
                path + "Training Ground" + "icon.png",
                path + "Training Ground" + "back",
                "Circus",
                "",
                2

        ));
        cards1.add(new Military("Training Ground",
                6,
                2,
                new Cost(0, "", o * o * w),
                path + "Training Ground" + "image",
                path + "Training Ground" + "icon.png",
                path + "Training Ground" + "back",
                "Circus",
                "",
                2

        ));
        cards1.add(new Military("Training Ground",
                7,
                2,
                new Cost(0, "", o * o * w),
                path + "Training Ground" + "image",
                path + "Training Ground" + "icon.png",
                path + "Training Ground" + "back",
                "Circus",
                "",
                2

        ));
        cards1.add(new Military("Stables",
                3,
                2,
                new Cost(0, "Apothecary", o * c * w),
                path + "Stables" + "image",
                path + "Stables" + "icon.png",
                path + "Stables" + "back",
                "",
                "",
                2

        ));
        cards1.add(new Military("Stables",
                5,
                2,
                new Cost(0, "Apothecary", o * c * w),
                path + "Stables" + "image",
                path + "Stables" + "icon.png",
                path + "Stables" + "back",
                "",
                "",
                2

        ));
        cards1.add(new Military("Archery Range",
                3,
                2,
                new Cost(0, "Workshop", w * w * o),
                path + "Archery Range" + "image",
                path + "Archery Range" + "icon.png",
                path + "Archery Range" + "back",
                "",
                "",
                2

        ));
        cards1.add(new Military("Archery Range",
                6,
                2,
                new Cost(0, "Workshop", o * w * w),
                path + "Archery Range" + "image",
                path + "Archery Range" + "icon.png",
                path + "Archery Range" + "back",
                "",
                "",
                2

        ));
        //Science Age 2
        cards1.add(new Science("Dispensary",
                3,
                2,
                new Cost(0, "Apothecary", o * o * d),
                path + "Dispensary" + "image",
                path + "Dispensary" + "icon.png",
                path + "Dispensary" + "back",
                "Arena",
                "Lodge",
                "Ruler"
        ));
        cards1.add(new Science("Dispensary",
                4,
                2,
                new Cost(0, "Apothecary", o * o * d),
                path + "Dispensary" + "image",
                path + "Dispensary" + "icon.png",
                path + "Dispensary" + "back",
                "Arena",
                "Lodge",
                "Ruler"
        ));
        cards1.add(new Science("Laboratory",
                3,
                2,
                new Cost(0, "Workshop", c * c * p),
                path + "Laboratory" + "image",
                path + "Laboratory" + "icon.png",
                path + "Laboratory" + "back",
                "Siege Workshop",
                "Observatory",
                "Gear"
        ));
        cards1.add(new Science("Laboratory",
                5,
                2,
                new Cost(0, "Workshop", c * c * p),
                path + "Laboratory" + "image",
                path + "Laboratory" + "icon.png",
                path + "Laboratory" + "back",
                "Siege Workshop",
                "Observatory",
                "Gear"
        ));
        cards1.add(new Science("Library",
                3,
                2,
                new Cost(0, "Scriptorium", so * so * si),
                path + "Library" + "image",
                path + "Library" + "icon.png",
                path + "Library" + "back",
                "Senate",
                "University",
                "Tablet"
        ));
        cards1.add(new Science("Library",
                6,
                2,
                new Cost(0, "Scriptorium", so * so * si),
                path + "Library" + "image",
                path + "Library" + "icon.png",
                path + "Library" + "back",
                "Senate",
                "University",
                "Tablet"
        ));
        cards1.add(new Science("School",
                3,
                2,
                new Cost(0, "", w * p),
                path + "School" + "image",
                path + "School" + "icon.png",
                path + "School" + "back",
                "Academy",
                "Study",
                "Tablet"
        ));
        cards1.add(new Science("School",
                7,
                2,
                new Cost(0, "", w * p),
                path + "School" + "image",
                path + "School" + "icon.png",
                path + "School" + "back",
                "Academy",
                "Study",
                "Tablet"
        ));
        //Commerce Age 2
        cards1.add(new Commerce("Forum",
                3,
                2,
                new Cost(0, "East Trading Post", c*c),
                path + "Forum" + "image",
                path + "Forum" + "icon.png",
                path + "Forum" + "back",
                "Haven",
                "",
                0,
                arr(si*d*p),
                0,
                0,
                false,
                false,
                false,
                false,
                ""
        ));
        cards1.add(new Commerce("Forum",
                6,
                2,
                new Cost(0, "East Trading Post", c*c),
                path + "Forum" + "image",
                path + "Forum" + "icon.png",
                path + "Forum" + "back",
                "Haven",
                "",
                0,
                arr(si*d*p),
                0,
                0,
                false,
                false,
                false,
                false,
                ""
        ));
        cards1.add(new Commerce("Forum",
                7,
                2,
                new Cost(0, "East Trading Post", c*c),
                path + "Forum" + "image",
                path + "Forum" + "icon.png",
                path + "Forum" + "back",
                "Haven",
                "",
                0,
                arr(si*d*p),
                0,
                0,
                false,
                false,
                false,
                false,
                ""
        ));
        cards1.add(new Commerce("Caravansery",
                3,
                2,
                new Cost(0, "Marketplace", w*w),
                path + "Caravansery" + "image",
                path + "Caravansery" + "icon.png",
                path + "Caravansery" + "back",
                "Lighthouse",
                "",
                0,
                arr(c*so*o*w),
                0,
                0,
                false,
                false,
                false,
                false,
                ""
        ));
        cards1.add(new Commerce("Caravansery",
                5,
                2,
                new Cost(0, "Marketplace", w*w),
                path + "Caravansery" + "image",
                path + "Caravansery" + "icon.png",
                path + "Caravansery" + "back",
                "Lighthouse",
                "",
                0,
                arr(c*so*o*w),
                0,
                0,
                false,
                false,
                false,
                false,
                ""
        ));
        cards1.add(new Commerce("Caravansery",
                6,
                2,
                new Cost(0, "Marketplace", w*w),
                path + "Caravansery" + "image",
                path + "Caravansery" + "icon.png",
                path + "Caravansery" + "back",
                "Lighthouse",
                "",
                0,
                arr(c*so*o*w),
                0,
                0,
                false,
                false,
                false,
                false,
                ""
        ));
        cards1.add(new Commerce("Vineyard",
                3,
                2,
                new Cost(0, "", 1),
                path + "Vineyard" + "image",
                path + "Vineyard" + "icon.png",
                path + "Vineyard" + "back",
                "",
                "",
                0,
                arr(1),
                1, //TODO coin calculation algorithm works this way?
                0,
                true,
                true,
                true,
                false,
                "Resource"
        ));
        cards1.add(new Commerce("Vineyard",
                6,
                2,
                new Cost(0, "", 1),
                path + "Vineyard" + "image",
                path + "Vineyard" + "icon.png",
                path + "Vineyard" + "back",
                "",
                "",
                0,
                arr(1),
                1,
                0,
                true,
                true,
                true,
                false,
                "Resource"
        ));
        cards1.add(new Commerce("Bazaar",
                4,
                2,
                new Cost(0, "", 1),
                path + "Bazaar" + "image",
                path + "Bazaar" + "icon.png",
                path + "Bazaar" + "back",
                "",
                "",
                0,
                arr(1),
                2,
                0,
                true,
                true,
                true,
                false,
                "Resource"
        ));
        cards1.add(new Commerce("Bazaar",
                7,
                2,
                new Cost(0, "", 1),
                path + "Bazaar" + "image",
                path + "Bazaar" + "icon.png",
                path + "Bazaar" + "back",
                "",
                "",
                0,
                arr(1),
                2,
                0,
                true,
                true,
                true,
                false,
                "Resource"
        ));

        return cards1;
    }

    private ArrayList<Card> initAge3() {
        String path = "";
        ArrayList<Card> cards1 = new ArrayList<Card>();
        cards1.add(new Civic("Pantheon",
                3,
                3,
                new Cost(0, "Temple", c * c * si * o * d * p),
                path + "Pantheon" + "image",
                path + "Pantheon" + "icon.png",
                path + "Pantheon" + "back",
                "",
                "",
                7,
                0
        ));
        cards1.add(new Civic("Pantheon",
                6,
                3,
                new Cost(0, "Temple", c * c * si * o * d * p),
                path + "Pantheon" + "image",
                path + "Pantheon" + "icon.png",
                path + "Pantheon" + "back",
                "",
                "",
                7,
                0
        ));
        cards1.add(new Civic("Gardens",
                3,
                3,
                new Cost(0, "Statue", c * c * w),
                path + "Gardens" + "image",
                path + "Gardens" + "icon.png",
                path + "Gardens" + "back",
                "",
                "",
                5,
                0
        ));
        cards1.add(new Civic("Gardens",
                4,
                3,
                new Cost(0, "Statue", c * c * w),
                path + "Gardens" + "image",
                path + "Gardens" + "icon.png",
                path + "Gardens" + "back",
                "",
                "",
                5,
                0
        ));
        cards1.add(new Civic("Town Hall",
                3,
                3,
                new Cost(0, "", d * o * so * so),
                path + "Town Hall" + "image",
                path + "Town Hall" + "icon.png",
                path + "Town Hall" + "back",
                "",
                "",
                6,
                0
        ));
        cards1.add(new Civic("Town Hall",
                5,
                3,
                new Cost(0, "", d * o * so * so),
                path + "Town Hall" + "image",
                path + "Town Hall" + "icon.png",
                path + "Town Hall" + "back",
                "",
                "",
                6,
                0
        ));
        cards1.add(new Civic("Town Hall",
                6,
                3,
                new Cost(0, "", d * o * so * so),
                path + "Town Hall" + "image",
                path + "Town Hall" + "icon.png",
                path + "Town Hall" + "back",
                "",
                "",
                6,
                0
        ));
        cards1.add(new Civic("Palace",
                3,
                3,
                new Cost(0, "", d * o * so * p * si * c * w),
                path + "Palace" + "image",
                path + "Palace" + "icon.png",
                path + "Palace" + "back",
                "",
                "",
                8,
                0
        ));
        cards1.add(new Civic("Palace",
                7,
                3,
                new Cost(0, "", d * o * so * p * si * c * w),
                path + "Palace" + "image",
                path + "Palace" + "icon.png",
                path + "Palace" + "back",
                "",
                "",
                8,
                0
        ));
        cards1.add(new Civic("Senate",
                3,
                3,
                new Cost(0, "Library", o * so * w * w),
                path + "Senate" + "image",
                path + "Senate" + "icon.png",
                path + "Senate" + "back",
                "",
                "",
                6,
                0
        ));
        cards1.add(new Civic("Senate",
                5,
                3,
                new Cost(0, "Library", d * o * so * p * si * c * w),
                path + "Senate" + "image",
                path + "Senate" + "icon.png",
                path + "Senate" + "back",
                "",
                "",
                6,
                0
        ));
        //Military Age 3
        cards1.add(new Military("Fortifications",
                3,
                3,
                new Cost(0, "Walls", so * o * o * o),
                path + "Fortifications" + "image",
                path + "Fortifications" + "icon.png",
                path + "Fortifications" + "back",
                "",
                "",
                3

        ));
        cards1.add(new Military("Fortifications",
                7,
                3,
                new Cost(0, "Walls", so * o * o * o),
                path + "Fortifications" + "image",
                path + "Fortifications" + "icon.png",
                path + "Fortifications" + "back",
                "",
                "",
                3

        ));
        cards1.add(new Military("Circus",
                4,
                3,
                new Cost(0, "Training Grounds", so * so * so * o),
                path + "Circus" + "image",
                path + "Circus" + "icon.png",
                path + "Circus" + "back",
                "",
                "",
                3

        ));
        cards1.add(new Military("Circus",
                5,
                3,
                new Cost(0, "Training Grounds", so * so * so * o),
                path + "Circus" + "image",
                path + "Circus" + "icon.png",
                path + "Circus" + "back",
                "",
                "",
                3

        ));
        cards1.add(new Military("Circus",
                6,
                3,
                new Cost(0, "Training Grounds", so * so * so * o),
                path + "Circus" + "image",
                path + "Circus" + "icon.png",
                path + "Circus" + "back",
                "",
                "",
                3

        ));
        cards1.add(new Military("Arsenal",
                3,
                3,
                new Cost(0, "", o * w * w * si),
                path + "Arsenal" + "image",
                path + "Arsenal" + "icon.png",
                path + "Arsenal" + "back",
                "",
                "",
                3

        ));
        cards1.add(new Military("Arsenal",
                4,
                3,
                new Cost(0, "", o * w * w * si),
                path + "Arsenal" + "image",
                path + "Arsenal" + "icon.png",
                path + "Arsenal" + "back",
                "",
                "",
                3

        ));
        cards1.add(new Military("Arsenal",
                7,
                3,
                new Cost(0, "", o * w * w * si),
                path + "Arsenal" + "image",
                path + "Arsenal" + "icon.png",
                path + "Arsenal" + "back",
                "",
                "",
                3

        ));
        cards1.add(new Military("Siege Workshop",
                3,
                3,
                new Cost(0, "Laboratory", w * c * c * c * c),
                path + "Siege Workshop" + "image",
                path + "Siege Workshop" + "icon.png",
                path + "Siege Workshop" + "back",
                "",
                "",
                3

        ));
        cards1.add(new Military("Siege Workshop",
                5,
                3,
                new Cost(0, "Laboratory", w * c * c * c * c),
                path + "Siege Workshop" + "image",
                path + "Siege Workshop" + "icon.png",
                path + "Siege Workshop" + "back",
                "",
                "",
                3

        ));
        //Science Age 3
        cards1.add(new Science("Lodge",
                3,
                3,
                new Cost(0, "Dispensary", c * c * si * p),
                path + "Lodge" + "image",
                path + "Lodge" + "icon.png",
                path + "Lodge" + "back",
                "",
                "",
                "Ruler"
        ));
        cards1.add(new Science("Lodge",
                6,
                3,
                new Cost(0, "Dispensary", c * c * si * p),
                path + "Lodge" + "image",
                path + "Lodge" + "icon.png",
                path + "Lodge" + "back",
                "",
                "",
                "Ruler"
        ));
        cards1.add(new Science("Observatory",
                3,
                3,
                new Cost(0, "Laboratory", o * o * d * si),
                path + "Observatory" + "image",
                path + "Observatory" + "icon.png",
                path + "Observatory" + "back",
                "",
                "",
                "Gear"
        ));
        cards1.add(new Science("Observatory",
                7,
                3,
                new Cost(0, "Laboratory", o * o * d * si),
                path + "Observatory" + "image",
                path + "Observatory" + "icon.png",
                path + "Observatory" + "back",
                "",
                "",
                "Gear"
        ));
        cards1.add(new Science("Study",
                3,
                3,
                new Cost(0, "School", w * p * si),
                path + "Study" + "image",
                path + "Study" + "icon.png",
                path + "Study" + "back",
                "",
                "",
                "Gear"
        ));
        cards1.add(new Science("Study",
                5,
                3,
                new Cost(0, "School", w * p * si),
                path + "Study" + "image",
                path + "Study" + "icon.png",
                path + "Study" + "back",
                "",
                "",
                "Gear"
        ));
        cards1.add(new Science("Academy",
                3,
                3,
                new Cost(0, "School", so * so * so * d),
                path + "Academy" + "image",
                path + "Academy" + "icon.png",
                path + "Academy" + "back",
                "",
                "",
                "Ruler"
        ));
        cards1.add(new Science("Academy",
                7,
                3,
                new Cost(0, "School", so * so * so * d),
                path + "Academy" + "image",
                path + "Academy" + "icon.png",
                path + "Academy" + "back",
                "",
                "",
                "Ruler"
        ));
        cards1.add(new Science("University",
                3,
                3,
                new Cost(0, "Library", w * w * d * si),
                path + "University" + "image",
                path + "University" + "icon.png",
                path + "University" + "back",
                "",
                "",
                "Tablet"
        ));
        cards1.add(new Science("University",
                4,
                3,
                new Cost(0, "Library", w * w * d * si),
                path + "University" + "image",
                path + "University" + "icon.png",
                path + "University" + "back",
                "",
                "",
                "Tablet"
        ));

        //Commerce Age 3
        cards1.add(new Commerce("Haven",
                3,
                3,
                new Cost(0, "Forum", si*o*w),
                path + "Haven" + "image",
                path + "Haven" + "icon.png",
                path + "Haven" + "back",
                "",
                "",
                1,
                arr(1),
                1,
                0,
                false,
                false,
                true,
                false,
                "Resource"
        ));
        cards1.add(new Commerce("Haven",
                4,
                3,
                new Cost(0, "Forum", si*o*w),
                path + "Haven" + "image",
                path + "Haven" + "icon.png",
                path + "Haven" + "back",
                "",
                "",
                1,
                arr(1),
                1,
                0,
                false,
                false,
                true,
                false,
                "Resource"
        ));
        cards1.add(new Commerce("Lighthouse",
                3,
                3,
                new Cost(0, "Caravansery", d*so),
                path + "Lighthouse" + "image",
                path + "Lighthouse" + "icon.png",
                path + "Lighthouse" + "back",
                "",
                "",
                1,
                arr(1),
                1,
                0,
                false,
                false,
                true,
                false,
                "Commerce"
        ));
        cards1.add(new Commerce("Lighthouse",
                6,
                3,
                new Cost(0, "Caravansery", d*so),
                path + "Lighthouse" + "image",
                path + "Lighthouse" + "icon.png",
                path + "Lighthouse" + "back",
                "",
                "",
                1,
                arr(1),
                1,
                0,
                false,
                false,
                true,
                false,
                "Commerce"
        ));
        cards1.add(new Commerce("Chamber of Commerce",
                4,
                3,
                new Cost(0, "", c*c*p),
                path + "Chamber of Commerce" + "image",
                path + "Chamber of Commerce" + "icon.png",
                path + "Chamber of Commerce" + "back",
                "",
                "",
                2,
                arr(1),
                2,
                0,
                false,
                false,
                true,
                false,
                "Resource"
        ));
        cards1.add(new Commerce("Chamber of Commerce",
                6,
                3,
                new Cost(0, "", c*c*p),
                path + "Chamber of Commerce" + "image",
                path + "Chamber of Commerce" + "icon.png",
                path + "Chamber of Commerce" + "back",
                "",
                "",
                2,
                arr(1),
                2,
                0,
                false,
                false,
                true,
                false,
                "Resource"
        ));
        cards1.add(new Commerce("Arena",
                3,
                3,
                new Cost(0, "Dispensary", o*so*so),
                path + "Arena" + "image",
                path + "Arena" + "icon.png",
                path + "Arena" + "back",
                "",
                "",
                1,
                arr(1),
                3,
                0,
                false,
                false,
                true,
                true,
                "Commerce"
        ));
        cards1.add(new Commerce("Arena",
                5,
                3,
                new Cost(0, "Dispensary", o*so*so),
                path + "Arena" + "image",
                path + "Arena" + "icon.png",
                path + "Arena" + "back",
                "",
                "",
                1,
                arr(1),
                3,
                0,
                false,
                false,
                true,
                true,
                "Commerce"
        ));
        cards1.add(new Commerce("Arena",
                7,
                3,
                new Cost(0, "Dispensary", o*so*so),
                path + "Arena" + "image",
                path + "Arena" + "icon.png",
                path + "Arena" + "back",
                "",
                "",
                1,
                arr(1),
                3,
                0,
                false,
                false,
                true,
                true,
                "Commerce"
        ));

        return cards1;
    }
    private ArrayList<Card> initAndRandomizeCrisis(int noOfPlayers) {
        //CrisisCards Initialization
        ArrayList<Card> crisis = new ArrayList<>();
        String path= "";

        crisis.add(new Crisis("Ned Stark's Execution",
                new Cost(0, "", p*si*d),
                path + "Ned Stark's Execution" + "image",
                path + "Ned Stark's Execution" + "icon.png",
                path + "Ned Stark's Execution" + "back",
                1
        ));
        crisis.add(new Crisis("Battle of Winterfell",
                new Cost(0, "", o*o*c*so*w),
                path + "Battle of Winterfell" + "image",
                path + "Battle of Winterfell" + "icon.png",
                path + "Battle of Winterfell" + "back",
                2
        ));
        crisis.add(new Crisis("Battle of the Blackwater",
                new Cost(0, "", o*o*so*so),
                path + "Battle of the Blackwater" + "image",
                path + "Battle of the Blackwater" + "icon.png",
                path + "Battle of the Blackwater" + "back",
                3
        ));
        crisis.add(new Crisis("Fall of King’s Landing",
                new Cost(0, "", c*c*c*si*p),
                path + "Fall of King’s Landing" + "image",
                path + "Fall of King’s Landing" + "icon.png",
                path + "Fall of King’s Landing" + "back",
                4
        ));
        crisis.add(new Crisis("Battle of Castle Black",
                new Cost(0, "", c*c*c*d),
                path + "Battle of Castle Black" + "image",
                path + "Battle of Castle Black" + "icon.png",
                path + "Battle of Castle Black" + "back",
                5
        ));
        crisis.add(new Crisis("Massacre at Hardhome",
                new Cost(0, "", o*o*so*si),
                path + "Massacre at Hardhome" + "image",
                path + "Massacre at Hardhome" + "icon.png",
                path + "Massacre at Hardhome" + "back",
                6
        ));
        crisis.add(new Crisis("Blackwater Rush",
                new Cost(0, "", w*w*w*p*d),
                path + "Blackwater Rush" + "image",
                path + "Blackwater Rush" + "icon.png",
                path + "Blackwater Rush" + "back",
                7
        ));
        crisis.add(new Crisis("Frozen Lake Battle",
                new Cost(0, "", w*w*o*o*p),
                path + "Frozen Lake Battle" + "image",
                path + "Frozen Lake Battle" + "icon.png",
                path + "Frozen Lake Battle" + "back",
                8
        ));
        crisis.add(new Crisis("Jon's Resurrection",
                new Cost(0, "", w*w*w*so*si),
                path + "Jon's Resurrection" + "image",
                path + "Jon's Resurrection" + "icon.png",
                path + "Jon's Resurrection" + "back",
                9
        ));
        crisis.add(new Crisis("Daenerys's death",
                new Cost(0, "", so*so*c*c*d),
                path + "Daenerys's death" + "image",
                path + "Daenerys's death" + "icon.png",
                path + "Daenerys's death" + "back",
                10
        ));



        for (int i = 0; i < 10 - (noOfPlayers + 2); i++) {
            int rand = (int) (Math.random() * crisis.size()) ;
            crisis.remove(rand);
        }
        return crisis;
    }



}
