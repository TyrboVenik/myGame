package clientconnection.packets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.jetbrains.annotations.NotNull;
import protocol.CommandAuthFail;
import protocol.CommandAuthOk;
import utils.JsonHelper;

import java.io.IOException;

/**
 * Created by venik on 08.01.17.
 */
public class PacketAuthFail {
    @NotNull
    private static final Logger log = LogManager.getLogger(PacketAuthOk.class);

    private String cause;

    public PacketAuthFail(String cause){
        this.cause = cause;
    }

    public void write(Session session){
        String msg = JsonHelper.toJson(new CommandAuthFail(cause));
        try {
            session.getRemote().sendString(msg);
        } catch (IOException e) {
            log.error(e);
        }
    }
}
