package domain;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ShaoJ
 * Date: 2020/7/10
 * Time: 10:30
 */
public class User {

    final String DEFAULTPHTO="/static/images/default.jpg";

    private String name;
    private String password;
    private String email;
    private String img=DEFAULTPHTO;
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public User() {
    }

    public User(String name, String password, String email,String img) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.img=img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "User{" +
                "DEFAULTPHTO='" + DEFAULTPHTO + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
