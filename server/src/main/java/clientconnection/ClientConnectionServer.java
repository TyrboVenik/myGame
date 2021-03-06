package clientconnection;

import com.sun.istack.internal.NotNull;
import main.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by venik on 04.01.17.
 */
public class ClientConnectionServer extends Service{
    private final static Logger log = LogManager.getLogger(ClientConnectionServer.class);

    private final int port;

    public ClientConnectionServer(){
        super("client_connection_server");
        port = 8090;
    }

    public void  startApi(){
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);

        server.setHandler(ServletContext.getInstance());

        // Add a websocket to a specific path spec
        // ws://localhost:8090/clientConnection
        ClientConnectionServlet clientConnectionServlet = new ClientConnectionServlet();
        ServletContext.getInstance().addServlet(new ServletHolder(clientConnectionServlet), "/clientConnection");

        try {
            server.start();
        } catch (Exception e) {
            log.error("Faild to start ClientConnectionServer{}",e);
        }

        log.info("ClientConnectionServer started on port {}",port);

    }

    @Override
    public void run(){
        startApi();
    }

    public static void main(@NotNull String[] args)throws Exception{
        ClientConnectionServer clientConnectionServer = new ClientConnectionServer();
        clientConnectionServer.run();
        clientConnectionServer.join();
    }
}
