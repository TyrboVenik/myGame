package clientconnection;

import com.sun.istack.internal.NotNull;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.net.URI;
import java.util.concurrent.Future;

/**
 * Created by venik on 07.01.17.
 */
public class ServerConnector {

    public static Session session = null;

    public static void connect(){
        URI uri = URI.create("ws://localhost:8090/clientConnection");

        WebSocketClient client = new WebSocketClient();

        try {
            client.start();
            ConnectionSocket socket = new ConnectionSocket();
            client.connect(socket,uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
