package clientconnection;

import clientconnection.handlers.PacketAuthHandler;
import com.sun.istack.internal.NotNull;
import main.ApplicationContext;
import mathmaker.MatchMaker;
import model.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import protocol.Command;
import protocol.CommandAuth;
import utils.JsonHelper;

import java.util.Map;


/**
 * Created by venik on 04.01.17.
 */
public class ClientConnectionHandler extends WebSocketAdapter {

    private final static Logger log = LogManager.getLogger(ClientConnectionHandler.class);

    @Override
    public void onWebSocketConnect(@NotNull Session session){
        super.onWebSocketConnect(session);
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
        ClientConnections clientConnections = ApplicationContext.getInstanse().get(ClientConnections.class);

       for(Map.Entry<Player,Session> key: clientConnections.getSessions()){
           if (key.getValue().equals(getSession())){
               MatchMaker matchMaker = ApplicationContext.getInstanse().get(MatchMaker.class);
               matchMaker.leave(key.getKey());
           }
       }
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
