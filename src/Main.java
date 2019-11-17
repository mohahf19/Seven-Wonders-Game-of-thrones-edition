
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
        primaryStage.setScene(new Scene(root, 1080, 700));
        primaryStage.show();

        new Server();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                (new Thread(() -> new Client( "arham", "139.179.206.93"))).start();

                (new Thread(() -> new Client( "mohamad", "139.179.206.93"))).start();
            }
        }, 1000);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
