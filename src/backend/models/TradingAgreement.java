package backend.models;

import java.util.HashMap;

public class TradingAgreement {
    HashMap<Integer, Integer> map;

    public TradingAgreement() {
        map = new HashMap<>();
        map.put(2, 2);
        map.put(3, 2);
        map.put(5, 2);
        map.put(7, 2);
        map.put(11, 2);
        map.put(13, 2);
        map.put(17, 2);
    }

    public int getCost(int res){
        return map.get(res);
    }

    public void setCost(int res, int cost){
        map.put(res, cost);
    }
}
