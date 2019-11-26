package is.hi.hbv501.bokamarkadur.bokamarkadur.Controllers;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Message;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Subjects;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.MessageService;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;


@Controller
public class BookController {

    private static String uploadedFolder = "/src/main/resources/static/";
    private static String currentDirectory = System.getProperty("user.dir");

    private static Date date = new Date();

    private BookService bookService;
    private UserService userService;
    private MessageService messageService;

    @Autowired
    public BookController(BookService bookService, UserService userService, MessageService messageService){
        this.bookService = bookService;
        this.userService = userService;
        this.messageService = messageService;
    }


    /*
     * Returns a page where you can see all books available on site, both for sale and requested.
     */
    @RequestMapping(value="/all-books", method = RequestMethod.GET)
    public String allBooks(Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
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
        return "redirect:/";
    }

    /*
     * Returns a page with information about a particular book
     */
    @RequestMapping(value ="/viewbook/{id}", method = RequestMethod.GET)
    public String viewBook(@PathVariable("id") long id, Model model, HttpSession session) {
        Book book = bookService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid book ID"));
        model.addAttribute("book", book);
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "book-info";
    }

    /*
     * Returns a page where the user can choose to either put up a book for sale or request a book.
     */
    @RequestMapping(value="/newbook", method = RequestMethod.GET)
    public String addBook(HttpSession session, Model model) {
        if ((User) session.getAttribute("LoggedInUser") == null) {
            return "please-log-in";
        }
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "add-book";
    }

    /*
     * Returns a page of about development.
     */
    @RequestMapping(value="/aboutus", method = RequestMethod.GET)
    public String aboutus(HttpSession session, Model model) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "about-us";
    }


    /*
     * A form to insert information about a book a user wants to put up for sale.
     * Returns a page where the user is thanked for his contribution.
     */
    @RequestMapping(value ="/addbookforsale", method = RequestMethod.POST)
    public String addBookForSale(@Valid Book book, BindingResult result, Model model, HttpSession session,
                                 @RequestParam("file") MultipartFile file) {
        if(result.hasErrors()) {
            return "sell-book";
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
        System.out.println("date: " + date);
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        User current = userService.findByUsername(sessionUser.getUsername());
        book.setUser(current);
        bookService.save(book);
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("loggedIn", sessionUser);
        return "Success";
    }

    /*
     * Returns a page where a user can put up a book for sale.
     */
    @RequestMapping(value="/addbookforsale", method = RequestMethod.GET)
    public String addBookForSaleForm(Book book,Model model, HttpSession session) {
        if ((User) session.getAttribute("LoggedInUser") == null) {
            return "please-log-in";
        }
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "sell-book";
    }

    /*
     * A form to insert information about a book a user wants to request.
     * Returns a page where the user is thanked for his contribution.
     */
    @RequestMapping(value ="/addrequestbook", method = RequestMethod.POST)
    public String addRequestBook(@Valid Book book, BindingResult result, Model model, HttpSession session,
                                 @RequestParam("file") MultipartFile file) {
        if(result.hasErrors()) {
            return "request-book"; //Inni í gæsalöppum: HTML skrá.
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
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        User current = userService.findByUsername(sessionUser.getUsername());
        book.setUser(current);
        bookService.save(book);
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("loggedIn", sessionUser);
        return "Success";
    }

    /*
     * Returns a page where a user can request a book.
     */
    @RequestMapping(value="/addrequestbook", method = RequestMethod.GET)
    public String addRequestBook(Book book, Model model, HttpSession session) {
        if ((User) session.getAttribute("LoggedInUser") == null) {
            return "please-log-in";
        }
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "request-book";
    }

    /*
     * A method that retrieves books by subject. It returns a list of books belonging to
     * a chosen subject.
     */
    @RequestMapping(value ="/viewsubjectbooks/{subjects}", method = RequestMethod.GET)
    public String viewsubjectbooks(@PathVariable("subjects") Subjects subject, Model model, HttpSession session) {
        List<Book> subjectbooks = bookService.findBySubjects(subject);//.orElseThrow(()-> new IllegalArgumentException("Invalid subject"));
        model.addAttribute("Books", subjectbooks);
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "subject-results";
    }


    /*
     * Returns a page where the logged in user can see all books he has put on the site,
     * both for sale and requested.
     */
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

}
