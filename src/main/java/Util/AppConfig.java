package Util;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 * Created by rim on 24/01/17.
 */
public class AppConfig extends ResourceConfig {

    public AppConfig() {
        register(RolesAllowedDynamicFeature.class);
    }

}
