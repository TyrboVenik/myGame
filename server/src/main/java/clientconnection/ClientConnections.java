package clientconnection;

import model.Player;
import org.eclipse.jetty.websocket.api.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by venik on 04.01.17.
 */
public class ClientConnections {
    private Map<Player, Session> connectionList = new ConcurrentHashMap<>();

    public void addConnection(Player player, Session session){
        connectionList.put(player, session);
    }

    public Player getPlayer(Session session){
        for (Player player: connectionList.keySet())
            if(connectionList.get(player).equals(session))
                return player;

        return null;
    }

    public Set<Map.Entry<Player,Session>> getSessions(){
        return connectionList.entrySet();
    }

    public void deleteConnection(Player player){
        connectionList.remove(player);
    }
}
