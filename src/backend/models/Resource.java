package backend.models;

import backend.app.constants;

public class Resource extends Card {
    private int[] resourcesList;
    private String[] type;


    public Resource(String name, int cardFreq, int age, Cost cost, String imagePath,
                    String iconPath, String backPath, String chain1, String chain2, int[] resourcesList, String[] type){
        super(name, cardFreq, age, cost, constants.path +"resource.jpg", iconPath, constants.path +"brown.jpg", chain1, chain2);

        this.resourcesList = new int[resourcesList.length];
        for( int i = 0; i < resourcesList.length; i++)
            this.resourcesList[i] = resourcesList[i];

        this.type = new String[type.length];
        for( int i = 0; i < type.length; i++)
            this.type[i] = type[i];
    }

    public Resource(String name, int cardFreq, int age, Cost cost, String imagePath,
                    String iconPath, String backPath, String chain1, String chain2, int[] resourcesList){
        super(name, cardFreq, age, cost, constants.path +"resource.jpg", iconPath, constants.path +"brown.jpg", chain1, chain2);

        this.resourcesList = new int[resourcesList.length];
        for( int i = 0; i < resourcesList.length; i++)
            this.resourcesList[i] = resourcesList[i];

        this.type = null;
    }

    @Override
    public boolean isResource(){
        return true;
    }

    public int[] getResourcesList() {
        return resourcesList;
    }
}
