package is.hi.hbv501.bokamarkadur.bokamarkadur.Services.Implementations;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Subjects;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories.BookRepository;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {

    /*
     * Implements the functions from BookService.
     */

    BookRepository repository;

    /*
     * Title: Search string
     * Status: Declares whether a book is for sale or requested
     * Looks through all book titles in the DB and returns all books where the search string matches
     * a part of the title.
     */
    static Specification<Book> containsTitleAndStatus(String title, String status) {
        return (book, cq, cb) -> {
            return cb.and(
                    cb.like(cb.lower(book.get("title")), "%" + title.toLowerCase() + "%"),
                    cb.equal(book.get("status"), status)
            );
        };
    }

    /*
     * Author: Search string
     * Status: Declares whether a book is for sale or requested
     * Looks through all book authors in the DB and returns all books where the search string matches
     * a part of the author name.
     */
    static Specification<Book> hasAuthorAndStatus(String author, String status) {
        return (book, cq, cb) -> {
            return cb.and(
                    cb.like(cb.lower(book.get("author")), "%" + author.toLowerCase() + "%"),
                    cb.equal(book.get("status"), status)
            );
        };
    }

    /*
     * Currently not being used.
     * Retrieves a status of a book, that is whether it is for sale or requested.
     */
    static Specification<Book> getStatus(String status) {
        return (book, cq, cb) -> cb.equal(book.get("status"), status);
    }

    @Autowired
    public BookServiceImplementation(BookRepository bookRepository){this.repository = bookRepository;}

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }

    @Override
    public void delete(Book book) {
        repository.delete(book);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Book> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Book> findByTitle(String title, String status) {
        return repository.findAll(containsTitleAndStatus(title, status));
    }

    @Override
    public List<Book> findByAuthor(String author, String status) {
        return repository.findAll(hasAuthorAndStatus(author, status));
    }

    // Currently not being used.
    @Override
    public List<Book> findByStatus(String status) {
        return repository.findAll(getStatus(status));
    }

    @Override
    public List<Book> findByUser(User user) { return repository.findByUser(user); };

    @Override
    public List<Book> findBySubjects(Subjects subject) { return repository.findBySubjects(subject); }

    // Something that didn't work.
    /*
    @Override
    public List<Subjects> findAvailableSubjects(List<Book> books) {
        return repository.findAvailableSubjects(books);
    }
*/
    /*
    @Override
    List<Subjects> findAvailableSubjects(List<Book> books) {
        SELECT subjects FROM Book;
    }
    */

}
