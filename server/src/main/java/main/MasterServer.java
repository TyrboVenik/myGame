package main;

import accountserver.AccountServer;
import clientconnection.ClientConnectionServer;
import clientconnection.ClientConnections;
import mathmaker.MatchMaker;
import messagesystem.Message;
import messagesystem.MessageSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.CORBA.Object;

/**
 * Created by venik on 06.01.17.
 */
public class MasterServer {
    private final static Logger log = LogManager.getLogger(MasterServer.class);

    private void start(){
        ApplicationContext app = ApplicationContext.getInstanse();

        AccountServer accountServer = new AccountServer();
        ClientConnectionServer clientConnectionServer = new ClientConnectionServer();

        MessageSystem messageSystem = new MessageSystem();

        app.put(MessageSystem.class, messageSystem);
        app.put(MatchMaker.class,new MatchMaker());
        app.put(ClientConnections.class,new ClientConnections());

        messageSystem.registerService(accountServer);
        messageSystem.registerService(clientConnectionServer);

        for(Service service:messageSystem.getServices())
            service.start();


        try {
            for(Service service:messageSystem.getServices())
                service.join();
        } catch (InterruptedException e) {
            log.error(e);
        }

    }

    public static void main(String[] args){
        new MasterServer().start();
    }
}
