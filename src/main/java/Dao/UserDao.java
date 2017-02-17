package Dao;

import Model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import Model.Role;
/**
 * Created by rim on 15/01/17.
 */

public class UserDao {

    EntityManager em;
    EMFactory emf;
    List<Role> roles;
    RoleDao rDao;

    public UserDao(){
        rDao = new RoleDao();
        this.emf = new EMFactory();
        try {
            this.roles = new ArrayList<Role>();
            this.roles.add(rDao.get(1)); // default role 1 : utilisateur
            this.em = this.emf.getEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() throws Exception
    {
        try
        {
            List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
            return users;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public User get(int id) throws Exception{
        try{
            User user;
            user = em.find(User.class, id);
            return user;
        }catch(Exception e)
        {
            throw e;
        }
    }

    public User findUser(String username)throws Exception{
        try{
            User user;
            user = (User) em.createQuery("SELECT u FROM User u WHERE u.username = ?1")
                    .setParameter(1, username)
                    .getSingleResult();
            return user;
        }catch(Exception e)
        {
            throw e;
        }
    }

    public void add(User u)throws Exception{
        try{
            u.setRoles(roles);
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
        }catch(Exception e)
        {
            throw e;
        }
    }


    public void update(User u)throws Exception{
        try{
            em.getTransaction().begin();
            em.merge(u);
            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            throw e;
        }
    }


    public void delete(int id)throws Exception{
        try{
            User user;
            user = em.find(User.class, id);
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            throw e;
        }

    }

}
