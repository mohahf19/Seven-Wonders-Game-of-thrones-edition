package backend.models;

public class Crisis extends Card {
    public int crisisID;

    public Crisis(String name, Cost cost, String imagePath,
                  String iconPath, String backPath, int crisisID){
        super(name, 1, 3, cost, imagePath, iconPath, backPath, "","");
        this.crisisID = crisisID;

        this.cardType = "crisis";
    }

    @Override
    public boolean isCrisis(){
        return true;
    }
}
