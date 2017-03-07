package Service;

import Dao.CodeDao;
import Dao.LangageDao;
import Dao.TagDao;
import Model.Code;
import Model.Langage;
import Model.Tag;
import org.hibernate.annotations.Parameter;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ParamConverter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rim on 03/03/17.
 */

@Path("/search")
@PermitAll
public class SearchService {

    CodeDao dao;
    LangageDao ldao;
    TagDao tdao;
    List<Langage> langages;
    List<Tag> tags;

    public SearchService() throws Exception{
        this.dao = new CodeDao();
        this.ldao = new LangageDao();
        this.tdao = new TagDao();
        langages = ldao.getAll();
        tags = tdao.getAll();
    }


    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Code> search(String keys) throws Exception{
        String[] kArray = keys.split("\\s+");
        String lang = "";
        List<String> tArray = new ArrayList<>();
        for(int i=0; i<kArray.length; i++){
            for(Langage l : langages){
                if(l.getLangage().equals(kArray[i])){
                    lang = kArray[i];
                    break;
                }
            }
            for(Tag t : tags){
                if(t.getTag().equals(kArray[i])){
                    tArray.add(kArray[i]);
                }
            }
        }
        if(lang.equals("") && tArray.size()>0)
            return dao.getByTags(tArray);
        if(!lang.equals("") && tArray.size()==0)
            return dao.getByLanguage(lang);
        if(!lang.equals("") && tArray.size()>0)
            return dao.getByTagsAndLanguage(tArray, lang);
        return null;
    }



}
