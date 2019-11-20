
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


    public User sender;
    public User receiver;
    public Book book;
    public String message;




    //(targetEntity=Book.class, mappedBy = "user", fetch=FetchType.EAGER)
    @OneToMany(mappedBy="message")
    private List<Message> messages = new ArrayList<>();

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

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<Message> getMessages() { return messages; }


    public Message(long id, String message, User sender, User receiver, Book book) {
        this.id = id;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.book = book;
    }

}
