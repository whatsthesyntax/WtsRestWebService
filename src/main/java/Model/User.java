package Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * Created by rim on 17/01/17.
 */
@Entity
@Table(name = "users", schema = "mydb")
public class User implements Serializable {

    private int userId;
    private String username;
    private String email;
    private String password;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    public int getUserId() {
        return userId;
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



    public void setUserId(int userId) {
        this.userId = userId;
    }


    public User(){

    }

}
