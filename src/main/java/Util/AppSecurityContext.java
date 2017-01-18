package Util;

import Model.User;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

/**
 * Created by rim on 18/01/17.
 */
public class AppSecurityContext implements SecurityContext{
    private User user;
    private String scheme;

    public AppSecurityContext(User user, String scheme) {
        this.user = user;
        this.scheme = scheme;
    }


    public Principal getUserPrincipal() {return this.user;}

    public boolean isUserInRole(String s) {
        if (user.getRoles() != null) {
            return user.hasRole(s);
        }
        return false;
    }


    public boolean isSecure() {return "https".equals(this.scheme);}


    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }

}
