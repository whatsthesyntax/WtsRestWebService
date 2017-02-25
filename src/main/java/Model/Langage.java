package Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

/**
 * Created by rim on 30/01/17.
 */
@Entity
@Table(name = "langages", schema = "mydb")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="langageId")
public class Langage {
    private int langageId;
    private String langage;
    private List<Code> codes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "langageId")
    public int getLangageId() {
        return langageId;
    }

    public void setLangageId(int langageId) {
        this.langageId = langageId;
    }

    @Basic
    @Column(name = "langage")
    public String getLangage() {
        return langage;
    }
    public void setLangage(String langage) {
        this.langage = langage;
    }

   @OneToMany(mappedBy = "langage")
   @JsonIgnore
    public List<Code> getCodes(){
        return codes;
   }
   public void setCodes(List<Code> codes){
        this.codes = codes;
   }
}
