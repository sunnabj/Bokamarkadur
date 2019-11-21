
package is.hi.hbv501.bokamarkadur.bokamarkadur.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MESSAGES")
public class Message {

    @Id
    //Býr sjálfkrafa til ID gildi
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public String body;

    @ManyToOne(targetEntity=User.class)
    public User sender;

    @ManyToOne(targetEntity=User.class)
    public User receiver;

    @ManyToOne(targetEntity=Book.class)
    public Book book;


    //(targetEntity=Book.class, mappedBy = "user", fetch=FetchType.EAGER)
    //@OneToMany(mappedBy="message")
    //private List<Message> messages = new ArrayList<>();

    public Message() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() { return receiver; }

   public void setReceiver(User receiver) { this.receiver = receiver; }

    public String getMessage() {
        return body;
    }

    public void setMessage(String message) {
        this.body = message;
    }

    public Book getBook() { return book; }

    public void setBook(Book book) { this.book = book; }

    public Message(long id, String body, User sender, User receiver, Book book) {
        this.id = id;
        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
        this.book = book;
    }

}
