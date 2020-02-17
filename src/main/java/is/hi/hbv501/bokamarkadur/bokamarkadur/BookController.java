package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Subjects;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.AddBookResponse;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.DeleteBookResponse;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.GetAllBooksResponse;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.GetBookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class BookController {

    //private static String uploadedFolder = "/src/main/resources/static/";
    // The folder where images uploaded with added books are stored.
    private static String uploadedFolder = "/static/";
    private static String currentDirectory = System.getProperty("user.dir");
    // A date attribute is required so that the most recently added books can be displayed.
    private static Date date = new Date();

    private BookService bookService;
    private UserService userService;

    @Autowired
    public BookController(BookService bookService, UserService userService){
        this.bookService = bookService;
        this.userService = userService;
    }


    /*
    * Föll sem virka!
     */


    /*
     * Returns a page where you can see all books available on site, both for sale and requested.
     */
    @RequestMapping(value="/all-books")
    public ResponseEntity<GetAllBooksResponse> allBooks() {
        return new ResponseEntity<>(new GetAllBooksResponse(bookService.findAll()), HttpStatus.OK);
    }

    /*
     * Deletes a specific book
     */
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DeleteBookResponse> deleteBook(@PathVariable("id") long id) {
        //Tries to fetch a book with this id from the database - throws an exception
        // if it doesn't exist.
        if (!bookService.findById(id).isPresent()) {
            List<String> errors = new ArrayList<>();
            errors.add("No book with id: " + id + " exists");
            return new ResponseEntity<>(new DeleteBookResponse(null, errors), HttpStatus.NOT_FOUND);
        }
        bookService.findById(id).ifPresent(book -> bookService.delete(book));

        return new ResponseEntity<>(new DeleteBookResponse(), HttpStatus.OK);
    }

    /*
     * A form to insert information about a book a user wants to put up for sale.
     * Returns a page where the user is thanked for his contribution.
     */
    @RequestMapping(value ="/addbookforsale", method = RequestMethod.POST)
    public ResponseEntity<AddBookResponse> addBookForSale(@Valid @RequestBody Book book, BindingResult result, HttpSession session,
                                                          @RequestParam("file") MultipartFile file) {
        if(result.hasErrors()) {
            return new ResponseEntity<>(new AddBookResponse(null, result.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(currentDirectory + uploadedFolder + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        book.setImage(file.getOriginalFilename());
        book.setStatus("For sale");
        book.setDate(date);
        //Spurning með sessionUser og HttpSession?
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        User current = userService.findByUsername(sessionUser.getUsername());
        book.setUser(current);
        return new ResponseEntity<>(new AddBookResponse(bookService.save(book)), HttpStatus.CREATED);
    }

    /*
     * A form to insert information about a book a user wants to request.
     * Returns a page where the user is thanked for his contribution.
     */
    @RequestMapping(value ="/addrequestbook", method = RequestMethod.POST)
    public ResponseEntity<AddBookResponse> addRequestBook(@Valid @RequestBody Book book, BindingResult result, Model model, HttpSession session,
                                 @RequestParam("file") MultipartFile file) {
        if(result.hasErrors()) {
            return new ResponseEntity<>(new AddBookResponse(null, result.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(currentDirectory + uploadedFolder + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        book.setImage(file.getOriginalFilename());
        book.setStatus("Requested");
        //Spurning með sessionUser - er það notað?
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        User current = userService.findByUsername(sessionUser.getUsername());
        book.setUser(current);
        return new ResponseEntity<>(new AddBookResponse(bookService.save(book)), HttpStatus.CREATED);
    }


    /*
     * Returns a page with information about a particular book
     */
    @RequestMapping(value ="/viewbook/{id}", method = RequestMethod.GET)
    public ResponseEntity<GetBookResponse> viewBook(@PathVariable("id") long id) {

        if (!bookService.findById(id).isPresent()) {
            List<String> errors = new ArrayList<>();
            errors.add("No book with id " + id + " exists");
            return new ResponseEntity<>(new GetBookResponse(null, null, errors), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new GetBookResponse(bookService.findById(id)), HttpStatus.OK);
    }


    /*
     * A method that retrieves books by subject. It returns a list of books belonging to
     * a chosen subject.
     */
    @RequestMapping(value ="/viewsubjectbooks/{subjects}")
    public ResponseEntity<GetAllBooksResponse> viewsubjectbooks(@PathVariable("subjects") Subjects subject) {
        List<Book> subjectbooks = bookService.findBySubjects(subject);//.orElseThrow(()-> new IllegalArgumentException("Invalid subject"));
        // TODO: Gera eitthvað villu response
        return new ResponseEntity<>(new GetAllBooksResponse(subjectbooks), HttpStatus.OK);
    }


    /*
    * Föll sem má sleppa!
     */

    /*
     * Returns a page where the logged in user can see all books he has put on the site,
     * both for sale and requested.
     */
    /*
    @RequestMapping(value="/myBooks", method = RequestMethod.GET)
    public String myBooks(Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if ((User) session.getAttribute("LoggedInUser") == null) {
            return "please-log-in";
        }
        User current = userService.findByUsername(sessionUser.getUsername());
        model.addAttribute("books", bookService.findByUser(current));
        model.addAttribute("loggedIn", sessionUser);
        return "my-books";
    }

     */

    /*
     * Returns a page where the user can choose to either put up a book for sale or request a book.
     */
    /*
    @RequestMapping(value="/newbook", method = RequestMethod.GET)
    public String addBook(HttpSession session, Model model) {
        if ((User) session.getAttribute("LoggedInUser") == null) {
            return "please-log-in";
        }
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "add-book";
    }
    */

    /*
     * Returns a page with information about the developers.
     */
    /*
    @RequestMapping(value="/aboutus", method = RequestMethod.GET)
    public String aboutus(HttpSession session, Model model) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "about-us";
    }
    */

    /*
     * Returns a page where a user can put up a book for sale.
     */
    /*
    @RequestMapping(value="/addbookforsale", method = RequestMethod.GET)
    public String addBookForSaleForm(Book book,Model model, HttpSession session) {
        if ((User) session.getAttribute("LoggedInUser") == null) {
            return "please-log-in";
        }
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "sell-book";
    }

     */

    /*
     * Returns a page where a user can request a book.
     */
    /*
    @RequestMapping(value="/addrequestbook", method = RequestMethod.GET)
    public String addRequestBook(Book book, Model model, HttpSession session) {
        if ((User) session.getAttribute("LoggedInUser") == null) {
            return "please-log-in";
        }
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "request-book";
    }

     */

}
