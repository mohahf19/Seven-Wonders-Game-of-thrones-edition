package backend.app;

import backend.controllers.GameEngine;
import backend.controllers.SoundController;
import comm.ServerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Main extends Application {

    public static Stage window;

    public static int state = 0; //0 for client, 1 for server;
    public static ServerController serverController = null;
    public static GameEngine gameEngine = null;


    //testing
    double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        //sets up the stage
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPaths.mainMenu));
        primaryStage.setTitle("Seven Houses");



        primaryStage.setScene(new Scene(root, 1440, 900));
        primaryStage.show();

        SoundController.playMainSound();
    }

    public static void initServer(){
        serverController = new ServerController();
        gameEngine = new GameEngine();
        state = 1;
        serverController.initServer();
    }
    public static void initClient( String ip){
        gameEngine = new GameEngine();
        serverController = null;
        state = 0;
        gameEngine.initClient( "" + ip);

    }



    public static void main(String[] args) {
        launch(args);
    }


}
