package backend.app;

import backend.controllers.GameEngine;
import backend.controllers.SoundController;
import comm.ServerController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Main extends Application {

    public static Stage window;

    public static Parent waitScreen;
    public static Parent mainScreen;
    public static Parent playScreen;

    public static int state = 0; //0 for client, 1 for server;
    public static ServerController serverController = null;
    public static GameEngine gameEngine = null;


    //testing
    double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        //sets up the stage
        mainScreen = FXMLLoader.load(getClass().getResource(fxmlPaths.mainMenu));

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream( "/assets/gameLogo.png")));
        primaryStage.setTitle("Seven Houses");


        primaryStage.setScene(new Scene(mainScreen, 1440, 900));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }

        });

        SoundController.playMainSound();
    }

    public static void initServer(){
        System.gc();
        serverController = new ServerController();
        gameEngine = new GameEngine();
        state = 1;
        serverController.initServer();
    }
    public static void initClient( String ip){
        System.gc();
        gameEngine = new GameEngine();
        serverController = null;
        state = 0;
        gameEngine.initClient( "" + ip);

    }



    public static void main(String[] args) {
        launch(args);
    }


}
