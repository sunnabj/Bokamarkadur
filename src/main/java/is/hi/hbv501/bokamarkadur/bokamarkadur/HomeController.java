package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Subjects;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.GetAllBooksResponse;
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
     * Skilar 10 nýjustu bókunum!
     */
    @RequestMapping(value = "/newest-books", method = RequestMethod.GET)
    public ResponseEntity<GetAllBooksResponse> newestBooks() {
        List<Book> availableBooks = bookService.findAll();
        return new ResponseEntity<>(new GetAllBooksResponse(bookService.findNewestBooks()), HttpStatus.OK);
    }

    /*
    * Fall til að sýna available subejcts.
    * Þarf að skoða mun betur!
    *
    @RequestMapping(value = "/available-subjects", method = RequestMethod.GET)
    public ResponseEntity<GetAllBooksResponse> availableSubjects() {
        List<Book> availableBooks = bookService.findAll();
        ArrayList<Subjects> bookSubjects = new ArrayList<Subjects>();
        ArrayList<Book> subjectBooks = new ArrayList<Book>();
        // Go through all books and insert subjects into the bookSubjects array.
        for (int i = 0; i < availableBooks.size(); i++) {
            if (!bookSubjects.contains(availableBooks.get(i).getSubjects())) {
                bookSubjects.add(availableBooks.get(i).getSubjects());
            }
        }
        // Go through the subjects and add one book to the subjectBooks array
        // for each subject
        for (int j = 0; j < bookSubjects.size(); j++) {
            for (int i = 0; i < availableBooks.size(); i++) {
                if (availableBooks.get(i).getSubjects() == bookSubjects.get(j)) {
                    subjectBooks.add(availableBooks.get(i));
                    break;
                }
            }
        }

        return new ResponseEntity<>(new GetAllBooksResponse(subjectBooks, HttpStatus.OK);
    }

     */



    /*
     * Gamla fallið!
     *
     * Returns the home (front) page.
     * Returns the 10 most recently added books to the site.
     */
    /*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Home(Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        model.addAttribute("books", bookService.findNewestBooks());
        List<Book> availableBooks = bookService.findAll();
        ArrayList<Subjects> bookSubjects = new ArrayList<Subjects>();
        ArrayList<Book> subjectBooks = new ArrayList<Book>();
        // Go through all books and insert subjects into the bookSubjects array.
        for (int i = 0; i < availableBooks.size(); i++) {
            if (!bookSubjects.contains(availableBooks.get(i).getSubjects())) {
                bookSubjects.add(availableBooks.get(i).getSubjects());
            }
        }
        // Go through the subjects and add one book to the subjectBooks array
        // for each subject
        for (int j = 0; j < bookSubjects.size(); j++) {
            for (int i = 0; i < availableBooks.size(); i++) {
                if (availableBooks.get(i).getSubjects() == bookSubjects.get(j)) {
                    subjectBooks.add(availableBooks.get(i));
                    break;
                }
            }
        }

        model.addAttribute("subjectBooks", subjectBooks);
        return "Home";
    }

     */

}
