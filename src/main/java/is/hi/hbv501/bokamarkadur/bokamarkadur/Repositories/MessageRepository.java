
package is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Message;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // JPA repository handles the methods 'save' and 'delete',
    // and they are constructed there.

    //Store a new message.
    Message save(Message message);

    // Method for deleting messages.
    void delete(Message message);
/*
    //Hægt að gera @Query("SELECT o.s.frv. - okkar eigin SQL kóði")
    // The Method for finding messages between users
    // as we had in our Design Model was like this:
    Message findMessage(User sender, User receiver);
*/

    // I suggest needed changes to this. Messages between users are
    // regarding a specific book (not just a general chat about whatsoever)
    // and same users could be having separate conversationns about two (or more)
    // different books at a time. So the method from the Design Model needs at least
    // additionally another parameter, 'Book book', then we have messages regarding
    // a specific book between some two users.
    // I am not sure if it is needed to have a method for individual messages and then another
    // list-method that is for showing the whole conversation (list of messages).
    // But for now I add on to the original method and have another method, 'findConversation',
    // to return the conversation as whole between the two users, that is all messages between
    // the two users (sender and receiver) relevant to the book in question.
    //Message findMessage(User sender, User receiver, Book book);

    // Returns the list of messages between the two users (i.e.
    // it shows the conversation between them regarding a specific book).
    //List<Message> findConversation(User sender, User receiver, Book book);


    // Not sure if we would ever want or need to get allMessages, but to be save it is here.
    List<Message> findAll();

    // Not sure if we would ever want or need to find messages by message id, but to be save it is here.
    Optional<Message> findById(long id);

    // Not sure if we would ever want or need to find all messages for some user (which would
    // include all messages to all users about any and all books), but to be save it is here.
    List<Message> findByReceiver(User receiver);
    List<Message> findBySender(User sender);

    //List<Message> findBySenderAndReceiverAndBook(User sender, User receiver, Book book)

    // Not sure if we would ever want or need to find all messages for one specific book,
    // from all users that have sent messages about that book title, but to be save it is here.
    //List<Message> findByBook(Book book);

    //Optional<Message> acceptMessage(Message message, User user, Book book);

}