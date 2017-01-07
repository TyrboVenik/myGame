package clientconnection.handlers;

import accountserver.api.AuthServlet;
import accountserver.api.Token;
import clientconnection.packets.PacketAuthFail;
import clientconnection.packets.PacketAuthOk;
import main.ApplicationContext;
import org.eclipse.jetty.websocket.api.Session;
import protocol.Command;
import protocol.CommandAuth;
import protocol.CommandAuthOk;
import utils.JsonHelper;

import java.io.IOException;

/**
 * Created by venik on 08.01.17.
 */
public class PacketAuthHandler {

    public PacketAuthHandler(Session session, String msg){
        CommandAuth comAuth = JsonHelper.fromJson(msg,CommandAuth.class);
        ApplicationContext.getInstanse().get(AuthServlet.class);

        Token token = JsonHelper.fromJson(comAuth.getToken(), Token.class);

        if(AuthServlet.checkToken(comAuth.getUser(),token))
            new PacketAuthOk(0).write(session);
        else
            new PacketAuthFail("incorrect token").write(session);
    }
}
