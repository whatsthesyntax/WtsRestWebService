package Service;

import Dao.LangageDao;
import Model.Langage;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by rim on 06/03/17.
 */
@Path("/langages")
@PermitAll
public class LangageService {

    LangageDao dao;

    public LangageService(){
        this.dao = new LangageDao();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Langage> getAllLangages(){
        List<Langage> langages = null;
        try
        {
            langages = dao.getAll();
            return langages;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("no languages");
        return langages;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addLangage(Langage l){
        try {
            dao.add(l);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



}
