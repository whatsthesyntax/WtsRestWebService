package Dao;

import Model.Tag;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by rim on 13/02/17.
 */
public class TagDao {
    EntityManager em;
    EMFactory emf;

    public TagDao(){
        this.emf = new EMFactory();
        try {
            this.em = this.emf.getEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Tag> getAll() throws Exception
    {
        try
        {
            List<Tag> codes = em.createQuery("SELECT t FROM Tag t", Tag.class).getResultList();
            return codes;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public Tag getById(int id) throws Exception{
        try{
            Tag tag;
            tag = em.find(Tag.class, id);
            return tag;
        }catch(Exception e)
        {
            throw e;
        }
    }
    public Tag getByName(String tagname) throws Exception{
        try{
            Tag tag;
            tag = (Tag)em.createQuery("SELECT t FROM Tag t WHERE t.tag = ?1")
                    .setParameter(1, tagname)
                    .getSingleResult();
            return tag;
        }catch(Exception e){
            return null;
        }
    }

    public void add(Tag t)throws Exception{
        try{
            em.getTransaction().begin();
            em.persist(t);
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
