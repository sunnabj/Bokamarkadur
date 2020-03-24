
package is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Message;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // JPA repository handles the methods 'save' and 'delete',
    // and they are constructed there.

    //Store a new message.
    Message save(Message message);

    // Method for deleting messages.
    void delete(Message message);

    // Find all messages.
    List<Message> findAll();

    // Find message by message id.
    Optional<Message> findById(long id);

    // Find all messages by the receiver user.
    List<Message> findByReceiver(User receiver);

    // Find all messages by the sender user.
    List<Message> findBySender(User sender);

}