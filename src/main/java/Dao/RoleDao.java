package Dao;

import Model.Role;
import Model.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;

/**
 * Created by rim on 18/01/17.
 */
public class RoleDao {

    EntityManager em;
    EMFactory emf;

    public RoleDao(){
        this.emf = new EMFactory();
        try {
            this.em = this.emf.getEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Role get(int id) throws Exception{
        try{
            Role role;
            role = em.find(Role.class, id);
            return role;
        }catch(Exception e)
        {
            throw e;
        }
    }

}
