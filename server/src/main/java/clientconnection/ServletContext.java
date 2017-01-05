package clientconnection;

import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * Created by venik on 04.01.17.
 */
public class ServletContext {
    private static ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

    public static ServletContextHandler getInstance(){
        return  context;
    }
}
