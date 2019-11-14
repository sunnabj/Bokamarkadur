package is.hi.hbv501.bokamarkadur.bokamarkadur.Services;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Subjects;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BookService {

    /*
     * These functions represent the queries explained in the UserRepository,
     * and serve as a layer between the controller and the database.
     */
    Book save(Book book);
    void delete(Book book);
    List<Book> findAll();
    Optional<Book> findById(long id);
    List<Book> findByTitle(String title, String status);
    List<Book> findByAuthor(String author, String status);
    List<Book> findByStatus(String status);
    List<Book> findByUser(User user);
    List<Book> findBySubjects(Subjects subject);
}
