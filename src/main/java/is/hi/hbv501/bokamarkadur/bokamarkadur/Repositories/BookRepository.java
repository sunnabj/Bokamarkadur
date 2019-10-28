package is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Subjects;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

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

    //Prófa
    List<Book> findByUser(User user);

    //Nýtt
    List<Book> findBySubjects(Subjects subject);

    //Virkaði ekki
    //List<Subjects> findAvailableSubjects(List<Book> books);
}
