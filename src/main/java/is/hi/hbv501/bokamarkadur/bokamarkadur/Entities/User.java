package is.hi.hbv501.bokamarkadur.bokamarkadur.Entities;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "USERS")
public class User {

    /*
     * Each entity class represents a table in the database.
     * This one describes the users of the book exchange market.
     * Each one has a name, username, password, email and will
     * be able to insert information about himself, as well as
     * put books up for sale or request books through the site.
     */

    @Id
    // Automatic generation of ID values
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    public String info;

    @Size(min = 2, message = "Length should be in at least 2 digits")
    public String name;

    @Size(min = 2, message = "Length should be at least 2 digits")
    public String username;


    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email pattern")
    private String email;

    @Size(min = 8, message = "Length should be at least 8 digits")
    public String password;

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    @Transient
    public String retypePassword ;


    // One user can have many books in the database
    @OneToMany(targetEntity=Book.class,mappedBy="user",cascade={CascadeType.ALL},orphanRemoval=true)
    private List<Book> books = new ArrayList<>();

    @OneToMany(targetEntity=Message.class, mappedBy="receiver")
    private List<Message> receivedMessages = new ArrayList<>();

    @OneToMany(targetEntity=Message.class, mappedBy="sender")
    private List<Message> sentMessages = new ArrayList<>();

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

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<Message> received) {
        this.receivedMessages = received;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sent) {
        this.sentMessages = sent;
    }


    public User(long id, String name, String username, String password, String retypePassword, String info,
                String email, List<Book> books, List<Message> sentMessages, List<Message> receivedMessages) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.info = info;
        this.email = email;
        this.books = books;
        this.sentMessages = sentMessages;
        this.receivedMessages = receivedMessages;
        this.retypePassword = retypePassword;
    }

    @Override
    public String toString() {
        return this.username;
    }



}
