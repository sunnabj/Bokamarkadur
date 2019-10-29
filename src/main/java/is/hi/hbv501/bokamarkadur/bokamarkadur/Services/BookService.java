package is.hi.hbv501.bokamarkadur.bokamarkadur.Services;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Subjects;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book save(Book book);
    void delete(Book book);
    List<Book> findAll();
    Optional<Book> findById(long id);
    List<Book> findByTitle(String title);
    //Prófa
    List<Book> findByUser(User user);
    // Nýtt
    List<Book> findBySubjects(Subjects subject);

    //Virkaði ekki
    //List<Subjects> findAvailableSubjects(List<Book> books);


    //Repository er með föll sem eru með útfærslu í höndum JPA. Kannski viljum við gera eitthvað aðeins
    // öðruvísi, það er stjórna meiru sjálf.
    // Service er þá millilag milli repository og gagnagrunnsins. Service getur unnið með gögnin aukalega.
    // Filterað þau öðruvísi, raðað í ákveðna röð eða eitthvað.
    // Getum bætt við aukaföllum hér sem geta gert meira en repository getur gert.

}
