package Dao;

import Model.User;
import Util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by rim on 15/01/17.
 */
public class UserDao {

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
}
