package backend.app;

import backend.controllers.GameEngine;
import comm.ServerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        ExecutorService service = Executors.newFixedThreadPool(4);
        service.execute( new Runnable() {
            @Override
            public void run() {
                //playSound();
            }
        });
    }

    public void playSound(){
        String musicFile = getClass().toString() + "/assets/sound/mainSound.mp3";//"/assets/sound/mainSound.mp3";

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
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
