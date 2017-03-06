package Service;

import Dao.TagDao;
import Model.Tag;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by rim on 06/03/17.
 */

@Path("tags")
@PermitAll
public class TagService {

    TagDao dao;

    public TagService(){
        this.dao = new TagDao();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> getAllTags(){
        List<Tag> tags = null;
        try
        {
            tags = dao.getAll();
            return tags;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("no tags");
        return tags;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addTag(Tag t){
        try {
            dao.add(t);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
