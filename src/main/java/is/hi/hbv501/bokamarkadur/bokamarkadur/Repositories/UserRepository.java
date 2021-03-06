package is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /*
     * These functions interact with the User table in the database.
     * JPA creates queries that return certain users, based on
     * conditions.
     */
    User save(User user);

    Optional<User> findById(long id);

    List<User> findAll();

    User findByUsername(String username);

}
