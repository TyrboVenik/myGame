package clientconnection;

import com.sun.istack.internal.NotNull;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.net.URI;
import java.util.concurrent.Future;

/**
 * Created by venik on 04.01.17.
 */
public class EventClient {
    public static void main(@NotNull String[] args){
        URI uri = URI.create("ws://localhost:8090/clientConnection");

        WebSocketClient client = new WebSocketClient();

        try {
            client.start();
            EventHandler socket = new EventHandler();

            Future<Session> future = client.connect(socket,uri);

            Session session = future.get();

            session.getRemote().sendString("HELLO");

            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
