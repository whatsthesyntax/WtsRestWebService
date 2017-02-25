package Service;

/**
 * Created by rim on 18/01/17.
 */

import Util.AppSecurityContext;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;


// class for testing permissions

@Path("logged")
public class loggedService {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("Utilisateur")
    public String loggedUser(@Context SecurityContext sc) {
        if(sc.getUserPrincipal() == null)
            return ("no user");
        return sc.isUserInRole("Utilisateur")?"Hello user!":"not registered :(";
    }

    @Path("admin")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("Administrateur")
    public String loggedAdmin(@Context SecurityContext sc) {
        return sc.isUserInRole("Administrateur")?"Hello admin!":"not admin :(";
    }

}
