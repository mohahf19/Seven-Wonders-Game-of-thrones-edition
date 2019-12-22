package comm;

import backend.models.*;
import com.google.gson.*;
import sun.security.ec.ECDHKeyAgreement;

import javax.swing.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class PlayerDeserializer implements JsonDeserializer<Player>
{
    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE  = "INSTANCE";

//    @Override
//    public JsonElement serialize(ArrayList<Player> src, Type typeOfSrc,
//                                 JsonSerializationContext context) {
//
//        System.out.println("Card serialized");
//
//        JsonObject retValue = new JsonObject();
//        String className = src.getClass().getName();
//        retValue.addProperty(CLASSNAME, className);
//        JsonElement elem = context.serialize(src);
//        retValue.add(INSTANCE, elem);
//        return retValue;
//    }

    @Override
    public Player deserialize(JsonElement je, Type t, JsonDeserializationContext jdc)
            throws JsonParseException
    {


        Gson g = new Gson();
        JsonObject object = je.getAsJsonObject();
        Player player = g.fromJson( object, Player.class);

        ArrayList<Card> cards = new ArrayList<>();
        JsonArray jo = je.getAsJsonObject().getAsJsonArray("cards");
        for( JsonElement e: jo){
            String type = e.getAsJsonObject().get("cardType").getAsString();
            switch ( type){
                case "resource":
                    cards.add( g.fromJson( e, Resource.class));
                    break;
                case "military":
                    cards.add( g.fromJson( e, Military.class));
                    break;
                case "science":
                    cards.add( g.fromJson( e, Science.class));
                    break;
                case "civic":
                    cards.add( g.fromJson( e, Civic.class));
                    break;
                case "commerce":
                    cards.add( g.fromJson( e, Commerce.class));
                    break;
                case "crisis":
                    cards.add( g.fromJson( e, Crisis.class));
                    break;
                default:
                    System.out.println("Invalid card");
                    break;

            }
        }
        player.cards = cards;

        try{
            ArrayList<Card> playedCards = new ArrayList<>();
            jo = je.getAsJsonObject().getAsJsonArray("playedCards");
            for( JsonElement e: jo){
                String type = e.getAsJsonObject().get("cardType").getAsString();
                switch ( type){
                    case "resource":
                        playedCards.add( g.fromJson( e, Resource.class));
                        break;
                    case "military":
                        playedCards.add( g.fromJson( e, Military.class));
                        break;
                    case "science":
                        playedCards.add( g.fromJson( e, Science.class));
                        break;
                    case "civic":
                        playedCards.add( g.fromJson( e, Civic.class));
                        break;
                    case "commerce":
                        playedCards.add( g.fromJson( e, Commerce.class));
                        break;
                    case "crisis":
                        playedCards.add( g.fromJson( e, Crisis.class));
                        break;
                    default:
                        System.out.println("Invalid card");
                        break;

                }
            }
            player.playedCards = playedCards;
        } catch ( Exception e){
            player.playedCards = new ArrayList<>();
        }


        return player;
    }
}