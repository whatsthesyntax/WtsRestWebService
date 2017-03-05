package Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by rim on 30/01/17.
 */
@Entity
@Table(name = "codes", schema = "mydb")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="codeId")
public class Code implements Serializable{
    private int codeId;
    private String code;
    private String description;
    private List<Tag> tags;
    private Langage langage;
    private User user;
    private Boolean visible;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codeID")
    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name="visible")
    public Boolean getVisible(){
        return this.visible;
    }
    public void setVisible(Boolean v){
        this.visible = v;
    }


    @ManyToMany
    @JoinTable(name = "codeTags",
            joinColumns = { @JoinColumn(name = "codeId", referencedColumnName = "codeId") },
            inverseJoinColumns = { @JoinColumn(name = "tagId", referencedColumnName = "tagId") })
    public List<Tag> getTags()
    {
        return tags;
    }
    public void setTags(List<Tag> tags)
    {
        this.tags = tags;
    }

    @ManyToOne
    @JoinColumn(name="langageId",referencedColumnName="langageId")
    public Langage getLangage(){
        return langage;
    }
    public void setLangage(Langage l){
        this.langage = l;
    }

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName="userId")
    public User getUser() { return user;}
    public void setUser(User u) {this.user = u;}


}
