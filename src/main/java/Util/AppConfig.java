package Util;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

/**
 * Created by rim on 24/01/17.
 */
@ApplicationPath("/")
public class AppConfig extends ResourceConfig {

    public AppConfig() {
        register(ResponseFilter.class);
        register(RolesAllowedDynamicFeature.class);
        register(JacksonFeature.class);
    }

}
