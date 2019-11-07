package is.hi.hbv501.bokamarkadur.bokamarkadur.Entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Each entity class represents a table in the database.
 * This one describes the users of the book exchange market.
 * Each one has a name, username, password, email and will
 * be able to insert information about himself, as well as
 * put books up for sale or request books through the site.
 */

@Entity
@Table(name = "USERS")
public class User {

    @Id
    // Automatic generation of ID values
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public String name;
    public String username;
    public String email;
    public String password;
    public String info;

    // One user can have many books in the database
    //@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    @OneToMany(targetEntity=Book.class,mappedBy="user",cascade={CascadeType.ALL},orphanRemoval=true)
    private List<Book> books = new ArrayList<>();

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

    public List<Book> getBooks() { return books; }

    public void setBooks(List<Book> books) { this.books = books; }


    public User(long id, String name, String username, String password, String info,
                String email, List<Book> books) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.info = info;
        this.email = email;
        this.books = books;
    }

    @Override
    public String toString() {
        return this.username;
    }

}
