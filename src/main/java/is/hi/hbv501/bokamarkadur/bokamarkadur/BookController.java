package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Subjects;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Controller
public class BookController {

    private static String UPLOADED_FOLDER = "/src/main/resources/static/";

    private BookService bookService;
    private UserService userService;

    @Autowired
    public BookController(BookService bookService, UserService userService){
        this.bookService = bookService;
        this.userService = userService;
    }


    /*
     * Returns a page where you can see all books available on site, both for sale and requested.
     */
    @RequestMapping(value="/all-books", method = RequestMethod.GET)
    public String allBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "all-books";
    }

    /*
     * Deletes a specific book
     */
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") long id, Model model) {
        //Tries to fetch a book with this id from the database - throws an exception
        // if it doesn't exist.
        Book book = bookService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid book ID"));
        //Deletes this book and returns the list of books again.
        bookService.delete(book);
        model.addAttribute("books", bookService.findAll());
        return "Home";
    }

    /*
     * Returns a page with information about a particular book
     */
    @RequestMapping(value ="/viewbook/{id}", method = RequestMethod.GET)
    public String viewBook(@PathVariable("id") long id, Model model) {
        Book book = bookService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid book ID"));
        model.addAttribute("book", book);
        return "book-info";
    }

    /*
     * Returns a page where the user can choose to either put up a book for sale or request a book.
     */
    @RequestMapping(value="/newbook", method = RequestMethod.GET)
    public String addBook(HttpSession session) {
        if ((User) session.getAttribute("LoggedInUser") == null) {
            return "please-log-in";
        }
        return "add-book";
    }

    /*
     * A form to insert information about a book a user wants to put up for sale.
     * Returns a page where the user is thanked for his contribution.
     */
    @RequestMapping(value ="/addbookforsale", method = RequestMethod.POST)
    public String addBookForSale(@Valid Book book, BindingResult result, Model model, HttpSession session,
                                 @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "sell-book";
        }
        String currentDirectory = System.getProperty("user.dir");

        try {

            System.out.println("The current working directory is " + currentDirectory);
            String current = new java.io.File( "." ).getCanonicalPath();
            System.out.println("The current working directory is " + current);
        } catch (IOException iex) {}

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        } try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(currentDirectory + UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("UPLOADED_FOLDLER: " + UPLOADED_FOLDER + ", file: " + file.getOriginalFilename());
        book.setImage(file.getOriginalFilename());
        book.setStatus("For sale");
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        User current = userService.findByUsername(sessionUser.getUsername());
        book.setUser(current);
        bookService.save(book);
        model.addAttribute("books", bookService.findAll());
        return "Success";
    }

    /*
     * Returns a page where a user can put up a book for sale.
     */
    @RequestMapping(value="/addbookforsale", method = RequestMethod.GET)
    public String addBookForSaleForm(Book book) {
        return "sell-book";
    }

    /*
     * A form to insert information about a book a user wants to request.
     * Returns a page where the user is thanked for his contribution.
     */
    @RequestMapping(value ="/addrequestbook", method = RequestMethod.POST)
    public String addRequestBook(@Valid Book book, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()) {
            return "request-book"; //Inni í gæsalöppum: HTML skrá.
        }
        book.setStatus("Requested");
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        User current = userService.findByUsername(sessionUser.getUsername());
        book.setUser(current);
        bookService.save(book);
        model.addAttribute("books", bookService.findAll());
        return "Success";
    }

    /*
     * Returns a page where a user can request a book.
     */
    @RequestMapping(value="/addrequestbook", method = RequestMethod.GET)
    public String addRequestBook(Book book) {
        return "request-book";
    }

    /*
     * A method that retrieves books by subject. It returns a list of books belonging to
     * a chosen subject.
     */
    @RequestMapping(value ="/viewsubjectbooks/{subjects}", method = RequestMethod.GET)
    public String viewsubjectbooks(@PathVariable("subjects") Subjects subject, Model model) {
        List<Book> subjectbooks = bookService.findBySubjects(subject);//.orElseThrow(()-> new IllegalArgumentException("Invalid subject"));
        model.addAttribute("Books", subjectbooks);
        return "subject-results";
    }
}
