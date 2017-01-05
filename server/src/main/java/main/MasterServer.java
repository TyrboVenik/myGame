package main;

import accountserver.AccountServer;
import clientconnection.ClientConnectionServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by venik on 06.01.17.
 */
public class MasterServer {
    private final static Logger log = LogManager.getLogger(MasterServer.class);

    private void start(){
        AccountServer accountServer = new AccountServer();
        ClientConnectionServer clientConnectionServer = new ClientConnectionServer();

        accountServer.start();
        clientConnectionServer.start();

        try {
            accountServer.join();
            clientConnectionServer.join();
        } catch (InterruptedException e) {
            log.error(e);
        }

    }

    public static void main(String[] args){
        new MasterServer().start();
    }
}
