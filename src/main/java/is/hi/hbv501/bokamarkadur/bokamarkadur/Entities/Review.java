package is.hi.hbv501.bokamarkadur.bokamarkadur.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name ="REVIEWS")
public class Review {






    @Id
    //Automatic generation of ID values.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity=User.class)
    @JsonIgnoreProperties("writtenReviews")
    private User reviewer;

    @ManyToOne(targetEntity=User.class)
    @JsonIgnoreProperties("receivedReviews")
    private User user;

    private String reviewBody;

    public Review() {

    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }

    public Review(long id, User reviewer, User user, String reviewBody) {

        this.id = id;
        this.reviewer = reviewer;
        this.user = user;
        this.reviewBody = reviewBody;
    }

    @Override
    public String toString() {
        return this.reviewBody;
    }

}
