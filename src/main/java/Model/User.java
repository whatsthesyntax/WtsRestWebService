package Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

import java.security.Principal;
import java.util.List;

/**
 * Created by rim on 17/01/17.
 */
@Entity
@Table(name = "users", schema = "mydb")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="userId")
public class User implements Serializable, Principal {

    private int userId;
    private String username;
    private String email;
    private String password;
    private List<Role> roles;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(mappedBy = "users")
    //@JsonIgnore
    public List<Role> getRoles()
    {
        return roles;
    }
    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }


    public String getName() {
        return this.username;
    }

    public boolean hasRole(String role){
        for (Role r: this.roles) {
            if(r.getRole().equals(role))
                return true;
        }
        return false;
    }

    public User(){

    }



}
