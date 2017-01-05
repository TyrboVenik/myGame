package accountserver.api;

import dao.AuthDao;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by venik on 05.01.17.
 */

@Path("/auth")
public class AuthServlet {
    private static final Logger log = LogManager.getLogger(AuthServlet.class);

    private static final AuthDao dao = new AuthDao();

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

    @NotNull
    @POST
    @Path("login")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public Response login(@NotNull @FormParam("user") String user,
                             @NotNull @FormParam("password") String password){

        return Response.ok(Long.toString(123)).build();
    }
}
