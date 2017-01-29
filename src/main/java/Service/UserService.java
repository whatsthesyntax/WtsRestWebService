package Service;

/**
 * Created by rim on 15/01/17.
 */
import java.util.ArrayList;
import java.util.List;
import Model.User;
import Dao.UserDao;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;


@Path("/users")
@PermitAll
public class UserService {

    UserDao dao;

    public UserService() {
        this.dao = new UserDao();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Administrateur")
    public List<User> getAllUsers(@Context SecurityContext sc)
    {
        List<User> users = null;
        try
        {
            users = dao.getAll();
            return users;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("no users");
        return users;
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") int id){
        try {
            return dao.get(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void addUser(User user)
    {
       try {
        dao.add(user);
       }catch (Exception e) {
            e.printStackTrace();
       }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void updateUser(User user)
    {
        try {
            dao.update(user);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUser(@PathParam("id") int id)
    {
        try {
            dao.delete(id);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
