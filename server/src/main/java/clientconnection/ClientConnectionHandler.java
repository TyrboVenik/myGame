package clientconnection;

import clientconnection.handlers.PacketAuthHandler;
import com.sun.istack.internal.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import protocol.Command;
import protocol.CommandAuth;
import utils.JsonHelper;



/**
 * Created by venik on 04.01.17.
 */
public class ClientConnectionHandler extends WebSocketAdapter {

    private final static Logger log = LogManager.getLogger(ClientConnectionHandler.class);

    @Override
    public void onWebSocketConnect(@NotNull Session session){
        super.onWebSocketConnect(session);
        ClientConnections.connectionList.add(session);
        log.info("onWebSocketConnect");
    }

    @Override
    public void onWebSocketText(@NotNull String message){
        super.onWebSocketText(message);
        log.info("onWebSocketText {}",message);
        readMessage(message);
    }

    @Override
    public void onWebSocketClose(int statusCode,@NotNull String reason){
        log.info("onWebSocketClose");
    }


    private void readMessage(String msg){
        Command command = JsonHelper.fromJson(msg,Command.class);
        switch (command.getName()) {
            case CommandAuth.NAME:
                new PacketAuthHandler(getSession(), msg);
                break;
        }

    }
}
