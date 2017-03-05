package Dao;

import Model.Code;
import Model.Langage;
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
            user = (User) em.createQuery("SELECT u FROM User u WHERE u.name = ?1")
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

    public List<Code> getCodes(int id)throws Exception{
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

    public List<Langage> getLangages(int id)throws Exception{
        try{
            List<Langage> langages = new ArrayList<Langage>();
            List<Code> codes = getCodes(id);
            for(Code c : codes){
                if(!langages.contains(c.getLangage())){
                    langages.add(c.getLangage());
                }
            }
            return langages;
        }catch(Exception e){
            throw e;
        }
    }

}
