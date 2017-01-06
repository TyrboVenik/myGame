package accountserver;

import main.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.jetbrains.annotations.NotNull;

/**
 * Created by venik on 04.01.17.
 */
public class AccountServer extends Service {
    private final static @NotNull Logger log = LogManager.getLogger(AccountServer.class);

    private final int port;

    public AccountServer(){
        super("account_server");
        port = 7000;
    }

    private void startApi(){
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");

        Server server = new Server(port);

        server.setHandler(context);

        ServletHolder servlet = context.addServlet(ServletContainer.class, "/*");
        servlet.setInitOrder(0);

        servlet.setInitParameter(
                "jersey.config.server.provider.packages",
                "accountserver.api"
        );


        try {
            server.start();
            log.info("AuthenticationServer started on port {}", 7000);
            server.join();
        } catch (Exception e) {
            log.error("Failed to start account server {}",e);
        }

    }

    @Override
    public void run(){
        startApi();
    }

    public static void main(@NotNull String[] args){
        new AccountServer().startApi();
    }
}
