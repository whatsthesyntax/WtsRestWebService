package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Created by rim on 17/01/17.
 */
public class EMFactory {

    protected EntityManager getEntityManager() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WTS");
        return emf.createEntityManager();
    }

}
