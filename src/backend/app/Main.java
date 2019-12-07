package backend.app;

import backend.controllers.GameController;
import backend.models.Cost;
import backend.models.House;
import backend.models.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static Stage window;
    public static GameController game;

    @Override
    public void start(Stage primaryStage) throws Exception {

        game = new GameController();

        window = primaryStage;
        //sets up the stage
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPaths.mainMenu));
        primaryStage.setTitle("Seven Houses");
        primaryStage.setScene(new Scene(root, 1080, 760));
        //primaryStage.show();

        House house1 = new House("myHouse");
        House house2 = new House("left");
        House house3 = new House("right");

//        house.printResources();
//        int arr1[] = {2};
//        int[] arr2 = {3};
//        int[] arr3 = {2, 3};
//        int[] arr4 = {2, 3, 5};
//        int[] arr5 = {2, 7, 17};
//
//        house.addResource(arr1);
//        house.printResources();
//        house.addResource(arr2);
//        house.printResources();
//        house.addResource(arr3);
//        house.printResources();
//        house.addResource(arr4);
//        house.printResources();
//        house.addResource(arr5);
//        house.printResources();

        int res1[] = {2};
        int res2[] = {3};
        int res3[] = {5};
        int res7[] = {17};
        int res6[] = {13};
        int res6or1[] = {2, 13};
        int res7or1[] = {2, 17};

        house1.addResource(res1);
        house1.addResource(res1);
        house1.addResource(res2);
        house1.addResource(res3); //2,2,3,5

        house2.addResource(res6or1); //2 or 13

        house3.addResource(res2);
        house3.addResource(res7);
        house3.addResource(res6);
        house3.addResource(res7or1); // 2,2,13,17 or 2,13,17,17

        Player me = new Player(house1);
        Player left = new Player(house2);
        Player right = new Player(house3);

        me.setNeighbors(left, right);
        left.setNeighbors(me, right);
        right.setNeighbors(left, me);





        Cost cost = new Cost(0, "card10", 2*2*3*5*13*17); //costs 2,2,3,5,17
        if (me.canBuild(cost)){
            System.out.println("Buildable!");
        } else {
            System.out.println("sorry, it is not buildable.");
        }
        if (house1.canAfford(cost).code ==1 ) System.out.println("YES");
    }


    public static void main(String[] args) {
        launch(args);
    }


}
