package Service;

import Dao.CodeDao;
import Dao.LangageDao;
import Dao.TagDao;
import Dao.UserDao;
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
import java.util.ArrayList;
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
    LangageDao ldao;
    TagDao tdao;
    UserDao udao;

    public CodeService(){
        this.dao = new CodeDao();
        this.ldao = new LangageDao();
        this.tdao = new TagDao();
        this.udao = new UserDao();
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
    @Path("/validate/{id}")
    public boolean validateCode(@PathParam("id") int id){
        try{
            Code c = dao.getById(id);
            if(c!=null){
                c.setValide(true);
                dao.update(c);
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @POST
    @Path("/show/{id}")
    public boolean showCode(@PathParam("id") int id){
        try{
            Code c = dao.getById(id);
            if(c!=null){
                c.setVisible(true);
                dao.update(c);
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @POST
    @Path("/hide/{id}")
    public boolean hideCode(@PathParam("id") int id){
        try{
            Code c = dao.getById(id);
            if(c!=null){
                c.setVisible(false);
                dao.update(c);
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }



    @POST
    @Path("/tags")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Code> getCodeByTags(String str){
        try {
            List<String> tags = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jarray = jsonObject.getJSONArray("taglist");
            for(int i = 0 ; i < jarray.length(); i++){
                tags.add(jarray.getJSONObject(i).getString("tag"));
            }
            return dao.getByTags(tags);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/language/{lang}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Code> getCodeByLanguage(@PathParam("lang") String lang){
        try {
            return dao.getByLanguage(lang);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Code> getCodeByUser(@PathParam("id") int id){
        try {
            return dao.getByUser(id);
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

            //check existing langages
            String langage = gson.fromJson(jsonObject.getJSONObject("langage").getString("langage"), String.class);
            Langage l = ldao.getByName(langage);
            if(l == null) {
                //create new langage
                l = gson.fromJson(jsonObject.getJSONObject("langage").toString(), Langage.class);
                ldao.add(l);
            }

            //check existings tags
            List<Tag> tags = new ArrayList<>();
            JSONArray jarray = jsonObject.getJSONArray("tags");
            for(int i = 0 ; i < jarray.length(); i++){
                Tag t = tdao.getByName(jarray.getJSONObject(i).getString("tag"));
                if(t == null){
                    t = new Tag();
                    t.setTag(jarray.getJSONObject(i).getString("tag"));
                    tdao.add(t);
                }
                tags.add(t);
            }

            //set user
            if(jsonObject.has("userid")){
                int id = Integer.parseInt(jsonObject.getString("userid"));
                User u = udao.get(id);
                boolean v = true;
                if(jsonObject.has("visible")){
                    v = jsonObject.getBoolean("visible");
                }
                dao.add(c,l,tags,u,v);
            }else{
                dao.add(c,l,tags,null, true);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void updateCode(String str)
    {
        try {
            Gson gson = new GsonBuilder().create();
            JSONObject jsonObject = new JSONObject(str);
            Code c = gson.fromJson(jsonObject.toString(), Code.class);

            //check existing langages
            String langage = gson.fromJson(jsonObject.getJSONObject("langage").getString("langage"), String.class);
            Langage l = ldao.getByName(langage);
            if(l == null) {
                //create new langage
                l = gson.fromJson(jsonObject.getJSONObject("langage").toString(), Langage.class);
                ldao.add(l);
            }

            //check existings tags
            List<Tag> tags = new ArrayList<>();
            JSONArray jarray = jsonObject.getJSONArray("tags");
            for(int i = 0 ; i < jarray.length(); i++){
                Tag t = tdao.getByName(jarray.getJSONObject(i).getString("tag"));
                if(t == null){
                    t = new Tag();
                    t.setTag(jarray.getJSONObject(i).getString("tag"));
                    tdao.add(t);
                }
                tags.add(t);
            }

            //set visible
            boolean vi = true;
            if(jsonObject.has("visible")){
               vi = jsonObject.getBoolean("visible");
            }

            //set valide
            boolean va = true;
            if(jsonObject.has("valide")){
                va = jsonObject.getBoolean("valide");
            }

            c.setLangage(l);
            c.setValide(va);
            c.setTags(tags);
            c.setVisible(vi);

            dao.update(c);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteCode(@PathParam("id") int id)
    {
        try {
            dao.delete(id);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
