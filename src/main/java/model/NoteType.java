package model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "note_type")
public class NoteType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 100)
    private String name;
    @Size(max = 200)
    private String description;

    public NoteType() {
    }

    public NoteType(@Size(max = 100) String name, @Size(max = 200) String description) {
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
