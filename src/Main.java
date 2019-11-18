import backend.app.fxmlPaths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;
        //sets up the stage
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPaths.mainMenu));
        primaryStage.setTitle("Seven Houses");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }


}
