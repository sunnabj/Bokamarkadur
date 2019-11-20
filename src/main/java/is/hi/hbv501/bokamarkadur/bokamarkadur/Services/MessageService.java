package is.hi.hbv501.bokamarkadur.bokamarkadur.Services;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Message;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    /*
     * These functions represent the queries explained in the UserRepository,
     * and serve as a layer between the controller and the database.
     */

    Message save(Message message);
    Optional<Message> findById(long id);
    List<Message> findAll();
    Message findByUser(String user);
    List<Message> findByBook(Book book);

    // acceptMessage(Message message, User user, Book book);
    @Override
    Message acceptMessage(Message message, User user, Book book);
}
