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



    /* autre config


    DbConnection database;

    public UserDao(){
        this.database = new DbConnection();
    }


    public ArrayList<User> getAll() throws Exception
    {
        ArrayList<User> dataFeed = new ArrayList<User>();
        try
        {
            Connection connection = database.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                dataFeed.add(user);
            }
            connection.close();
            return dataFeed;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public User get(int id) throws Exception{
        try{
            Connection connection = database.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE user_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            connection.close();
            return user;
        }catch(Exception e)
        {
            throw e;
        }
    }

    public void add(User u)throws Exception{
        try{
            String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, u.getUsername());
            statement.setString(2, u.getEmail());
            statement.setString(3, u.getPassword());
            int rowsInserted = statement.executeUpdate();
            connection.close();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        }catch(Exception e)
        {
            throw e;
        }
    }

    public void update(User u)throws Exception{
        try{
            String sql = "UPDATE users SET username=?, email=?, password=? WHERE user_id=?";
            Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, u.getUsername());
            statement.setString(2, u.getEmail());
            statement.setString(3, u.getPassword());
            statement.setInt(4, u.getId());
            int rowsUpdated = statement.executeUpdate();
            connection.close();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public void delete(int id)throws Exception{
        try{
            String sql = "DELETE FROM users WHERE user_id=?";
            Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            connection.close();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
        }
        catch(Exception e)
        {
            throw e;
        }

    }
    */
}
