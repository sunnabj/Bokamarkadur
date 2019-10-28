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

    BookRepository repository;

    static Specification<Book> titleContains(String title) {
        return (book, cq, cb) -> cb.like(book.get("title"), "%" + title + "%");
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
    public List<Book> findByTitle(String title) {
        return repository.findAll(titleContains(title));
    }

    //Prófa
    @Override
    public List<Book> findByUser(User user) { return repository.findByUser(user); };

    // Nýtt
    @Override
    public List<Book> findBySubjects(Subjects subject) { return repository.findBySubjects(subject); }

    // Virkaði ekki
    /*
    @Override
    public List<Subjects> findAvailableSubjects(List<Book> books) {
        return repository.findAvailableSubjects(books);
    }
*/

}
