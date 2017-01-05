package clientconnection;

import com.sun.istack.internal.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.io.IOException;


/**
 * Created by venik on 04.01.17.
 */
public class ClientConnectionHandler extends WebSocketAdapter {

    private final static Logger log = LogManager.getLogger(ClientConnectionHandler.class);

    @Override
    public void onWebSocketConnect(@NotNull Session session){
        ClientConnections.connectionList.add(session);
        try {
            session.getRemote().sendString("Hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("onWebSocketConnect");
    }

    @Override
    public void onWebSocketText(@NotNull String message){
        log.info("onWebSocketText");
    }

    @Override
    public void onWebSocketClose(int statusCode,@NotNull String reason){
        log.info("onWebSocketClose");
    }

}
