package is.hi.hbv501.bokamarkadur.bokamarkadur.Entities;

import javax.persistence.Entity;

public class Bookmap {

    private long userID;
    private long bookID;

    public Bookmap() {

    }


    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }
}
