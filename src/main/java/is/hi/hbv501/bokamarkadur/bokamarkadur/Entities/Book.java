package is.hi.hbv501.bokamarkadur.bokamarkadur.Entities;

import javax.persistence.*;

@Entity
public class Book {

    /*
     * Each entity class represents a table in the database.
     * This one describes the books available in the exchange market.
     * Each one has a few attributes, such as title and price.
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
    private String image;

    // Many books can belong to each user.
    @ManyToOne(targetEntity=User.class)//(cascade=CascadeType.PERSIST)
    @JoinColumn(name="user_username")
    private User user;

    public Subjects subjects;

    // Entity constructors must be empty.
    public Book() {

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

    public void setImage(String image) { this.image = image; }

    public void setStatus(String status) { this.status = status; }

    public void setUser(User user) { this.user = user; }

    public Book(long id, String title, String author, Integer edition, String condition,
                Subjects subjects, Integer price, String image, String status, User user) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.condition = condition;
        this.subjects = subjects;
        this.price = price;
        this.image = image;
        this.status = status;
        this.user = user;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
