package clientconnection;


import clientconnection.network.PacketAuth;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import properties.PlayerProperties;


/**
 * Created by venik on 04.01.17.
 */
@WebSocket(maxTextMessageSize = 1024*8)
public class ConnectionSocket{
    private static final Logger log = LogManager.getLogger(ConnectionSocket.class);

    @OnWebSocketConnect
    public void connect(Session sess) {
        ServerConnector.session = sess;
        new PacketAuth(PlayerProperties.USER_NAME,PlayerProperties.TOKEN).write();
        log.info("Connect");
    }

    @OnWebSocketClose
    public void close(int statusCode, String reason) {
        log.info("Closed");
        ServerConnector.session = null;
    }

    @OnWebSocketMessage
    public void text(String message) {
        log.info("message {}",message);
    }
}
