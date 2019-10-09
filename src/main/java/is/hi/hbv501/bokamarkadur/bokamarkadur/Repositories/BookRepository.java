package is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    //Fallið fyrir gagnagrunninn! Setur nýja bók inn.
    Book save(Book book);
    //Þessi eyðir úr gagnagrunninum.
    // Þetta er allt gert í gegnum JPA repository. save og delete búin til þar.
    void delete(Book book);

    //Hægt að gera @Query("SELECT o.s.frv. - okkar eigin SQL kóði")

    //Leið til að sækja gögnin okkar - skilar öllum bókum í gagnagrunninum.
    List<Book> findAll();

    //Skilar lista af öllum bókum sem uppfylla þennan titil.
    List<Book> findByTitle(String title);

    Optional<Book> findById(long id);
}
