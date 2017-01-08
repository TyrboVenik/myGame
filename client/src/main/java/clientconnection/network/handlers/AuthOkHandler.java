package clientconnection.network.handlers;

import aunthentification.AuthFunctional;
import game.Game;
import game.view.GameFrame;
import properties.PlayerProperties;
import protocol.CommandAuthOk;
import utils.JsonHelper;

import java.net.Authenticator;
import java.util.Properties;

/**
 * Created by venik on 08.01.17.
 */
public class AuthOkHandler {

    public AuthOkHandler(String msg){
        CommandAuthOk comAuthOk = JsonHelper.fromJson(msg,CommandAuthOk.class);
        PlayerProperties.ID = comAuthOk.getId();

        Game game = new Game();

        game.start();
        try {
            game.join();
        } catch (InterruptedException e) {
            AuthFunctional.startAuth();
        }
    }
}
