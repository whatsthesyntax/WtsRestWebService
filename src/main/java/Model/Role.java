package Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
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
