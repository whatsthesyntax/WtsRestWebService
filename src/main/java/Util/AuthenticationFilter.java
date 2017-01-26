package Util;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter  {

    public void filter(ContainerRequestContext request) {

        //Get the authentification passed in HTTP headers parameters
        String auth = request.getHeaderString("Authorization");

        //If the user does not have the right (does not provide any HTTP Basic Auth)
        if(auth == null) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

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

/*
    public static final String AUTHENTICATION_HEADER = "Authorization";

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filter) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String authCredentials = httpServletRequest
                    .getHeader(AUTHENTICATION_HEADER);

            // better injected
            Authentication authentication = new Authentication();

            boolean authenticationStatus = authentication
                    .authenticate(authCredentials);

            if (authenticationStatus) {
                filter.doFilter(request, response);
            } else {
                if (response instanceof HttpServletResponse) {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse
                            .setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
    }


    public void destroy() {
    }


    public void init(FilterConfig arg0) throws ServletException {
    }
*/

}
