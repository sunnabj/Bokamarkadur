package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Subjects;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;

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
     * Returns a page where you can see all books available on site, both for sale and requested.
     * Search parameter is possible - then books that fit the parameter and status are shown.
     * T.d. /all-books?search=Data&status=For+sale
     */
    @RequestMapping(value="/all-books", method = RequestMethod.GET)
    public ResponseEntity<GetAllBooksResponse> allBooks(
            @RequestParam(value = "search" ,required = false) String search,
            @RequestParam(value = "status", required = false) String status
    ) {
        if (search != null) {
            List<Book> books = bookService.findByAuthorOrTitle(status, search, search);

            return new ResponseEntity<>(new GetAllBooksResponse(books), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new GetAllBooksResponse(bookService.findAll()), HttpStatus.OK);
        }

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
    public ResponseEntity<AddBookResponse> addBookForSale(@Valid @ModelAttribute Book book, BindingResult result,
                                                          Authentication authentication,
                                                          @RequestParam("file") MultipartFile file) {
        if(result.hasErrors()) {
            return new ResponseEntity<>(new AddBookResponse(null, result.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }

        try {
            // Get the file and save it somewhere
            if (file.getSize() > 0) {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(currentDirectory + uploadedFolder + file.getOriginalFilename());
                Files.write(path, bytes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        book.setImage(file.getOriginalFilename());


        book.setStatus("For sale");
        book.setDate(date);

        //User sessionUser = (User) session.getAttribute("LoggedInUser");

        User loggedinUser = userService.findByUsername(authentication.getName());

        /*
        if (sessionUser == null) {
            List<String> errors = new ArrayList<>();
            errors.add("You must be logged in to visit this page");
            return new ResponseEntity<>(new AddBookResponse(null, null, errors ), HttpStatus.UNAUTHORIZED);
        }
         */

        if (authentication == null || loggedinUser == null) {
            List<String> errors = new ArrayList<>();
            errors.add("You must be logged in to visit this page");
            return new ResponseEntity<>(new AddBookResponse(null, null, errors ), HttpStatus.UNAUTHORIZED);
        }



        //User current = userService.findByUsername(sessionUser.getUsername());
        //book.setUser(current);

        book.setUser(loggedinUser);

        return new ResponseEntity<>(new AddBookResponse(bookService.save(book)), HttpStatus.CREATED);
    }

    /*
     * A form to insert information about a book a user wants to request.
     * Returns a page where the user is thanked for his contribution.
     */
    @RequestMapping(value ="/addrequestbook", method = RequestMethod.POST)
    public ResponseEntity<AddBookResponse> addRequestBook(@Valid @ModelAttribute Book book, BindingResult result,
                                                          Authentication authentication,
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
        User loggedinUser = userService.findByUsername(authentication.getName());


        if (authentication != null) {
            if (loggedinUser != null) {
                book.setUser(loggedinUser);
                return new ResponseEntity<>(new AddBookResponse(bookService.save(book)), HttpStatus.CREATED);
            }

        }

        List<String> errors = new ArrayList<>();
        errors.add("You must be logged in to visit this page");
        return new ResponseEntity<>(new AddBookResponse(null, null, errors ), HttpStatus.UNAUTHORIZED);

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
    @RequestMapping(value ="/viewsubjectbooks/{subjects}", method = RequestMethod.GET)
    public ResponseEntity<GetAllBooksResponse> viewsubjectbooks(@PathVariable("subjects") String subject) {
        List<Book> subjectbooks = bookService.findBySubject(subject);//.orElseThrow(()-> new IllegalArgumentException("Invalid subject"));
        // TODO: Gera eitthvað villu response
        return new ResponseEntity<>(new GetAllBooksResponse(subjectbooks), HttpStatus.OK);
    }


    /*
     * Returns a page where the logged in user can see all books he has put on the site,
     * both for sale and requested.
     */
    //TODO: VilluReponse þegar notandi er ekki loggaður inn
    @RequestMapping(value="/myBooks", method = RequestMethod.GET)
    public ResponseEntity<GetAllBooksResponse> myBooks(Authentication authentication) {
        User loggedinUser = userService.findByUsername(authentication.getName());
        User current = userService.findByUsername(loggedinUser.getUsername());
        return new ResponseEntity<>(new GetAllBooksResponse(bookService.findByUser(current)), HttpStatus.OK);

    }

    @RequestMapping(value="/getUsersBooks", method = RequestMethod.GET)
    public ResponseEntity<GetAllBooksResponse> myBooks(String username) {
        User loggedinUser = userService.findByUsername(username);
        User current = userService.findByUsername(loggedinUser.getUsername());
        return new ResponseEntity<>(new GetAllBooksResponse(bookService.findByUser(current)), HttpStatus.OK);

    }

}
