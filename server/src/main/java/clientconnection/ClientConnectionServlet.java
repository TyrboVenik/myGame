package clientconnection;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * Created by venik on 04.01.17.
 */
public class ClientConnectionServlet extends WebSocketServlet {
    public void configure(WebSocketServletFactory factory) {
        factory.register(ClientConnectionHandler.class);
    }
}
