package is.hi.hbv501.bokamarkadur.bokamarkadur.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

    //ID, titill, description og  ratings

    //Bókamarkaður þarf m.a.:
    //ID, title, author, description, price

    @Id
    //Býr sjálfkrafa til ID gildi
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    private Double rating;

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

    public String getDescription() {
        return description;
    }

    public Double getRating() {
        return rating;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Book(long id, String title, String description, Double rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
    }
}
