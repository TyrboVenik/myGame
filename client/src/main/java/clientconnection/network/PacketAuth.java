package clientconnection.network;

import aunthentification.Authentification;
import clientconnection.ServerConnector;
import com.sun.istack.internal.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import protocol.CommandAuth;
import utils.JsonHelper;

import java.io.IOException;

/**
 * Created by venik on 08.01.17.
 */
public class PacketAuth {
    @NotNull
    private static final Logger log = LogManager.getLogger(Authentification.class);

    private String user;
    private String token;

    public PacketAuth(@NotNull String user,@NotNull String token){
        this.user =user;
        this.token=token;
    }

    public void write(){
        String json = JsonHelper.toJson(new CommandAuth(user,token));
        try {
            ServerConnector.session.getRemote().sendString(json);
        } catch (IOException e) {
            log.error(e);
        }
    }
}
