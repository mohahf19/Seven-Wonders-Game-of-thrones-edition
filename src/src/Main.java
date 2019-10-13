import connection.models.Client;
import connection.models.Server;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println( "starts");
                Client client = new Client("127.0.0.1", 5000);
            }
        }, 2000);

        Server server = new Server(5000);



    }

}
