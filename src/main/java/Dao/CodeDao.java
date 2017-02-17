package Dao;

import Model.Code;
import Model.Langage;
import Model.Tag;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rim on 30/01/17.
 */
public class CodeDao {

    EntityManager em;
    EMFactory emf;

    public CodeDao(){
        this.emf = new EMFactory();
        try {
            this.em = this.emf.getEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Code> getAll() throws Exception
    {
        try
        {
            List<Code> codes = em.createQuery("SELECT c FROM Code c", Code.class).getResultList();
            return codes;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public Code getById(int id) throws Exception{
        try{
            Code code;
            code = em.find(Code.class, id);
            return code;
        }catch(Exception e)
        {
            throw e;
        }
    }

    public Code getByTags(List<String> tags) throws Exception {
        try {
            Code code;
            code = (Code) em.createQuery("SELECT c FROM Code c WHERE c.tags.tag IN ?1")
                    .setParameter(1, tags)
                    .getSingleResult();
            return code;
        } catch (Exception e) {
            throw e;
        }
    }


    public void add(Code c, Langage l, List<Tag> tags)throws Exception{
        try{
            //check if language exists?setlangage:create and set langage
            //check tags
            c.setLangage(l);
            c.setTags(tags);
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        }catch(Exception e)
        {
            throw e;
        }
    }

    /*

    public void update(Code c)throws Exception{
        try{
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            throw e;
        }
    }


    public void delete(int id)throws Exception{
        try{
            Code code;
            code = em.find(Code.class, id);
            em.getTransaction().begin();
            em.remove(code);
            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            throw e;
        }

    }
    */

}
