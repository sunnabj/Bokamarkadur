
package is.hi.hbv501.bokamarkadur.bokamarkadur.Entities;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "MESSAGES")
public class Message {
    /*
     * Each entity class represents a table in the database.
     * This entity describes the Messages between users.
     * Message entity has id, messageBody, User: sender, User: receiver,
     * and the book in question.
     */
    @Id
    //Automatic creation of ID values
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public String messageBody;

    @ManyToOne(targetEntity=User.class)
    public User sender;

    @ManyToOne(targetEntity=User.class)
    public User receiver;

    @ManyToOne(targetEntity=Book.class)
    public Book book;


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

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Book getBook() { return book; }

    public void setBook(Book book) { this.book = book; }

    public Message(long id, String messageBody, User sender, User receiver, Book book) {
        this.id = id;
        this.messageBody = messageBody;
        this.sender = sender;
        this.receiver = receiver;
        this.book = book;
    }

    @Override
    public String toString() {
        return this.messageBody;
    }

}
