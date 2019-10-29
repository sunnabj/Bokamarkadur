package is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    Optional<User> findById(long id);

    List<User> findAll();

    //boolean checkIfExists(String userName, String password);

    List<User> findByUsername(String username);

    //checkPassword(String username, String password)



}
