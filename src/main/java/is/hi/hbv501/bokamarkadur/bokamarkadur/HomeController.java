package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Subjects;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.GetAllBooksResponse;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.GetSubjectResponse;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.GetSubjectsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    private BookService bookService;

    @Autowired
    public HomeController(BookService bookService){
        this.bookService = bookService;
    }

    /*
     * Skilar 10 nýjustu bókunum.
     */
    @RequestMapping(value = "/newest-books", method = RequestMethod.GET)
    public ResponseEntity<GetAllBooksResponse> newestBooks() {
        return new ResponseEntity<>(new GetAllBooksResponse(bookService.findNewestBooks()), HttpStatus.OK);
    }


    /*
    * Fall til að sýna available subjects
    */
    @RequestMapping(value = "/available-subjects", method = RequestMethod.GET)
    public ResponseEntity<GetSubjectResponse> availableSubjects() {
        List<Book> availableBooks = bookService.findAll();
        ArrayList<String> bookSubjects = new ArrayList<String>();
        // Go through all books and insert subjects into the bookSubjects array.
        for (int i = 0; i < availableBooks.size(); i++) {
            if (!bookSubjects.contains(availableBooks.get(i).getSubject())) {
                bookSubjects.add(availableBooks.get(i).getSubject());
            }
        }

        return new ResponseEntity<>(new GetSubjectResponse(bookSubjects), HttpStatus.OK);
    }

}
