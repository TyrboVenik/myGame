package clientconnection;

import clientconnection.network.handlers.AuthFailHandler;
import clientconnection.network.handlers.AuthOkHandler;
import clientconnection.network.packets.PacketAuth;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import properties.PlayerProperties;
import protocol.Command;
import protocol.CommandAuthFail;
import protocol.CommandAuthOk;
import utils.JsonHelper;


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
        readMessage(message);
    }

    private void readMessage(String msg){
        Command command = JsonHelper.fromJson(msg,Command.class);
        switch (command.getName()) {
            case CommandAuthFail.NAME:
                new AuthFailHandler(msg);
                break;
            case CommandAuthOk.NAME:
                new AuthOkHandler(msg);
                break;
        }
    }
}
