package is.hi.hbv501.bokamarkadur.bokamarkadur.Services.Implementations;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Message;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories.MessageRepository;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImplementation implements MessageService {

    /*
     * Implements the functions from MessageService.
     */
    MessageRepository repository;

    @Autowired
    public MessageServiceImplementation(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Message save(Message message) {
        return repository.save(message);
    }

    @Override
    public Optional<Message> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Message> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Message> findByReceiver(User receiver) { return repository.findByReceiver(receiver); }

    @Override
    public List<Message> findBySender(User sender) { return repository.findBySender(sender); }

}
