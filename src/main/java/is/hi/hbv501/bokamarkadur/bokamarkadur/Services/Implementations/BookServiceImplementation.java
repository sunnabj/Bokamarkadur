package is.hi.hbv501.bokamarkadur.bokamarkadur.Services.Implementations;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories.BookRespository;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {

    BookRespository repository;

    @Autowired
    public BookServiceImplementation(BookRespository movieRepository){this.repository = movieRepository;}

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
        return repository.findByTitle(title);
    }
}
