package is.hi.hbv501.bokamarkadur.bokamarkadur.Entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Book {

    //ID, title, author, edition, condition og price
    // Ekki fullbúið, vantar enn gildi fyrir book. Sjá Verkefnisplan

    @Id
    //Býr sjálfkrafa til ID gildi
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String author;
    private Integer edition; //Eða strengur? //SB: Gæti verið sniðugt, þá er hægt að skrifa 5th t.d.
    private String condition;
    private Integer price;
    private String status;

    @ManyToOne
    private User user;

    public Subjects subjects;

    //Mikilvægt að hafa tóman smið fyrir entity-ið okkar.
    // Þarf alltaf að vera svo, til að JPA geti búið til tilvik af þessum klösum.
    public Book() {

    }

    //Allt hér fyrir neðan búið til með alt-insert

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

    public void setStatus(String status) { this.status = status; }

    public void setUser(User user) { this.user = user; }

    public Book(long id, String title, String author, Integer edition, String condition,
                Subjects subjects, Integer price, String status, User user) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.condition = condition;
        this.subjects = subjects;
        this.price = price;
        this.status = status;
        this.user = user;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
