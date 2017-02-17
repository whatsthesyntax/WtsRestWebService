package Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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

    @ManyToMany(mappedBy = "codes")
    public List<Tag> getTags()
    {
        return tags;
    }
    public void setTags(List<Tag> tags)
    {
        this.tags = tags;
    }

    @ManyToOne
    public Langage getLangage(){
        return langage;
    }
    public void setLangage(Langage l){
        this.langage = l;
    }

}
