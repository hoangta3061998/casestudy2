package model;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {
    private Integer id;
    @Pattern(regexp = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$" , message = "Email not valid")
    private String email;
    @Size(min = 8, max = 10)
    private String password;
    private MultipartFile avatar;
    private String image;

    public UserForm() {
    }

    public UserForm(@Pattern(regexp = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$", message = "Email not valid") String email, @Size(min = 8, max = 10) String password) {
        this.email = email;
        this.password = password;
    }

    public UserForm(Integer id, @Pattern(regexp = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$", message = "Email not valid") String email, @Size(min = 8, max = 10) String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UserForm(Integer id, @Pattern(regexp = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$", message = "Email not valid") String email, @Size(min = 8, max = 10) String password, String image) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public UserForm(Integer id, @Pattern(regexp = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$", message = "Email not valid") String email, @Size(min = 8, max = 10) String password, MultipartFile avatar) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
