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
@Table(name = "tags", schema = "mydb")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="tagId")
public class Tag implements Serializable{
    private int tagId;
    private String tag;
    private List<Code> codes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tagId")
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "tag")
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @ManyToMany
    @JoinTable(name = "codeTags",
            joinColumns = { @JoinColumn(name = "tagId") },
            inverseJoinColumns = { @JoinColumn(name = "codeId") })
    public List<Code> getCodes()
    {
        return codes;
    }
    public void setCodes(List<Code> codes)
    {
        this.codes = codes;
    }
}
