package accountserver.api;

import dao.AuthDao;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by venik on 05.01.17.
 */

@Path("/auth")
public class AuthServlet {
    private final Logger log = LogManager.getLogger(AuthServlet.class);

    private final AuthDao dao = new AuthDao();

    private final Map<String, Token> tokens = new ConcurrentHashMap<>();
    private final Map<Token, String> tokensRevert = new ConcurrentHashMap<>();


    // curl -i -X POST -H "Content-Type: application/x-www-form-urlencoded" -H "Host: localhost:7000" -d "user=1&password=1" "http://localhost:7000/auth/register"
    @NotNull
    @POST
    @Path("register")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public Response register(@NotNull @FormParam("user") String user,
                             @NotNull @FormParam("password") String pas){

        if (user==null || user == "" || pas==null || pas == "") {
            log.info("Incorrect user or password!");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if(dao.get(user)!=null){
            log.info("This login already exists!");
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

        dao.insert(new Pair<>(user,pas));

        log.info("User {} registered.", user);
        return Response.ok("User" + user + "registered.").build();
    }
    // curl -i -X POST -H "Content-Type: application/x-www-form-urlencoded" -H "Host: localhost:7000" -d "user=1&password=1" "http://localhost:7000/auth/login"
    @NotNull
    @POST
    @Path("login")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public Response login(@NotNull @FormParam("user") String user,
                             @NotNull @FormParam("password") String pas){

        if (user==null || user == "" || pas==null || pas == "") {
            log.info("Incorrect user or password!");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Pair<String,String> pair = dao.get(user);

        if(pair==null){
            log.info("Incorrect login!");
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

        if (!pair.getValue().equals(pas)){
            log.info("Incorrect password!");
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

        return Response.ok(getTocken(user).toString()).build();
    }

    private Token getTocken(String user){
        Token token = tokens.get(user);

        if(token!=null)
            return token;

        token = new Token();
        token.value = ThreadLocalRandom.current().nextLong();

        tokens.put(user,token);
        tokensRevert.put(token,user);

        log.info("Token {} was created for user {}", token,user);

        return token;
    }
}
