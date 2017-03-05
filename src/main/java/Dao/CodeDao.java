package Dao;

import Model.Code;
import Model.Langage;
import Model.Tag;
import Model.User;
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

    public List<Code> getByTags(List<String> tags) throws Exception {
        try {
            List<Code> codes;
            codes = (List<Code>) em.createQuery("SELECT c FROM Code c join c.tags t where t.tag in ?1 " +
                    " order by c.codeId")
                    .setParameter(1, tags).getResultList();
            return codes;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Code> getByLanguage(String l) throws Exception {
        try {
            List<Code> codes;
            codes = (List<Code>) em.createQuery("SELECT c FROM Code c join Langage l where l.langage =?1 " +
                    "order by c.codeId")
                    .setParameter(1, l).getResultList();
            return codes;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Code> getByUser(int id) throws Exception{
        try{
            List<Code> codes;
            codes = (List<Code>) em.createQuery("SELECT c FROM Code c join c.user u where u.userId= ?1 " +
                    "order by c.codeId")
                    .setParameter(1, id).getResultList();
            return codes;
        }catch(Exception e){
            throw e;
        }
    }

    public void add(Code c, Langage l, List<Tag> tags, User u)throws Exception{
        try{
            c.setLangage(l);
            c.setTags(tags);
            if(u != null)
                c.setUser(u);
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        }catch(Exception e)
        {
            throw e;
        }
    }

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
            System.out.println("---------------- CODE DELETED -----------------");
        }
        catch(Exception e)
        {
            throw e;
        }
    }

}
