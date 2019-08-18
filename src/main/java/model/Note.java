package model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "note")
@Component
public class Note implements Validator {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 100)
    private String title;
    @Size(max = 500)
    private String content;
    private Integer typeId;
    private String number;

    public Note() {
    }

    public Note(@Size(max = 100) String title) {
        this.title = title;
    }

    public Note(@Size(max = 100) String title, @Size(max = 500) String content) {
        this.title = title;
        this.content = content;
    }

    public Note(@Size(max = 100) String title, @Size(max = 500) String content, Integer typeId) {
        this.title = title;
        this.content = content;
        this.typeId = typeId;
    }

    public Note(@Size(max = 100) String title, @Size(max = 500) String content, Integer typeId, String number) {
        this.title = title;
        this.content = content;
        this.typeId = typeId;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Note.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Note note = (Note) target;
        String number = note.getNumber();
        ValidationUtils.rejectIfEmpty(errors, "number", "number.empty");
        if (number.length() != 10){
            errors.rejectValue("number", "number.length");
        }
        if (!number.startsWith("0")){
            errors.rejectValue("number", "number.startsWith");
        }
        if (!number.matches("(^$|[0-9]*$)")){
            errors.rejectValue("number", "number.matches");
        }
    }
}
