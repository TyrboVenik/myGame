package clientconnection.packets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.jetbrains.annotations.NotNull;
import protocol.CommandAuthOk;
import utils.JsonHelper;

import java.io.IOException;

/**
 * Created by venik on 08.01.17.
 */
public class PacketAuthOk {
    @NotNull
    private static final Logger log = LogManager.getLogger(PacketAuthOk.class);

    private int id;

    public PacketAuthOk(int id){
        this.id = id;
    }

    public void write(Session session){
        String msg = JsonHelper.toJson(new CommandAuthOk(id));
        try {
            session.getRemote().sendString(msg);
        } catch (IOException e) {
            log.error(e);
        }
    }
}
