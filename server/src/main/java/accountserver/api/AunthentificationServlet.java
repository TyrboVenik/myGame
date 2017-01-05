package accountserver.api;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by venik on 05.01.17.
 */

@Path("/auth")
public class AunthentificationServlet {

    // curl -i -X POST -H "Content-Type: application/x-www-form-urlencoded" -H "Host: localhost:7000" -d "user=1&password=1" "http://localhost:7000/auth/register"

    @NotNull
    @POST
    @Path("register")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public Response register(@NotNull @FormParam("user") String user,
                             @NotNull @FormParam("password") String password){
        System.out.println("User" + user + "registered.");
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
