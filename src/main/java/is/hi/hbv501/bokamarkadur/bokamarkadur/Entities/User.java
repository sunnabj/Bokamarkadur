package is.hi.hbv501.bokamarkadur.bokamarkadur.Entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {

    @Id
    //Býr sjálfkrafa til ID gildi
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public String name;
    public String username;
    public String email;
    public String password;
    public String info;
    //logged in boolean?

    @OneToMany(mappedBy = "user")
    private List<RentalLog> rentals = new ArrayList<>();

    public User() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {this.email = email; }

    public User(long id, String name, String username, String password, String info, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.info = info;
        this.email = email;
    }

}
