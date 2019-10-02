package is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRespository extends JpaRepository<Movie, Long> {

    //Fallið fyrir gagnagrunninn! Setur nýja mynd inn.
    Movie save(Movie movie);
    //Þessi eyðir úr gagnagrunninum.
    // Þetta er allt gert í gegnum JPA repository. save og delete búin til þar.
    void delete(Movie movie);

    //Hægt að gera @Query("SELECT o.s.frv. - okkar eigin SQL kóði")

    //Leið til að sækja gögnin okkar - skilar öllum myndunum í gagnagrunninum.
    List<Movie> findAll();

    //Skilar lista af öllum bíómyndum sem uppfylla þennan titil.
    List<Movie> findByTitle(String title);

    Optional<Movie> findById(long id);
}
