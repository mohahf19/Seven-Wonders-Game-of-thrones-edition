package backend.controllers;

import backend.models.Client;
import backend.models.Server;

public class ConnectionController {
    public static Server server = null;
    public static Client client = null;

    public static int state = 1; //0 for server, 1 for client

    public static void initServer( String userName){
        (new Thread(() -> server = new Server( userName))).start();
        state = 0;
    }
    public static void initClient( String userName, String ip){
        server = null;
        (new Thread(() -> client = new Client( userName, ip))).start();
        state = 1;
    }
}
