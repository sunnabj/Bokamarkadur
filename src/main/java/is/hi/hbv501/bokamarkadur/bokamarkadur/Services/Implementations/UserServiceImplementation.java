package is.hi.hbv501.bokamarkadur.bokamarkadur.Services.Implementations;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories.UserRepository;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    UserRepository repository;

    @Autowired
    public UserServiceImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findByUsername(String username) { return repository.findByUsername(username); }

    //Ef ekki til JPA skipun fyrir ákveðna query þá er hægt að skrifa sína eigin.
    // Nota fyrir subjects á forsíðu?

    // Fall úr stoðtíma - opið fyrir breytingum (Hieu!)
    @Override
    public User login(User user) {
        User exists = findByUsername(user.username);
        if (exists != null) {
            if (exists.password.equals(user.password)) {
                return user; //má logga sig inn - username og password rétt
            }
        }
        return null;
    }

}
