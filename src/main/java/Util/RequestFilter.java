package Util;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class RequestFilter implements javax.ws.rs.container.ContainerRequestFilter  {

    public void filter(ContainerRequestContext request) {

        //Get the authentification passed in HTTP headers parameters
        String auth = request.getHeaderString("Authorization");

        /*
        //If the user does not have the right (does not provide any HTTP Basic Auth)
        if(auth == null) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }*/

        if(auth != null){
            Authentication authentication = new Authentication();

            boolean authenticationStatus = authentication
                    .authenticate(auth);


            //If login or password fail
            if(!authenticationStatus) {
                throw new WebApplicationException(Response.Status.UNAUTHORIZED);
            }

            // configure Security Context here
            String scheme = request.getUriInfo().getRequestUri().getScheme();
            request.setSecurityContext(new AppSecurityContext(authentication.getLoginUser(), scheme));

            // TODO : ADD PARAMETER TO REQUEST, TO REMEMBER USER

        }

    }

}
