package Service;

import Model.Code;
import org.hibernate.annotations.Parameter;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ParamConverter;
import java.util.List;

/**
 * Created by rim on 03/03/17.
 */

@Path("/search")
@PermitAll
public class SearchService {

    @GET
    @Path("/{keys}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Code> search(@PathParam("keys") String keys){


        return null;
    }



}
