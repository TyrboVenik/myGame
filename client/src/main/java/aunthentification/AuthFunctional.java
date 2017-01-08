package aunthentification;

import clientconnection.ServerConnector;
import properties.PlayerProperties;
import utils.Reporter;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by venik on 07.01.17.
 */
public class AuthFunctional {
    private static String DEFAULT_LOGIN = "log228";
    private static String DEFAULT_PASSWORD = "log";

    private static Authentification authentification = new Authentification();

    private enum AuthOption {
        REGISTER, LOGIN
    }

    public static void startAuth(){
        Object[] options = {AuthOption.LOGIN, AuthOption.REGISTER};
        int authOption = JOptionPane.showOptionDialog(null,
                "Choose authentication option",
                "Authentication",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        if(authOption==0)
            login();
        if(authOption==1)
            register();
    }

    private static void register(){
        String login = JOptionPane.showInputDialog(null, "Login", DEFAULT_LOGIN);
        String password = JOptionPane.showInputDialog(null, "Password", DEFAULT_PASSWORD);

        Boolean answer = authentification.register(login,password);
        if(!answer){
            Reporter.reportError("Register","Register failed");
        }else {
            Reporter.reportOk("Register","User "+login+" registered");
        }

        startAuth();
    }

    private static void login(){
        String login = JOptionPane.showInputDialog(null, "Login", DEFAULT_LOGIN);
        String password = JOptionPane.showInputDialog(null, "Password", DEFAULT_PASSWORD);

        String token = authentification.login(login,password);
        if(token == null){
            Reporter.reportError("Login","Login failed");
            startAuth();
        }else {
            Reporter.reportOk("Login","Hello, "+login);
            PlayerProperties.TOKEN = token;
            PlayerProperties.USER_NAME = login;

            ServerConnector.connect();

        }
    }
}
