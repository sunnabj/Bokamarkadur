package is.hi.hbv501.bokamarkadur.bokamarkadur.Services.Implementations;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Subjects;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories.BookRepository;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class BookServiceImplementation implements BookService {

    /*
     * Implements the functions from BookService.
     */

    BookRepository repository;

    /*
     * Status: Declares whether a book is for sale or requested
     * Author: Search string
     * Title: Search string
     * Looks through all book titles and authors in the DB and returns all books where the search string matches
     * a part of the title or author name.
     */
    static Specification<Book> containsTitleOrAuthor(String status, String author, String title) {
        return (book, cq, cb) -> {
            return cb.or(cb.and(cb.equal(book.get("status"), status),
                    cb.like(cb.lower(book.get("title")), "%" + author.toLowerCase() + "%")),
                    (cb.and(cb.equal(book.get("status"), status),
                    cb.or(cb.like(cb.lower(book.get("author")), "%" + title.toLowerCase() + "%")))
                    )
            );
        };
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
    public List<Book> findByAuthorOrTitle(String status, String author, String title) {
        return repository.findAll(containsTitleOrAuthor(status, author, title));
    }

    @Override
    public List<Book> findNewestBooks() {
        return repository.findNewestBooks();
    }

    @Override
    public List<Book> findByUser(User user) { return repository.findByUser(user); };

    @Override
    public List<Book> findBySubjects(Subjects subject) { return repository.findBySubjects(subject); }

}
