package clientconnection;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;



/**
 * Created by venik on 04.01.17.
 */
public class EventHandler extends WebSocketAdapter {
    private static final Logger log = LogManager.getLogger(EventHandler.class);

    public void onWebSocketClose(int statusCode, String reason) {
        log.info("Closed");
    }

    public void onWebSocketConnect(Session sess) {
        log.info("Connect");
    }

    public void onWebSocketText(String message) {
        log.info("message {}",message);
    }
}
