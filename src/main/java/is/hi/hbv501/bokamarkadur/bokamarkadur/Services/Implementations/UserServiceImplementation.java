package is.hi.hbv501.bokamarkadur.bokamarkadur.Services.Implementations;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories.UserRepository;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    /*
     * Implements the functions from UserService.
     */
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

    /*Handles the login of a user.
     * Checks if the user trying to log in exists, that is the username he is typing,
     * and if so, checks if the typed-in password matches this username.
     * If everything is ok, this user is returned and login will be successful.
     */
    @Override
    public User login(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User exists = findByUsername(user.username);
        if (exists != null) {
            if (exists.password.equals(user.password) ||  passwordEncoder.matches(user.password, exists.password)) {
                return user;
            }
        }
        return null;
    }

}
