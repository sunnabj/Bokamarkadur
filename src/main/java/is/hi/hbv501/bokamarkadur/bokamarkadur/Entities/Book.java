package is.hi.hbv501.bokamarkadur.bokamarkadur.Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Book {

    /*
     * Each entity class represents a table in the database.
     * This entity describes the books available in the exchange market.
     * Book has id, title, author, edition, condition,
                subjects, price, image, status, date,
                user and list of messages.
     */
    @Id
    //Automatic generation of ID values.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String author;
    private Integer edition;
    private String condition;
    private Integer price;
    private String image;
    private String status;
    private Date date;

    // Many books can belong to each user.
    @ManyToOne(targetEntity=User.class)//(cascade=CascadeType.PERSIST)
    @JsonIgnoreProperties("books")
    @JoinColumn(name="user_username")
    private User user;

    // If a book is deleted, it's associated messages are deleted as well.
    @OneToMany(targetEntity=Message.class, mappedBy="book", cascade=CascadeType.ALL)
    private List<Message> messages;

    public Subjects subjects;

    // Entity constructors must be empty.
    public Book() {

    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getEdition() {
        return edition;
    }

    public String getCondition() {
        return condition;
    }

    public Subjects getSubjects() {return subjects; }

    public Integer getPrice() {
        return price;
    }

    public String getImage() { return image; }

    public String getStatus() { return status; }

    public Date getDate() { return date; }

    public User getUser() { return user; }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setSubjects(Subjects subjects) { this.subjects = subjects; }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setImage(String image) { this.image = image; }

    public void setStatus(String status) { this.status = status; }

    public void setDate(Date date) { this.date = date; }

    public void setUser(User user) { this.user = user; }

    public Book(long id, String title, String author, Integer edition, String condition,
                Subjects subjects, Integer price, String image, String status, Date date,
                User user, List<Message> messages) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.condition = condition;
        this.subjects = subjects;
        this.price = price;
        this.image = image;
        this.status = status;
        this.date = date;
        this.user = user;
        this.messages = messages;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
