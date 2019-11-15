package is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Subjects;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    //Inserts a new book into the database
    Book save(Book book);
    //Deletes a book from the database
    void delete(Book book);

    //Retrieves all the books in the database
    List<Book> findAll();

    // These functions query books by some particular condition.

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    Optional<Book> findById(long id);

    List<Book> findByUser(User user);

    List<Book> findBySubjects(Subjects subject);

    @Query(value = "SELECT * FROM book ORDER BY date DESC LIMIT 10;", nativeQuery = true)
    List<Book> findNewestBooks();
}
