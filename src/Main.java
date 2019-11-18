
import backend.models.Client;
import backend.models.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/ui/MainMenu.fxml"));
        primaryStage.setTitle("Seven Houses");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }


}
