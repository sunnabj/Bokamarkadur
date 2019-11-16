package is.hi.hbv501.bokamarkadur.bokamarkadur.Services;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Subjects;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookService {

    /*
     * These functions represent the queries explained in the BookRepository,
     * and serve as a layer between the controller and the database.
     */
    Book save(Book book);
    void delete(Book book);
    List<Book> findAll();
    Optional<Book> findById(long id);
    List<Book> findByAuthorOrTitle(String statusSearch, String authorSearch, String titleSearch);
    List<Book> findByUser(User user);
    List<Book> findBySubjects(Subjects subject);
    List<Book> findNewestBooks();
}
