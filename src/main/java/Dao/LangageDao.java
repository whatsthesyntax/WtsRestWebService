package Dao;

import Model.Langage;

import javax.persistence.EntityManager;

/**
 * Created by rim on 03/02/17.
 */
public class LangageDao {


    EntityManager em;
    EMFactory emf;

    public LangageDao(){
        this.emf = new EMFactory();
        try {
            this.em = this.emf.getEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Langage getById(int id) throws Exception{
        try{
            Langage langage;
            langage = em.find(Langage.class, id);
            return langage;
        }catch(Exception e)
        {
            throw e;
        }
    }

    public Langage getByName(String lang) throws Exception{
        try{
            Langage langage;
            langage = (Langage)em.createQuery("SELECT l FROM Langage l WHERE l.langage = ?1")
                    .setParameter(1, lang)
                    .getSingleResult();
            return langage;
        }catch(Exception e){
            return null;
        }
    }

    public void add(Langage l)throws Exception{
        try{
            em.getTransaction().begin();
            em.persist(l);
            em.getTransaction().commit();
        }catch(Exception e)
        {
            throw e;
        }
    }

}
