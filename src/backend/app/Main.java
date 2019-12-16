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
        primaryStage.setScene(new Scene(root, 1280, 900));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }


}
