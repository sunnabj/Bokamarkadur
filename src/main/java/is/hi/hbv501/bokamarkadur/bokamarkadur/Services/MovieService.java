package is.hi.hbv501.bokamarkadur.bokamarkadur.Services;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Movie save(Movie movie);
    void delete(Movie movie);
    List<Movie> findAll();
    Optional<Movie> findById(long id);
    List<Movie> findByTitle(String title);

    //Repository er með föll sem eru með útfærslu í höndum JPA. Kannski viljum við gera eitthvað aðeins
    // öðruvísi, það er stjórna meiru sjálf.
    // Service er þá millilag milli repository og gagnagrunnsins. Service getur unnið með gögnin aukalega.
    // Filterað þau öðruvísi, raðað í ákveðna röð eða eitthvað.
    // Getum bætt við aukaföllum hér sem geta gert meira en repository getur gert.

}
