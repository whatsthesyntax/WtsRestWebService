package Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by rim on 17/01/17.
 */
@Entity
@Table(name = "roles", schema = "mydb")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="roleId")
public class Role {

    private int roleId;
    private String role;
    private List<User> users;

    @Id
    @Column(name = "roleId")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @ManyToMany
    @JoinTable(name = "userRoles",
            joinColumns = { @JoinColumn(name = "roleId") },
            inverseJoinColumns = { @JoinColumn(name = "userId") })
    //@JsonIgnore
    public List<User> getUsers()
    {
        return users;
    }
    public void setUsers(List<User> users)
    {
        this.users = users;
    }

    public Role(){

    }


}
