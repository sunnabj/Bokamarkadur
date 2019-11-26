package is.hi.hbv501.bokamarkadur.bokamarkadur.Services;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /*
     * These functions represent the queries explained in the UserRepository,
     * and serve as a layer between the controller and the database.
     */

    User save(User user);
    Optional<User> findById(long id);
    List<User> findAll();
    User findByUsername(String username);

    /*
     * This function (explained in detail in UserServiceImplementation)
     * is part of the system's business logic and is therefore
     * implemented in a service class rather than in a controller class.
     */
    User login(User user);
}
