package is.hi.hbv501.bokamarkadur.bokamarkadur.Entities;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private Boolean forSale;
    private Boolean requested;

    @ElementCollection(targetClass= Subjects.class)
    @Column(name="subject", nullable=false)
    @CollectionTable(name="book_subjects", joinColumns= {@JoinColumn(name="book_id")})
    public Set<Subjects> subjects;

    @OneToMany(mappedBy = "book")
    private List<RentalLog> rentals = new ArrayList<>();

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

    public Set<Subjects> getSubjects() { return subjects; }

    public Integer getPrice() {
        return price;
    }

    public Boolean getForSale() { return forSale; }

    public Boolean getRequested() { return requested; }

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

    public void setSubjects(Set<Subjects> subjects) { this.subjects = subjects; }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Book setForSale(Boolean forSale) { this.forSale = forSale; return this; }

    public Book setRequested(Boolean requested) { this.requested = requested; return this; }

    public Book(long id, String title, String author, Integer edition, String condition, Set<Subjects> subjects, Integer price, Boolean forSale, Boolean requested) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.condition = condition;
        this.subjects = subjects;
        this.price = price;
        this.forSale = forSale;
        this.requested = requested;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
