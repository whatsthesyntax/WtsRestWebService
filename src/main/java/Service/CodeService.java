package Service;

import Dao.CodeDao;
import Model.Code;
import Model.Langage;
import Model.Tag;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.lang.reflect.Type;
import java.util.List;

import Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.*;

/**
 * Created by rim on 03/02/17.
 */

@Path("/codes")
@PermitAll
public class CodeService {

    CodeDao dao;

    public CodeService(){
        this.dao = new CodeDao();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Code> getAllCodes(@Context SecurityContext sc)
    {
        List<Code> codes = null;
        try
        {
            codes = dao.getAll();
            return codes;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("no codes");
        return codes;
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Code getCode(@PathParam("id") int id){
        try {
            return dao.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addCode(String str)
    {
        try {
            Gson gson = new GsonBuilder().create();
            JSONObject jsonObject = new JSONObject(str);
            Code c = gson.fromJson(jsonObject.getJSONObject("code").toString(), Code.class);
            Langage l = gson.fromJson(jsonObject.getJSONObject("langage").toString(), Langage.class);
            Type tagsType = new TypeToken<List<Tag>>(){}.getType();
            List<Tag> tags = gson.fromJson(jsonObject.getJSONArray("tags").toString(), tagsType);
            dao.add(c,l,tags);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
