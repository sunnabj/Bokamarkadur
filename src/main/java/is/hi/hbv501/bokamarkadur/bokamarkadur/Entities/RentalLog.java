package is.hi.hbv501.bokamarkadur.bokamarkadur.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class RentalLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Þetta er þetta mappedByBook
    @ManyToOne
    private Book book;

    //Þetta er það sem mappedByUser vísar í
    @ManyToOne
    private User user;

    public Date fromdate;
    public Date todate;

    public RentalLog() {
    }

    public Date getFromdate() {
        return fromdate;
    }

    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    public Date getToDate() {
        return todate;
    }

    public void setToDate(Date todate) {
        this.todate = todate;
    }


    public RentalLog(Book book, User user, Date fromdate, Date todate) {
        this.book = book;
        this.user = user;
        this.fromdate = fromdate;
        this.todate = todate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getMovie() {
        return book;
    }

    public void setMovie(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
