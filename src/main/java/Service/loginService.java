package Service;

/**
 * Created by rim on 18/01/17.
 */

import Model.User;
import Util.AppSecurityContext;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;


@Path("login")
public class loginService {


    // methods for testing permissions
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("User")
    public String loggedUser(@Context SecurityContext sc) {
        if(sc.getUserPrincipal() == null)
            return ("no user");
        return sc.isUserInRole("User")?"Hello user!":"not registered :(";
    }

    @Path("admin")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("Admin")
    public String loggedAdmin(@Context SecurityContext sc) {
        return sc.isUserInRole("Admin")?"Hello admin!":"not admin :(";
    }


    // Login Basic Auth returns user in json format, or 401 Unauthorized error
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User loginUser(@Context SecurityContext sc){
        return (User)sc.getUserPrincipal();
    }

}
